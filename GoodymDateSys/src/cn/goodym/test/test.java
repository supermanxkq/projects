/**
 * 
 */
package cn.goodym.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * @author ��Ӣ��  Oct 24, 2013
 *
 */
public class test {
	
	   private static final int BLACK = 0xFF000000;
	   private static final int WHITE = 0xFFFFFFFF;
	 
	 
	   
	   public static BufferedImage toBufferedImage(BitMatrix matrix) {
	     int width = matrix.getWidth();
	     int height = matrix.getHeight();
	     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	     for (int x = 0; x < width; x++) {
	       for (int y = 0; y < height; y++) {
	         image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
	       }
	     }
	     return image;
	   }
	 
	   
	   public static void writeToFile(BitMatrix matrix, String format, File file)
	       throws IOException {
	     BufferedImage image = toBufferedImage(matrix);
	     if (!ImageIO.write(image, format, file)) {
	       throw new IOException("Could not write an image of format " + format + " to " + file);
	     }
	   }
	 
	   
	   public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
	       throws IOException {
	     BufferedImage image = toBufferedImage(matrix);
	     if (!ImageIO.write(image, format, stream)) {
	       throw new IOException("Could not write an image of format " + format);
	     }
	   }
	
	
	public static void main(String[] args) throws IOException, WriterException {
	     String text = "��ã���ע70��80�󹺷��ߣ���2013�귿��ʿ���Ѵ�ᡱ����11��2������14:00�ڹ�󡤽���ɽ��¡�ؾٰ졣��ʱ�����з��Ĵ󽱵�������ȡ�����⣬���λ������һ���ഺ����¼����ʱ����Ϸ���ٶ����������ǰ����ã���ע70��80�󹺷��ߣ���2013�귿��ʿ���Ѵ�ᡱ����11��2������14:00�ڹ�󡤽���ɽ��¡�ؾٰ졣��ʱ�����з��Ĵ󽱵�������ȡ�����⣬���λ������һ���ഺ����¼����ʱ����Ϸ���ٶ����������ǰ����ã���ע70��80�󹺷��ߣ���2013�귿��ʿ���Ѵ�ᡱ����11��2������14:00�ڹ�󡤽���ɽ��¡�ؾٰ졣��ʱ�����з��Ĵ󽱵�������ȡ�����⣬���λ������һ���ഺ����¼����ʱ����Ϸ���ٶ����������ǰ����ã���ע70��80�󹺷��ߣ���2013�귿��ʿ���Ѵ�ᡱ����11��2������14:00�ڹ�󡤽���ɽ��¡�ؾٰ졣��ʱ�����з��Ĵ󽱵�������ȡ�����⣬���λ������һ���ഺ����¼����ʱ����Ϸ���ٶ����������ǰ����ã���ע70��80�󹺷��ߣ���2013�귿��ʿ���Ѵ�ᡱ����11��2������14:00�ڹ�󡤽���ɽ��¡�ؾٰ졣��ʱ�����з��Ĵ󽱵�������ȡ�����⣬���λ������һ���ഺ����¼����ʱ����Ϸ���ٶ����������ǰ����ã���ע70��80�󹺷��ߣ���2013�귿��ʿ���Ѵ�ᡱ����11��2������14:00�ڹ�󡤽���ɽ��¡�ؾٰ졣��ʱ�����з��Ĵ󽱵�������ȡ�����⣬���λ������һ���ഺ����¼����ʱ����Ϸ���ٶ����������ǰ����ã���ע70��80�󹺷��ߣ���2013�귿��ʿ���Ѵ�ᡱ����11��2������14:00�ڹ�󡤽���ɽ��¡�ؾٰ졣��ʱ�����з��Ĵ󽱵�������ȡ�����⣬���λ������һ���ഺ����¼����ʱ����Ϸ���ٶ����������ǰ����ã���ע70��80�󹺷��ߣ���2013�귿��ʿ���Ѵ�ᡱ����11��2������14:00�ڹ�󡤽���ɽ��¡�ؾٰ졣��ʱ�����з��Ĵ󽱵�������ȡ�����⣬���λ������һ���ഺ����¼����ʱ����Ϸ���ٶ����������ǰ����ã���ע70��80�󹺷��ߣ���2013�귿��ʿ���Ѵ�ᡱ����11��2������14:00�ڹ�󡤽���ɽ��¡�ؾٰ졣��ʱ�����з��Ĵ󽱵�������ȡ�����⣬���λ������һ���ഺ����¼����ʱ����Ϸ���ٶ����������ǰ����ã���ע70��80�󹺷��ߣ���2013�귿��ʿ���Ѵ�ᡱ����11��2������14:00�ڹ�󡤽���ɽ��¡�ؾٰ졣��ʱ�����з��Ĵ󽱵�������ȡ�����⣬���λ������һ���ഺ����¼���λ������һ���ഺ����¼���λ������һ���ഺ����¼���λ������һ���ഺ����¼����ʱ����Ϸ���ٶ����������ǰ������";  
	        int width = 500;  
	        int height = 500;  
	        String format = "png";      
	        Hashtable hints= new Hashtable();  
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
	         BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);  
	         File outputFile = new File("new.png");  
//	         MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);  
              writeToFile(bitMatrix, format, outputFile);  
	}

}
