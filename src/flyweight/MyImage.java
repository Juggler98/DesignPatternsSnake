package flyweight;

import javax.swing.*;
import java.awt.*;

public class MyImage {
    public final Image image;
    public final String resource;

    public MyImage(String resource) {
        ImageIcon imageIcon = new ImageIcon(resource);
        this.image = imageIcon.getImage();
        this.resource = resource;
    }
}
