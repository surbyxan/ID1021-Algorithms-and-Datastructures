package uppgift3;

import java.util.Random;

public class bench {

	public static boolean binary_search(int[] array, int key) {
		int first = 0;
		int last = array.length - 1;
		while (true) {
			int mid = (first + last) / 2;
			if (array[mid] == key) {
				return true;
			}
			if (array[mid] < key && mid < last) {
				first = mid + 1;
				continue;
			}
			if (array[mid] > key && mid > first) {
				last = mid - 1;
				continue;
			}
			if (mid == first || mid == last) {
				return false;
			}
		}

	}

	private static int[] sorted(int n) {
		Random rnd = new Random();
		int[] array = new int[n];
		int nxt = 0;
		for (int i = 0; i < n; i++) {
			nxt += rnd.nextInt(10) + 1;
			array[i] = nxt;
		}
		return array;
	}

	private static int[] keys(int loop, int n) {
		Random rnd = new Random();
		int[] key = new int[loop];
		for (int i = 0; i < loop; i++) {
			key[i] = rnd.nextInt(n * 5);
		}
		return key;
	}

	public static boolean unsorted_search(int[] array, int key) {
		for (int index = 0; index < array.length; index++) {
			if (array[index] == key) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] arg) {

		int[] sizes = { 10000, 20000, 40000, 80000, 160000, 320000, 640000 };

		int[] arrayjit = keys(10000, 10000);
		for (int i = 0; i < 100; i++) {
			boolean JIT = binary_search(arrayjit, i);
		}

		for (int n : sizes) {
			int loop = 1000;

			int[] key = keys(loop, n);
			int[] array = keys(n, n);
			int[] sort = sorted(n);

			System.out.printf("%8d\t", n);

			int k = 1000;

			double sum = 0;
			// double min = Double.POSITIVE_INFINITY;
			for (int i = 0; i < k; i++) {
				int key1 = key[i];
				long t0 = System.nanoTime();
				boolean search = binary_search(sort, key1);
				long t1 = System.nanoTime();
				double t = (t1 - t0);
				sum += t;
			}

			/*
			 * double mins = Double.POSITIVE_INFINITY;
			 * for (int i = 0; i < k; i++) {
			 * int key1 = key[i];
			 * long t0 = System.nanoTime();
			 * //boolean search = bs.binary(array, key1);
			 * long t1 = System.nanoTime();
			 * double t = (t1 - t0);
			 * if (t < mins)
			 * mins = t;
			 * }
			 */
			System.out.print(sum / k);
		}
	}
}
