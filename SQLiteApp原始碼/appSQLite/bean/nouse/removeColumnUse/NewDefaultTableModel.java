package bean.nouse.removeColumnUse;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class NewDefaultTableModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	// from https://stackoverflow.com/questions/5938436/removing-column-from-tablemodel-in-java
	// 另外新增removeColum 刪除表格用
	@SuppressWarnings("rawtypes")
	public void removeColumn(int column) {
		columnIdentifiers.remove(column);
		for (Object row : dataVector) {
			((Vector) row).remove(column);
		}
		fireTableStructureChanged();
	}
}
