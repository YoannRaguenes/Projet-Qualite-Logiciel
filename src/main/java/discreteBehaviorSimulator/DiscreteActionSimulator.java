
package discreteBehaviorSimulator;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Vector;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import action.DiscreteAction;
import action.DiscreteActionInterface;


/**
 * Manage actions in the simulation
 * @author Tiphaine Bulou (2016)
 * @author Flavien Vernier
 */
public class DiscreteActionSimulator implements Runnable {


	private Thread t; // a thread that manages an ordered list of actions to perform and a clock to simulate a virtual time virtual time
	private boolean running = false;  // true if an action is running
	
	private Clock globalTime;  // clock with corresponds to global time
	
	private Vector<DiscreteActionInterface> actionsList = new Vector<>(); // list of actions to do and their waiting time
	
	private int nbLoop; //nbLoop defines the number of loop for the simulation, the simulation is infinite if nbLoop is negative or 0.
	private int step; // 1 if nbLoop >1 else -1
	
	private Logger logger;  // logger to write on the console
	private FileHandler logFile; // manage log writing
	private ConsoleHandler logConsole;  // manage log printing

	
	/**
	 * Used to show the logs 
	 */
	public DiscreteActionSimulator(){
	
		this.logger = Logger.getLogger("DAS");
		this.logger.setLevel(Level.ALL);
		this.logger.setUseParentHandlers(true);
		try{
			this.logFile = new FileHandler(this.getClass().getName() + ".log");
			this.logFile.setFormatter(new LogFormatter());
			this.logConsole = new ConsoleHandler();
		}catch(Exception e){
			e.printStackTrace();
		}
		this.logger.addHandler(logFile);
		this.logger.addHandler(logConsole);
		

		this.globalTime = Clock.getInstance();
		
		this.t = new Thread(this);
		this.t.setName("Discrete Action Simulator");
	}
	
