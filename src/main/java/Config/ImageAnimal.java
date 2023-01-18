package Config;

import javax.swing.*;

public class ImageAnimal {
    ImageIcon image;
    int time;

    public ImageAnimal(ImageIcon image, int fps) {
        this.image = image;
        this.time = (int)((fps/24.0)*1000);
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
