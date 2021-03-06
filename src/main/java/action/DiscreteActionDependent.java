package action;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.TreeSet;

import timer.Timer;

/**
 * @author flver
 *
 */
//TODO Must be refactored to be generic
public class DiscreteActionDependent implements DiscreteActionInterface {
	
	protected DiscreteAction baseAction; 					// a DiscretAction that will be put in the tree
	protected TreeSet<DiscreteAction> depedentActions;		// tree containing multiple DiscreteAction
	private Iterator<DiscreteAction> it;					// iterator of all the DiscreteAction
	protected DiscreteAction currentAction;					// the actual DiscreteAction 
	
	
	/**
	 * Construct a series of dependent actions, first action (method) called is baseMethodName, then method nextMethod() is called to select the next action. 
	 * The first currentAction is the baseAction
	 * 
	 * 
	 * @param o Object on which the method will be applied
	 * @param baseMethodName first Method of the DiscreteAction
	 * @param timerBase first Timer provides new lapsTime
	 */	
	public DiscreteActionDependent(Object o, String baseMethodName, Timer timerBase){
		this.baseAction = new DiscreteAction(o, baseMethodName, timerBase);
		this.depedentActions = new TreeSet<DiscreteAction>();
		this.it = this.depedentActions.iterator();
		this.currentAction = this.baseAction;
	}
	
	/**
	 * addDependence to the tree of dependentActions 
	 * 
	 * @param o Object on which the method will be applied
	 * @param depentMethodName Method of the DiscreteAction
	 * @param timerDependence Timer provides new lapsTime
	 */
	public void addDependence(Object o, String depentMethodName, Timer timerDependence) {
		this.depedentActions.add(new DiscreteAction(o, depentMethodName, timerDependence));
	}
	
	/*private void dates2Timalapse(TreeSet<Integer> datesOn, TreeSet<Integer> datesOff, Vector<Integer> timeLapseOn, Vector<Integer> timeLapseOff) {
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
	@SuppressWarnings("unchecked")
	public DiscreteActionDependent(Wo o, String on, TreeSet<Integer> datesOn, String off, TreeSet<Integer> datesOff){
		Vector<Integer> timeLapseOn = new Vector<Integer>();
		Vector<Integer> timeLapseOff = new Vector<Integer>();
		
		dates2Timalapse((TreeSet<Integer>)datesOn.clone(), (TreeSet<Integer>)datesOff.clone(), timeLapseOn, timeLapseOff);
		
		this.baseAction = new DiscreteAction(o, on, timeLapseOn);
		this.offAction = new DiscreteAction(o, off, timeLapseOff);
		
		if(datesOn.first() < datesOff.first()){
			this.currentAction = this.baseAction;
		}else{
			this.currentAction = this.offAction;
		}
	}
*/
	/*
	 * 
	 */
	private void reInit() {
		//this.baseAction.updateTimeLaps();
		for (Iterator<DiscreteAction> iter = this.depedentActions.iterator(); iter.hasNext(); ) {
		    DiscreteAction element = iter.next();
		    //element.updateTimeLaps();
		}		
	}
	
	/**
	 * get the nextMethod if there is not then call reInit() and set the currentAction equals to the baseAction
	 */
	public void nextMethod(){
		if (this.currentAction == this.baseAction){
			this.it = this.depedentActions.iterator();
			this.currentAction = this.it.next();
		}else if(this.currentAction == this.depedentActions.last()){
			this.currentAction = this.baseAction;
			this.reInit();
		}else {
			this.currentAction = this.it.next();
		}
	}
	
	/**
	 * 
	 * Spend the time of the actualAction 
	 *
	 * @param t int how much time you want to spend 
	 */
	public void spendTime(int t) {
		for (Iterator<DiscreteAction> iter = this.depedentActions.iterator(); iter.hasNext(); ) {
		    DiscreteAction element = iter.next();
		    element.spendTime(t);
		}
	}
	
	/**
	 * call method nextMethod()
	 */
	public void updateTimeLaps() {
		// time laps is updated at the re-initialization
		//this.currentAction.updateTimeLaps();	
		this.nextMethod();	
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
	 * test if the DiscreteActionDependant is finished or not
	 * @return Boolean false if there is more iteration and true if not
	 */
	public Boolean isEmpty() {
		return !this.hasNext();
	}
	
	/**
	 * 
	 * @return DiscreteActionInterface
	 */
	public DiscreteActionInterface next() {
		//Integer lapsTime = this.getNextLapsTime();
		Method method = this.getMethod();
		Object object = this.getObject();
		return this;
	}
	
	/**
	 * test if there is next action or not
	 * @return Boolean 
	 */
	public boolean hasNext() {
		return this.baseAction.hasNext() || !this.depedentActions.isEmpty();		
	}

}
