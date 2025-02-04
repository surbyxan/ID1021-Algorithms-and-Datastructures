package uppgift9;

public class Naive {
	
	private static Integer shortest(City from, City to, Integer max) {
		if ((max < 0) && (max != null))
			return null;
		if (from == to)
			return 0;

		Integer shrt = null;

		for (int i = 0; i < from.connections.length; i++) {
			if (from.connections[i] != null) {
				City.Connection conn = from.connections[i];
				Integer distance = shortest(conn.dest, to, (max != null) ? max - conn.time : null);
				//Integer temp = conn.time;
	
				if ((distance != null) && ((shrt == null) || (shrt > distance + conn.time))) {
					shrt = distance + conn.time;
					
				}
				if((shrt != null) && (((max == null)) || (max > shrt))){
					max = shrt;
				}
			}
		}
		return shrt;
	}

	public static void main(String[] args) {
		Map map = new Map("trains.csv");

		String[] input = new String[4];
		input[0] = "Umeå";
		input[1] = "Göteborg";
		input[2] = "800";

		String from = input[0];
		String to = input[1];
		Integer max = Integer.valueOf(input[2]);



		long t0 = System.nanoTime();
		Integer dist = shortest(map.lookup(from), map.lookup(to), max);
		long time = (System.nanoTime() - t0)/1000000;
		
		System.out.println("shortest: " + dist + " min (" + time + " ms)");
	}
}
