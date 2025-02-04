package uppgift4;

import java.util.Random;

public class bench {
	
	public static void main(String[] args) {
		
		int loop = 10000;
		long bencht = benching(1000,loop);

		System.out.print("Sorterat med tiden:\t");
		System.out.println(bencht + "ns");			
				
	}
	
	public static long benching (int n, int loop){
		int[] array = new int[n];
		Random rnd = new Random();
		int time = 0;
		for (int i = 0; i < n; i++) {
			array[i] = rnd.nextInt(n+i);
		}

		for (int i = 0; i < loop; i++) {
			
			long t1 = System.nanoTime();
			sort(array);
			//Selectionstort(array);
			//insert(array);
			long t2 = System.nanoTime();
			time += (t2 - t1);
		}
		long time_avg = time / loop;
		return time_avg;
	}
	public static void sort(int[] org) {
		if (org.length == 0)
		return;
		int[] aux = new int[org.length];
		sort(org, aux, 0, org.length -1);
		}

	private static void sort(int[] org, int[] aux, int lo, int hi) {
			if (lo != hi) {
			int mid = (lo + hi)/2;
			// sort the items from lo to mid
			sort(org, aux, lo, mid);
			// sort the items from mid+1 to hi
			sort(org,aux, mid +1, hi);
			// merge the two sections using the additional array
			merge(org, aux, lo, mid, hi);
		}
	}

	private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {
		// copy all items from lo to hi from org to aux
		for ( int i = lo; i <= hi; i++ ) {
			aux[i] = org[i];
		}
		// let's do the merging
		int i = lo; // the index in the first part
		int j = mid+1; // the index in the second part
		// for all indices from lo to hi

		for ( int k = lo; k <= hi; k++) {
		// if i is greater than mid then
		// move the j'th item to the org array, update j
			if (i > mid) {
				org[k] = aux[j++];
			}
		// else if j is greate than hi then
		// move the i'th item to the org array, update i
			else if (j > hi) {
				org[k] = aux[i++];
			}
		// else if the i'th item is smaller than the j'th item,
		// move the i'th item to the org array, update i
			else if (aux[i] < aux[j]) {
				org[k] = aux[i++];
			}
		// else
		// move the j'th item to the org array, update j
			else {
				org[k] = aux[j++];
			}
		}
	}
	public static void insert(int[] array){
		for (int i = 1; i < array.length; i++) {
			//int temp = array[i];
			for (int j = i; j > 0 && (array[j] < array[j-1]); j--) {
				int temp = array[j];
				array[j] = array[j-1];
				array[j-1] = temp;
			}
		}
	}
	public static void Selectionstort (int[] array){
		for (int i = 0; i < array.length-1; i++) {
			int candidate =  i;
			for (int j = i + 1; j < array.length ; j++) {
				if (array[j] < array[candidate]) {
					candidate = j;
				}
			}
		int temp = array[i];
		array[i] = array[candidate];
		array[candidate] = temp;
		}
	}
}
