package timer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import timer.RandomTimer.randomDistribution;

class RandomTimerTest {

	@Test
	/**
	 * first constructor test
	 * @throws Exception
	 */
	void testFirstConstructor() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		Assertions.assertNotNull(randomTimer.getDistributionParam());
	}
	
	@Test
	/**
	 * second constructor test
	 * @throws Exception
	 */
	void testSecondConstructor() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.GAUSSIAN,-10, 10); 
		Assertions.assertNotNull(randomTimer.getDistribution());
	}
	
	@Test
	/**
	 * get distribution of an EXP randomTimer test
	 * @throws Exception
	 */
	void testGetDistribution() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		Assertions.assertEquals(randomTimer.getDistribution(),randomDistribution.EXP.name());
	}
	
	@Test
	/**
	 * get mean of an EXP randomTimer test
	 * @throws Exception
	 */
	void testMean() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		Assertions.assertTrue(randomTimer.getMean() == 1);
	}
	
	@Test
	/**
	 * test hasNext of an EXP randomTimer 
	 * @throws Exception
	 */
	void testHasNext() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		Assertions.assertTrue(randomTimer.hasNext());
	}
	
	@Test
	/**
	 * Constructor of a POISSON randomTimer test with out of the bounds values
	 * @throws Exception
	 */
	void testOutOfBoundsPoissonConstructor() throws Exception {
		Assertions.assertThrows(Exception.class, () -> {
			 new RandomTimer(randomDistribution.POISSON, 0);
		  });
	}
	
	@Test
	/**
	 * Constructor of a POISSON randomTimer test with closed to limits values
	 * @throws Exception
	 */
	void testCLoseToLimitPoisson() throws Exception{
		RandomTimer randomTimer = new RandomTimer(randomDistribution.POISSON, Double.MIN_VALUE);
		Assertions.assertNotNull(randomTimer.getDistributionParam());
	}
	
	@Test
	/**
	 * Constructor of a GAUSSIAN randomTimer test with closed to limits values
	 * @throws Exception
	 */
	void testCLoseToLimitGaussian() throws Exception{
		RandomTimer randomTimer = new RandomTimer(randomDistribution.GAUSSIAN,  Integer.MAX_VALUE, Integer.MAX_VALUE);
		Assertions.assertNotNull(randomTimer.getDistributionParam());
	}
	
	@Test
	/**
	 * Test if an EXP randomTimer has a next
	 * @throws Exception
	 */
	void testNextExp() throws Exception{
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);
		Assertions.assertTrue(randomTimer.next() != -1);
	}
	
	@Test
	/**
	 * Test if a POSSIBILIST randomTimer has a next
	 * @throws Exception
	 */
	void testNextPossibilist() throws Exception{
		RandomTimer randomTimer = new RandomTimer(randomDistribution.POSIBILIST, -1, 1);
		Assertions.assertTrue(randomTimer.next() != -1);
	}
	
	@Test
	/**
	 * Test if a POISSON randomTimer has a next
	 * @throws Exception
	 */
	void testNextPoisson() throws Exception{
		RandomTimer randomTimer = new RandomTimer(randomDistribution.POISSON, 1);
		Assertions.assertTrue(randomTimer.next() != -1);
	}
	
	@Test
	/**
	 * Test if a GAUSSIAN randomTimer has a next
	 * @throws Exception
	 */
	void testNextGaussian() throws Exception{
		RandomTimer randomTimer = new RandomTimer(randomDistribution.GAUSSIAN,-1, 1);
		Assertions.assertTrue(randomTimer.next() != -1);
	}
	
	@Test
	/**
	 * test the conversion of distribution from String to randomDistribution
	 */
	void testString2Distribution(){
		Assertions.assertTrue(RandomTimer.string2Distribution("poisson")== randomDistribution.POISSON);
		Assertions.assertTrue(RandomTimer.string2Distribution("exp") == randomDistribution.EXP);
		Assertions.assertTrue(RandomTimer.string2Distribution("posibilist") == randomDistribution.POSIBILIST);
		Assertions.assertTrue(RandomTimer.string2Distribution("gaussian") == randomDistribution.GAUSSIAN);
	}
	
	@Test
	/**
	 * test the conversion of distribution from randomDistribution to String
	 */
	void testDistribution2String() {
		Assertions.assertEquals(RandomTimer.distribution2String(randomDistribution.GAUSSIAN), "GAUSSIAN"); // vérifier si c'est le bon string
		Assertions.assertEquals(RandomTimer.distribution2String(randomDistribution.EXP), "EXP");
		Assertions.assertEquals(RandomTimer.distribution2String(randomDistribution.POSIBILIST), "POSIBILIST");
		Assertions.assertEquals(RandomTimer.distribution2String(randomDistribution.POISSON), "POISSON");
	}


}
