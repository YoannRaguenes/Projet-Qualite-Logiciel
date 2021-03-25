package discreteBehaviorSimulator;

/**
 * Interface for the clock observer
 */
public interface ClockObserver {
	
	/**
	 * notify the user of a clock change
	 * @param time 
	 */
	public void clockChange(int time);
	
	/**
	 * notify the user of the next clock change
	 * @param next jump 
	 */
	public void nextClockChange(int nextJump);
}
