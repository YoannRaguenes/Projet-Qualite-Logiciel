package timer;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

public class DateTimer  implements Timer {
	
	Vector<Integer> lapsTimes;
	Iterator<Integer> it;
	
	/**
	 * @param dates
	 * 
	 * creation with an integer TreeSet of dates 
	 */
	public DateTimer(TreeSet<Integer> dates) {
		this.lapsTimes = new Vector<Integer>();
		Integer last;
		Integer current=0;
		
		Iterator<Integer> itr = dates.iterator();
		while (itr.hasNext()) {
			last = current;
			current = itr.next();
			this.lapsTimes.add(current-last);
		}
		this.it = this.lapsTimes.iterator();

	}
	
	/**
	 * 
	 * @param lapsTimes
	 * 
	 * creation with an integer Vector of lapsTimes
	 */
	public DateTimer(Vector<Integer> lapsTimes) {
		this.lapsTimes = new Vector<Integer>(lapsTimes);
		this.it = this.lapsTimes.iterator();
	}

	@Override
	/**
	 * Returns true if there are still elements in "it"
	 */
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	/**
	 * Returns next item in "it"
	 */
	public Integer next() {
		return it.next();
	}

}
