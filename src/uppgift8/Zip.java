package uppgift8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Zip {
	Area[] postnr;
	int max = 10000;

	public class Area {
		String code;
		String name;
		Integer pop;

		public Area(String code, String name, Integer pop){
			this.code = code;
			this.name = name;
			this.pop = pop;
		}
	}

	public Zip(String file) {
		this.postnr = new Area[this.max];
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null && i < this.max) {
				String[] row = line.split(",");
				postnr[i++] = new Area(row[0], row[1], Integer.valueOf(row[2]));
			}
		this.max = i;
		} catch (Exception e) {
			System.out.println(" file " + file + " not found");
			}		
}
public String lookup(String search){
	Area nr = postnr[0];
	try{
		for (int i = 0; i < postnr.length && !nr.code.equals(search); i++) {
			nr = postnr[i];
		}
		return nr.code;
	} catch(NullPointerException e) {}
	return "item does not exist in database";
}

public String binary(String search){
	int high = this.postnr.length -1;
	int low = 0;
	while(low <= high){
		int mid = low + (high - low) / 2;
		int res = 0;
		try{
			res = search.compareTo(this.postnr[mid].code);
			} catch (NullPointerException e){
				break;
			}
		if (res > 0) {
			low = mid + 1;				
		} else if (res < 0) {
			high = mid - 1;
		} else{
			return this.postnr[mid].code;
		}

	}
	return "item does not exist in database";
}
	public static void main(String[] args) {
		String s = "postnummer.csv";
		Zip2 zip = new Zip2(s);
		//System.out.println(zip.postnr[81592].code);
		//System.out.println(Arrays.toString(zip.postnr));
		double t_lookup_11115 = 0;
		double t_lookup_98499 = 0;
		double t_binary_11115 = 0;
		double t_binary_98499 = 0;

		for (int i = 0 ; i < 10000; i++){
			double t0 = System.nanoTime();
			zip.lookup(11115);
			t_lookup_11115 += System.nanoTime() - t0;
			
			t0 = System.nanoTime();
			zip.lookup(98499);
			t_lookup_98499 += System.nanoTime() - t0;

			t0 = System.nanoTime();
			zip.binary(11115);
			t_binary_11115 += System.nanoTime() - t0;

			t0 = System.nanoTime();
			zip.binary(98499);
			t_binary_98499 += System.nanoTime() - t0;
			
			
		}
		System.out.println("Tid för lookup:\t" + t_lookup_11115/10000 + "us ");
		System.out.println("Tid för lookup:\t" + t_lookup_98499/10000 + "us ");
		System.out.println("Tid för binary:\t" + t_binary_11115/10000 + "us ");
		System.out.println("Tid för binary:\t" + t_binary_98499/10000 + "us ");
	}		

}
