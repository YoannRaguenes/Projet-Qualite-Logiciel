package timer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OneShotTimerTest {

	@Test
	/**
	 * Constructor test with closed to limits values
	 */
	void testCloseToLimitsConstructor() {
		OneShotTimer oneShotTimer = new OneShotTimer(Integer.MAX_VALUE);
		Assertions.assertTrue(oneShotTimer.next() == Integer.MAX_VALUE);
	}
	
	@Test
	/**
	 * Constructor test with out of the bounds values
	 */
	void testOutOfTheBoundsConstructor() {
		Assertions.assertThrows(Exception.class, () -> {
			new OneShotTimer(-1);
		});
	}
	
	@Test
	/**
	 * test if we the next of the next of a oneShotTimer is null
	 */
	void testDoubleNext() {
		OneShotTimer oneShotTimer = new OneShotTimer(1);
		oneShotTimer.next();
		Assertions.assertNull(oneShotTimer.next());
	}
	
	@Test
	/**
	 * test if the next time is superior than current time
	 */
	void testNextTimeSupCurrentTime() {
		int currentTime = 1;
		OneShotTimer oneShotTimer = new OneShotTimer(currentTime);
		Assertions.assertTrue(oneShotTimer.next()>= currentTime);
	}
	
	@Test
	/**
	 * next function test
	 */
	void testNext() {
		OneShotTimer ost = new OneShotTimer(5);
		Assertions.assertEquals(ost.next(),5);
	}
	@Test
	/**
	 * hasNext function test
	 */
	void testHasNext() {
		OneShotTimer ost = new OneShotTimer(5);
		Assertions.assertTrue(ost.hasNext());
	}

}
