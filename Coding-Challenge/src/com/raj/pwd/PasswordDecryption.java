package com.raj.pwd;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/********
 * JSP Code On Submit Handler Before Submitting form 
 * 
 * random = "ABCXYZQAFSAF788956895662"; var pass = $("#userPwd").val(); var
 * random = randomsalt; var actualpass = new Hashes.SHA256().hex(pass); var
 * SHA256 = new Hashes.SHA256().hex(new Hashes.SHA256().hex(pass)+ random);
 * $("#userPwd").val(SHA256);
 * 
 * @author Raj
 *
 */

public class PasswordDecryption {
	public static String receivedpwd = "Pass@1234";
	public static String random = "WCJQCXTQDSWB860878541859";
	// Get SHA OF Received String
	private static byte[] getSHA(String receivedpwd2) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(receivedpwd2.getBytes(StandardCharsets.UTF_8));
	}
	//Generating Hash of received String
	public static String toHexString(byte[] hash) {
		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);
		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));
		// Pad with leading zeros
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}
		return hexString.toString();
	}
	
	/*This Method design for decrypting dual hashed password*/
	public static String encrypt(String userPwd) {
		String passwordToHash = userPwd;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(passwordToHash.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			userPwd = sb.toString();
		} catch (Exception e) {
			System.out.print(e);
		}
		return userPwd;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		String pwdinhex = toHexString(getSHA(receivedpwd));
		System.out.println("\n" + receivedpwd + " : " + toHexString(getSHA(pwdinhex+random)));
		System.out.println("Decrypte Hashed Pwd" + encrypt(receivedpwd));
		//6453962bbcc935871f8cbaeace7b67ae2143a36cfee63fffd63505e852770185
		//6453962bbcc935871f8cbaeace7b67ae2143a36cfee63fffd63505e852770185		
	}

}
class RandomSaltGenerator {
	 private static final Random RANDOM = new SecureRandom();
	public static String generateSalt() throws NoSuchAlgorithmException
	{
		char[] CHARSET_AZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		char[] CHARSET_09 = "0123456789".toCharArray();
		
		 Random random = new SecureRandom();
		    char[] result = new char[12];
		    char[] result1 = new char[12];
		    for (int i = 0; i < result.length; i++) {
		        // picks a random index out of character set > random character
		        int randomCharIndex = random.nextInt(CHARSET_AZ.length);
		        result[i] = CHARSET_AZ[randomCharIndex];
		    }
		    for (int i = 0; i < result1.length; i++) {
		        // picks a random index out of character set > random character
		        int randomCharIndex = random.nextInt(CHARSET_09.length);
		        result1[i] = CHARSET_09[randomCharIndex];
		    }
		    return new String(result)+ new String(result1);
	

   }
	public static String generatesecureRandomSalt() throws NoSuchAlgorithmException
	{
		char[] CHARSET_AZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		char[] CHARSET_az = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] CHARSET_09 = "0123456789".toCharArray();
		char[] CHARSET_special = "!@#$%^&*".toCharArray();
		
		Random random = new SecureRandom();
		char[] result = new char[12];
		char[] result1 = new char[10];
		char[] result2 = new char[10];
		//char[] result3 = new char[6];
		for (int i = 0; i < result.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(CHARSET_AZ.length);
			result[i] = CHARSET_AZ[randomCharIndex];
		}
		for (int i = 0; i < result1.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(CHARSET_az.length);
			result1[i] = CHARSET_az[randomCharIndex];
		}
		for (int i = 0; i < result2.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(CHARSET_09.length);
			result2[i] = CHARSET_09[randomCharIndex];
		}
		/*for (int i = 0; i < result3.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(CHARSET_special.length);
			result3[i] = CHARSET_special[randomCharIndex];
		}*/
		//System.out.println("new ::; " +new String(result)+ new String(result1)+ new String(result2));
		return new String(result)+ new String(result1)+ new String(result2);
		
		
	}
	
	public static String generatetoken() throws NoSuchAlgorithmException
	{
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		 byte[] bSalt = new byte[25];
		 random.nextBytes(bSalt);
		 String randSalt=bSalt.toString();
	/*	 String randSalt=new String(bSalt);
		 System.out.println(randSalt+">>>>>>>>>>>salt");*/
		 return randSalt;
	}
	
	public static String generateOtp() throws NoSuchAlgorithmException
	{
		char[] CHARSET_09 = "0123456789".toCharArray();
		Random random = new SecureRandom();
		    char[] result = new char[6];
		    for (int i = 0; i < result.length; i++) {
		        // picks a random index out of character set > random character
		        int randomCharIndex = random.nextInt(CHARSET_09.length);
		        result[i] = CHARSET_09[randomCharIndex];
		    }
	return new String(result);
	}
}

