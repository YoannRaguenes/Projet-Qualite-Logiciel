package timer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OneShotTimerTest {

	@Test
	void testCloseToLimitsConstructor() {
		OneShotTimer oneShotTimer = new OneShotTimer(Integer.MAX_VALUE);
		Assertions.assertTrue(oneShotTimer.next() == Integer.MAX_VALUE);
	}
	
	@Test
	void testOutOfTheBoundsConstructor() {
		Assertions.assertThrows(Exception.class, () -> {
			new OneShotTimer(-1);
		});
	}
	
	@Test
	void testDoubleNext() {
		OneShotTimer oneShotTimer = new OneShotTimer(1);
		oneShotTimer.next();
		Assertions.assertNull(oneShotTimer.next());
	}
	
	@Test
	void testNextTimeSupCurrentTime() {
		int currentTime = 1;
		OneShotTimer oneShotTimer = new OneShotTimer(currentTime);
		Assertions.assertTrue(oneShotTimer.next()>= currentTime);
	}

}
