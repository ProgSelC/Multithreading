package multisort;

class SortArray implements Runnable {
	private int[] a;

	public SortArray(int[] a) {
		this.a = a;
	}

	@Override
	public void run() {
		quicksort(a, 0, a.length-1);
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

	public static int[] merge(int[] arr1, int[] arr2) {
		int len1 = arr1.length;
		int len2 = arr2.length;
		int ind1 = 0, ind2 = 0;
		int len = len1 + len2;
		int[] result = new int[len];
		
		for (int i = 0; i < len; i++) {
			if (ind2 < len2 && ind1 < len1) {
				if (arr1[ind1] > arr2[ind2])
					result[i] = arr2[ind2++];
				else
					result[i] = arr1[ind1++];
			} else if (ind2 < len2) {
				result[i] = arr2[ind2++];
			} else {
				result[i] = arr1[ind1++];
			}
		}
		return result;
	}
}
