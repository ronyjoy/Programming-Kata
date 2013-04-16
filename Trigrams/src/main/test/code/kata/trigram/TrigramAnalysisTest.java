package code.kata.trigram;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TrigramAnalysisTest {

	private TrigramAnalysis trigramAnalysis;

	private static final String largeFilePath = "/Users/ronyjoy/Documents/eclipse/workspace/coding/Trigrams/src/main/resources/THE ADVENTURES OF BUSTER BEAR.txt";

	private static final String smallTestFile = "/Users/ronyjoy/Documents/eclipse/workspace/coding/Trigrams/src/main/resources/TestFile.txt";

	private static final String mutatedText = "BUSTER BEAR GOES FISHING Buster Bear yawned as he lay on his comfortable bed of leaves "
			+ "and watched the first early morning sunbeams creeping through the Green Forest to chase out the Black Shadows. Once more he yawned, "
			+ "and slowly got to his feet and shook himself. Then he walked over to a big pine-tree, stood up on his hind legs, reached as high up on the trunk of the tree as he could, "
			+ "and scratched the bark with his great claws. After that he yawned until it seemed as if his jaws would crack, and then sat down to think what";

	@Before
	public void setUp() {

	}

	@Test
	public void createTheTrigramsFromASimpleString() throws InvalidInputException {
		trigramAnalysis = new TrigramAnalysis();
		trigramAnalysis.createTrigramsFromTheText("I wish I may");
		List<Trigram> trigrams = trigramAnalysis.getTrigrams();

		assertEquals("I wish", trigrams.get(0).getKey());
		assertEquals("I", trigrams.get(0).getNextWord());

		assertEquals("wish I", trigrams.get(1).getKey());
		assertEquals("may", trigrams.get(1).getNextWord());
	}

	@Test
	public void createTheTrigramWithMultipleWordsForAKey() throws InvalidInputException {
		trigramAnalysis = new TrigramAnalysis();
		trigramAnalysis.createTrigramsFromTheText("I wish I may I wish I might");
		List<Trigram> trigrams = trigramAnalysis.getTrigrams();

		assertEquals("I wish", trigrams.get(0).getKey());
		assertEquals("I", trigrams.get(0).getNextWord());
		assertEquals("I", trigrams.get(0).getNextWord());

		assertEquals("wish I", trigrams.get(1).getKey());
		assertEquals("may", trigrams.get(1).getNextWord());
		assertEquals("might", trigrams.get(1).getNextWord());

		assertEquals("I may", trigrams.get(2).getKey());
		assertEquals("I", trigrams.get(2).getNextWord());

		assertEquals("may I", trigrams.get(3).getKey());
		assertEquals("wish", trigrams.get(3).getNextWord());

	}

	@Test(expected = InvalidInputException.class)
	public void nullInputToCreateTrigramsFromTheTextThrowsInvalidInputException() throws InvalidInputException {
		String text = null;
		trigramAnalysis = new TrigramAnalysis();
		trigramAnalysis.createTrigramsFromTheText(text);
	}

	@Test(expected = InvalidInputException.class)
	public void textLessThanThreeWordsLenghtThrowsException() throws InvalidInputException {
		trigramAnalysis = new TrigramAnalysis();
		trigramAnalysis.createTrigramsFromTheText("Hi there");
	}

	@Test
	public void mutateTheTextGeneratesTheAllDifferentPosibilitiesFromASmallText() throws InvalidInputException {
		trigramAnalysis = new TrigramAnalysis();
		trigramAnalysis.createTrigramsFromTheText("I wish I may I wish I might");
		List<String> texts = trigramAnalysis.mutateTheText();
		assertTrue(texts.size() > 0);
		for (String text : texts) {
			if (text.startsWith("I wish")) {
				assertEquals("I wish I may I wish I might", text);
			}
			if (text.startsWith("wish I")) {
				assertEquals("wish I may I wish I might", text);
			}
			if (text.startsWith("I may")) {
				assertEquals("I may I wish I may I wish I might", text);
			}
			if (text.startsWith("may I")) {
				assertEquals("may I wish I may I wish I might", text);
			}

		}

	}

	@Test
	public void mutateTextGeneratesAllDifferentPosibilitiesFromAParagraphSizedText() throws InvalidInputException {
		String input = "To generate new text from this analysis, choose an arbitrary word pair as a starting point. Use these to look up a random "
				+ "next word (using the table above) and append this new word to the text so far. This now gives you a new word pair at the end of the text, so look up a potential "
				+ "next word based on these. Add this to the list, and so on. In the previous example, we could start with \"I may\". The only possible next word is \"I\", so now we have:";

		trigramAnalysis = new TrigramAnalysis();
		trigramAnalysis.createTrigramsFromTheText(input);
		List<String> texts = trigramAnalysis.mutateTheText();
		assertTrue(texts.size() > 0);
		assertEquals(input, texts.get(0));

	}

	@Test
	public void readfileSuccess() throws FileNotFoundException {
		trigramAnalysis = new TrigramAnalysis(300, 20);
		FileChannel fileChannel = null;
		FileInputStream fis = new FileInputStream(smallTestFile);
		fileChannel = fis.getChannel();
		String text = trigramAnalysis.readFile(fileChannel);
		assertEquals("This is test file used to test the file channel reader and createTrigramFromTheFile.", text);
		try {
			fis.close();
		} catch (IOException e) {
		}

	}

	@Test
	public void createTrigramFromASmallFile() throws InvalidInputException, IOException {
		trigramAnalysis = new TrigramAnalysis(300, 20);
		trigramAnalysis.createTrigramsFromTheFile(smallTestFile);
		List<Trigram> trigrams = trigramAnalysis.getTrigrams();
		assertEquals("This is", trigrams.get(0).getKey());
		assertEquals("test", trigrams.get(0).getNextWord());

		assertEquals("is test", trigrams.get(1).getKey());
		assertEquals("file", trigrams.get(1).getNextWord());

		assertEquals("test file", trigrams.get(2).getKey());
		assertEquals("used", trigrams.get(2).getNextWord());

		assertEquals("file used", trigrams.get(3).getKey());
		assertEquals("to", trigrams.get(3).getNextWord());

		assertEquals("used to", trigrams.get(4).getKey());
		assertEquals("test", trigrams.get(4).getNextWord());

		assertEquals("to test", trigrams.get(5).getKey());
		assertEquals("the", trigrams.get(5).getNextWord());

		assertEquals("test the", trigrams.get(6).getKey());
		assertEquals("file", trigrams.get(6).getNextWord());

		assertEquals("the file", trigrams.get(7).getKey());
		assertEquals("channel", trigrams.get(7).getNextWord());

		assertEquals("file channel", trigrams.get(8).getKey());
		assertEquals("reader", trigrams.get(8).getNextWord());

	}

	@Test
	public void mutateTextFromTheFile() throws InvalidInputException, IOException {
		trigramAnalysis = new TrigramAnalysis(300, 15);
		trigramAnalysis.createTrigramsFromTheFile(smallTestFile);
		List<String> texts = trigramAnalysis.mutateTheText();
		assertTrue(texts.size() == 11);
		for (String text : texts) {
			if (text.startsWith("This is"))
				assertEquals("This is test file used to test the file channel reader and createTrigramFromTheFile.", text);

		}
	}

	// @Ignore
	@Test
	public void mutateTextFromALargeFileInput() throws InvalidInputException, IOException {
		trigramAnalysis = new TrigramAnalysis(100, 2);
		trigramAnalysis.createTrigramsFromTheFile(largeFilePath);
		List<String> texts = trigramAnalysis.mutateTheText();
		for (String text : texts) {
			if (text.startsWith("BUSTER BEAR GOES"))
				assertEquals(mutatedText, text);

			System.out.println(text);

		}

	}

	@Test
	public void splitTheTextAndIgnoreTheWhiteSpaceWords() throws InvalidInputException, IOException {
		String input = "hi     \"how are\" you 		how'z      is your baby";
		List<String> words = TrigramAnalysis.splitTextIntoWords(input);
		assertEquals("hi", words.get(0));
		assertEquals("\"how", words.get(1));
		assertEquals("are\"", words.get(2));
		assertEquals("you", words.get(3));
		assertEquals("how'z", words.get(4));
		assertEquals("is", words.get(5));
		assertEquals("your", words.get(6));
		assertEquals("baby", words.get(7));

	}

}
