package code.kata.trigram;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrigramAnalysis extends NGramAnalysis {

	private static final String CLASS = "TrigramAnalysis";

	final Logger logger = LoggerFactory.getLogger(TrigramAnalysis.class);

	private String mutatedText = "";

	private int wordLimit = 100;

	private int sentenceLimit = 10;

	private int wordCount = 0;

	private static final int TRIGRAM = 3;

	private List<Trigram> trigrams = new ArrayList<Trigram>();

	private static final Pattern regex = Pattern.compile("[^\\s]+");

	public TrigramAnalysis() {
	}

	public TrigramAnalysis(int wordLimit, int sentenceLimit) {
		logger.debug(CLASS + ":constructor called with wordLimit:" + wordLimit + " sentenceLimit:" + sentenceLimit);
		this.wordLimit = wordLimit;
		this.sentenceLimit = sentenceLimit;
	}

	public void createTrigramsFromTheFile(String filePath) throws InvalidInputException, IOException {
		logger.debug(CLASS + "createTrigramsFromTheFile called with " + filePath);
		if (filePath == null) {
			throw new InvalidInputException("file name is  null, not a valid input");
		}
		FileChannel fileChannel = null;
		FileInputStream fis = new FileInputStream(filePath);
		fileChannel = fis.getChannel();
		String text = "";
		long fileSize = fileChannel.size();
		long currentPosition = 0;
		while (fileSize > currentPosition) {
			text += readFile(fileChannel);
			currentPosition = fileChannel.position();
			List<String> words = splitTextIntoWords(text);
			int wordsLenght = fileSize > currentPosition ? words.size() - 1 : words.size();
			createTrigramsFromTheWords(words.subList(0, wordsLenght));
			text = words.get(wordsLenght - 1);
		}

		try {
			fis.close();
		} catch (IOException e) {
		}

	}

	public void createTrigramsFromTheText(String text) throws InvalidInputException {
		logger.debug(CLASS + "createTrigramsFromTheText called with:" + text);

		if (text == null) {
			throw new InvalidInputException("null is not a valid input");
		}

		List<String> words = splitTextIntoWords(text);

		createTrigramsFromTheWords(words);

	}

	public static final List<String> splitTextIntoWords(String text) {
		List<String> matchList = new ArrayList<String>();
		Matcher regexMatcher = regex.matcher(text);
		while (regexMatcher.find()) {
			matchList.add(regexMatcher.group());
		}

		return matchList;
	}

	public List<Trigram> getTrigrams() {
		return trigrams;
	}

	public List<String> mutateTheText() {
		logger.debug(CLASS + ":mutateTheText called ");
		List<String> mutatedTextList = new ArrayList<String>();
		int sentenceCount = 0;
		for (Trigram trigram : trigrams) {
			if (sentenceCount++ >= sentenceLimit) {
				break;
			}
			mutatedText = trigram.getKey();
			mutatedTextList.add(nextWordInThisPath(trigram.getKey()));
			mutatedText = "";
		}
		return mutatedTextList;
	}

	protected Trigram findTrigram(String key) {
		Trigram trigram = (Trigram) CollectionUtils.find(trigrams, PredicateUtils.equalPredicate(new Trigram(key, "")));
		return trigram;
	}

	private void createTrigramsFromTheWords(List<String> words) throws InvalidInputException {
		logger.debug(CLASS + "createTrigramsFromTheWords called with an array Sized:" + words.size());
		if (words.size() < TRIGRAM) {
			throw new InvalidInputException("word lenght of the text should be more than 2");
		}

		for (int i = 0; i < words.size() - 2; i++) {
			String key = words.get(i) + " " + words.get(i + 1);
			String word = words.get(i + 2);
			Trigram trigram = new Trigram(key, word);
			if (!trigrams.contains(trigram)) {
				trigrams.add(trigram);
			} else {
				trigram = findTrigram(trigram.getKey());
				trigram.addWord(word);
			}
		}
	}

	private String nextWordInThisPath(String key) {
		Trigram trigram = findTrigram(key);
		if (trigram == null || wordCount++ >= wordLimit) {
			wordCount = 0;
			return mutatedText;
		}
		String word = trigram.getNextWord();
		mutatedText += " " + word;
		String nextKey = trigram.getKey().split(" ")[1] + " " + word;
		return nextWordInThisPath(nextKey);

	}

}
