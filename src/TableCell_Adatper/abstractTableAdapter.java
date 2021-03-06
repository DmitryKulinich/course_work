//package TableCell_Adatper;
//
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.collections.ObservableList;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//
//public abstract class abstractTableAdapter {
//    TableView tableView;
//    TableColumn[] columns;
//    Object[] columnNames;
//    TableCell[] tableCellValues;
//    ObservableList<SimpleIntegerProperty> listRows;
//
//    public abstractTableAdapter() {
//    }
//
//    public abstract Object getValueAt(int var1, int var2);
//
//    public abstract void setValueAt(Object var1, int var2, int var3);
//
//    public abstract void setTableCellValue(TableCell<Object, Object> var1, int var2);
//
//    public abstract void adapt();
//
//    public void refresh() {
//        if (this.tableView.getColumns().size() > 0) {
//            ((TableColumn)this.tableView.getColumns().get(0)).setVisible(!((TableColumn)this.tableView.getColumns().get(0)).isVisible());
//            ((TableColumn)this.tableView.getColumns().get(0)).setVisible(!((TableColumn)this.tableView.getColumns().get(0)).isVisible());
//        }
//
//    }
//}