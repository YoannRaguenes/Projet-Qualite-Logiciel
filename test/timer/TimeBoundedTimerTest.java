package timer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import timer.RandomTimer.randomDistribution;

class TimeBoundedTimerTest {

	@Test
	void testFirstConstructor() throws Exception {
		TimeBoundedTimer timeBoundedTimer = new TimeBoundedTimer(new RandomTimer(randomDistribution.EXP, 1), 0, 100);
		Assertions.assertTrue(timeBoundedTimer.hasNext());
		Assertions.assertNotNull(timeBoundedTimer.next());
	}
	
	@Test
	void testSecondConstructor() throws Exception {
		TimeBoundedTimer timeBoundedTimer = new TimeBoundedTimer(new RandomTimer(randomDistribution.EXP, 1), 0);
		Assertions.assertTrue(timeBoundedTimer.hasNext());
		Assertions.assertNotNull(timeBoundedTimer.next());
	}
	
	@Test
	void testEndTimeInfStartTimeConstructor() throws Exception {
		TimeBoundedTimer timeBoundedTimer = new TimeBoundedTimer(new RandomTimer(randomDistribution.EXP, 1), 100, 0);
		Assertions.assertNotNull(timeBoundedTimer.hasNext());
		Assertions.assertNotNull(timeBoundedTimer.next());
	}

}
