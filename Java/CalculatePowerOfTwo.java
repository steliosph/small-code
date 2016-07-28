public class CalculatePowerOfTwo  {

	// Write function to calculate sum of first N powers of 2 starting from 1.
	// You shouldn't use any built-in function for calculating power.
	// Implement the most efficient solution.

	public static void main(String[] args) {
		TestExample example = new TestExample();
	}

	private long executionTime;

	private CalculatePowerOfTwo() {
		startStopWatch();
		calculateSum(21);
		getElapsedTime();

	}

	public long calculateSum(int times) {
		// The most efficient way is to calculate 2^n+1−2
		long result = 2;
		for (int count = 1; count <= times; count++)
			result *= 2;
		
		long answer = result - 2;
		System.out.println("calculateSum:" + answer);

		return answer;
	}

	private void startStopWatch() {
		executionTime = System.currentTimeMillis();
	}

	public void getElapsedTime() {
		long endTime = System.currentTimeMillis();
		System.out.println("time:" + (double) (endTime - executionTime) / (1000));
	}

}
