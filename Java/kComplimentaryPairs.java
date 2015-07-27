import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class kComplimentaryPairs {

	private Integer kValue = null;
	private final int arrayValues[] = { -1, 2, 2, 4, 3, 3, 3, 7 };

	public static void main(String[] args) {
		kComplimentaryPairs complimentary = new kComplimentaryPairs(args);
		complimentary.findkComplimentaryPairs();
		complimentary.findkComplimentaryPairsHashMap();
		System.exit(0);
	}

	public kComplimentaryPairs(String[] args) {
		if (args.length == 0)
			showErrorAndExit();
		String value = args[0];
		if (value == null)
			showErrorAndExit();
		try {
			kValue = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			showErrorAndExit();
		}
	}

	private void showErrorAndExit() {
		System.err
				.println("Could not check the K-Complimentary Value. No Value was provided.");
		System.err
				.println(" * kComplimentaryPairs 4 : Enter the integer to be checked");
		System.exit(1);
	}

	// This will produce O(n log(n)) value.
	// Although we sort them and break out when the sum is higher from our
	// values the speed can be made better by using HashMaps as they have an
	// O(n) access
	public void findkComplimentaryPairs() {
		// Sort the array to make the process fast
		Arrays.sort(arrayValues);
		int valuesFound = 0;
		String v = "";
		for (int first = 0; first < arrayValues.length; first++) {
			for (int second = 0; second < arrayValues.length; second++) {
				if (first == second)
					continue;

				int sum = arrayValues[first] + arrayValues[second];
				// is Sum is greater than our value then skip to first
				if (sum > kValue)
					break;

				if (sum == kValue) {
					v += "{" + arrayValues[first] + "," + arrayValues[second]
							+ "}";
					valuesFound++;
				}
			}
		}
		System.out.println("Found " + valuesFound + " " + v
				+ " k-complimentary values for " + kValue);
	}

	public void findkComplimentaryPairsHashMap() {
		HashMap<Integer, Integer> complimentaryOccurs = new HashMap<>();
		// Add the total values of each value in the array in our hashmap
		for (int i : arrayValues) {
			if (complimentaryOccurs.get(i) == null)
				complimentaryOccurs.put(i, 1);
			else
				complimentaryOccurs.put(i, complimentaryOccurs.get(i) + 1);
		}
		int valuesFound = 0;
		String v = "";

		Iterator<Entry<Integer, Integer>> it = complimentaryOccurs.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<Integer, Integer> item = it.next();
			Integer key = item.getKey();
			Integer count = item.getValue();
			int keyRequired = kValue - key;
			if (complimentaryOccurs.containsKey(keyRequired)) {
				if (key == keyRequired)
					if (count == 1)
						continue;
					else {
						int singleCount = (complimentaryOccurs.get(key) - 1)
								* complimentaryOccurs.get(keyRequired);
						v += singleCount + "-{" + key + "," + keyRequired + "}";
						valuesFound += singleCount;
						continue;
					}

				v += "{" + key + "," + keyRequired + "}";
				valuesFound += complimentaryOccurs.get(key)
						* complimentaryOccurs.get(keyRequired);
			}
		}

		System.out.println("Found " + valuesFound + " " + v
				+ " k-complimentary values for " + kValue);
	}
}
