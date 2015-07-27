
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Palindrome {

	enum CheckedVariables {
		CORRECT, WRONG;
	}

	private final List<String> palindromeStrings_ = new ArrayList<>();
	private final HashMap<CheckedVariables, List<String>> checkedPalindromes_ = new HashMap<>();

	public static void main(String[] args) {
		Palindrome palindrome = new Palindrome(args);
		palindrome.findPalindrome();
		palindrome.displayMessage();
		System.exit(0);
	}

	public Palindrome(String[] args) {
		for (String arg : args) {
			palindromeStrings_.add(arg);
		}

		if (palindromeStrings_.isEmpty()) {
			System.err
					.println("Could not check for Palindromes. No Value was provided.");
			System.err
					.println(" * Palindrome string string : Enter the strings to be checked");
			System.exit(1);
		}
		checkedPalindromes_.put(CheckedVariables.CORRECT,
				new ArrayList<String>());
		checkedPalindromes_
				.put(CheckedVariables.WRONG, new ArrayList<String>());
	}

	public void findPalindrome() {
		for (String palindromeCheckString : palindromeStrings_) {
			// Could have used StringUtils but would require an import
			// String reverseString =
			// StringUtils.reverse(palindromeCheckString);
			String reverseString = new StringBuilder(palindromeCheckString)
					.reverse().toString();
			if (palindromeCheckString.equals(reverseString)) {
				List<String> list = checkedPalindromes_
						.get(CheckedVariables.CORRECT);
				list.add(palindromeCheckString);
				checkedPalindromes_.put(CheckedVariables.CORRECT, list);
			} else {
				List<String> list = checkedPalindromes_
						.get(CheckedVariables.WRONG);
				list.add(palindromeCheckString);
				checkedPalindromes_.put(CheckedVariables.WRONG, list);
			}
		}
	}

	public void displayMessage() {
		List<String> correctPalindromes = checkedPalindromes_
				.get(CheckedVariables.CORRECT);
		if (!correctPalindromes.isEmpty()) {
			System.out.println("The following string are palindromes : ");
			for (String string : correctPalindromes) {
				System.out.println(" * " + string);
			}
		} else {
			System.err
					.println(" No String have been found to be a palindrome.");
		}
		List<String> notPalindromes = checkedPalindromes_
				.get(CheckedVariables.WRONG);
		if (!notPalindromes.isEmpty()) {
			System.out.println("The following string are NOT palindromes : ");
			for (String string : notPalindromes) {
				System.out.println(" * " + string);
			}
		}
	}
}

