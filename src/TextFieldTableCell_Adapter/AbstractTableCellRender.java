package TextFieldTableCell_Adapter;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

public abstract class AbstractTableCellRender<S, T> extends TextFieldTableCell<S, T> {
    boolean autoAlign;
    boolean autoRender;

    public AbstractTableCellRender(boolean autoAlign, boolean autoRender) {
        this.autoAlign = autoAlign;
        this.autoRender = autoRender;
    }

    public AbstractTableCellRender(boolean autoAlign, boolean autoRender, StringConverter StrConv) {

        super(StrConv);
        this.autoAlign = autoAlign;
        this.autoRender = autoRender;
    }

    public abstract void renderItem(T var1);
}