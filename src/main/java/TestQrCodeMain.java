import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author work
 */
public class TestQrCodeMain {
	
	public static byte[] ivFile() throws IOException{

		byte[] bytes = Files.readAllBytes(Paths.get("D:/cdk/AES_256.iv"));

		System.out.println("=================");
		System.out.println("=======iv size =>" + bytes.length );
		System.out.println("=================");
		
		return bytes;
	}
	
	public static byte[] keyFile() throws IOException{
		
		byte[] bytes = Files.readAllBytes(Paths.get("D:/cdk/AES_256.key"));

		System.out.println("=================");
		System.out.println("=======key size =>" + bytes.length );
		System.out.println("=================");
		
		return bytes;
	}
	
	static boolean checkBeforeFile(File file){
		if(file.exists()){
			if(file.isFile() && file.canRead()){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String ar[]){

		QRCodeWriter q = new QRCodeWriter();
		
		try{
			String text = "g1O1yC6ybr8EOdVRfp9T+w==";
			text = new String(text.getBytes("UTF-8"), "ISO-8859-1");
			BitMatrix bm = q.encode(text, BarcodeFormat.QR_CODE, 200, 200);
			int qrcodeColor = 0xFF000000;
			int backColor = -1;
			
			MatrixToImageConfig mtic = new MatrixToImageConfig(qrcodeColor, backColor);
			
			BufferedImage bi = MatrixToImageWriter.toBufferedImage(bm, mtic);
			
			WritableRaster raster = bi.getRaster();
			DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
			byte[] imageByte;
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			ImageIO.write(bi, "png", baos);
			baos.flush();
			
			imageByte = baos.toByteArray();
			baos.close();
			
			File f = new File("D:\\qrtest", "TT000000001.png");
			
			f.getPath();
			
			System.out.println("========>" + Arrays.toString(Files.readAllBytes(f.toPath())));
			
			System.out.println("========>" + Arrays.toString(imageByte));
			
			MatrixToImageWriter.writeToStream(bm, "png", new FileOutputStream(new File("qrcode.png")));
			
			
//			ServletOutputStream so = response
//			MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
		}catch (Exception e){
			
		}

////			File file = null;
//
//		String imgPath = "D:\\qrtest";
//
//		File file = new File(imgPath);
//
//		if(!file.exists()){
//			file.mkdirs();
//		}
//
//		String str = "TT000000001";
//		
//		try{
//			
//			byte[] key = TestMain.keyFile();
//			byte[] iv = TestMain.ivFile();
//			Aes256SmsUtil aes256 = new Aes256SmsUtil(key, iv);
//
//			String encText = aes256.aesEncodes(str);
//
//			System.out.println("암호화된 문자:" + encText);
//			
//			String codeurl = new String(encText.getBytes("UTF-8"), "ISO-8859-1");
//			
//			int qrcodeColor = 0xFF000000;
//			int backColor = -1;
//			
//			QRCodeWriter qcw = new QRCodeWriter();
//			
//			BitMatrix bm = qcw.encode(codeurl, BarcodeFormat.QR_CODE, 200, 200);
//			
//			MatrixToImageConfig mtic = new MatrixToImageConfig(qrcodeColor, backColor);
//			
//			
//			
//			BufferedImage bi = MatrixToImageWriter.toBufferedImage(bm, mtic);
//			
//			ImageIO.write(bi, "png", new File(imgPath + "/" + str + ".png"));
//			
//			
//		}catch (Exception e){
//			e.printStackTrace();
//		}
	}
}
