package timer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import timer.RandomTimer.randomDistribution;

class TimeBoundedTimerTest {

	@Test
	/**
	 * first constructor test
	 * @throws Exception
	 */
	void testFirstConstructor() throws Exception {
		TimeBoundedTimer timeBoundedTimer = new TimeBoundedTimer(new RandomTimer(randomDistribution.EXP, 1), 0, 100);
		Assertions.assertTrue(timeBoundedTimer.hasNext());
		Assertions.assertNotNull(timeBoundedTimer.next());
	}
	
	@Test
	/**
	 * second constructor test
	 * @throws Exception
	 */
	void testSecondConstructor() throws Exception {
		TimeBoundedTimer timeBoundedTimer = new TimeBoundedTimer(new RandomTimer(randomDistribution.EXP, 1), 0);
		Assertions.assertTrue(timeBoundedTimer.hasNext());
		Assertions.assertNotNull(timeBoundedTimer.next());
	}
	
	@Test
	/**
	 * test what happens if end time is inferior to start time
	 * @throws Exception
	 */
	void testEndTimeInfStartTimeConstructor() throws Exception {
		TimeBoundedTimer timeBoundedTimer = new TimeBoundedTimer(new RandomTimer(randomDistribution.EXP, 1), 100, 0);
		Assertions.assertNotNull(timeBoundedTimer.hasNext());
		Assertions.assertNotNull(timeBoundedTimer.next());
	}

}
