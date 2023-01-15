package flyweight;

import java.util.LinkedList;

public class MyImageFactory {
    private static final LinkedList<MyImage> images = new LinkedList<>();

    private MyImageFactory() {

    }
    public static MyImage getOrAddImage(String resource) {
        for (MyImage m : images) {
            if (m.resource.equals(resource)) {
                return m;
            }
        }
        MyImage myImage = new MyImage(resource);
        images.add(myImage);
        return myImage;
    }
}
