package timer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import action.DiscreteAction;
import action.DiscreteActionDependent;

public class DiscreteActionDependentTest {
	Object object ;
	OneShotTimer oneShotTimer;
	DiscreteActionDependent discreteActionDependent;

	@Before
	public void setUp() throws Exception {
		object = new Object();
        oneShotTimer = new OneShotTimer(5);
        discreteActionDependent = new DiscreteActionDependent(object, "methodTest", oneShotTimer);
	}

	
	

	    @Test
	    public void nextMethod() {
	    	discreteActionDependent.addDependence(object,"methodTest2",oneShotTimer);
	        discreteActionDependent.nextMethod();
	        assertEquals(discreteActionDependent.currentAction,discreteActionDependent.depedentActions.last());

	    }


	    @Test
	    public void updateTimeLaps() {
	        
	        discreteActionDependent.addDependence(object,"methodTest",oneShotTimer);
	        discreteActionDependent.updateTimeLaps();
	        assertEquals( discreteActionDependent.currentAction,discreteActionDependent.depedentActions.last());
	    }


	    @Test
	    public void isEmpty() {
	        discreteActionDependent.addDependence(object,"methodTest",oneShotTimer);
	        assertFalse(discreteActionDependent.depedentActions.isEmpty());
	    }

}
