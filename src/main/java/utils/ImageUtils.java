package utils;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ImageUtils {

    public static String getImageFormat(Image is) throws IOException {
        ImageInputStream imageInputStream = ImageIO.createImageInputStream(is);
        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);

        if (imageReaders.hasNext()) {
            ImageReader imageReader = imageReaders.next();
            return imageReader.getFormatName();
        }
        return null;
    }

    public static InputStream imageToInputStream(Image image) {
        PixelReader pixelReader = image.getPixelReader();
        BufferedImage bufferedImage = new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int argb = pixelReader.getArgb(x, y);
                bufferedImage.setRGB(x, y, argb);
            }
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", outputStream);
            InputStream is = new ByteArrayInputStream(outputStream.toByteArray());
            return is;
        } catch (IOException e) {
            System.out.println("CUpu goblok");
            throw new RuntimeException(e);
        }
    }

    public static Image inputStreamToImage(InputStream is) {
        try {
            Image image = new Image(is);
            return image;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
