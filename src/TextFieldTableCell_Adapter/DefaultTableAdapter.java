package TextFieldTableCell_Adapter;

import java.util.Arrays;

import Numbers.Number;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

public final class DefaultTableAdapter extends AbstractTableAdapter {
    Number[][] data;
    boolean[] autoRender;
    boolean[] autoAlign;
    boolean autoAlignContentAll;
    boolean autoRenderAll;
    StringConverter<?> StrConv = new DefaultStringConverter();

    public DefaultTableAdapter() {
        this((TableView) null, (Number[][]) null, true);
    }

    public DefaultTableAdapter(TableView tableView, Number[][] data, boolean editable) {
        this(tableView, data, (Object[]) null, editable);
    }

    public DefaultTableAdapter(TableView tableView, Number[][] data, Object[] columnNames, boolean editable) {
        this(tableView, data, columnNames, false, editable);
    }

    public DefaultTableAdapter(TableView tableView, Number[][] data, Object[] columnNames, boolean autoAlignContentAll, boolean editable) {
        this(tableView, data, columnNames, autoAlignContentAll, false, editable);
    }

    public DefaultTableAdapter(TableView tableView, Number[][] data, Object[] columnNames, boolean autoAlignContentAll, boolean autoRenderAll, boolean editable) {
        this.tableView = tableView;
        this.data = data;
        this.columnNames = columnNames;
        this.autoAlignContentAll = autoAlignContentAll;
        this.autoRenderAll = autoRenderAll;
        this.tableView.setEditable(editable);
        this.adapt();
    }

    public void setTypeOfTableCell() {
    }

    public void setAutoAlignContentAll(boolean value) {
        if (this.autoAlign != null) {
            for (int i = 0; i < this.autoAlign.length; ++i) {
                this.autoAlign[i] = value;
            }

        }
    }

    public void setAutoAlignContent(int columnIndex, boolean value) {
        this.autoAlign[columnIndex] = value;
    }

    public boolean isAutoAlignContent(int columnIndex) {
        return this.autoAlign[columnIndex];
    }

    public void setAutoRenderAll(boolean value) {
        if (this.autoRender != null) {
            for (int i = 0; i < this.autoRender.length; ++i) {
                this.autoRender[i] = value;
            }

        }
    }

    public void setAutoRender(int columnIndex, boolean value) {
        this.autoRender[columnIndex] = value;
    }

    public boolean isAutoRender(int columnIndex) {
        return this.autoRender[columnIndex];
    }

    public void adapt() {
        if (this.tableView != null && this.data != null) {
            this.initColumns(this.autoAlignContentAll, this.autoRenderAll);
            this.createRows();
            this.createCellValues();
            this.bind();
        }
    }

    private void initColumns(boolean autoAlignContentAll, boolean autoRenderAll) {
        Object colNameTemp = null;
        if (this.data != null && this.data.length >= 1) {
            this.columns = new TableColumn[this.data[0].length];
            int i;
            if (this.columnNames == null) {
                this.columnNames = new Object[this.columns.length];

                for (i = 0; i < this.columnNames.length; ++i) {
                    this.columnNames[i] = "Column " + (i + 1);
                }
            }
            this.tableCellValues = new TextFieldTableCell[this.columns.length];

            for (i = 0; i < this.columns.length; ++i) {
                colNameTemp = this.columnNames[i];
                this.columns[i] = new TableColumn<String, String>(colNameTemp.toString());
                this.columns[i].setEditable(true);
            }

            this.autoAlign = new boolean[this.columns.length];
            this.setAutoAlignContentAll(autoAlignContentAll);
            this.autoRender = new boolean[this.columns.length];
            this.setAutoRenderAll(autoRenderAll);
        } else {
            this.columns = null;
        }
    }

    private void createRows() {
        this.listRows = FXCollections.observableArrayList();
        if (this.data != null && this.data.length > 0) {
            for (int i = 0; i < this.data.length; ++i) {
                this.listRows.add(new SimpleIntegerProperty(i));
            }
        }

    }

    private void createCellValues() {
        for (int j = 0; j < this.columns.length; ++j) {
            final int i = j;
            this.columns[i].setCellValueFactory(new PropertyValueFactory(""));
            this.columns[i].setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
                public TextFieldTableCell call(TableColumn<String, String> param) {
                    return new DefaultTableCellRender(DefaultTableAdapter.this.autoAlign[i],
                            DefaultTableAdapter.this.autoRender[i], StrConv) {
                        public void updateItem(Object item, boolean empty) {
                            Object value = null;
                            super.updateItem(item, empty);
                            if (this.getIndex() >= 0 && this.getIndex() < DefaultTableAdapter.this.data.length) {
                                value = DefaultTableAdapter.this.data[this.getIndex()][i];
                                this.renderItem(value);
                            }
                            if (StrConv != null)
                                this.setConverter(StrConv);

                        }
                    };
                }
            });
            this.columns[i].setOnEditCommit(t -> updateCell(t));
        }

    }

    private void updateCell(TableColumn.CellEditEvent<String, String> t) {
        int row = t.getTablePosition().getRow();
        int column = t.getTablePosition().getColumn();
        data[row][column].setNumber(t.getNewValue());
    }


    private void bind() {
        this.tableView.getColumns().clear();
        this.tableView.getColumns().addAll(Arrays.asList(this.columns));
        this.tableView.getItems().clear();
        this.tableView.setItems(this.listRows);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data[rowIndex][columnIndex];
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        this.data[rowIndex][columnIndex].setNumber((String) value);
    }

    public void setTableCellValue(final TextFieldTableCell<Object, Object> tableCell, int columnIndex) {
//        if (this.tableCell == new TextFieldTableCell<>().getClass()) {
        this.columns[columnIndex].setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            public TextFieldTableCell call(TableColumn<String, String> param) {
                return tableCell;
            }
        });
//        } else {
//            this.columns[columnIndex].setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
//                public TableCell call(TableColumn<String, String> param) {
//                    return tableCell;
//                }
//            });
//        }
    }

//    public void removeRow(int rowIndex) {
//        Number[][] tmp = new [this.data.length - 1][this.columnNames.length];
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
//
//    public static DefaultTableAdapter build(TableView tableView, Object[][] data) {
//        return new DefaultTableAdapter(tableView, data, (Object[])null);
//    }
//
//    public static DefaultTableAdapter build(TableView tableView, Object[][] data, Object[] columnNames) {
//        return new DefaultTableAdapter(tableView, data, columnNames, false);
//    }
//
//    public static DefaultTableAdapter build(TableView tableView, Object[][] data, Object[] columnNames, boolean autoAlignContentAll) {
//        return new DefaultTableAdapter(tableView, data, columnNames, autoAlignContentAll, false);
//    }
//
//    public static DefaultTableAdapter build(TableView tableView, Object[][] data, Object[] columnNames, boolean autoAlignContentAll, boolean autoRenderAll) {
//        return new DefaultTableAdapter(tableView, data, columnNames, autoAlignContentAll, autoRenderAll);
//    }
}