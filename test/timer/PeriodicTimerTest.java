package timer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PeriodicTimerTest {

	@Test
	void testCloseToLimitsConstructor() {
		PeriodicTimer periodicTimer = new PeriodicTimer(Integer.MAX_VALUE);
		Assertions.assertTrue(periodicTimer.getPeriod() == Integer.MAX_VALUE);
	}
	
	@Test
	void testPeriodOutOfTheBoundsConstructor() {
		Assertions.assertThrows(Exception.class, () -> {
			new PeriodicTimer(-1, 1);
		});
	}
	
	@Test
	void testAtOutOfTheBoundsConstructor() {
		Assertions.assertThrows(Exception.class, () -> {
			new PeriodicTimer(-1);
		});
	}
	
	@Test
	void testNextTimeSupCurrentTime() {
		int currentTime = 1;
		PeriodicTimer periodicTimer = new PeriodicTimer(currentTime);
		Assertions.assertTrue(periodicTimer.next() >= currentTime);
	}
	

}
