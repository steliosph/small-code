package com.stelios.code;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class StackMinFunctionTest {

	private StackMinFunction stack_;

	@Before
	public void beforeClass() {
		stack_ = new StackMinFunction();
	}

	@Test
	public void testSizeOfMinStackWithPush() {
		int randomNumber = new Random().nextInt(1000) + 1;
		for (int i = 1; i <= randomNumber; i++) {
			stack_.push(i);
		}
		Assert.assertEquals(randomNumber, stack_.size());
	}

	@Test
	public void testSizeOfMinStackWithPop() {
		int pushNumber = new Random().nextInt(600) + 400;
		for (int i = 1; i <= pushNumber; i++)
			stack_.push(i);

		int popNumber = new Random().nextInt(400);
		for (int i = 0; i < popNumber; i++)
			stack_.pop();

		Assert.assertEquals(pushNumber - popNumber, stack_.size());
	}

	@Test
	public void testMinWithIncrementalValues() {
		for (int i = 1; i < 100; i++)
			stack_.push(i);

		Integer min = new Integer(1);
		Assert.assertEquals(min, stack_.min());
	}

	@Test
	public void testMinWithDecrementalValues() {
		for (int i = 100; i >= 1; i--)
			stack_.push(i);

		Integer min = new Integer(1);
		Assert.assertEquals(min, stack_.min());
	}

	@Test
	public void testMinWithPopValues() {
		stack_.push(10);
		stack_.push(7);
		stack_.push(8);
		stack_.push(6);
		stack_.push(100);

		Assert.assertEquals(Integer.valueOf(6), stack_.min());
		stack_.pop();
		Assert.assertEquals(Integer.valueOf(6), stack_.min());
		stack_.pop();
		Assert.assertEquals(Integer.valueOf(7), stack_.min());
		stack_.pop();
		Assert.assertEquals(Integer.valueOf(7), stack_.min());
		stack_.pop();
		Assert.assertEquals(Integer.valueOf(10), stack_.min());
	}

}
