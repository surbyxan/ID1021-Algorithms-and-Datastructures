package uppgift6;



public class Queue {
	Node head;
	Node Last;

	private class Node {
		Integer item;
		Node next;
		private Node(Integer item, Node list) {
			this.item = item;
			this.next = list;
		}
	}
	public Queue() {
		head = null;
		Last = null;
	}

	public void enqueue(Integer item) {
		//push till sista elementet
		Node node = new Node(item, null);
		Node n = head;
		if(n == null){
			head = node;
			Last = head;
			return;
		}
		else 
		/*while (n.next != null) {
			n = n.next;
		}
		n.next = node;*/
	
		Last.next = node;
		Last = node;
	}

	public Integer dequeue() {
	//pop första elementet
		if(head == null){
			return null;
		}
		Integer first;
		first = head.item; 
		head = head.next;
		return first;

	}

	public static void main(String[] args) {
		Queue kö = new Queue();
		
		//System.out.println(kö.toString());
		int[] sizes = {1000, 1000, 2000, 4000, 8000, 16000, 32000};
		for (int i : sizes) {
			for (int j = 0; j < i ; j++) {
				kö.enqueue(j);
			}

			double t0 = System.nanoTime();
			
				kö.enqueue(4);
			
			double t1 = System.nanoTime();
			double time = (t1 -t0);
			System.out.println("Tid det tar för dequeue:\t" + time + "\tmed storleken:" + i);
		}
		
	}

	public String toString ()
	{
		String s = "";
		Node n = head;
		while (n.next != null)
		{
		    s = s + n.item + ", ";
		    n = n.next;
		}
		s = s + n.item;
		return s;
	}
}