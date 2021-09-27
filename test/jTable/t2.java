package jTable;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

public class t2 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Vector<Vector<String>> data = new Vector<Vector<String>>();
	private Vector<String> dataTitle = new Vector<String>();

	public t2() {
		super();
		setTitle("建立不可滾動的表格");
		setBounds(300, 300, 300, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dataTitle.add("編號");
		dataTitle.add("內容");
		for (int i = 1; i < 10; i++) {
			Vector<String> W = new Vector<String>();
			W.add("編號" + i);
			W.add("B" + i);
			data.add(W);
		}
		JTable table = new JTable(data, dataTitle);
		add(table, BorderLayout.CENTER);
		JTableHeader tableHeader = table.getTableHeader();// 獲得表格頭物件
// 將表格頭新增到邊界佈局的上方
		add(tableHeader, BorderLayout.NORTH);
	}

	public static void main(String[] args) {
		new t2();
	}
}
