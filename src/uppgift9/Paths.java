package uppgift9;

public class Paths {
	City[] path;
	int sp;

	public Paths() {
		path = new City[54];
		sp = 0;
	}

	private Integer shortest(City from, City to, Integer max) {
		if ((max != null) && (max < 0)) {
			return null;
		}
		if (from == to) {
			return 0;
		}		
		for (int i = 0; i < sp; i++) {
			if (path[i] == from){
				return null;
			}
		}

		path[sp++] = from;
		Integer shrt = null;
		for (int i = 0; i < from.connections.length; i++) {
			if (from.connections[i] != null) {
				City.Connection conn = from.connections[i];
				Integer distance = shortest(conn.dest, to, (max != null) ? max - conn.time : null);
	
				if ((distance != null) && ((shrt == null) || (shrt > distance + conn.time))) {
					shrt = distance + conn.time;
				}
				if((shrt != null) && (((max == null)) || (max > shrt))){
					max = shrt;
				}
			}
		}
		path[sp--] = null;
		return shrt;
	}
	public static void main(String[] args) {
		Map map = new Map("trains.csv");
		Paths path = new Paths();

		//String[] input = new String[4];
		String from = "Malmö";
		String to = "Kiruna";
		Integer max = 10000;
				
		long t0 = System.nanoTime();
		Integer dist = path.shortest(map.lookup(from), map.lookup(to), max);
		long time = (System.nanoTime() - t0)/1000;
		
		System.out.println("shortest: " + dist + " min (" + time + " us)");
	}
}
