package uppgift8;

import java.io.BufferedReader;
import java.io.FileReader;

public class Ziphash {
	Area[] postnr;
	int[] keys;
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

	public class Buckets{
		private Area code;
		private Buckets next;
		private int head;

		public Buckets(Integer code, String name, Integer pop){
			this.code = new Area(code, name, pop);
			next = null;
			head = 0;
		}
	}
	

	public Ziphash(String file) {
		postnr = new Area[max];
		keys = new int[9677];

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null && i < this.max) {
				String[] row = line.split(",");
				Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
				postnr[i] = new Area(code, row[1], Integer.valueOf(row[2]));
				keys[i] = code;
				i++;
				
			}
		this.max = i - 1;
		} catch (Exception e) {
			System.out.println(" file " + file + " not found");
			}
	}	


	public Buckets[] hashfunction(int mod){
		Buckets[] buck = new Buckets[mod];

		for (int i = 0; i < max; i++) {
			Integer index = keys[i] % mod;

			if (buck[index] != null) {
				Buckets pointer = buck[index];

				while (pointer.next != null) 
					pointer = pointer.next;

				pointer.next = new Buckets(postnr[i].code, postnr[i].name, postnr[i].pop);
			}
			else
				buck[index] = new Buckets(postnr[i].code, postnr[i].name, postnr[i].pop);
		}
		return buck;
	}

	public void collisions(int mod) {
		int mx = 20;
		int[] data = new int[mod];
		int[] cols = new int[mx];
		// keys[] are the zip codes
		for (int i = 0; i < max; i++) {
			Integer index = keys[i] % mod;
			cols[data[index]]++;
			data[index]++;
		}
		int maxColl = 0;
		int totColl = 0;

		while(cols[maxColl] > 0){
			totColl += cols[maxColl] *maxColl++;
		}
		float avgColl = totColl/(float)max;
		float score = (float)(1-avgColl/mod)*100000;

		System.out.println("mod: " + mod + " Average collisions: " + avgColl + "score: " + score);

		for(int i = 0; i < mod; i++) {
			if (data[i] < mx)
				cols[data[i]]++;
		}
		System.out.print(mod + ": ");
		for (int i = 1; i < mx; i++) {
			System.out.print("\t" + cols[i]);
		}
		System.out.println();
	}

	public int lookup(Buckets[] buck, Integer zip, int mod){
		Integer index = zip % mod;
		int depth = 0;
		if (buck[index] == null) {
			return 0;
		}
		//System.out.println(buck[index].code.code);
		if (buck[index] != null && zip != buck[index].code.code) {
			while (buck[index].next != null && buck[index].code.code != zip) {
				buck[index] = buck[index].next;
				if (buck[index].next == null) {
					System.out.println("buckbuck");
				}
				depth++;
			}
		}
		if (buck[index] != null && zip == buck[index].code.code) {
			return depth;
		}
		//System.out.println(buck[index].code.code);
		return 0;
	}

	public Integer slowlookup(Integer search){
		Integer n = null;
		try{
			n = this.postnr[search].code;
		} catch(NullPointerException e) {}
		return n;
	}

	public static void main(String[] args) {
		String s = "postnummer.csv";
		Ziphash zip = new Ziphash(s);
		int input = 18752;
		//System.out.println(zip.postnr[0].code);
		//zip.collisions(input);
		//zip.collisions(13600);
		//zip.collisions(14000);
		Buckets[] b = zip.hashfunction(input);
		int i = zip.lookup(b, 17070, input);
		//System.out.println(i);

		//benchmarktime
		double tid = 0;
		double tidslow = 0;
		 for (int j = 0; j < 80000; j++) {
			
			 double t0 = System.nanoTime();
			 zip.lookup(b, 17070, input);
			 tid += System.nanoTime() - t0;
			 t0 = System.nanoTime();
			 zip.slowlookup(input);
			 tidslow += System.nanoTime() - t0;	
		}
		System.out.println("tid för snabb lookup: " + tid/10000 + "ns");
		System.out.println("långsam tid för lookup: " + tidslow/10000 + "ns");
		
	}
}
