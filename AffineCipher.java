/**
 * The Affine Cipher is a type of monoalphabetic substitution cipher where each letter in
 * an alphabet is mapped to its numeric equivalent, encrypted using a simple mathematical
 * function, and converted back to a letter. The encryption function is defined as
 * E(x) = (ax + b) % m, where 'a' and 'b' are the encryption keys, and 'm' is the size of
 * the alphabet. For decryption, the inverse function D(x) = a^(-1)(x - b) % m is applied.
 * To ensure decryption is possible, 'a' must be chosen such that it is coprime to 'm'.
 *
 * Pros:
 * - Simple and easy to understand.
 * - Provides a degree of security against casual attackers.
 *
 * Cons:
 * - Vulnerable to frequency analysis attacks.
 * - Limited key space, making it susceptible to brute force attacks.
 * - Affine ciphers with small key values can be easily broken using known-plaintext attacks.
 * - The requirement of 'a' being coprime to 'm' limits the choice of keys.
 *
 * Cryptanalyst's Considerations:
 * - Frequency analysis can be effective due to the predictable nature of the cipher.
 * - Knowledge of the language and patterns in the plaintext can aid in cryptanalysis.
 * - Brute force attacks become more feasible with the limited key space.
 * - For improved security, larger key spaces and the use of key pairs can be considered.
 */

import java.util.Scanner;

public class AffineCipher {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the String to be encrypted: ");
		String plainMessage = in.nextLine();

		System.out.println("Enter the multiplication key (Coprime of 26): ");
		int multiplicationKey = in.nextInt();
		System.out.println("Enter the addition key: ");

		int addtionKey = in.nextInt();

		String cipherText = encrypt(plainMessage, multiplicationKey, addtionKey);

		System.out.println("Encrypted message: " + cipherText);

		String decryptedText = decrypt(cipherText, multiplicationKey, addtionKey);

		System.out.println("Decrypted text: " + decryptedText);

		in.close();

	}

	public static String encrypt(String plainText, int key, int key2) {
		StringBuilder sb = new StringBuilder();
		for (char c : plainText.toCharArray()) {
			if (Character.isLetter(c)) {
				char encryptedChar;
				if (c >= 'a' && c <= 'z') {
					encryptedChar = (char) (((((c - 'a') * key) + key2) % 26) + 'a');
				} else {
					encryptedChar = (char) (((((c - 'A') * key) + key2) % 26) + 'A');

				}
				sb.append(encryptedChar);

			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String decrypt(String cipherText, int key, int key2) {

		StringBuilder sb = new StringBuilder();
		int k_inverse = 0;

		while ((k_inverse * key) % 26 != 1)
			k_inverse++;

		for (char c : cipherText.toCharArray()) {
			if (Character.isLetter(c)) {
				char decryptedChar;
				if (c >= 'a' && c <= 'z') {
					decryptedChar = (char) ((k_inverse * ((c - 'a') - key2 + 26) % 26) + 'a');
				} else {
					decryptedChar = (char) ((k_inverse * ((c - 'A') - key2 + 26)) % 26 + 'A');
				}

				sb.append(decryptedChar);

			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

}
