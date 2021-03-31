package timer;

/**
 * A timer returnin periodic values
 * @author Damien
 *
 */
public class PeriodicTimer implements Timer {

	private int period; // the period between each call
	private int next; // next value of the time
	private RandomTimer moreOrLess = null; // the random time used to alter period
	
	/**
	 * Create a Periodic Timer with the desired period
	 * 
	 * @param at time at which it repeats
	 */
	public PeriodicTimer(int at) {
		this.period = at;
		this.next = at;
	}
	
	/**
	 * Create a Periodic Timer with the desired period and a random alteration
	 * 
	 * @param at the period to use
	 * @param moreOrLess a random timer used to produce noises
	 * @deprecated use {@link MergedTimer} instead
	 */
	@Deprecated
	public PeriodicTimer(int at, RandomTimer moreOrLess) {
		this.period = at;
		this.moreOrLess = moreOrLess;
		this.next = at + (int)(this.moreOrLess.next() - this.moreOrLess.getMean());
	}
	
	/**
	 * Construct a periodic timer
	 * @param period period of the timer
	 * @param at time of the next timer
	 * 
	 */
	public PeriodicTimer(int period, int at) {
		this.period = period;
		this.next = at;
	}
	
	/**
	 * Create a Periodic Timer with the desired period and a random alteration
	 * 
	 * @param period the period to use
	 * @param at the delay before the first call, added to the randomized period
	 * @param moreOrLess random timer used to produce noises
	 * @deprecated use {@link MergedTimer} instead
	 */
	@Deprecated
	public PeriodicTimer(int period, int at, RandomTimer moreOrLess) {
		this.period = period;
		this.moreOrLess = moreOrLess;
		this.next = at + (int)(this.moreOrLess.next() - this.moreOrLess.getMean());
	}
	
	
	/**
	 * @return the period of the time
	 */
	public int getPeriod() {
		return this.period;
	}
	
	/**
	 * @return the next time
	 */
	@Override
	public Integer next() {
		
		int next =  this.next;
		
		if(this.moreOrLess != null) {
			this.next = this.period + (int)(this.moreOrLess.next() - this.moreOrLess.getMean());
		}else {
			this.next = this.period;
		}
		
		return next;
	}
	

	/**
	 * @return if it has a next time
	 */
	@Override
	public boolean hasNext() {
		return true;
	}

}
