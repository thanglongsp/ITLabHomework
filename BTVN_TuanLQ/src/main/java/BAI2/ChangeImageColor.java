/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI2;

/**
 *
 * @author thanglongsp
 */
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class ChangeImageColor {

    public static void main(String args[]) throws IOException {
        BufferedImage img = null;
        File f = null;

        // Đọc file
        try {
            f = new File("..\\Change_color_image\\src\\images\\input.jpg");
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        }

        int width = img.getWidth();
        int height = img.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int p = img.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                float ty_le_do;

                try {
                    ty_le_do = r / (b + g); // ty_le_do : Tỷ lệ đỏ
                    if (ty_le_do > 0.2) {
                        System.out.println(+ty_le_do);
                        b = Math.min(r + g + b, 255);
                        r = 0;
                    }
                } catch (ArithmeticException e) {
                    b = r;
                    r = 0;
                } finally {
                    p = (a << 24) | (r << 16) | (g << 8) | b;
                    img.setRGB(x, y, p);
                }
            }
        }

        // Ghi file
        try {
            f = new File("..\\Change_color_image\\src\\images\\output.jpg");
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}