package timer;

import java.util.Random;
import java.util.Vector;

/**
 * A time giving random values based on a given distributiuon
 * @author Flavien Vernier
 *
 */
public class RandomTimer implements Timer {
	
	/**
	 * Types of distributions that can be used by a {@link RandomTimer}
	 */
	public static enum randomDistribution {
		/**
		 * Poisson distribution
		 * @see <a href="https://en.wikipedia.org/wiki/Poisson_distribution"><i>Poisson distribution</i> on Wikipedia</a>
		 */
		POISSON,
		/**
		 * Exponential distribution
		 * @see <a href="https://en.wikipedia.org/wiki/Exponential_distribution"><i>Exponential distribution</i> on Wikipedia</a>
		 */
		EXP,
		/**
		 * Uniform distribution using {@link Random#nextDouble()}
		 */
		POSIBILIST,
		/**
		 * Gaussian distribution
		 * @see <a href="https://en.wikipedia.org/wiki/Normal_distribution"><i>Normal distribution</i> on Wikipedia</a>
		 */
		GAUSSIAN;
	}
	
	//private static String randomDistributionString[] = {"POISSON", "EXP", "POSIBILIST", "GAUSSIAN"};
	
	private Random r = new Random(); // The random random number generator
	private randomDistribution distribution; // the distribution formula used by this timer
	private double rate; // rate parameter of the exponential distribution
	private double mean; // mean of the distribution
	private double lolim; // minimum value returned by gaussian and poibilist timers
	private double hilim;  // maximum value returned by gaussian and posibilist timers
	//private int width; 
	
	
	/**
	 * Find a distribution matching the given name
	 * @param distributionName the name of the distribution
	 * @throws IllegalArgumentException if the distribution does not exist
	 * @return the distribution matching the name
	 * @see randomDistribution
	 */
	public static randomDistribution string2Distribution(String distributionName){
		return RandomTimer.randomDistribution.valueOf(RandomTimer.randomDistribution.class, distributionName.toUpperCase());
	}	
	
	/**
	 * Give the name of a distribution
	 * @param distribution the distribution to get the name from
	 * @return the name of the distribution
	 */
	public static String distribution2String(randomDistribution distribution){
		return distribution.name();
	}
	
	/**
	 * Construct a randomTimer for {@link randomDistribution#EXP exponential} and {@link randomDistribution#POISSON poisson} distributions
	 * @param distribution distribution followed by the timer
	 * @param param param according to the given distribution
	 * @throws Exception if the given distribution is not one of the previously mentionned distributions
	 */
	public RandomTimer(randomDistribution distribution, double param) throws Exception{
		if(distribution == randomDistribution.EXP ){
			this.distribution = distribution;
			this.rate = param;
			this.mean = 1/param;
			this.lolim = 0;
			this.hilim = Double.POSITIVE_INFINITY;
		}else if(distribution == randomDistribution.POISSON){
			this.distribution = distribution;
			this.rate = Double.NaN;
			this.mean = param;
			this.lolim = 0;
			this.hilim = Double.POSITIVE_INFINITY;
		}else{
			throw new Exception("Bad Timer constructor for selected distribution");
		}
	}
	
	/**
	 * Construct a randomTimer for {@link randomDistribution#POSIBILIST posibilist} and {@link randomDistribution#GAUSSIAN gaussian} distributions
	 * @param distribution distribution followed by the timer
	 * @param lolim min value of the distribution
	 * @param hilim max value of the distribution
	 * @throws Exception if the given distribution is not one of the previously mentionned distributions
	 */
	public RandomTimer(randomDistribution distribution, int lolim, int hilim) throws Exception{
		if(distribution == randomDistribution.POSIBILIST || distribution == randomDistribution.GAUSSIAN){
			this.distribution = distribution;
			this.mean = lolim + (hilim - lolim)/2;
			this.rate = Double.NaN;
			this.lolim = lolim;
			this.hilim = hilim;
		}else{
			throw new Exception("Bad Timer constructor for selected distribution");
		}
	}
	
	/** 
	 * Get the name of the distribution used by the time
	 * @return the distribution name
	 * 
	 */
	public String getDistribution(){
		return this.distribution.name();
	}
	
	/** 
	 * Get the distribution parameters 
	 * @return a string formated as <i>key: value</i> for each parametter of the distribution or <i>null</i> if the distribution is null (impossible)
	 */
	public String getDistributionParam() {
		if(distribution == randomDistribution.EXP ){
			return "rate: " + this.rate;
		}else if(distribution == randomDistribution.POISSON){
			return "mean: " + this.mean;
		}else if(distribution == randomDistribution.POSIBILIST || distribution == randomDistribution.GAUSSIAN){
			return "lolim: " + this.lolim + " hilim: " + this.hilim;
		}
		
		return "null";
	}
	
	/** 
	 * Get the mean of the distribution
	 * @return the mean 
	 */
	public double getMean(){
		return this.mean;
	}
	
	/**
	 * Overides {@link Object#toString()}
	 * @return a string representing the timer
	 */
	public String toString(){
		String s = this.getDistribution();
		switch (this.distribution){
		case POSIBILIST :
			s += " LoLim:" + this.lolim + " HiLim:" + this.hilim;
			break;
		case POISSON :
			s += " mean:" + this.mean;
			break;
		case EXP :
			s += " rate:" + this.rate;
			break;
		case GAUSSIAN :
			s += " LoLim:" + this.lolim + " HiLim:" + this.hilim;
			break;
		}
		
		return s;
	}
	

	/* (non-Javadoc)
	 * @see methodInvocator.Timer#next()
	 * @return the next time according to the distribution type
	 */
	@Override
	public Integer next(){
		switch (this.distribution){
		case POSIBILIST :
			return this.nextTimePosibilist();
		case POISSON :
			return this.nextTimePoisson();
		case EXP :
			return this.nextTimeExp();
		case GAUSSIAN :
			return this.nextTimeGaussian();
		}
		return -1; // Theoretically impossible !!!
	}
	
	/*
	 * Equivalent to methodInvocator.RandomTimer#next()
	 * 
	 * @param since has no effect
	 * 
	 * @see methodInvocator.RandomTimer#next(int)
	 */
	/*@Override
	public Integer next(int since){
		return this.next();
	}*/
	
	/**
	 * Get the next time a possibilist distribution should return
	 * @return next time
	 */
	private int nextTimePosibilist(){
	    return (int)this.lolim + (int)(this.r.nextDouble() * (this.hilim - this.lolim));
	}
	
	/**
	 * Get the next time a possibilist distribution should return
	 * @return next time
	 */
	private int nextTimeExp(){
	    return (int)(-Math.log(1.0 - this.r.nextDouble()) / this.rate);
	}
	
	
	/**
	 * Get the next time a poisson distribution should return
	 * @return next time
	 */
	private int nextTimePoisson() {
	    
	    double L = Math.exp(-this.mean);
	    int k = 0;
	    double p = 1.0;
	    do {
	        p = p * this.r.nextDouble();
	        k++;
	    } while (p > L);
	    return k - 1;
	}   		
	    
	/**
	 * Get the next time a gaussian distribution should return
	 * @return next time
	 */
	private int nextTimeGaussian(){
		return (int)this.lolim + (int)((this.r.nextGaussian() + 1.0)/2.0 * (this.hilim - this.lolim));
	}
	
	@Override
	public boolean hasNext() {
		return true;
	}
}
