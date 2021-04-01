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

	@Test
	/**
	 * first builder test
	 */
	void testDateTimerTreeSetOfInteger() {
		TreeSet<Integer> dates = new TreeSet<Integer>();
		dates.add(1);
		dates.add(2);
		dates.add(3);
		DateTimer d = new DateTimer(dates);
		Vector<Integer> lapsTimesExcepte = new Vector<Integer>();
		lapsTimesExcepte.add(1);
		lapsTimesExcepte.add(1);
		lapsTimesExcepte.add(1);
		assertEquals(d.lapsTimes, lapsTimesExcepte);
	}

	@Test
	/**
	 * first builder test with negative values
	 */
	void testDateTimerTreeSetOfInteger2() {
		TreeSet<Integer> dates = new TreeSet<Integer>();
		dates.add(-3);
		dates.add(1);
		dates.add(2);
		DateTimer d = new DateTimer(dates);
		Vector<Integer> lapsTimesExcepte = new Vector<Integer>();
		lapsTimesExcepte.add(-3);
		lapsTimesExcepte.add(4);
		lapsTimesExcepte.add(1);
		assertEquals(d.lapsTimes, lapsTimesExcepte);
	}
	
	@Test
	/**
	 * first builder test with values in descending order
	 */
	void testDateTimerTreeSetOfInteger3() {
		TreeSet<Integer> dates = new TreeSet<Integer>();
		dates.add(3);
		dates.add(2);
		dates.add(1);
		DateTimer d = new DateTimer(dates);
		Vector<Integer> lapsTimesExcepte = new Vector<Integer>();
		lapsTimesExcepte.add(1);
		lapsTimesExcepte.add(1);
		lapsTimesExcepte.add(1);
		assertEquals(d.lapsTimes, lapsTimesExcepte);
	}
	
	@Test
	/**
	 * second builder test
	 */
	void testDateTimerVectorOfInteger() {
		Vector<Integer> lapsTime = new Vector<Integer>();
		lapsTime.add(1);
		lapsTime.add(1);
		lapsTime.add(2);
		DateTimer d = new DateTimer(lapsTime);
		assertEquals(d.lapsTimes, lapsTime);
	}
	
	@Test
	/**
	 * second builder test with negative values
	 */
	void testDateTimerVectorOfInteger2() {
		Vector<Integer> lapsTime = new Vector<Integer>();
		lapsTime.add(1);
		lapsTime.add(-1);
		lapsTime.add(-2);
		DateTimer d = new DateTimer(lapsTime);
		assertEquals(d.lapsTimes, lapsTime);
	}
	
	@Test
	/**
	 * hasNext function test
	 */
	void testHasNext() {
		Vector<Integer> lapsTime = new Vector<Integer>();
		lapsTime.add(1);
		lapsTime.add(-1);
		lapsTime.add(-2);
		DateTimer d = new DateTimer(lapsTime);
		assertTrue(d.hasNext());
	}
	
	@Test
	/**
	 * hasNext function test with an empty vector
	 */
	void testHesNext2() {
		Vector<Integer> lapsTime = new Vector<Integer>();
		DateTimer d = new DateTimer(lapsTime);
		assertFalse(d.hasNext());
	}
	
	@Test
	/**
	 * next function test
	 */
	void testNext() {
		Vector<Integer> lapsTime = new Vector<Integer>();
		lapsTime.add(1);
		lapsTime.add(1);
		lapsTime.add(2);
		DateTimer d = new DateTimer(lapsTime);
		assertEquals(d.next(),1);
	}
	
	@Test
	/**
	 * next function test with an empty vector
	 */
	void testNext2() {
		Vector<Integer> lapsTime = new Vector<Integer>();
		DateTimer d = new DateTimer(lapsTime);
		assertNull(d.next());
	}
}
