package multisort;

class SortArray implements Runnable {
	private int[] a;

	public SortArray(int[] a) {
		this.a = a;
	}

	@Override
	public void run() {
		quicksort(a, 0, a.length - 1);
	}

	int partition(int[] array, int start, int end) {
		int marker = start;
		for (int i = start; i <= end; i++) {
			if (array[i] <= array[end]) {
				int temp = array[marker];
				array[marker] = array[i];
				array[i] = temp;
				marker += 1;
			}
		}
		return marker - 1;
	}

	void quicksort(int[] array, int start, int end) {
		if (start >= end) {
			return;
		}
		int pivot = partition(array, start, end);
		quicksort(array, start, pivot - 1);
		quicksort(array, pivot + 1, end);
	}

	public static int[] merge(int[] a, int[] b) {
		int i = 0;
		int iA = 0;
		int iB = 0;
		int aLen = a.length;
		int bLen = b.length;
		int[] result = new int[(aLen + bLen)];

		for (; iA < aLen && iB < bLen; i++) {
			if (a[iA] < b[iB]) {
				result[i] = a[iA++];
			} else {
				result[i] = b[iB++];
			}
		}

		for(;iA < aLen; i++) {
			result[i] = a[iA++];
		}

		
		for (;iB < bLen; i++) {
			result[i] = b[iB++];
		}

		return result;
	}

}
