package code.kata.trigram;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Trigram {

	private String key;

	private List<String> words = new ArrayList<String>();

	private int current = 0;

	public Trigram(String key, String word) {
		this.key = key;
		this.words.add(word);

	}

	public void addWord(String word) {
		this.words.add(word);
	}

	public String getKey() {
		return key;
	}

	public String getNextWord() {
		if (current > words.size() - 1) {
			current = 0;
		}
		return words.get(current++);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Trigram trigram = (Trigram) obj;
		return new EqualsBuilder().append(key, trigram.key).isEquals();
	}

	@Override
	public String toString() {
		return "Trigram [key=" + key + ", words=" + words + ", current=" + current + "]";
	}

}
