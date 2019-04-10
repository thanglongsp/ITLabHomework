/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI3;

/**
 *
 * @author thanglongsp
 */
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author thanglongsp
 */
public class CompareImage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedImage img_100_png = null;
        BufferedImage jmg_100_bmp = null;
        
        File f_100_png = null;
        File f_100_bmp = null;
        
        f_100_png = new File("..\\SoSanhAnhPngBmp\\src\\images\\img_100_png.png");
        f_100_bmp = new File("..\\SoSanhAnhPngBmp\\src\\images\\img_100_bmp.bmp");
        FileInputStream f_10k_png = new FileInputStream("..\\SoSanhAnhPngBmp\\src\\images\\img_10k_png.png");
        FileInputStream f_10k_bmp = new FileInputStream("..\\SoSanhAnhPngBmp\\src\\images\\img_10k_bmp.bmp");

        img_100_png = ImageIO.read(f_100_png);
        jmg_100_bmp = ImageIO.read(f_100_bmp);
        
        DataBuffer db_png_100 = img_100_png.getData().getDataBuffer();
        DataBuffer db_bmp_100 = jmg_100_bmp.getData().getDataBuffer();

        int size_png_100 = db_png_100.getSize();
        int size_bmp_100 = db_bmp_100.getSize();
        double size_png_10k = f_10k_png.getChannel().size();
        double size_bmp_10k = f_10k_bmp.getChannel().size();

        if (size_png_100 > size_bmp_100) {
            System.out.println("size_png_100 > size_bmp_100");
        } else if (size_png_100 < size_bmp_100) {
            System.out.println("size_png_100 > size_bmp_100");
        } else {
            System.out.println("size_png_100 == size_bmp_100");
        }
        
        if (size_png_10k > size_bmp_10k) {
            System.out.println("size_png_10k > size_bmp_10k");
        } else if (size_png_10k < size_bmp_10k) {
            System.out.println("size_png_10k > size_bmp_10k");
        } else {
            System.out.println("size_png_10k == size_bmp_10k");
        }

    }

}

