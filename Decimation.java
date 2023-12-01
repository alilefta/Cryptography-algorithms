package trainingOnCiphers.lec5_substitutionMethods;

import java.util.Scanner;

public class Decimation {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Enter The string to encrypt: ");
//		String plainText = in.nextLine();
		String plainText = "Hello from the other side babe!";

		System.out.println("Enter the key (Coprime of 26): ");
		int key = 25;
//		int key = in.nextInt();

		String cipherText = encrypt(plainText, key);
		System.out.println("Encrypted Text: " + cipherText);

		String decryptedText = decrypt(cipherText, key);
		System.out.println("Text after decryption: " + decryptedText);
		
		in.close();
	}

	public static String encrypt(String plainText, int key) {
		StringBuilder sb = new StringBuilder();
		for (char c : plainText.toCharArray()) {
			if (Character.isLetter(c)) {
				char encryptedChar;
				if (c >= 'a' && c <= 'z') {
					encryptedChar = (char) (((c - 97) * key % 26) + 65);
				} else {
					encryptedChar = (char) (((c - 65) * key % 26) + 65);
				}

				sb.append(encryptedChar);
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String decrypt(String cipherText, int key) {
		StringBuilder sb = new StringBuilder();
		int k_inverse = 0;

		while ((k_inverse * key) % 26 != 1) {
			k_inverse++;
		}

		for (char c : cipherText.toCharArray()) {
			if (Character.isLetter(c)) {
				char decryptedChar;
				if (c >= 'a' && c <= 'z') {
					decryptedChar = (char) (((c - 97) * k_inverse % 26) + 65);
				} else {
					decryptedChar = (char) (((c - 65) * k_inverse % 26) + 65);
				}

				sb.append(decryptedChar);
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

}
