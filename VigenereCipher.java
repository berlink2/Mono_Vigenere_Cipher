// class for the vigenere cipher
public class VigenereCipher {
	private char[] Alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private char[][] vCipher;
	private int sizeOfKeyword;
// attributes are a 1-d array for the alphabet, and a 2-d array for the vigenere cipher, and an int for the size of the keyword

//constructor that takes the keyword from the main, and passes it to the method that makes the vigenere cipher.
	public VigenereCipher(String keyWord) {
		vCipher = new char[Alphabet.length][keyWord.length()];
		makeVCipher(keyWord);
	}

//get methods for cipher
	public char[] getAlphabet() {
		return Alphabet;
	}

	public int setSizeOfKeyword(int n) {
		sizeOfKeyword = n;
		return sizeOfKeyword;
	}

	public int getSizeOfKeyword() {
		return sizeOfKeyword;
	}

	public char[][] getVCipher() {

		return vCipher;
	}

//method to make the cipher
	public void makeVCipher(String keyWord) {
		int nextIndex = 0;
		setSizeOfKeyword(keyWord.length());
		for (int i = 0; i < keyWord.length(); i++) {

			vCipher[0][i] = keyWord.charAt(i);
			for (int j = 0; j < Alphabet.length; j++) {

				if (vCipher[0][i] == Alphabet[j]) {
					nextIndex = j;
					break;
				}

			}
			for (int j = 0; j < Alphabet.length; j++) {
				vCipher[j][i] = Alphabet[nextIndex % 26];
				nextIndex++;
			}
		}
		//for (int i = 0; i < keyWord.length(); i++) {			//prints created vignere cipher for testing purposes. not needed for program functionality.
		//	for (int j = 0; j < Alphabet.length; j++) {
		//		System.out.print(vCipher[j%Alphabet.length][i%keyWord.length()] + ", ");
		//	}System.out.println();
		//}

	}

//method to encode the message with the vigenere cipher
	public String Encode(String s, VigenereCipher v) {
		char[] sArray = s.toCharArray();
		char[] Alphabet = v.getAlphabet();
		int pos = -1;
		int nextIndex = 0;
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

				sArray[i] = vCipher[pos][nextIndex % getSizeOfKeyword()];
				nextIndex++;

			}

		}
		String Encoded = new String(sArray);
		return Encoded;

	}

//method to decode a message with the vigenere cipher
	public String Decode(String s, VigenereCipher v) {
		char[] sArray = s.toCharArray();
		char[] Alphabet = v.getAlphabet();

		int pos = -1;
		int nextIndex = 0;
		for (int i = 0; i < s.length(); i++) {
			boolean letterMatch = false;
			for (int j = 0; j < Alphabet.length; j++) {
				if (sArray[i] == vCipher[j][nextIndex % getSizeOfKeyword()]) {
					pos = j;
					letterMatch = true;
					nextIndex++;
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
