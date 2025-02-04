package uppgift9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Map {
	
	public City[] cities;
	private final int mod = 541;

		public Map(String file) {
			cities = new City[mod];
			try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] row = line.split(",");
					City one = lookup(row[0]);
					City two = lookup(row[1]);
					int dist = Integer.valueOf(row[2]);
					one.connect(two, dist);
					two.connect(one, dist);
					}
			} catch (Exception e) {
				System.out.println(" file " + file + " not found or corrupt");
			}
	}
	
	public City lookup(String name){
		int index = hash(name, mod);
		City lookupCity = new City(name);

		for (int i = index; i < cities.length; i++) {
			if (cities[i] == null) {
				cities[i] = lookupCity;
				return cities[i];
			}
			else if(name.equals(cities[i].name)){
				return cities[i];
			}
		}
		return null;
	}

	private static Integer hash(String name, int mod) {
		int hash = 0;
		for (int i = 0; i < name.length(); i++) {
		hash = (hash*31 + name.charAt(i)) % mod;
		}
		return hash;
		}

		
}
