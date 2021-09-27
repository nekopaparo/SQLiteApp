package frame.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import bean.CRUD.Delete;
import bean.CRUD.Update;
import bean.SQL.SQLite;
import frame.CR.Created;
import frame.CR.Search;
import frame.tools.BMI;
import frame.tools.Caclulator;
import frame.tools.Slots;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import net.miginfocom.swing.MigLayout;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private DataReader dataReader;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {			
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
		});
	}

	public MainMenu() throws SQLException {
		// 操作主體
		dataReader = new DataReader();
		//表格
		DefaultTableModel table = new DefaultTableModel();
		JTable Jtable = new JTable(table) {
			private static final long serialVersionUID = 1L;
			// 取消編輯
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			// https://stackoverflow.com/questions/40705616/background-color-flickers-using-jcheckbox-in-jtable
			// https://stackoverflow.com/questions/31585718/change-background-color-of-jtable-whole-row-based-on-column-value
			// 單雙顏色不同
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				// 單雙顏色
				c.setBackground(row % 2 == 1 ? Color.LIGHT_GRAY : null);
				// 被選擇時的顏色
				if (row == this.getSelectedRow())
					c.setBackground(new Color(123, 213, 111));
				((JComponent) c).setBorder(null);
				return c;
			}

		};
		Jtable.setCellSelectionEnabled(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 480);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("小工具");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("BMI");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BMI BMI = new BMI();
				BMI.setVisible(true);
				System.out.println(BMI.isVisible());
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("計算機");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Caclulator caclulator = new Caclulator();
				caclulator.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("拉霸機");
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Slots slot = new Slots();
				slot.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2_1);

		JMenu mnNewMenu_1 = new JMenu("基本資料維護作業");
		menuBar.add(mnNewMenu_1);

		JMenu mnNewMenu_2 = new JMenu("資料維護");
		mnNewMenu_1.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("新增資料");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Created created = new Created(dataReader.conn, table, getBounds().x, (int)getBounds().getMaxY());
				created.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("查詢資料");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search search = new Search(dataReader.conn, table, getBounds().x, (int)getBounds().getMaxY());
				search.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 預覽主選單
		// 主體
		JPanel panel = new JPanel();
		panel.setBounds(10, 40, 638, 143);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		// 佔存資料
		ArrayList<String> nameData = new ArrayList<String>();
		ArrayList<String> valueData = new ArrayList<String>();

		// 內容
		Connection conn = dataReader.conn;
		Statement sm = conn.createStatement();
		ResultSet rs = sm.executeQuery("SELECT * FROM city");
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		JPanel[] dataJPanel = new JPanel[count];
		JLabel[] dataJLabel = new JLabel[count];
		JTextField[] dataJTextField = new JTextField[count];
		for (int i = 0; i < count; i++) {
			dataJPanel[i] = new JPanel();
			dataJPanel[i].setBorder(new BevelBorder(BevelBorder.LOWERED));
			dataJPanel[i].setBackground(Color.WHITE);
			panel.add(dataJPanel[i]);
			dataJPanel[i].setLayout(new FlowLayout(FlowLayout.LEADING, 5, 15));
			dataJLabel[i] = new JLabel(rsmd.getColumnName(i + 1) + ":\s");
			dataJPanel[i].add(dataJLabel[i]);
			dataJTextField[i] = new JTextField();
			int now = i;
			dataJTextField[i].addFocusListener(new FocusAdapter() {
				private String value;
				private String valueName;
				private int index;

				@Override
				public void focusGained(FocusEvent e) {
					value = dataJTextField[now].getText();
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (!dataJTextField[now].getText().equals(value)) {
						valueName = dataJLabel[now].getText().split(":\s")[0];
						// 資料未存在
						if ((index = nameData.indexOf(valueName)) < 0) {
							nameData.add(valueName);
							valueData.add(dataJTextField[now].getText());
						}
						// 資料已存在
						else {
							valueData.set(index, dataJTextField[now].getText());
						}
					}
				}
			});
			dataJPanel[i].add(dataJTextField[i]);
			dataJTextField[i].setColumns(10);
		}
		dataJTextField[0].setEnabled(false);
		rs.close();
		sm.close();
		System.gc(); // System.gc()介紹
						// https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/709562/

		// 修改, 刪除
		JPanel btnHome = new JPanel();
		btnHome.setBorder(new BevelBorder(BevelBorder.LOWERED));
		btnHome.setBackground(Color.WHITE);
		panel.add(btnHome);
		// 刪除
		JButton btnDelete = new JButton("刪除");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 確認刪除
				if (dataUpdate(btnDelete.getText())) {
					try {
						dataReader.dataRemove(dataJTextField[0].getText());
						JOptionPane.showMessageDialog(contentPane, "刪除成功");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(contentPane, "刪除失敗");
					} finally {
						for (JTextField text : dataJTextField) {
							text.setText("");
						}
						nameData.clear();
						valueData.clear();
						dataReader.show(table);
					}
				}
			}
		});
		// 修改
		JButton btnDataSet = new JButton("修改");
		btnDataSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!nameData.isEmpty()) {
					// 確認修改
					if (dataUpdate(btnDataSet.getText())) {
						try {
							nameData.add(0, "ID");
							valueData.add(0, dataJTextField[0].getText());
							dataReader.dataSet(nameData.toArray(), valueData.toArray());
							JOptionPane.showMessageDialog(contentPane, "修改成功");
							nameData.clear();
							valueData.clear();
							int select = Jtable.getSelectedRow();
							dataReader.show(table);
							Jtable.getSelectionModel().addSelectionInterval(select, select);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(contentPane, "修改失敗");
						}
					}
				}
			}
		});
		btnHome.setLayout(new MigLayout("", "[159px][159px]", "[143px]"));
		btnHome.add(btnDataSet, "cell 0 0,grow");
		btnHome.add(btnDelete, "cell 1 0,grow");

