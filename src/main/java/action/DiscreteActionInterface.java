package action;

import java.lang.reflect.Method;
import java.util.Iterator;

/*
 * TODO must implement Iterator<Action>
 */
public interface DiscreteActionInterface extends Comparable<DiscreteActionInterface>, Iterator<DiscreteActionInterface>{

	/**
	 * decrease the time of discrete action 
	 * @param t how much time you want to spend
	 */
	public	void spendTime(int t);
	
	/**
	 * return the method to execute
	 */
	public Method getMethod();
	
	/**
	 * return the last laps time without update
	 */
	public Integer getCurrentLapsTime();
	
	/**
	 * get the object on which the method must be invoked
	 */
	public Object getObject();

	// COMPARAISON
	/**
	 * compare discrete action according to the time before execution
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(DiscreteActionInterface c);
	
	/**
	 * @see java.util.Iterator#next()
	 */
	public DiscreteActionInterface next();
	
	/**
	 * @see java.util.Iterator#hasNext()
	 */
	//public boolean hasNext();
}
