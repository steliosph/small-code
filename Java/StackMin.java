# Find the minimum entry of a stack having O(1)

package com.stelios.code;

import java.util.EmptyStackException;
import java.util.Stack;

public class StackMinFunction extends Stack<Integer> {

	private Stack<Integer> minimumValueStack_;

	public StackMinFunction() {
		minimumValueStack_ = new Stack<>();
	}

	@Override
	public Integer push(Integer item) {
		pushMinimumValueStack(item);
		return super.push(item);
	}

	private void pushMinimumValueStack(Integer currentEntry) {
		if (minimumValueStack_.size() == 0) {
			minimumValueStack_.add(currentEntry);
		} else {
			Integer topEntry = minimumValueStack_.peek();
			if (topEntry > currentEntry)
				minimumValueStack_.add(currentEntry);
			else
				minimumValueStack_.add(topEntry);
		}
	}

	@Override
	public synchronized Integer pop() {
		popMinimumValueStack();
		return super.pop();
	}

	private void popMinimumValueStack() {
		if (minimumValueStack_.size() == 0)
			throw new EmptyStackException();
		minimumValueStack_.pop();
	}

	public Integer min() {
		return minimumValueStack_.peek();
	}
}
