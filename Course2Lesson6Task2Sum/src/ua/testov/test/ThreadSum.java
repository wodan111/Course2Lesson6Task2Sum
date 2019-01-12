package ua.testov.test;

public class ThreadSum implements Runnable {
	private int[] x;
	private int sum;
	private int begin;
	private int end;

	public ThreadSum() {
		super();
	}

	public ThreadSum(int[] x, int begin, int end) {
		super();
		this.x = x;
		this.begin = begin;
		this.end = end;
	}

	private int arrayElemSum() {
		int sum = 0;
		try {
			for (int i = this.begin; i < this.end; i++) {
				sum += this.x[i];
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		return sum;
	}
	public int getSum() {
		return sum;
	}
@Override
	public void run() {
		this.sum = arrayElemSum();
	}
}
