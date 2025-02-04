package uppgift5;

import java.util.Random;


class linkedlist {
	Cell first;

	private class Cell {
		int head;
		Cell tail;
		
		Cell(int val, Cell tl){
			head = val;
			tail = tl;
		}
	}	

		void add(int item){
			Cell cell = new Cell(item, first);
			first = cell;
		}

		int length(){
			if (first == null) {
				return 0;
			}
			Cell nxt = first;
			int len = 1;

			while (nxt.tail != null) {  
				len ++;
				nxt = nxt.tail;
			}
			return len;
		}

		boolean find(int item){
			Cell nxt = first;
			while (nxt != null){
				if (nxt.head == item) {
					return true;
				}
			nxt = nxt.tail;
			}
			return false;
		}

		void remove(int item){
			Cell nxt = first;
			Cell prev = null;
			if (nxt.head == item) {
				first = first.tail;
				return;
			}
			while (nxt.head != item) {
				if (nxt.tail == null) {
					return;
				}
				prev = nxt;
				nxt = nxt.tail;
			}
			prev.tail = nxt.tail;
		}

		public void append(linkedlist b) {
			Cell nxt = first;
			while (nxt.tail != null) {
				nxt = nxt.tail;
				}
			nxt.tail = b.first;
			
		}
		
	linkedlist(int n) {
			Cell last = null;
			for (int i = 0; i < n; i++) {
				last = new Cell(i, last);
				}
				first = last;
			}
	
	public static void main(String[] args) {
		int loop = 320;
		Random rnd = new Random();
		int n = rnd.nextInt(loop);
			linkedlist lista = new linkedlist(n+1);
			linkedlist listb = new linkedlist(loop);
			int time = 0;
			
			System.out.print("Listorna innan append" + "\t");
			System.out.print("Lista A:" + lista.toString() + "\t");
			System.out.println("Lista B:" + listb.toString());

			//int len = listb.length();
			//listb.remove(4);
						
			 	long t1 = System.nanoTime();
				listb.append(lista);
				long t2 = System.nanoTime();
				time += (t2 - t1);
			
		
			
			System.out.println("Listan efter append\t" + lista.toString());
			System.out.print("tiden det tog att lägga ihop listorna:" + "\t");
			System.out.println(time + "ns");
	}
	
public String toString ()
	{
		String s = "";
		Cell n = first;
		while (n.tail != null)
		{
		    s = s + n.head + ", ";
		    n = n.tail;
		}
		s = s + n.head;

		return s;
	}

}	