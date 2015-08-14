package multisort;

public class ArrayThread implements Runnable {

	private int[] arr;

	public int[] getArr() {
		return arr;
	}

	ArrayThread(int[] arr) {
		this.arr = arr;
	}

	public void run() {
		mergeSort(arr, 0, arr.length);
	}

	private void mergeSort(int[] arr, int low, int high) {
		if (low + 1 < high) {
			int mid = (low + high) >>> 1;
			mergeSort(arr, low, mid);
			mergeSort(arr, mid, high);

			int size = high - low;
			int[] b = new int[size];
			int i = low;
			int j = mid;
			for (int k = 0; k < size; k++) {
				if (j >= high || i < mid && arr[i] < arr[j]) {
					b[k] = arr[i++];
				} else {
					b[k] = arr[j++];
				}
			}
			System.arraycopy(b, 0, arr, low, size);
		}

	}
}
