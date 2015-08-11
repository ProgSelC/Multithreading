package selcpkg;

import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void main(String args[]) {
		final int[] myArr = new int[100000];
		final int THREADS = 4;

		Random rnd = new Random();

		for (int i = 0; i < myArr.length; i++) {
			myArr[i] = (rnd.nextInt(10) + 1);
		}

		int pLength = myArr.length / THREADS;
		int begin = 0;

		pLength = (myArr.length % THREADS > 0) ? pLength + 1 : pLength;

		Thread[] thr = new Thread[THREADS];
		int[][] pSort = new int[THREADS][];

		long rTime = System.currentTimeMillis();

		for (int i = 0; i < THREADS; i++) {
			pLength = ((begin+pLength) < myArr.length) ? pLength : (myArr.length - begin);
			pSort[i] = Arrays.copyOfRange(myArr, begin, begin + pLength);
			
			thr[i] = new Thread(new SortArray(pSort[i]));
			thr[i].start();
			begin += pLength;

		}

		for (Thread th : thr) {
			try {
				th.join();
			} catch (InterruptedException e) {

			}

		}

		int[] thrSortedArr = SortArray.merge(SortArray.merge(pSort[0], pSort[1]), SortArray.merge(pSort[2], pSort[3]));

		rTime = System.currentTimeMillis() - rTime;

		System.out.printf("Sorting time with %d threads: %dms\n", THREADS, rTime);

		rTime = System.currentTimeMillis();

		Arrays.sort(myArr);
		rTime = System.currentTimeMillis() - rTime;

		System.out.printf("Sorting time with Arrays.sort: %dms\n", rTime);

		System.out.println("Arrays are equal: " + Arrays.equals(thrSortedArr, myArr));
	}
}
