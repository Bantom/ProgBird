package main;

import java.awt.Image;
import java.net.URL;

public class PicturesFox {

    static Image fox;
    URL url;
    static StartingPoint sp;

    public PicturesFox(StartingPoint sp) {

        try {
            url = sp.getDocumentBase();
        } catch (Exception e) {

        }
        fox = sp.getImage(url, "main/images/fox.png");
        this.sp = sp;
    }
}
