package arrays;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class Images {

    public static void main(String[] args) throws Exception{
        byte[] data = readImage("data/cat.jpg");

        saveImage(data, "data/fuzzycat.jpg");
    }

    public static byte[] readImage(String path) throws Exception{
        BufferedImage bufferedimage = ImageIO.read(new File(path));
        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        ImageIO.write(bufferedimage, "jpg", byteout);
        byte[] data = byteout.toByteArray();
        return data;
    }
    
    public static void saveImage(byte[] data, String path) throws Exception {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(path));
        out.write(data);
        out.close();
    }
}
