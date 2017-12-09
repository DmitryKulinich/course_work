//package TableCell_Adatper;
//
//import java.util.Arrays;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.collections.FXCollections;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.util.Callback;
//
//public final class defaultTableAdapter extends abstractTableAdapter {
//    Object[][] data;
//    boolean[] autoRender;
//    boolean[] autoAlign;
//    boolean autoAlignContentAll;
//    boolean autoRenderAll;
//
//    public defaultTableAdapter() {
//        this((TableView)null, (Object[][])null);
//    }
//
//    public defaultTableAdapter(TableView tableView, Object[][] data) {
//        this(tableView, data, (Object[])null);
//    }
//
//    public defaultTableAdapter(TableView tableView, Object[][] data, Object[] columnNames) {
//        this(tableView, data, columnNames, false);
//    }
//
//    public defaultTableAdapter(TableView tableView, Object[][] data, Object[] columnNames, boolean autoAlignContentAll) {
//        this(tableView, data, columnNames, autoAlignContentAll, false);
//    }
//
//    public defaultTableAdapter(TableView tableView, Object[][] data, Object[] columnNames, boolean autoAlignContentAll, boolean autoRenderAll) {
//        this.tableView = tableView;
//        this.data = data;
//        this.columnNames = columnNames;
//        this.autoAlignContentAll = autoAlignContentAll;
//        this.autoRenderAll = autoRenderAll;
//        this.adapt();
//    }
//
//    public void setAutoAlignContentAll(boolean value) {
//        if (this.autoAlign != null) {
//            for(int i = 0; i < this.autoAlign.length; ++i) {
//                this.autoAlign[i] = value;
//            }
//
//        }
//    }
//
//    public void setAutoAlignContent(int columnIndex, boolean value) {
//        this.autoAlign[columnIndex] = value;
//    }
//
//    public boolean isAutoAlignContent(int columnIndex) {
//        return this.autoAlign[columnIndex];
//    }
//
//    public void setAutoRenderAll(boolean value) {
//        if (this.autoRender != null) {
//            for(int i = 0; i < this.autoRender.length; ++i) {
//                this.autoRender[i] = value;
//            }
//
//        }
//    }
//
//    public void setAutoRender(int columnIndex, boolean value) {
//        this.autoRender[columnIndex] = value;
//    }
//
//    public boolean isAutoRender(int columnIndex) {
//        return this.autoRender[columnIndex];
//    }
//
//    public void adapt() {
//        if (this.tableView != null && this.data != null) {
//            this.initColumns(this.autoAlignContentAll, this.autoRenderAll);
//            this.createRows();
//            this.createCellValues();
//            this.bind();
//        }
//    }
//
//    private void initColumns(boolean autoAlignContentAll, boolean autoRenderAll) {
//        Object colNameTemp = null;
//        if (this.data != null && this.data.length >= 1) {
//            this.columns = new TableColumn[this.data[0].length];
//            int i;
//            if (this.columnNames == null) {
//                this.columnNames = new Object[this.columns.length];
//
//                for(i = 0; i < this.columnNames.length; ++i) {
//                    this.columnNames[i] = "Column " + (i + 1);
//                }
//            }
//
//            this.tableCellValues = new TableCell[this.columns.length];
//
//            for(i = 0; i < this.columns.length; ++i) {
//                colNameTemp = this.columnNames[i];
//                this.columns[i] = new TableColumn(colNameTemp.toString());
//            }
//
//            this.autoAlign = new boolean[this.columns.length];
//            this.setAutoAlignContentAll(autoAlignContentAll);
//            this.autoRender = new boolean[this.columns.length];
//            this.setAutoRenderAll(autoRenderAll);
//        } else {
//            this.columns = null;
//        }
//    }
//
//    private void createRows() {
//        this.listRows = FXCollections.observableArrayList();
//        if (this.data != null && this.data.length > 0) {
//            for(int i = 0; i < this.data.length; ++i) {
//                this.listRows.add(new SimpleIntegerProperty(i));
//            }
//        }
//
//    }
//
//    private void createCellValues() {
//        for(final int i = 0; i < this.columns.length; ++i) {
//            this.columns[i].setCellValueFactory(new PropertyValueFactory(""));
//            this.columns[i].setCellFactory(new Callback<TableColumn<Object, Object>, Object>() {
//                public TableCell call(TableColumn<Object, Object> param) {
//                    return new DefaultTableCellRender<Object, Object>(DefaultTableAdapter.this.autoAlign[i], DefaultTableAdapter.this.autoRender[i]) {
//                        protected void updateItem(Object item, boolean empty) {
//                            Object value = null;
//                            super.updateItem(item, empty);
//                            if (this.getIndex() >= 0 && this.getIndex() < DefaultTableAdapter.this.data.length) {
//                                value = DefaultTableAdapter.this.data[this.getIndex()][i];
//                                this.renderItem(value);
//                            }
//
//                        }
//                    };
//                }
//            });
//        }
//
//    }
//
//    private void bind() {
//        this.tableView.getColumns().clear();
//        this.tableView.getColumns().addAll(Arrays.asList(this.columns));
//        this.tableView.getItems().clear();
//        this.tableView.setItems(this.listRows);
//    }
//
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        return this.data[rowIndex][columnIndex];
//    }
//
//    public void setValueAt(Object value, int rowIndex, int columnIndex) {
//        this.data[rowIndex][columnIndex] = value;
//    }
//
//    public void setTableCellValue(final TableCell<Object, Object> tableCell, int columnIndex) {
//        this.columns[columnIndex].setCellFactory(new Callback<TableColumn<Object, Object>, Object>() {
//            public TableCell call(TableColumn<Object, Object> param) {
//                return tableCell;
//            }
//        });
//    }
//
//    public void removeRow(int rowIndex) {
//        Object[][] tmp = new Object[this.data.length - 1][this.columnNames.length];
//        int rowNum = 0;
//
//        for(int i = 0; i < this.data.length; ++i) {
//            if (i != rowNum) {
//                tmp[rowNum++] = this.data[i];
//            }
//        }
//
//        this.data = tmp;
//        this.adapt();
//    }
//}
