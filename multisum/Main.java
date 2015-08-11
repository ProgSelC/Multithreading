package selcpkg;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
	public static void main(String args[]) {
		final int[] myArr = new int[200000000];
		final int THREADS = 4;

		Random rnd = new Random();

		for (int i = 0; i < myArr.length; i++) {
			myArr[i] = (rnd.nextInt(10) + 1);
		}

		int pLength = myArr.length / THREADS;
		int begin = 0;

		pLength = (myArr.length % THREADS > 0) ? pLength + 1 : pLength;

		AtomicInteger result = new AtomicInteger(0);
		Thread[] thr = new Thread[THREADS];

		long rTime = System.currentTimeMillis();
		
		for (int i = 0; i < THREADS; i++) {
			thr[i] = new Thread(new SumArray(myArr, begin, begin + pLength, result));
			thr[i].start();
			begin += pLength;
		}

		for (int i = 0; i < THREADS; i++) {
			try {
				thr[i].join();
			} catch (InterruptedException e) {

			}

		}
		rTime = System.currentTimeMillis() - rTime;

		String formatStr = "sum: %d\t%2dms\t%5d threads\n";
		System.out.printf(formatStr, result.get(), rTime, THREADS);

		rTime = System.currentTimeMillis();
		int sum = 0;

		for (int i = 0; i < myArr.length; i++)
			sum += myArr[i];

		System.out.printf(formatStr, sum, (System.currentTimeMillis() - rTime), 1);
	}
}