//------------------------------------------------------------------------------------------------------------

		// DefaultListModel<String> list = new DefaultListModel<String>();
		// 表格顯示
		// Jtable.setEnabled(false); table取消變動(e.g. 點擊變顏色、編輯)
		Jtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!nameData.isEmpty()) {
					nameData.clear();
					valueData.clear();
				}
				for (int i = 0; i < count; i++) {
					dataJTextField[i].setText(String.valueOf(Jtable.getValueAt(Jtable.getSelectedRow(), i)));
				}
			}
		});

		JScrollPane Jscrollpane = new JScrollPane(Jtable);
		Jscrollpane.setBounds(10, 193, 638, 216);
		contentPane.add(Jscrollpane);

		JButton viewButton = new JButton("瀏覽");
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent E) {
				dataReader.show(table);
			}
		});
		viewButton.setBounds(10, 7, 87, 23);
		contentPane.add(viewButton);
		/*
		 * //下拉式選單table Choice choice = new Choice(); choice.addItemListener(new
		 * ItemListener() { public void itemStateChanged(ItemEvent e) { DataReader.SQL =
		 * "SHOW TABLES FROM " + e.getItem().toString();
		 * System.out.println(DataReader.SQL); DataReader.show(table); } });
		 * choice.setBounds(79, 90, 107, 21); contentPane.add(choice);
		 * DataReader.set(choice);
		 */
	}
	//確認視窗
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

class DataReader {
	Connection conn;
	ArrayList<String> inputTableUseTmp = new ArrayList<>();
	String SQL = "SELECT * FROM city ORDER BY ID ASC;";

	DataReader() throws SQLException {
		// MySQL sql = new MySQL();
		SQLite sql = new SQLite();
		this.conn = sql.getCon();
	}
	public void view(DefaultTableModel table) {
		clear(table);
		// https://stackoverflow.com/questions/2780284/how-to-get-all-table-names-from-a-database
		// sqlite-jdbc 無法使用SHOW TABLES
		DatabaseMetaData md;
		try {
			md = conn.getMetaData();
			table.addColumn("table");
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
				String[] tmp = { rs.getString(3) };
				table.addRow(tmp);
			}
		} catch (SQLException e) {
			System.out.println("view error");
			//e.printStackTrace();
		}
		// System.out.println(SQL);
		// show(table);
	}
	public void show(DefaultTableModel table) {
		clear(table);
		Statement sm = null;
		ResultSet rs = null;
		// 載入資料
		try {
			sm = this.conn.createStatement();
			rs = sm.executeQuery(this.SQL);
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
			System.out.print("show error");
			// e.printStackTrace();
		} finally {
			if (sm != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.print("show rs.colse() error");
					//e.printStackTrace();
				}
				try {
					sm.close();
				} catch (SQLException e) {
					System.out.print("show sm.close() error");
					//e.printStackTrace();
				}
			}
		}
	}
	// 清空table
	public void clear(DefaultTableModel table) {
		table.setColumnCount(0);
		table.setRowCount(0);
		/*
		 * // 清除column int columnClearUse = table.getColumnCount(); for (int i = 0; i <
		 * columnClearUse; i++) { table.removeColumn(0); } // 清除row
		 * table.getDataVector().clear(); /* =上面 int rowClearUse = table.getRowCount();
		 * for (int i = 0; i < rowClearUse; i++) { table.removeRow(0); }
		 */
	}

	// 修改
	public void dataSet(Object[] name, Object[] value) throws SQLException {
		new Update(conn, name, value);
	}

	// 刪除
	public void dataRemove(Object ID) throws SQLException {
		new Delete(conn, ID);
	}
}