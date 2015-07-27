import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

// Given   a   large   file   that   does   not   fit   in   memory   (say   10GB),   find   the   top   100000
// most   frequent   phrases.   The   file   has   50   phrases   per   line   separated   by   a   pipe   (|).   
// Assume   that   the   phrases   do   not   contain   pipe.
// Example   line   may   look   like:   Foobar   Candy   |   Olympics   2012   |   PGA   |   CNET   | Microsoft   Bing 
// The   above   line   has   5   phrases   in   visible   region.
public class FileParser {

	private final Map<String, Integer> frequentPhrases = new TreeMap<>();

	public static void main(String[] args) {
		new FileParser().find50MostUsedPhrases();
		System.exit(0);
	}

	private void find50MostUsedPhrases() {
		File file = new File("test-file");

		try (BufferedReader reader = new BufferedReader(new FileReader(
				"test-file"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				readIndividualLine(line);
			}
			printMostUsedPhrases();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void readIndividualLine(String line) {
		String[] lineSplit = line.trim().split("\\|");
		for (String element : lineSplit) {
			String string = element.trim();
			Integer count = frequentPhrases.get(string);
			if (count == null)
				count = 0;

			frequentPhrases.put(string, ++count);
		}
	}

	private void printMostUsedPhrases() {
		System.out.println("The 50 Most used Prases are : ");
		SortedSet<Map.Entry<String, Integer>> sortedEntries = new TreeSet<>(
				new Comparator<Map.Entry<String, Integer>>() {
					@Override
					public int compare(Map.Entry<String, Integer> m1,
							Map.Entry<String, Integer> m2) {
						int res = m2.getValue().compareTo(m1.getValue());
						return res != 0 ? res : -1;
					}
				});
		sortedEntries.addAll(frequentPhrases.entrySet());

		Iterator<Entry<String, Integer>> it = sortedEntries.iterator();
		int count = 1;
		while (it.hasNext()) {
			Entry<String, Integer> entry = it.next();
			System.out.println(count++ + " ) " + entry.getKey() + " : "
					+ entry.getValue() + " total entries");
			if (count > 50)
				return;
		}

	}
}
