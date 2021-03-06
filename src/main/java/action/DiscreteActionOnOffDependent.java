package action;

import java.lang.reflect.Method;
import java.util.TreeSet;
import java.util.Vector;

import timer.DateTimer;
import timer.Timer;

/**
 * @author flver
 *
 */
public class DiscreteActionOnOffDependent implements DiscreteActionInterface {
	
	protected DiscreteActionInterface onAction; 		// the on action
	protected DiscreteActionInterface offAction;		// the off action
	protected DiscreteActionInterface currentAction;	// the current action 
	
	private Integer currentLapsTime;					// the current LapsTime
	private Integer lastOffDelay=0;						// delay initialy equals 0 
	
	/**
	 * Construct an On Off dependence, first action (method) called is On, then method nextMethod() is called to select the next action.
	 * The default behavior of nextMethod() is to switch between On and Off actions.  It can be change by overloading. 
	 * 
	 * @param o 
	 * @param on
	 * @param timerOn
	 * @param off
	 * @param timerOff
	 */
	/*public DiscreteActionOnOffDependent(Wo o, Method on, Timer timerOn, Method off, Timer timerOff){
		this.onAction = new DiscreteAction(o, on, timerOn);
		this.offAction = new DiscreteAction(o, off, timerOff);
		
		this.currentAction = this.onAction;
	}*/
	
	/**
	 * Constructor
	 * @param o Object on which methods will be applied
	 * @param on Method of the onAction
	 * @param timerOn Timer of the onAction 
	 * @param off Method of the offAction
	 * @param timerOff Timer of the offAction
	 */
	public DiscreteActionOnOffDependent(Object o, String on, Timer timerOn, String off, Timer timerOff){
		this.onAction = new DiscreteAction(o, on, timerOn);
		this.offAction = new DiscreteAction(o, off, timerOff);
		
		this.currentAction = this.offAction;
		this.currentLapsTime = 0;
	}
	
	/*
	 * 
	 */
	private void dates2Timalapse(TreeSet<Integer> datesOn, TreeSet<Integer> datesOff, Vector<Integer> timeLapseOn, Vector<Integer> timeLapseOff) {
		Vector<Integer> currentTimeLapse;
		TreeSet<Integer> currentDates;
		Integer date=0;
		if(datesOn.first()<datesOff.first()) {
			currentTimeLapse = timeLapseOn;
			currentDates = datesOn;
		}else {
			currentTimeLapse = timeLapseOff;	
			currentDates = datesOff;		
		}
		Integer nextDate;
		
		while(datesOn.size()>0 || datesOff.size()>0) {
			nextDate = currentDates.first();
		
			currentTimeLapse.add(nextDate - date);
			currentDates.remove(nextDate);
		
			date = nextDate;
			
			if(currentDates == datesOn) {
				currentDates = datesOff;
				currentTimeLapse = timeLapseOff;
			}else {
				currentDates = datesOn;
				currentTimeLapse = timeLapseOn;			
			}
		}
		
	}
	
	/**
	 * Constructor
	 *  @param o Object on which methods will be applied
	 * @param on Method of the onAction 
	 * @param datesOn Tree of multiple Timer for the onAction
	 * @param off Method of the offAction
	 * @param datesOff Tree of multiple Timer for the offAction 
	 */
	public DiscreteActionOnOffDependent(Object o, String on, TreeSet<Integer> datesOn, String off, TreeSet<Integer> datesOff){
		/*Vector<Integer> timeLapseOn = new Vector<Integer>();
		Vector<Integer> timeLapseOff = new Vector<Integer>();
		
		dates2Timalapse((TreeSet<Integer>)datesOn.clone(), (TreeSet<Integer>)datesOff.clone(), timeLapseOn, timeLapseOff);
		*/
		this.onAction = new DiscreteAction(o, on, new DateTimer(datesOn));
		this.offAction = new DiscreteAction(o, off, new DateTimer(datesOff));
		
		
		
		if(datesOn.first() < datesOff.first()){
			this.currentAction = this.onAction;
		}else{
			this.currentAction = this.offAction;
		}
	}
	
	/**
	 * set currentAction to be the next action
	 */
	public void nextAction(){
		if (this.currentAction == this.onAction){
			this.currentAction = this.offAction;
			this.currentAction = this.currentAction.next();
			this.lastOffDelay = this.currentAction.getCurrentLapsTime();
		}else{
			this.currentAction = this.onAction;
			this.currentAction = this.currentAction.next();
			this.currentAction.spendTime(this.lastOffDelay);
		}
	}
	
	/**
	 * 
	 * Spend the time of the actualAction 
	 *
	 * @param t int how much time you want to spend 
	 */
	public	void spendTime(int t) {
		this.currentAction.spendTime(t);
	}

	/**
	 * get the method of the currentAction
	 * @return Method 
	 */
	public Method getMethod() {
		return this.currentAction.getMethod();
	}

	/**
	 * get current LapsTime of the currentAction
	 * @return Integer
	 */
	public Integer getCurrentLapsTime() {
		return this.currentAction.getCurrentLapsTime();
	}

	/**
	 * get the object of the currentAction
	 * @return Object
	 */
	public Object getObject() {
		return this.currentAction.getObject();
	}

	/**
	 * compare the currentAction to a DiscreteActionInterface
	 * 
	 * @param c DiscreteActionInterface you want to compare
	 */
	public int compareTo(DiscreteActionInterface c) {
		return this.currentAction.compareTo(c);
	}
	
	/**
	 * get the next action 
	 * @return DiscreteActionInterface
	 */
	public DiscreteActionInterface next() {
		this.nextAction();
		return this;
	}
	
	/**
	 * test if there is next action or not
	 * @return Boolean 
	 */
	public boolean hasNext() {
		return this.onAction.hasNext() || this.offAction.hasNext();		
	}

	

}
