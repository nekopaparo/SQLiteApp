package jTable;

import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.*;

public class t3 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JScrollPane jscrollpane = new JScrollPane();
	private Vector<String> dataTitle = new Vector<String>();
	private Vector<Vector<String>> data = new Vector<Vector<String>>();

	public t3() {
		setTitle("定製表格");
		setBounds(400, 400, 400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//定義表格每列的標題
		for (int i = 0; i < 10; i++) {
			dataTitle.add(String.valueOf((char) ('A' + i)));
		}
//定義表格每列的內容
		for (int i = 1; i < 11; i++) {
			Vector<String> W = new Vector<String>();
			for (int j = 1; j < 11; j++) {
				W.add(dataTitle.get(j - 1) + j);
			}
			data.add(W);
		}
		JTable jtable = new MJTable(data, dataTitle);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 關閉表格列的自動調整功能
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 設定選擇模式為單選
		jtable.setSelectionBackground(Color.blue);// 設定被選擇行的背景色為藍色
		jtable.setSelectionForeground(Color.RED);// 設定被選擇行的前景色為紅色
		jtable.setRowHeight(50);// 設定單元格的行高為50
		jtable.setRowHeight(3, 100);// 指定第4行的行高為100
		jscrollpane.setViewportView(jtable);
		add(jscrollpane);
	}

//實現自己制定的表格
	private class MJTable extends JTable {

		private static final long serialVersionUID = 1L;

		public MJTable(Vector<Vector<String>> data, Vector<String> dataTitle) {
			super(data, dataTitle);
		}

//定義表格表頭
		public JTableHeader getTableHeader() {
			JTableHeader jtableHeader = super.getTableHeader();// 獲得表格的表頭物件
			jtableHeader.setReorderingAllowed(false);// 設定表格不可重排
			DefaultTableCellRenderer hr = (DefaultTableCellRenderer) jtableHeader.getDefaultRenderer();// 獲得表頭的單元格物件
			hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);// 設定列名居中顯示
			return jtableHeader;
		}

//定義單元格
		public TableCellRenderer getDefaultRenderer(Class<?> data) {
			DefaultTableCellRenderer cr = (DefaultTableCellRenderer) super.getDefaultRenderer(data);// 獲得表格的單元格物件
			cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);// 設定單元格中的內容居中顯示
			return cr;
		}

//定義表格不可編輯
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	public static void main(String[] args) {
		new t3();
	}
}
