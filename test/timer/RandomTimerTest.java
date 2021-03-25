package timer;

import org.junit.jupiter.api.Test;

import timer.RandomTimer.randomDistribution;

class RandomTimerTest {

	@Test
	void testFirstConstructor() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		randomTimer.getDistributionParam();
	}
	
	

}