	/**
	 * setter of the number of loops
	 * @param nbLoop defines the number of loop for the simulation, the simulation is infinite if nbLoop is negative or 0.
	 */
	public void setNbLoop(int nbLoop){
		if(nbLoop>0){
			this.nbLoop = nbLoop;
			this.step = 1;
		}else{ // running infinitely
			this.nbLoop = 0;
			this.step = -1;
		}
	}
	
	
	/**
	 * 
	 * Add an action to a DiscreteActionInterface if it has more elements
	 * 
	 * @param c action to add to the actionslist
	 */
	public void addAction(DiscreteActionInterface c){

		if(c.hasNext()) {
				
			this.actionsList.add(c.next()); // add to list of actions, next is call to the action exist at the first time

			
			Collections.sort(this.actionsList);  // sort the list for ordered execution 
		}
	}
	

	
	private int nextLapsTime() {
		DiscreteActionInterface currentAction = this.actionsList.get(0);
		return currentAction.getCurrentLapsTime();
	}
	/*
	 * return laps time of the running action
	 */
	private int runAction(){
		// Run the first action
		int sleepTime = 0;


		// TODO Manage parallel execution !  
		DiscreteActionInterface currentAction = this.actionsList.get(0);
		Object o = currentAction.getObject();
		Method m = currentAction.getMethod();
		sleepTime = currentAction.getCurrentLapsTime();
		
		try {
			//Thread.sleep(sleepTime); // Real time can be very slow
			Thread.yield();
			//Thread.sleep(1000); // Wait message bus sends
			if(this.globalTime!=null) {
				this.globalTime.increase(sleepTime);
			}
			m.invoke(o);
			if(this.globalTime!=null) {
				this.logger.log(Level.FINE, "[DAS] run action " + m.getName() + " on " + o.getClass().getName() + ":" + o.hashCode() + " at " + this.globalTime.getTime() + " after " + sleepTime + " time units\n");
				System.out.println("[DAS] run action " + m.getName() + " on " + o.getClass().getName() + ":" + o.hashCode() + " at " + this.globalTime.getTime() + " after " + sleepTime + " time units\n");
			}else {
				this.logger.log(Level.FINE, "[DAS] run action " + m.getName() + " on " + o.getClass().getName() + ":" + o.hashCode() + " after " + sleepTime + " time units\n");
				System.out.println("[DAS] run action " + m.getName() + " on " + o.getClass().getName() + ":" + o.hashCode() + " after " + sleepTime + " time units\n");
			
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		return sleepTime;
	}

	
	/**
	 * extract the first action from the list (the one with the lowest waiting time) by updating it
	 * @param running time of the first capsul of the list
	 * @return running time of the first capsul of the list
	 */
	private void updateTimes(int runningTimeOf1stCapsul){
		
		// update time laps off all actions
		for(int i=1 ; i < this.actionsList.size(); i++){
			//int d = this.actionsList.get(i).getLapsTime();
			//this.actionsList.get(i).setLapsTemps(d- runningTimeOf1stCapsul);
			this.actionsList.get(i).spendTime(runningTimeOf1stCapsul);
		}
		
		
		
		

		// get new time lapse of first action
		/*if(this.globalTime == null) {
			this.actionsList.get(0).updateTimeLaps();
		}else {	
			this.actionsList.get(0).updateTimeLaps(this.globalTime.getTime());
		}
		
		// remove the action if no more lapse time is defined
		if(this.actionsList.get(0).getLastLapsTime() == null) {
			this.actionsList.remove(0);
		}else {
			// resort the list
			Collections.sort(this.actionsList);
		}*/

		DiscreteActionInterface a = this.actionsList.remove(0);
		if(a.hasNext()) {
			a = a.next();
			this.actionsList.addElement(a);
			if(this.globalTime!=null) {
				this.logger.log(Level.FINE, "[DAS] reset action " + a.getMethod().getName() + " on " + a.getObject().getClass().getName() + ":" + a.getObject().hashCode() + " at " + this.globalTime.getTime() + " to " + a.getCurrentLapsTime() + " time units\n");
				System.out.println("[DAS] reset action " + a.getMethod().getName() + " on " + a.getObject().getClass().getName() + ":" + a.getObject().hashCode() + " at " + this.globalTime.getTime() + " to " + a.getCurrentLapsTime() + " time units\n");
			}else {
				this.logger.log(Level.FINE, "[DAS] reset action " + a.getMethod().getName() + " on " + a.getObject().getClass().getName() + ":" + a.getObject().hashCode() + " to " + a.getCurrentLapsTime() + " time units\n");
				System.out.println("[DAS] reset action " + a.getMethod().getName() + " on " + a.getObject().getClass().getName() + ":" + a.getObject().hashCode() + " to " + a.getCurrentLapsTime() + " time units\n");
			}
			Collections.sort(this.actionsList);
		}
	}

	/**
	 * execute the simulation
	 */
	public void run() {
		int count = this.nbLoop;
		boolean finished = false;

		System.out.println("LANCEMENT DU THREAD " + t.getName() + " \n");

		while(running && !finished){

			if(!this.actionsList.isEmpty()){
				System.out.println(this);
				this.globalTime.setNextJump(this.nextLapsTime());
				
				this.globalTime.lockWriteAccess();
				int runningTime = this.runAction();
				this.updateTimes(runningTime);
				this.globalTime.unlockWriteAccess();
				try {
					Thread.sleep(100);
				}catch(Exception e) {
					e.printStackTrace();
				}
				//TODO add global time synchronizer for action with list of date and avoid drift 
			}else{
				System.out.println("NOTHING TO DO\n");
				this.stop();
			}

			count -= this.step;
			if(count<=0) {
				finished = true;
			}
		}
		this.running = false;
		if(this.step>0) {
			System.out.println("DAS: " + (this.nbLoop - count) + " actions done for " + this.nbLoop + " turns asked.");
		}else {
			System.out.println("DAS: " + (count) + " actions done!");			
		}
	}

	/**
		start thread
	 */
	public void start(){
		this.running = true;
		this.t.start();
	}
	
	/**
	stop thread
 */
	public void stop(){
		System.out.println("STOP THREAD " + t.getName() + "obj " + this);
		this.running = false;
	}
	
	
	/**
	 * Facilitates the readability of the messages returned by the simulation
 */
	public String toString(){
		StringBuffer toS = new StringBuffer("------------------\nTestAuto :" + this.actionsList.size());
		for(DiscreteActionInterface c : this.actionsList){
			toS.append(c.toString() + "\n");
		}
		toS.append("---------------------\n");
		return toS.toString();
	}
	
	/**
	@return running (true or false)
 */
	public boolean getRunning() {
		return this.running;
	}

}
