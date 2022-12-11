package abstractFactory;

import appPackage.App;
import flyweight.MyImage;

public abstract class AbstractProduct {
    public final MyImage myImage;
    public AbstractProduct(String resource) {
        myImage = App.getOrAddImage(resource);
    }
}
