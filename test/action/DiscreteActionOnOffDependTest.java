package timer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import action.DiscreteActionOnOffDependent;

public class DiscreteActionOnOffDependTest {
	public OneShotTimer oneShotTimerOn;
	OneShotTimer oneShotTimerOff;
	Object object;

	@Before
	public void setUp() throws Exception {
		object = new Object();
        oneShotTimerOn = new OneShotTimer(1);
        oneShotTimerOff = new OneShotTimer(5);
	}

	@Test
	public void nextActionTest() {
		DiscreteActionOnOffDependent discreteActionOnOffDependent = new DiscreteActionOnOffDependent(object,"methodTestON",oneShotTimerOn,"methodTestOFF",oneShotTimerOff);
	    discreteActionOnOffDependent.nextAction();
	    assertEquals (discreteActionOnOffDependent.getCurrentAction(),discreteActionOnOffDependent.onAction);
        discreteActionOnOffDependent.nextAction();
        assertEquals (discreteActionOnOffDependent.getCurrentAction(),discreteActionOnOffDependent.offAction);
	}
	@Test
	public void spendTime() {
		 DiscreteActionOnOffDependent discreteActionOnOffDependent = new DiscreteActionOnOffDependent(object,"methodTestON",oneShotTimerOn,"methodTestOFF",oneShotTimerOff);
	     discreteActionOnOffDependent.spendTime(5);
	     //assertEquals ne fonctionne pas
	     assertTrue(discreteActionOnOffDependent.getCurrentLapsTime()== -5);
    }

}