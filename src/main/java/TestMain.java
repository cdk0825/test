
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import net.minidev.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author work
 */
public class TestMain {
	
//	public static void main(String[] args){
//		String key = TestMain.keyFile();
//		
//		System.out.println("====================");
//		System.out.println("key = >" + key);
//		System.out.println("====================");
//	}
	
	public static byte[] ivFile() throws IOException{

		byte[] bytes = Files.readAllBytes(Paths.get("D:/cdk/AES_256.iv"));
		
		return bytes;
	}
	
	public static byte[] keyFile() throws IOException{
		
		byte[] bytes = Files.readAllBytes(Paths.get("D:/cdk/AES_256.key"));

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
	
	public static void EncPhone1() throws Exception{
		// AES-256(CBC) 암호화 테스트
		String pNum = "jaBsA8VUiL6aod3Me2gntA==";
		String pNum2 = "01191295086";
		byte[] key = TestMain.keyFile();
		byte[] iv = TestMain.ivFile();
		Aes256SmsUtil aes256 = new Aes256SmsUtil(key, iv);
		
		String deCode = aes256.aesDecode(pNum);
		
		String enCode = aes256.aesEncodes(pNum2);
		
		System.out.println("deCode:" + deCode);
		System.out.println("enCode:" + enCode);
	}
	
	public static void EncPhone2() throws Exception{
		// AES-256(CBC) 암호화 테스트
//		String pNum = "MbU1KpVSWLhOl3Nj";
		String pNum = "HdDBo5dV1Oh6kZUS";
		String pNum2 = "01191295086";
		
		String encNum = AES256.AES_Encode(pNum2, pNum);
		
		System.out.println("deCode:" + encNum);
	}
	
	public static void main(String[] args) throws Exception{
		
		TestMain.EncPhone2(); 
	
//		// ppp sms 연동 테스트
//		HostnameVerifier hv = new HostnameVerifier() {
//			public boolean verify(String urlHostName, SSLSession session) {
//			   System.out.println("Warning: URL Host: "+urlHostName+" vs. "
//				 +session.getPeerHost());
//			   return true;
//			}
//		};

		// set this property to the location of the cert file
//		System.setProperty("@@@@==========javax.net.ssl.trustStore", "jssecacerts.cert");
//
//		HttpsURLConnection.setDefaultHostnameVerifier(hv);
//
//		URL url = new URL("https://api.homeschool.pinkfong.com/tlab/test/");
////		URL url = new URL("https://api.homeschool.pinkfong.com/tlab/");
//
//		HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
//
//		System.out.println("@@@@==========sending request...");
//		urlConn.setRequestMethod("POST");
//		urlConn.setConnectTimeout(300);
//		urlConn.setAllowUserInteraction(false); // no user interaction
//		urlConn.setDoOutput(true); // want to send
//		urlConn.setDoInput(true); // want to out
//		urlConn.setRequestProperty( "Content-type", "application/json" );
//		urlConn.setRequestProperty( "x-api-key", "3Hr04xA1OV6pj63nrDFsiaG6XJhcguFe7VgaqF5n");
//		
//		
//		JSONObject json = new JSONObject();
//		json.put("event_id", null);
//		json.put("said", "TESTCODE1234");
//		json.put("phone", encText);
//		json.put("type", "A");
//		String body = json.toString();
//		System.out.println("==body==>" + body);
//		
//		OutputStream out = urlConn.getOutputStream();
//		out.write(body.getBytes("euc-kr"));
//		out.flush();
//		out.close();
//
//		int rspCode = urlConn.getResponseCode();
//
//		System.out.println("@@@@==========rspCode=>" + rspCode);
//		
//		if (rspCode == 200) {
//			InputStream ist = urlConn.getInputStream();
//			InputStreamReader isr = new InputStreamReader(ist);
//			BufferedReader br = new BufferedReader(isr);
//
//			String nextLine = br.readLine();
//			
//			while (nextLine != null) {
//				System.out.println(nextLine);
//				
//				JsonObject jobj = new Gson().fromJson(nextLine, JsonObject.class);
//				String result = jobj.get("result_code").getAsString();
//				String msg = jobj.get("result_msg").toString();
//				System.out.println("====result===>" + result);
//				System.out.println("====msg===>" + msg);
//				
//				try {
//					nextLine = br.readLine();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//			}
//		}

	}

}
