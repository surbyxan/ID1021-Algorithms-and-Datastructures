package uppgift3;

import java.util.Random;

public class recursive_search {
	
	private static boolean recursive(int[] arr, int key, int min, int max) {
		int mid = min + ((max - min)/2);
		if (arr[mid] == key) {
		return true;
		}
		if ((arr[mid] > key) && (min < mid)) {
		// call recursive but narrow the search
		boolean one = recursive(arr, key, min, mid+1);
		}
		if ((arr[mid] < key) && (mid < max)) {
		boolean two = recursive(arr, key, mid, max-1);
		}
		if (mid == min || mid == max) {
			return false;
		}
		return false;
	}

			private static int[] sorted(int n) {
		Random rnd = new Random();
		int[] array = new int[n];
		int nxt = 0;
		for (int i = 0; i < n ; i++) {
			nxt += rnd.nextInt(10) + 1;
			array[i] = nxt;
		}
		return array;
		}
	
	private static int[] keys(int loop, int n) {
	Random rnd = new Random();	
	int[] key = new int[loop];
	for (int i = 0; i < loop ; i++) {
	    key[i] = rnd.nextInt(n*5);
	}	
	return key;
    }

	public static void main(String[] arg) {

		int[] sizes = {10000,20000,40000,80000,160000,320000,640000};
		
		int[] arrayjit = keys(10000, 10000);
		for (int i = 0; i < 100; i++) {
			boolean JIT = recursive(arrayjit, i, 10, 30);
		}

		for ( int n : sizes) {
			int loop = 1000;
			
			int[] key = keys(loop, n);
			int[] array = keys(n, n);
			int[] sort = sorted(n);
				
			System.out.printf("%8d\t", n);
	
			int k = 1000;

			double sum = 0;
			for (int i = 0; i < k; i++) {
				int key1 = key[i];
				long t0 = System.nanoTime();
				boolean search = recursive(sort, 50, 10, 70);
				long t1 = System.nanoTime();
				double t = (t1 - t0);
				 	sum += t;
				}
			System.out.print(sum/k);
		}
	}
}
