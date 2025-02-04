package uppgift9;

public class City {
	public String name;
	public Connection[] connections;
	
	public class Connection{

		City city;
		City dest;
		int time;

		public Connection(City start, City destination, int distance){
				city = start;
				dest = destination;
				time = distance;

		}
	}

	public City(String name){
		this.name = name;
		connections = new Connection[10];
	}

	public Connection[] Connections(){
		return connections;
	}

	public void connect(City nxt, int dst){
		int i = 0;

		while (connections[i] != null){
			i++;
		}
		connections[i] = new Connection(this, nxt, dst);
	}
}
