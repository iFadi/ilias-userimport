package de.unihannover.elsa.iui.util;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordUtility {

	private static final char[] randomPassword = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray(); 
//	private char[] randomLogin = "abcdefghijklmnopqrstuvwxyz".toCharArray(); 

	
	public static String randomString(int length) {
		return randomString(randomPassword, length);
	}
	/**
	 * Generate a Random String.
	 * 
	 * @param characterSet
	 * @param length
	 * @return
	 */
	public static String randomString(char[] characterSet, int length) {
		Random random = new SecureRandom();
		char[] result = new char[length];
		for (int i = 0; i < result.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(characterSet.length);
			result[i] = characterSet[randomCharIndex];
		}
		return new String(result);
	}
}
