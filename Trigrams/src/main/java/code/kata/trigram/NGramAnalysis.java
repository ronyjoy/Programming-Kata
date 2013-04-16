package code.kata.trigram;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NGramAnalysis {

	final Logger logger = LoggerFactory.getLogger(TrigramAnalysis.class);

	private static final String CLASS = "NGramAnalysis";

	public static final int BUFFER_SIZE = 1024;

	private static final ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);

	protected String readFile(FileChannel fileChannel) {
		logger.debug(CLASS + " readFile called with fileChannel");
		String text = "";
		try {
			int bytes = fileChannel.read(byteBuffer);
			if (bytes != -1) {
				byteBuffer.flip();
				while (byteBuffer.hasRemaining()) {
					text += (char) byteBuffer.get();
				}
				byteBuffer.clear();
			} else {
				text = null;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
}
