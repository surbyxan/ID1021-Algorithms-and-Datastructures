package uppgift4;

import java.util.Random;
import java.util.Arrays;

public class Selectionstort {

	public static void select (int[] array){
	for (int i = 0; i < array.length -1; i++) {
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

	public static void main(String[] args) {
		int[] array = new int[10];
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			array[i] = rnd.nextInt(10*3);
		}
		System.out.println("Array osorterad :" + Arrays.toString(array));
		select(array);
		System.out.println("Array Sorterad :" + Arrays.toString(array));
	}
}

