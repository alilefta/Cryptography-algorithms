/**
 * The Decimation Cipher is a type of monoalphabetic substitution cipher.
 * In this cipher, each letter in the plaintext is replaced by a letter that is a fixed number of positions away
 * in the alphabet, following a multiplication-based encryption scheme.
 *
 * Encryption: E_k(x) = (x * k) mod n
 * Decryption: D_k(y) = (y * k^(-1)) mod n, where k^(-1) is the modular multiplicative inverse of k modulo n.
 *
 * Pros:
 * - Simple and easy to implement.
 * - Provides a basic level of confidentiality for messages.
 *
 * Cons:
 * - Vulnerable to frequency analysis since it preserves the underlying frequencies of the plaintext.
 * - Limited key space, making it susceptible to brute-force attacks.
 *
 * From a cryptanalyst's point of view:
 * - Decimation cipher can be quickly broken using statistical analysis, especially for longer texts.
 * - Knowledge of the key's structure (multiplicative factor) simplifies decryption efforts.
 * - The use of a small key space makes it vulnerable to exhaustive search attacks.
 */

import java.util.Scanner;

public class Decimation {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Enter The string to encrypt: ");
		String plainText = in.nextLine();

		System.out.println("Enter the key (Coprime of 26): ");
		int key = in.nextInt();

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
