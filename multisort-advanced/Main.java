package multisort;

import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void main(String args[]) {
		final int[] myArr = new int[10000000];
		int threads = 4;

		Random rnd = new Random();

		for (int i = 0; i < myArr.length; i++) {
			myArr[i] = (rnd.nextInt(myArr.length) + 1);
		}

		int[] arr1 = Arrays.copyOf(myArr, myArr.length);
		int[] arr2 = Arrays.copyOf(myArr, myArr.length);

		System.out.printf("Sorting Quick time with %d threads: %d ms\n",
				threads, runQickMulti(arr1, threads));

		System.out.printf("Sorting Quick time with %d thread: %d ms\n", 1,
				runQickMulti(arr2, 1));

		long rTime = System.currentTimeMillis();

		Arrays.sort(myArr);
		rTime = System.currentTimeMillis() - rTime;

		System.out.printf("Sorting time with Arrays.sort: %dms\n", rTime);

		System.out.println("Arrays.sort and qick-multi arrays are equal: " + Arrays.equals(arr1, myArr));
		System.out.println("Arrays.sort and qick-single arrays are equal: " + Arrays.equals(arr2, myArr));
	}

	static long runQickMulti(int[] a, int threads) {

		int pLen = a.length / threads;

		Thread[] thr = new Thread[threads];
		int[][] pSort = new int[threads][];

		long rTime = System.currentTimeMillis();

		int end = 0;
		for (int i = 0; i < threads; i++) {
			end = (i + 1 < threads) ? (i + 1) * pLen : a.length;

			pSort[i] = Arrays.copyOfRange(a, i * pLen, end);

			thr[i] = new Thread(new SortArray(pSort[i]));
			thr[i].start();

		}

		for (Thread th : thr) {
			try {
				th.join();
			} catch (InterruptedException e) {
				System.out.println(e);
			}

		}

		int[] sortedArr = null;

		switch (threads) {
		case 1:
			sortedArr = pSort[0];
			break;
		case 2:
			sortedArr = SortArray.merge(pSort[0], pSort[1]);
			break;
		case 3:
			sortedArr = SortArray.merge(SortArray.merge(pSort[0], pSort[1]),
					pSort[2]);
			break;
		case 4:
			sortedArr = SortArray.merge(SortArray.merge(pSort[0], pSort[1]),
					SortArray.merge(pSort[2], pSort[3]));
			break;
		}

		rTime = System.currentTimeMillis() - rTime;

		System.arraycopy(sortedArr, 0, a, 0, sortedArr.length);

		return rTime;
	}

}
