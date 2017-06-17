package main;

import java.awt.Image;
import java.net.URL;

public class Pictures {

    static int level = 1;
    static Image platform;
    URL url;
    static StartingPoint sp;

    public Pictures(StartingPoint sp) {

        try {
            url = sp.getDocumentBase();
        } catch (Exception e) {

        }
        platform = sp.getImage(url, "main/images/platform.png");
        this.sp = sp;
    }
}
