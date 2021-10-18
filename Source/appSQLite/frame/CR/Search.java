package frame.CR;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import bean.CRUD.Read;

import javax.swing.JTable;

public class Search extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private Connection conn = null;
	private DefaultTableModel mainTable = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Search(Connection conn, DefaultTableModel table, int x, int y) {
		this();
		this.conn = conn;
		this.mainTable = table;
		setBounds(x, y, getBounds().width, getBounds().height);
	}
	public Search() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 719, 159);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		DefaultTableModel dmt = new DefaultTableModel();
		String[] columnName = { "ID", "Name", "CountryCode", "District", "Population" };
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
		
		
		JButton btnNewButton = new JButton("搜尋");
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.isEditing())
					table.getCellEditor().stopCellEditing(); // 結束目前的編輯
				if (conn != null) {
					ResultSet rs = null;
					try {
						ArrayList<String> name = new ArrayList<String>();
						ArrayList<String> value = new ArrayList<String>();

						for (int i = 0; i < table.getColumnCount(); i++) {
							Object tmp;
							if ((tmp = table.getValueAt(0, i)) != null) {
								name.add(table.getColumnName(i));
								value.add(String.valueOf(tmp));
							}
						}
						rs = new Read(conn, name.toArray(), value.toArray()).getRS();
						if(rs != null) {
							reStart(mainTable, rs);
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "查無符合資料");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(contentPane, "查詢失敗");
					} finally {
						if (rs != null) {
							try {
								rs.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						// 清空內容
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
	public void reStart(DefaultTableModel table, ResultSet search) {
		table.setRowCount(0);
		table.setColumnCount(0);
		Statement sm = null;
		ResultSet rs = null;
		// 載入資料
		try {
			sm = conn.createStatement();
			rs = sm.executeQuery("SELECT * FROM city");
			// 取得欄位名稱
			ResultSetMetaData rsmd = rs.getMetaData();
			int tableColumnLength = rsmd.getColumnCount();
			for (int i = 1; i <= tableColumnLength; i++) {
				table.addColumn(rsmd.getColumnName(i));
			}
			// 取得欄位內容
			while (search.next()){
				for (int i = 0; i < tableColumnLength; i++)
					inputTableUseTmp.add(search.getString(i + 1));
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
				try {
					search.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
