package timer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import timer.RandomTimer.randomDistribution;

class RandomTimerTest {

	@Test
	void testFirstConstructor() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		Assertions.assertNotNull(randomTimer.getDistributionParam());
	}
	
	@Test
	void testSecondConstructor() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.GAUSSIAN,-10, 10); 
		Assertions.assertNotNull(randomTimer.getDistribution());
	}
	
	@Test
	void testGetDistribution() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		Assertions.assertEquals(randomTimer.getDistribution(),randomDistribution.EXP.name());
	}
	
	@Test
	void testMean() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		Assertions.assertTrue(randomTimer.getMean() == 1);
	}
	
	@Test
	void testHasNext() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		Assertions.assertTrue(randomTimer.hasNext());
	}
	
	@Test
	void testOutOfBoundsPoissonConstructor() throws Exception {
		Assertions.assertThrows(Exception.class, () -> {
			 new RandomTimer(randomDistribution.POISSON, 0);
		  });
	}
	
	@Test
	void testCLoseToLimitPoisson() throws Exception{
		RandomTimer randomTimer = new RandomTimer(randomDistribution.POISSON, Double.MIN_VALUE);
		Assertions.assertNotNull(randomTimer.getDistributionParam());
	}
	
	@Test
	void testCLoseToLimitGaussian() throws Exception{
		RandomTimer randomTimer = new RandomTimer(randomDistribution.GAUSSIAN,  Integer.MAX_VALUE, Integer.MAX_VALUE);
		Assertions.assertNotNull(randomTimer.getDistributionParam());
	}
	
	@Test
	void testNextExp() throws Exception{
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		Assertions.assertTrue(randomTimer.next() != -1);
	}
	
	@Test
	void testNextPossibilist() throws Exception{
		RandomTimer randomTimer = new RandomTimer(randomDistribution.POSIBILIST, -1, 1);
		Assertions.assertTrue(randomTimer.next() != -1);
	}
	
	@Test
	void testNextPoisson() throws Exception{
		RandomTimer randomTimer = new RandomTimer(randomDistribution.POISSON, 1);
		Assertions.assertTrue(randomTimer.next() != -1);
	}
	
	@Test
	void testNextGaussian() throws Exception{
		RandomTimer randomTimer = new RandomTimer(randomDistribution.GAUSSIAN,-1, 1);
		Assertions.assertTrue(randomTimer.next() != -1);
	}
	
	@Test
	void testString2Distribution(){
		Assertions.assertTrue(RandomTimer.string2Distribution("poisson")== randomDistribution.POISSON);
		Assertions.assertTrue(RandomTimer.string2Distribution("exp") == randomDistribution.EXP);
		Assertions.assertTrue(RandomTimer.string2Distribution("posibilist") == randomDistribution.POSIBILIST);
		Assertions.assertTrue(RandomTimer.string2Distribution("gaussian") == randomDistribution.GAUSSIAN);
	}
	
	@Test
	void testDistribution2String() {
		Assertions.assertEquals(RandomTimer.distribution2String(randomDistribution.GAUSSIAN), "GAUSSIAN");
		Assertions.assertEquals(RandomTimer.distribution2String(randomDistribution.EXP), "EXP");
		Assertions.assertEquals(RandomTimer.distribution2String(randomDistribution.POSIBILIST), "POSIBILIST");
		Assertions.assertEquals(RandomTimer.distribution2String(randomDistribution.POISSON), "POISSON");
	}


}
