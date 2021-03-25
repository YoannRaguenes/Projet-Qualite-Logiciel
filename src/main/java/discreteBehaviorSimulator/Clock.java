package discreteBehaviorSimulator;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Clock that is able to count the real and virtual time
 */

public class Clock {
	private static Clock instance = null;  // initialise the clock instance to null
	
	private int time;  // time in the clock
	private int nextJump; // the next time jump 
	private ReentrantReadWriteLock lock; // allow or not read and write
	private boolean virtual; // clock can be virtual or real
	
	
	private Set<ClockObserver> observers;  // clock observers
	
	private Clock() {   // constructor
		this.time = 0;
		this.nextJump=0;
		this.lock = new ReentrantReadWriteLock();
		this.virtual = true;
		this.observers = new HashSet<ClockObserver>();
	}
	
	/**
	 * @return instance of a clock
	 */
	public static Clock getInstance() {
		if (Clock.instance == null) {
			Clock.instance = new Clock();
		}
		return Clock.instance;
	}
	
	/**
	 * add an observer 
	 * @param new observer to add
	 */
	public void addObserver(ClockObserver o) {
		this.observers.add(o);
	}
	/**
	 * remove an observer 
	 * @param observer to remove
	 */
	public void removeObserver(ClockObserver o) {
		this.observers.remove(o);
	}
	
	/**
	 * set the clock to virtual or real
	 * @param virtual (or real)
	 */
	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}
	
	/**
	 * @return virtual (or not)
	 */
	public boolean isVirtual() {
		return this.virtual;
	}
	
	/**
	 * set the next jump
	 * @param nex jump
	 */
	public void setNextJump(int nextJump) {
		this.nextJump = nextJump;
		for(ClockObserver o:this.observers) {
			o.nextClockChange(this.nextJump);
		}
	}
	/*public void setTime(int time) throws IllegalAccessException {
		this.lock.lock();
		if (this.time < time) {
			this.time = time;
			for(ClockObserver o:this.observers) {
				o.ClockChange();
			}
		}else{
			this.lock.unlock();
			throw new IllegalAccessException("Set time error, cannot go back to the past !!!");
		}
		this.lock.unlock();
	}*/
	
	/**
	 * increase the time on clock 
	 * @param ammount of time to increase
	 * @throws exception to notify if the time is not equal to the next jump
	 */
	public void increase(int time) throws Exception {

		this.lockWriteAccess();

		if(time != this.nextJump) {
			throw new Exception("Unexpected time change");
		}
		this.time += time;
		for(ClockObserver o:this.observers) {
			o.clockChange(this.time);
		}
		this.unlockWriteAccess();
	}
	/**
	 * @return virtual or real time
	 */
	public long getTime() {
		if(this.virtual) {
			return this.time;
		}else {
			return new Date().getTime();
		}
	}
	/**
	 * lock the read access
	 */
	public void lockReadAccess() {
		this.lock.readLock().lock();
	}
	
	/**
	 * unlock the read access
	 */
	public void unlockReadAccess() {
		this.lock.readLock().unlock();
	}
	/**
	 * lock the write access
	 */
	public void lockWriteAccess() {
		this.lock.writeLock().lock();
	}
	/**
	 * unlock the read access
	 */
	public void unlockWriteAccess() {
		this.lock.writeLock().unlock();		
	}
	/**
	 * convert the time into string 
	 */
	public String toString() {
		return ""+this.time;
	}
}
