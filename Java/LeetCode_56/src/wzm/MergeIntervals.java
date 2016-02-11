package wzm;

import java.util.List;
import java.util.ArrayList;

public class MergeIntervals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1, 3));
		intervals.add(new Interval(2, 6));
		intervals.add(new Interval(8, 10));
		intervals.add(new Interval(15, 18));

		System.out.println(merge(intervals));
	}
	
	/**
	 * Solved by sorting. Sort all intervals according to their start and end.
	 * If the end of current interval is not smaller than the start of next interval, these two intervals are overlapping.
	 * Combine these two intervals, assign the start of current interval as the start of merged interval and 
	 * assign max(current.end, next.end) as the end of merged interval, then remove the next interval. 
	 * Interval.java has implemented Comparable interface and override compareTo method.
	 * Therefore, method compareInterval could be replaced by Interval.compareTo.
	 * Time complexity is O(nlogn).
	 * @param intervals unmerged intervals
	 * @return intervals after merging
	 */
	
	public static List<Interval> merge(List<Interval> intervals) {
		
		if (intervals.isEmpty()) {
			return intervals;
		}

		quickSort(intervals, 0, intervals.size() - 1);

		int i = 0;
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

	private static void quickSort(List<Interval> intervals, int left, int right) {

		int l = left;
		int r = right;
		Interval pivot = intervals.get(left + (right - left >> 1));

		while (l <= r) {
			while (compareInterval(intervals.get(l), pivot) < 0) {
				l++;
			}
			while (compareInterval(pivot, intervals.get(r)) < 0) {
				r--;
			}
			if (l <= r) {
				swap(intervals, l, r);
				l++;
				r--;
			}
		}
		if (l < right) {
			quickSort(intervals, l, right);
		}
		if (left < r) {
			quickSort(intervals, left, r);
		}
	}

	private static void swap(List<Interval> intervals, int a, int b) {

		Interval temp = intervals.get(a);
		intervals.set(a, intervals.get(b));
		intervals.set(b, temp);
	}

	private static int compareInterval(Interval a, Interval b) {

		if (a.start != b.start) {
			return a.start - b.start;
		}
		return a.end - b.end;
	}
}
