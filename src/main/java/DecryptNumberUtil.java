

import org.apache.commons.lang.StringUtils;

public class DecryptNumberUtil {
	
	private DecryptNumberUtil(){}
	
	
	/**
     *  전화번호 복호화
     * @param encryptedStr
     * @param key
     */
    public static String decryptPhoneNumber(String encryptedStr, String key) {

        String decodePhone = "";

        if (StringUtils.isEmpty(encryptedStr)) {
            return decodePhone;
        }
        
        if (StringUtils.isEmpty(key)) {
            return decodePhone;
        }
        
        try {
            decodePhone = SeedUtil.getSeedDecrypt(encryptedStr, key);
        } catch (Exception ex) {
            //getSeedDecrypt 복호화 에러시 WEB 복호화 진행
            try {
                decodePhone = AES256.AES_Decode(encryptedStr, key);
            } catch (Exception e1) {
                decodePhone = "";
            }
        }

        return decodePhone;
    }
	
}
