package uppgift8;

import java.io.BufferedReader;
import java.io.FileReader;

public class Zipkey {
	
	Area[] postnr;
	int max = 100000;

	public class Area {
		Integer code;
		String name;
		Integer pop;

		public Area(Integer code, String name, Integer pop){
			this.code = code;
			this.name = name;
			this.pop = pop;
		}
	}

	public Zipkey(String file) {
		postnr = new Area[max];
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null && i < this.max) {
				String[] row = line.split(",");
				Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
				postnr[code] = new Area(code, row[1], Integer.valueOf(row[2]));
			}
		this.max = i;
		} catch (Exception e) {
			System.out.println(" file " + file + " not found");
			}		
}
	public Integer lookup(Integer search){
		Integer n = null;
		try{
			n = this.postnr[search].code;
		} catch(NullPointerException e) {}
		return n;
	}

	public Integer binary(Integer search){
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
		return null;
	}
	public static void main(String[] args) {
		String s = "postnummer.csv";
		Zipkey zip = new Zipkey(s);
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
		System.out.println("Tid för lookup:\t" + t_lookup_11115/10000 + "ns ");
		System.out.println("Tid för lookup:\t" + t_lookup_98499/10000 + "ns ");
		System.out.println("Tid för binary:\t" + t_binary_11115/10000 + "ns ");
		System.out.println("Tid för binary:\t" + t_binary_98499/10000 + "ns ");
	}		
}
