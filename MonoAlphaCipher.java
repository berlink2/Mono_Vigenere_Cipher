//import java.util.Arrays; //for use if array is printed, which is used for testing purposes. not part of program functionality. 

//class for a Mono Alphabetic Cipher

public class MonoAlphaCipher {
	private char[] Alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private char[] cipher = new char[26];
// Class's attributes are char arrays for the cipher and the alphabet

//constructor takes the keyword from the main method, and passes it to the makeCipher method
	public MonoAlphaCipher(String keyWord) {
		makeCipher(keyWord);

	}

//get methods for the cipher and the alphabet
	public char[] getAlphabet() {
		return Alphabet;
	}

	public char[] getCipher() {
		return cipher;
	}

// method to create the cipher. 
	public void makeCipher(String keyWord) {
		for (int i = 0; i < keyWord.length(); i++) {
			cipher[i] = keyWord.charAt(i);
		}
		int nextIndex = keyWord.length();
		for (int i = 0; i < Alphabet.length; i++) {
			boolean hasLetter = false;
			for (int j = 0; j < cipher.length; j++) {

				if (cipher[j] == Alphabet[i]) {
					hasLetter = true;
					break;
				}

			}
			if (!hasLetter) {
				cipher[nextIndex % Alphabet.length] = Alphabet[i];
				nextIndex++;
			}
		}//System.out.println(Arrays.toString(cipher)); //method that prints out cipher for testing purposes. not for program functionality.
	}

// A method to encode a message. 
	public String Encode(String s, MonoAlphaCipher m) {
		char[] sArray = s.toCharArray();
		char[] Alphabet = m.getAlphabet();
		char[] Cipher = m.getCipher();
		int pos = -1;
		for (int i = 0; i < s.length(); i++) {
			boolean letterMatch = false;
			for (int j = 0; j < Alphabet.length; j++) {

				if (sArray[i] == Alphabet[j]) {
					pos = j;
					letterMatch = true;
					break;
				}

			}
			if (letterMatch == true) {
				sArray[i] = Cipher[pos];

			}

		}
		String Encoded = new String(sArray);
		return Encoded;

	}

// method to decode an encoded message. 
	public String Decode(String s, MonoAlphaCipher m) {
		char[] sArray = s.toCharArray();
		char[] Alphabet = m.getAlphabet();
		char[] Cipher = m.getCipher();
		int pos = -1;
		for (int i = 0; i < s.length(); i++) {
			boolean letterMatch = false;
			for (int j = 0; j < Cipher.length; j++) {

				if (sArray[i] == Cipher[j]) {
					pos = j;
					letterMatch = true;
					break;
				}

			}
			if (letterMatch == true) {
				sArray[i] = Alphabet[pos];

			}

		}
		String Decoded = new String(sArray);
		return Decoded;

	}

}
