package uppgift4;

import java.util.Arrays;
import java.util.Random;

public class Insertsort {

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
	public static void main(String[] args) {
		int[] array = new int[10];
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			array[i] = rnd.nextInt(10*3);
		}
		System.out.println("Array osorterad :" + Arrays.toString(array));
		insert(array);
		System.out.println("Array Sorterad :" + Arrays.toString(array));
	}
}
