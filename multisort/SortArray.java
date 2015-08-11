package selcpkg;

class SortArray implements Runnable {
	private int[] a;

	public SortArray(int[] a) {
		this.a = a;
	}

	@Override
	public void run() {
		SortArray.insertionSort(a, 0, a.length);
	}
	
	public static void insertionSort(int[] arr, int beg, int end) {
		for (int i = beg + 1; i < end; i++) {
			int currElem = arr[i];
			int prevKey = i - 1;
			while (prevKey >= beg && arr[prevKey] > currElem) {
				arr[prevKey + 1] = arr[prevKey];
				arr[prevKey] = currElem;
				prevKey--;
			}
		}
	}
	
	public static int[] merge(int[] arr1, int[] arr2) {
		int len1 = arr1.length, len2 = arr2.length;
		int a = 0, b = 0, len = len1 + len2;
		int[] result = new int[len];
		for (int i = 0; i < len; i++) {
			if (b < len2 && a < len1) {
				if (arr1[a] > arr2[b]) result[i] = arr2[b++];
				else result[i] = arr1[a++];
			} else if (b < len2) {
				result[i] = arr2[b++];
			} else {
				result[i] = arr1[a++];
			}
		}
		return result;
	}
}
