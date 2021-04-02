package timer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import timer.RandomTimer.randomDistribution;


class PeriodicTimerTest {

	@Test
	/**
	 * Constructor test with closed to limits values
	 */
	void testCloseToLimitsConstructor() {
		PeriodicTimer periodicTimer = new PeriodicTimer(Integer.MAX_VALUE);
		Assertions.assertTrue(periodicTimer.getPeriod() == Integer.MAX_VALUE);
	}
	
	@Test
	/**
	 * first constructor test with out of the bounds values
	 */
	void testPeriodOutOfTheBoundsConstructor() {
		Assertions.assertThrows(Exception.class, () -> {
			new PeriodicTimer(-1, 1);
		});
	}
	
	@Test
	/**
	 * second constructor test with out of the bounds values
	 */
	void testAtOutOfTheBoundsConstructor() {
		Assertions.assertThrows(Exception.class, () -> {
			new PeriodicTimer(-1);
		});
	}
	
	@Test
	/**
	 * test if next time is superior to current time
	 */
	void testNextTimeSupCurrentTime() {
		int currentTime = 1;
		PeriodicTimer periodicTimer = new PeriodicTimer(currentTime);
		Assertions.assertTrue(periodicTimer.next() >= currentTime);
	}
	
	@Test
	/**
	 * first builder test and getter of period attribute
	 */
	void testPeriodicTimer() {
		PeriodicTimer pt = new PeriodicTimer(3);
		Assertions.assertEquals(pt.getPeriod(),3);
	}
	
	@Test
	/**
	 * second builder test
	 */
	void testPeriodicTimer2() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		PeriodicTimer pt = new PeriodicTimer(3, randomTimer);
		Assertions.assertEquals(pt.getPeriod(),3);
	}
	
	@Test
	/**
	 * third builder test
	 */
	void testPeriodicTimer3() {
		PeriodicTimer pt = new PeriodicTimer(3,4);
		Assertions.assertEquals(pt.getPeriod(),3);
	}
	
	@Test
	/**
	 * fourth builder test
	 */
	void testPeriodicTimer4() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		PeriodicTimer pt = new PeriodicTimer(4, 4, randomTimer);
		Assertions.assertEquals(pt.getPeriod(),4);
	}
	
	@Test
	/**
	 * function next test
	 */
	void testNext() {
		PeriodicTimer pt = new PeriodicTimer(3,4);
		Assertions.assertEquals(pt.next(),4);
	}
	
	@Test
	/**
	 * function hasNext test
	 */
	void testHasNext() {
		PeriodicTimer pt = new PeriodicTimer(3,4);
		Assertions.assertTrue(pt.hasNext());
	}
	
}
