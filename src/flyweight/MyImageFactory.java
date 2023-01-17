package flyweight;

import java.util.HashMap;
import java.util.Map;

public class MyImageFactory {
    private static final Map<String, MyImage> images = new HashMap<>();

    private MyImageFactory() {

    }
    public static MyImage getOrAddImage(String resource) {
        MyImage myImage = images.get(resource);
        if (myImage != null) {
            return myImage;
        }
        myImage = new MyImage(resource);
        images.put(resource, myImage);
        return myImage;
    }
}
