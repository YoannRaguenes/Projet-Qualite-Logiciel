package timer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import action.DiscreteAction;
import action.DiscreteActionDependent;

public class DiscreteActionTest {


	@Test
    public void spendTimeTest() {
        Object object = new Object();
        OneShotTimer oneShotTimer = new OneShotTimer(5);
        DiscreteAction discreteAction = new DiscreteAction(object,"test",oneShotTimer);
        discreteAction.setLapsTime(5);
        discreteAction.spendTime(2);
        Integer expected =3;
        assertEquals(expected,discreteAction.getCurrentLapsTime());
        discreteAction.spendTime(4);
        expected =-1;
        assertEquals(expected,discreteAction.getCurrentLapsTime());
    }
	
	@Test
    public void nextTest() {
        Object object = new Object();
        OneShotTimer oneShotTimer = new OneShotTimer(10);
        DiscreteAction discreteAction = new DiscreteAction(object,"test",oneShotTimer);
        DiscreteAction discreteAction2 = new DiscreteAction(object,"test",oneShotTimer);
        discreteAction2.setLapsTime(10);
        assertEquals(discreteAction.next().getCurrentLapsTime(),discreteAction2.getCurrentLapsTime());
        discreteAction2.spendTime(4);
        assertEquals(discreteAction2.next().getCurrentLapsTime(),discreteAction2.getCurrentLapsTime());
    }
	
	 @Test
	    public void hasNextTest() {
	        Object object = new Object();
	        OneShotTimer oneShotTimer = new OneShotTimer(1);
	        OneShotTimer oneShotTimer2 = new OneShotTimer(0);
	        DiscreteAction discreteAction = new DiscreteAction(object,"test",oneShotTimer);
	        DiscreteAction discreteAction2 = new DiscreteAction(object,"test2",oneShotTimer2);
	        assertTrue(discreteAction.hasNext());
	        assertFalse(discreteAction2.next().hasNext());
	        
	    }
	 
	 @Test
	   public void compareTo() {
	        Object object = new Object();
	        OneShotTimer oneShotTimer = new OneShotTimer(5);
	        DiscreteAction discreteAction = new DiscreteAction(object,"test",oneShotTimer);
	        DiscreteAction discreteAction2 = new DiscreteAction(object,"test2",oneShotTimer);
	        // the same
	        discreteAction.setLapsTime(0);
	        discreteAction2.setLapsTime(0);
	        assertEquals(0,discreteAction.compareTo(discreteAction2));

	        // lapstime < lasptime2
	        discreteAction2.setLapsTime(1);
	        assertEquals(-1,discreteAction.compareTo(discreteAction2));

	        //lapstime > lasptime2
	        assertEquals(1,discreteAction2.compareTo(discreteAction));
	    }



}
