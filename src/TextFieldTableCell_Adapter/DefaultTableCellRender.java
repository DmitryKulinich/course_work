package TextFieldTableCell_Adapter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.StringConverter;

import javax.swing.SwingUtilities;

public class DefaultTableCellRender<S, T> extends AbstractTableCellRender<S, T> {
    private BufferedImage bimage = null;
    CheckBox checkBox = new CheckBox();

    public DefaultTableCellRender(boolean autoAlign, boolean autoRender) {
        super(autoAlign, autoRender);
        this.checkBox.setStyle("-fx-opacity: 1");
        this.checkBox.setDisable(true);
    }

    public DefaultTableCellRender(boolean autoAlign, boolean autoRender, StringConverter StrConv) {
        super(autoAlign, autoRender, StrConv);
        this.checkBox.setStyle("-fx-opacity: 1");
        this.checkBox.setDisable(true);
    }

    public void renderItem(T item) {
        if (item instanceof String) {
            this.renderAsString((String)item);
        } else if (item instanceof Number) {
            this.renderAsNumber((Number)item);
        } else if (item instanceof Boolean) {
            this.renderAsBoolean((Boolean)item);
        } else if (item instanceof Image) {
            this.renderAsImage((Image)item);
        } else if (item instanceof java.awt.Image) {
            this.renderAsImage((java.awt.Image)item);
        } else if (item instanceof Node) {
            this.renderAsNode((Node)item);
        } else {
            this.renderAsObject(item);
        }

    }

    private void renderAsString(String item) {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setText(item);
    }

    private void renderAsNumber(Number item) {
        this.setAlignment(this.autoAlign ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        this.setText(item == null ? "" : item.toString());
    }

    private void renderAsBoolean(Boolean item) {
        if (this.autoRender && item != null) {
            this.setText("");
            this.setAlignment(Pos.CENTER);
            this.checkBox.setSelected(item.booleanValue());
            this.setGraphic(this.checkBox);
        } else if (item != null && this.autoAlign) {
            this.setAlignment(Pos.CENTER);
            this.setText(item.toString());
        } else if (item != null) {
            this.setAlignment(Pos.CENTER_LEFT);
            this.setText(item.toString());
        }

    }

    private void renderAsImage(Image item) {
        if (this.autoRender) {
            this.setText("");
            this.setAlignment(Pos.CENTER);
            this.setGraphic(new ImageView(item));
        } else {
            this.setAlignment(Pos.CENTER_LEFT);
            this.setText(item.toString());
        }

    }

    private void renderAsImage(final java.awt.Image item) {
        WritableImage wimage = null;
        if (this.autoRender && item != null) {
            if (item instanceof BufferedImage) {
                this.bimage = (BufferedImage)item;
            } else {
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        public void run() {
                            try {
                                DefaultTableCellRender.this.bimage = DefaultTableCellRender.this.createBImage(item);
                            } catch (Exception var2) {
                                var2.toString();
                                System.out.println("Program continues normally but image was not rendered...");
                            }

                        }
                    });
                } catch (Exception var4) {
                    var4.printStackTrace();
                    System.out.println("Program continues normally but image was not rendered...");
                }
            }

            if (this.bimage != null) {
                wimage = new WritableImage((int)wimage.getWidth(), (int)wimage.getHeight());
                SwingFXUtils.toFXImage(this.bimage, wimage);
                this.setGraphic(new ImageView(wimage));
                this.setText("");
            }
        } else {
            this.setAlignment(Pos.CENTER_LEFT);
            this.setText(item.toString());
        }

    }

    private void renderAsNode(Node item) {
        if (this.autoRender) {
            this.setAlignment(Pos.CENTER);
            this.setText("");
            this.setGraphic(item);
        } else if (item != null) {
            this.setAlignment(Pos.CENTER_LEFT);
            this.setText(item.toString());
        } else {
            this.setText("");
        }

    }

    private void renderAsObject(Object item) {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setText(item == null ? "" : item.toString());
    }

    private BufferedImage createBImage(java.awt.Image image) throws Exception {
        BufferedImage bimage = new BufferedImage(image.getWidth((ImageObserver)null), image.getHeight((ImageObserver)null), 1);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, (ImageObserver)null);
        bGr.dispose();
        return bimage;
    }
}
