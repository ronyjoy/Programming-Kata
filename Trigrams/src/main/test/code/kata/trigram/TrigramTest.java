package code.kata.trigram;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TrigramTest {

	@Before
	public void setUp() {

	}

	@Test
	public void equalsMethodReturnTrueWhenTheCompareTrigramsWithSameKey() {

		Trigram trigram1 = new Trigram("key", "rony");
		Trigram trigram2 = new Trigram("key", "joy");
		assertTrue(trigram1.equals(trigram2));

	}

	@Test
	public void equalsMethodReturnFalseWhenTheCompareTrigramsWithDifferentKey() {

		Trigram trigram1 = new Trigram("key", "rony");
		Trigram trigram2 = new Trigram("key1", "rony");
		assertFalse(trigram1.equals(trigram2));
	}

	@Test
	public void addWordsToExistingTrigramSuccess() {
		Trigram trigram = new Trigram("key", "rony");
		trigram.addWord("joy");
		assertEquals("rony", trigram.getNextWord());
		assertEquals("joy", trigram.getNextWord());

	}

	@Test
	public void readWordsFromTrigramResetsThePointerToTheBeginingWhenItReachedTheEnd() {
		Trigram trigram = new Trigram("key", "rony");
		trigram.addWord("joy");
		assertEquals("rony", trigram.getNextWord());
		assertEquals("joy", trigram.getNextWord());
		assertEquals("rony", trigram.getNextWord());

	}
}
