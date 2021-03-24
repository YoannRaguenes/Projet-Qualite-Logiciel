package action;

import java.lang.reflect.Method;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import discreteBehaviorSimulator.LogFormatter;
import timer.Timer;

/**
 * @author Tiphaine Bulou (2016)
 * @author Flavien Vernier
 *
 */

// TODO must inherit from Action
public class DiscreteAction implements DiscreteActionInterface {
	private Object object;
	private Method method;
	
	
	private Timer timmer;				// timer provides new lapsTime
	//private TreeSet<Integer> dates;	// obsolete, managed in timer 
	//private Vector<Integer> lapsTimes;// obsolete, managed in timer
	private Integer lapsTime; 			// waiting time (null if never used)
	
	private Logger logger;				// logger to write on the console

	// Constructor
	/*
	 * DiscrteAction constructor 
	 * 
	 * It setups the logger
	 * 
	 */
	private DiscreteAction() {
		// Start logger
			this.logger = Logger.getLogger("DAS");
			//this.logger = Logger.getLogger("APP");
			this.logger.setLevel(Level.ALL);
			this.logger.setUseParentHandlers(true);
			
			/*FileHandler logFile; 
			ConsoleHandler logConsole; 
			try{
				this.logFile = new FileHandler(this.getClass().getName() + ".log");
				//this.logFile.setFormatter(new SimpleFormatter());
				this.logFile.setFormatter(new LogFormatter());
				this.logConsole = new ConsoleHandler();
			}catch(Exception e){
				e.printStackTrace();
			}
			this.logger.addHandler(logFile);
			this.logger.addHandler(logConsole);*/
	}
	
	/**
	 * DiscreteAction constructor
	 *
	 *
	 * @param Object 
	 * @param String 
	 * @param Timer 
	 * @exception 
	 */
	public DiscreteAction(Object o, String m, Timer timmer){
		this();
		this.object = o;
		try{	
			this.method = o.getClass().getDeclaredMethod(m, new Class<?>[0]);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		this.timmer = timmer;
		//this.updateTimeLaps();
	}
	
	// ATTRIBUTION
	/**
	 * spendTime 
	 *
	 * A fonction that will spend the time of a DiscreteAction and that will log it 
	 *
	 * @param int  
	 */
	public void spendTime(int t) {
		Integer old = this.lapsTime;
		if(this.lapsTime != null) {
			this.lapsTime -= t;
		}
		this.logger.log(Level.FINE, "[DA] operate spendTime on  " + this.getObject().getClass().getName() + ":" + this.getObject().hashCode() + ": old time " + old + " new time " + this.getCurrentLapsTime());
		//System.out.println(         "[DA] operate spendTime on  " + this.getObject().getClass().getName() + ":" + this.getObject().hashCode() + ": old time " + old + " new time " + this.getCurrentLapsTime() + "\n");
	}

	// RECUPERATION
	/**
	 * getMethod 
	 *
	 * @return method of the DiscreteAction
	 */
	public Method getMethod(){
		return method;
	}
	/**
	 * getCurrentLapsTime 
	 *
	 * @return current laps time
	 */
	public Integer getCurrentLapsTime(){
		return lapsTime;
	}
	/**
	 * getObject 
	 *
	 * @return object of the DiscreteAction
	 */
	public Object getObject(){
		return object;
	}



	// COMPARAISON
	/**
	 * compareTo 
	 *
	 *	Compare the lapsTime of the DiscreteAction with the lapsTime of a DiscreteActionInterface
	 *
	 * @param DiscreteActionInterface
	 * @return 0 if lapsTimes are equals
	 * @return 1 if there is no lapsTime for the DiscreteAction or if it is greater than lapsTime of the DiscreteActionInterface
	 * @return -1 if there is no lapsTime for the DiscreteActionInterface or if it is greater than lapsTime of the DiscreteAction
	 */
	public int compareTo(DiscreteActionInterface c) {
		if (this.lapsTime == null) { // no lapstime is equivalent to infinity 
			return 1;
		}
		if (c.getCurrentLapsTime() == null) {// no lapstime is equivalent to infinity 
			return -1;
		}
    	if(this.lapsTime > c.getCurrentLapsTime()){
    		return 1;
    	}
    	if(this.lapsTime < c.getCurrentLapsTime()){
    		return -1;
    	}
		if(this.lapsTime == c.getCurrentLapsTime()){
			return 0;
		}
		return 0;
	}
	
	/**
	 * toString 
	 *
	 * Describes the DiscreteAction
	 *
	 * @return String that describes the DiscreteAction
	 */
	public String toString(){
		return "Object : " + this.object.getClass().getName() + "\n Method : " + this.method.getName()+"\n Stat. : "+ this.timmer + "\n delay: " + this.lapsTime;

	}
	
	/**
	 * next 
	 *
	 * Implement the next function of the Interface DiscreteActionInterface
	 * 
	 * @return DiscreteAction
	 */
	public DiscreteActionInterface next() {
		Integer old = this.lapsTime;
		this.lapsTime = this.timmer.next();
		this.logger.log(Level.FINE, "[DA] operate next on  " + this.getObject().getClass().getName() + ":" + this.getObject().hashCode() + ": old time " + old + " new time " + this.getCurrentLapsTime());
		//System.out.println("[DA] operate 'next' on " + this.getObject().getClass().getName() + ":" + this.getObject().hashCode() + ": old time " + old + " new time " + this.getCurrentLapsTime() + "\n");
		return this;
	}
	
	/**
	 * hasNext 
	 *
	 * Tell if the DiscreteAction is finish or if it has other timmers
	 * 
	 * @return Boolean True if there is more timmers and false if not 
	 */
	public boolean hasNext() {
		Boolean more=false;
		if (this.timmer != null && this.timmer.hasNext()) {
			more = true;
		}/*else if (this.dates != null) {
			more = !this.dates.isEmpty();
		}else if (this.lapsTimes != null) {
			more = !this.lapsTimes.isEmpty();
		}*/
		return more;		
	}
	

}
