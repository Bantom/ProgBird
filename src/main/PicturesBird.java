package main;

import java.awt.Image;
import java.net.URL;

public class PicturesBird {

    static Image bird;
    URL url;
    static StartingPoint sp;

    public PicturesBird(StartingPoint sp) {

        try {
            url = sp.getDocumentBase();
        } catch (Exception e) {

        }
        bird = sp.getImage(url, "main/images/bird.png");
        this.sp = sp;
    }
}


