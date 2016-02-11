package wzm;

public class Interval implements Comparable<Interval> {

	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}

	public int compareTo(Interval anotherInterval) {

		if (this.start != anotherInterval.start) {
			return this.start - anotherInterval.start;
		}
		return this.end - anotherInterval.end;
	}
}
