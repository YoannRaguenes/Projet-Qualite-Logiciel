package timer;

public class MergedTimer implements Timer{
	
	private Timer timer1;
	private Timer timer2;

	
	/**
	 * Construct a merged timer (addition of 2 timer)
	 * @param timer1 first timer
	 * @param timer2 second timer
	 */
	public MergedTimer(Timer timer1, Timer timer2) {
		this.timer1 = timer1;
		this.timer2 = timer2;
	}
	/**
	 * @return if it has a next time
	 */
	@Override
	public boolean hasNext() {
		return (this.timer1.hasNext() && this.timer2.hasNext());
	}

	/**
	 * @return the delay time
	 */
	@Override
	public Integer next() {
		if(this.hasNext()) {
			return this.timer1.next() + this.timer2.next();
		}
		return null;
	}

}
