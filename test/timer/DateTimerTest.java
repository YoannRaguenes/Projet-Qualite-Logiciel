package timer;

import java.util.TreeSet;
import java.util.Vector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DateTimerTest {

	@Test
	void testPositiveInterval() {
		TreeSet<Integer> dates = new TreeSet<Integer>();
		dates.add(3);
		dates.add(1);
		dates.add(2);
		DateTimer dateTimer = new DateTimer(dates);
		while(dateTimer.hasNext()) {
			Assertions.assertTrue(dateTimer.next()>0);
		}
	}
	
	@Test
	void testClostToLimitConstructor() {
		TreeSet<Integer> dates = new TreeSet<Integer>();
		dates.add(Integer.MAX_VALUE);
		dates.add(Integer.MAX_VALUE);
		DateTimer dateTimer = new DateTimer(dates);
		Assertions.assertNotNull(dateTimer.lapsTimes);
	}
	
	@Test
	void testOutOfTheBoundsConstructor() {
		Vector<Integer> vector = new Vector<Integer>();
		vector.add(-1);
		vector.add(-2);
		Assertions.assertThrows(Exception.class, () -> {
			new DateTimer(vector);
		});
	}

}
