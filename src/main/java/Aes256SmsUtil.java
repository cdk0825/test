
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author dkcho
 */
public class Aes256SmsUtil {
	
	private byte[] iv;
	private Key keySpec;
	
	public Aes256SmsUtil(byte[] key, byte[] iv) throws UnsupportedEncodingException{
		this.iv = iv;
		
		byte[] b = key;
		SecretKeySpec keySpec = new SecretKeySpec(b, "AES");

		this.keySpec = keySpec;
	}
	
	// 암호화
	public String aesEncodes(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException{
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
		
		byte[] encryted = c.doFinal(str.getBytes("UTF-8"));
		String enStr = new String(Base64.encode(encryted));
		
		return enStr;
	}
	
//	// 복호화
	public String aesDecode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, Base64DecodingException{
		
		byte[] deStr = Base64.decode(str);
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
		
		byte[] decryted = c.doFinal(deStr);
		
		String enStr = new String(decryted);
		
		return enStr;
	}
	
}
