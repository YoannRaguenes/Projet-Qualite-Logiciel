package timer;

public class OneShotTimer  implements Timer {
	
	private Integer at;
	private boolean hasNext;
	
	/**
	 * Construct a randomTimer
	 * @param at time of the timer 
	 */
	public OneShotTimer(int at) {
		this.at = at;
		this.hasNext = true;
	}
	
	/**
	 * @return if it has a next time 
	 */
	@Override
	public boolean hasNext() {
		return this.hasNext;
	}

	/**
	 * @return the next time 
	 */
	@Override
	public Integer next() {
		Integer next=this.at;
		this.at=null;
		this.hasNext = false;
		return next;
	}

}
