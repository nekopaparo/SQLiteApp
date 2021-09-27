package frame.CR;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import bean.CRUD.Create;

import java.awt.Color;
import java.awt.Component;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

import java.awt.Font;

public class Created extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection conn = null;
	private DefaultTableModel mainTable = null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Created frame = new Created();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Created(Connection conn, DefaultTableModel table, int x, int y) {
		this();
		this.conn = conn;
		this.mainTable = table;
		setBounds(x, y, getBounds().width, getBounds().height);
		
	}
	public Created() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 722, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultTableModel dmt = new DefaultTableModel();
		String[] columnName = {"Name", "CountryCode", "District", "Population" };
		for (String i : columnName) {
			dmt.addColumn(i);
		}
		dmt.setRowCount(1);
		JTable table = new JTable(dmt) {
			private static final long serialVersionUID = 1L;
			// https://stackoverflow.com/questions/40705616/background-color-flickers-using-jcheckbox-in-jtable
			// https://stackoverflow.com/questions/31585718/change-background-color-of-jtable-whole-row-based-on-column-value
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				c.setBackground(Color.WHITE);
				// 被選擇時的顏色
				if (column == this.getSelectedColumn())
					c.setBackground(new Color(123, 213, 111));
				((JComponent) c).setBorder(null);
				return c;
			}
		};
		table.setCellSelectionEnabled(false);
		table.setRowHeight(30);
		table.setFont(new Font("新細明體", Font.PLAIN, 20));
		JScrollPane Jscrollpane = new JScrollPane(table);
		Jscrollpane.setBounds(10, 10, 686, 53);
		contentPane.add(Jscrollpane);

		JButton btnNewButton = new JButton("新增");
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.isEditing())
					table.getCellEditor().stopCellEditing(); // 結束目前的編輯
				int ID = 1;
				if (conn != null) {
					Statement sm = null;
					ResultSet rs = null;
					try {
						sm = conn.createStatement();
						rs = sm.executeQuery("SELECT ID FROM city ORDER BY ID ASC;");
						// 建立ID
						while (rs.next()) {
							if (ID == rs.getInt(1)) {
								ID++;
							} else {
								break;
							}
						}
						ArrayList<String> name = new ArrayList<String>();
						ArrayList<String> value = new ArrayList<String>();
						name.add("ID");
						value.add(String.valueOf(ID));

						for (int i = 0; i < table.getColumnCount(); i++) {
							Object tmp;
							if ((tmp = table.getValueAt(0, i)) != null) {
								name.add(table.getColumnName(i));
								value.add(String.valueOf(tmp));
							}
						}
						if(dataUpdate("新增")) {
							new Create(conn, name.toArray(), value.toArray());
							JOptionPane.showMessageDialog(contentPane, "新增成功");
							reStart(mainTable);
							// 清空表單
							dmt.setRowCount(0);
							dmt.setRowCount(1);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(contentPane, "新增失敗");
					} finally {
						if (sm != null) {
							try {
								rs.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							try {
								sm.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						// 清除選擇
						table.clearSelection();
					}
				}
			}
		});
		btnNewButton.setBounds(525, 83, 171, 28);
		contentPane.add(btnNewButton);
	}

	// 主頁面data更新
	ArrayList<String> inputTableUseTmp = new ArrayList<>();
	public void reStart(DefaultTableModel table) {
		table.setRowCount(0);
		table.setColumnCount(0);
		Statement sm = null;
		ResultSet rs = null;
		// 載入資料
		try {
			System.out.println();
			sm = conn.createStatement();
			rs = sm.executeQuery("SELECT * FROM city ORDER BY ID ASC");
			// 取得欄位名稱
			ResultSetMetaData rsmd = rs.getMetaData();
			int tableColumnLength = rsmd.getColumnCount();
			for (int i = 1; i <= tableColumnLength; i++) {
				table.addColumn(rsmd.getColumnName(i));
			}
			// 取得欄位內容
			while (rs.next()) {
				for (int i = 0; i < tableColumnLength; i++)
					inputTableUseTmp.add(rs.getString(i + 1));
				// 暫存內容輸入DefaultTableModel
				table.addRow(inputTableUseTmp.toArray());
				// 清空暫存
				inputTableUseTmp.clear();
			}
		} catch (SQLException e) {
			System.out.print("SQL描述錯誤");
			// e.printStackTrace();
		} finally {
			if (sm != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					sm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 確認視窗
	public boolean dataUpdate(String showDo) {
		Object[] options = { "確定", "取消" };
		JOptionPane confirm = new JOptionPane("確定" + showDo + "?", JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION, null, options, options[1]);
		JDialog dialog = confirm.createDialog(this, "警告");
		dialog.setVisible(true);
		Object selectedValue = confirm.getValue();
		if (selectedValue == options[0]) {
			return true;
		} else {
			return false;
		}
	}
}
