package uppgift3;

public class binary {
	
	public static boolean binary_search(int[] array, int key) {
		int first = 0;
		int last = array.length-1;
			while (true) {
				int index = (first + last)/2 ;
				if (array[index] == key) {
					return true;
				}
			if (array[index] < key && index < last) {
				first = index + 1;
				continue;
			}
			if (array[index] > key && index > first) {
				last = index - 1;
				continue;
			}
			if (index == first || index == last) {
				return false;
			}
			}
			
		}
}

