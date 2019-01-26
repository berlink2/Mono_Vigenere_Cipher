import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;

public class AssEx2 {

	public static void main(String[] args) {
		FileReader reader = null;
		Scanner s = new Scanner(System.in);

		String fileName = null;
		String trueFileName = null;
		String keyWord = null;
		// loop that checks if filename is valid
		boolean hasFN = false;
		while (!hasFN) {
			try {
				System.out.println("Please enter a filename: ");
				fileName = s.nextLine();
				trueFileName = fileName + ".txt";
				reader = new FileReader(trueFileName);
				hasFN = true;
			} catch (FileNotFoundException e) {
				System.out.println("That file does not exist.");
			}
		}

		keyWord = checkKW(s); // passes scanner to method that checks for valid keyword

		LetterFrequencies l = new LetterFrequencies(); //creation of letter frequencies object for letter frequency analysis
		MonoAlphaCipher m = new MonoAlphaCipher(keyWord); // creation of cipher objects.
		// VigenereCipher m = new VigenereCipher(keyWord);
		Scanner t = new Scanner(reader);
		FileWriter fw = null;
		String line = null;
		// if statements for deciding whether to encode or decode file contents
		try {
			if (fileName.charAt(fileName.length() - 1) == 'C') {
				StringBuilder newFileName = new StringBuilder(fileName); // changes last letter of output file to
																			// reflect
																			// that it has been decoded
				newFileName.setCharAt(fileName.length() - 1, 'D');
				fw = new FileWriter(new File(newFileName + ".txt"));
				while (t.hasNextLine()) {
					line = t.nextLine();
					String newLine = m.Decode(line, m);
					l.count(newLine);
					fw.write(newLine + "\n");
				}
				System.out.println(
						"Your file has been decoded. You will find the decoded file and analysis file in the source folder.");
			} else if (fileName.charAt(fileName.length() - 1) == 'P') {
				StringBuilder newFileName = new StringBuilder(fileName); // changes last letter of output file to show
																			// it has been encoded
				newFileName.setCharAt(fileName.length() - 1, 'C');
				fw = new FileWriter(new File(newFileName + ".txt"));
				while (t.hasNextLine()) {
					line = t.nextLine();
					String newLine = m.Encode(line, m);
					l.count(newLine);
					fw.write(newLine + "\n");
				}
				System.out.println(
						"Your file has been encoded. You will find the encoded file and analysis file in the source folder.");
			}
			l.writeF(fileName);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null && t != null && reader != null && s != null) {
				try {
					fw.close();
					t.close();
					reader.close();
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

//method for checking if keyword is valid, if valid it passes keyword back to main
	public static String checkKW(Scanner s) {
		String keyWord = null;
		boolean hasKW = false;
		while (!hasKW) {
			System.out.println("Please enter a keyword: ");
			keyWord = s.nextLine();
			boolean check = checkRepeat(keyWord); // passes proposed keyword into method that checks for repeating
			boolean check2 = checkCap(keyWord); // letters in keyword
			if (check == true && check2 == true) {
				hasKW = true;
			}
		}
		return keyWord;
	}

//method for checking if the keyword has repeating letters	
	public static boolean checkRepeat(String keyWord) {
		for (int i = 1; i < keyWord.length(); i++) {
			if (keyWord.charAt(i) == keyWord.charAt(i - 1)) {
				System.out.println("That is not a valid keyword. Keyword must have no repeating letters.");
				return false;
			}
		}
		return true;
	}

	// method to check if keyword is capital letters
	public static boolean checkCap(String keyWord) {
		for (int i = 0; i < keyWord.length(); i++) {
			if ((int) keyWord.charAt(i) < (int) 'A' || (int) keyWord.charAt(i) > (int) 'Z') {
				System.out.println("That is not a valid keyword. Keyword must be all capital letter characters.");
				return false;
			}
		}
		return true;
	}
}
