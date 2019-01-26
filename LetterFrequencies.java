import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//class for letter frequency analysis
public class LetterFrequencies {
	private double[] avgCounts = { 8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1,
			6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1 };

	private int[] letterCount = new int[26];

	private int totalCapLetters;

	private char[] Alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	// attributes for letterfrequency class. avgcounts provided from moodle, an
	// array that counts how often each capital letter appears, an alphabet array,
	// and an int to store total amount of capital letters in an input file

	public int getTotalCapLetters() {
		return totalCapLetters;
	}

	public int setTotalCapLetters(int a) {
		totalCapLetters = a;
		return a;
	}

	public int[] setLetterCount(int[] a) {
		letterCount = a;
		return a;
	}

	public char[] getAlphabet() {
		return Alphabet;
	}

	public int[] getCount() {
		return letterCount;
	}

	public double[] getAvg() {
		return avgCounts;
	}
	// get and set methods for the class

//method that counts how frequently a capital letter pops up in a file, and counts the total number of capital letters that appear
	public void count(String a) {
		Scanner s = new Scanner(a);
		try {
			while (s.hasNextLine()) {
				a = s.nextLine();
				char[] b = a.toCharArray();
				for (int i = 0; i < b.length; i++)
					for (int j = 0; j < Alphabet.length; j++) {
						if (b[i] == Alphabet[j]) {
							letterCount[j] += 1;
							totalCapLetters += 1;
						}
					}
			}
		} finally {
			s.close();
		}
	}

//method that finds the most frequent letter and its in the array as an integer, thus by inserting this integer into the alphabet array we can find what letter it is. By finding the most frequent letter in a loop that starts at the end of the alphabet, even if there are multiple letters that appear the most, this method chooses the one that appears latest in the alphabet as it starts looking through the array from the end not the beginning
	public int mostFrequent() {
		int max = 0;
		int pos = 0;
		for (int i = letterCount.length - 1; i >= 0; i--) {
			if (letterCount[i] > max) {
				max = letterCount[i];
				pos = i;
			}
		}
		return pos;

	}

//method that writes letter analysis into output file. 
	public void writeF(String fileName) {
		FileWriter fw = null;
		StringBuilder newFileName = new StringBuilder(fileName); // prepares the output file name
		newFileName.setCharAt(fileName.length() - 1, 'F');
		try {
			fw = new FileWriter(new File(newFileName + ".txt"));
			fw.write("LETTER ANALYSES" + "\n");
			fw.write("\n" + "Letter  Freq  Freq%  AvgFreq%    diff" + "\n");
			for (int i = 0; i < Alphabet.length; i++) {
				fw.write((String.format("   %c     %d     %.1f      %.1f      %.1f \n", Alphabet[i], letterCount[i],
						((double) letterCount[i] / (double) totalCapLetters * 100), avgCounts[i],
						(((double) letterCount[i] / (double) totalCapLetters) * 100) - avgCounts[i])));
			}
			int mostF = mostFrequent();
			fw.write("\n" + "The most frequent letter is " + Alphabet[mostF] + " at "
					+ String.format("%.1f", ((double) letterCount[mostF] / (double) totalCapLetters * 100)) + "%.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
