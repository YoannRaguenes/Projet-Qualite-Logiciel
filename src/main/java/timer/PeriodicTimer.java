package timer;

public class PeriodicTimer implements Timer {

	private int period;
	private int next;
	private RandomTimer moreOrLess = null;
	
	/**
	 * Construct a periodic timer
	 * @param at time at which it repeats
	 * 
	 */
	public PeriodicTimer(int at) {
		this.period = at;
		this.next = at;
	}
	
	/**
	 * @param at time at which it repeats
	 * @param moreOrLess noises of the period
	 * 
	 * use MergedTimer instead
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
	 * @param period period of the timer
	 * @param at time used to calculate the next timer
	 * @param moreOrLess noises
	 * 
	 * use MergedTimer instead
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
	
	/*@Override
	public Integer next(int since) {
		
		int next = (this.at - (since % this.period) + this.period) % this.period;
		
		if(this.moreOrLess != null) {
			next += this.moreOrLess.next() - this.moreOrLess.getMean();
			this.next = this.period * 2 - next;
		}else {
			this.next = this.period;
		}
		
		return next;
	}*/

	/**
	 * @return if it has a next time
	 */
	@Override
	public boolean hasNext() {
		return true;
	}

}
