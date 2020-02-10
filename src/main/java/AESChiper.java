/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author tykim <tykim@tlab.co.kr>
 */
@Component
public class AESChiper {
	
	private static final String CHARSET = "UTF-8";
	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	
	
	@Value("classpath:secure/AES_256.key")
	private Resource keyResource;
	@Value("classpath:secure/AES_256.iv")
	private Resource ivResource;
	
	private byte[] keyBytes = null;
	private byte[] ivBytes = null;
	
	@PostConstruct
	private void init() {
		
		try {
			this.keyBytes = Files.readAllBytes(keyResource.getFile().toPath());
			
		} catch (IOException ex) {
		}
		
		try {
			this.ivBytes = Files.readAllBytes(ivResource.getFile().toPath());
			
		} catch (IOException ex) {
		}
	}
	
	@PreDestroy
	private void destroy() {
		
		this.keyBytes = null;
		this.ivBytes = null;
	}
	
	public String encrypt(String str) throws UnsupportedEncodingException {
        SecretKeySpec keySpec = new SecretKeySpec(this.keyBytes, "AES");
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(this.ivBytes);
		
		byte[] encBytes = null;
        
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			encBytes = cipher.doFinal(str.getBytes(CHARSET));
		} catch (GeneralSecurityException ex) {
			return null;
		}
		
		return Base64.encodeBase64String(encBytes);
	}
	
	public String decrypt(String base64String) throws UnsupportedEncodingException {
		byte[] encBytes = Base64.decodeBase64(base64String);
        SecretKeySpec keySpec = new SecretKeySpec(this.keyBytes, "AES");
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(this.ivBytes);
		
		byte[] decBytes = null;
        
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
			decBytes = cipher.doFinal(encBytes);
		} catch (GeneralSecurityException ex) {
			return null;
		}
		
		return new String(decBytes, CHARSET);
	}
	
}
