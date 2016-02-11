package wzm;

import java.util.List;
import java.util.ArrayList;

public class InsertInterval {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Interval> intervals1 = new ArrayList<Interval>();
		intervals1.add(new Interval(1, 3));
		intervals1.add(new Interval(6, 9));
		Interval newInterval1 = new Interval(2, 5);
		
		insert(intervals1, newInterval1);

		List<Interval> intervals2 = new ArrayList<Interval>();
		intervals2.add(new Interval(1, 2));
		intervals2.add(new Interval(3, 5));
		intervals2.add(new Interval(6, 7));
		intervals2.add(new Interval(8, 10));
		intervals2.add(new Interval(12, 16));
		Interval newInterval2 = new Interval(4, 9);

		insert(intervals2, newInterval2);
	}
	
	/**
	 * Solved similarly to MergeInterval.
	 * At first we need to insert the new interval to an appropriate place where new interval <= intervals[i].
	 * The strategy of insertion is designed to keep the intervals still sorted.
	 * Then merge the intervals again as what has been done in MergeInterval.
	 * Time complexity is O(n).
	 * @param intervals sorted intervals
	 * @param newInterval new interval to be inserted
	 * @return intervals after insertion
	 */
	
	public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
			
		int i = 0;
		for (i = 0; i < intervals.size(); i++) {
			if (compareInterval(newInterval, intervals.get(i)) <= 0) {
				break;	
			}
		}
		intervals.add(i, newInterval);
		i = 0;
		while (i < intervals.size() - 1) {
			Interval current = intervals.get(i);
			Interval next = intervals.get(i + 1);
			if (current.end >= next.start) {
				current.end = Math.max(current.end, next.end);
				intervals.remove(i + 1);
			} else {
				i++;
			}
		}

		return intervals;
	}

	private static int compareInterval(Interval a, Interval b) {

		if (a.start != b.start) {
			return a.start - b.start;
		}
		return a.end - b.end;
	}
}
