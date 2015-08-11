package selcpkg;

import java.util.concurrent.atomic.AtomicInteger;

class SumArray implements Runnable {
	private int begin;
	private int end;
	private int[] a;
	private AtomicInteger result;

	public SumArray(int[] a, int begin, int end, AtomicInteger result) {
		this.a = a;
		this.begin = begin;
		this.end = (a.length > end) ? end : a.length;
		this.result = result;
	}

	@Override
	public void run() {
		int sum = 0;

		for (int i = begin; i < end; i++) {
			sum += a[i];
		}

		result.addAndGet(sum);
	}
}
