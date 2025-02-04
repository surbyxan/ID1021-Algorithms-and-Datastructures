package uppgift7;

import java.util.Random;

public class BinaryTree {
	private class Node {
		private Integer value;
		private Node left, right;

		private Node(Integer value) {
			this.value = value;
			this.left = this.right = null;
		}
		
		public void add(Integer value){
			if (this.value == value) 
			return;
			
			if (this.value > value) {
				if (this.left != null) 
				this.left.add(value);
				else
				this.left = new Node(value);
			}
			if (this.value < value) {
				if (this.right != null) 
				this.right.add(value);
				else 
				this.right = new Node(value);
			}	
		}
		public void print() {
			if(left != null)
				left.print();
			System.out.println(value);
			if(right != null)
				right.print();
			}
	}	

	private Node root;

	public BinaryTree() {
		root = null;
	}

	public void add(Integer value){
		if(root == null)
			root = new Node(value);
			else
			root.add(value);
		}
		
	boolean lookup(Integer key){
		return lookup(root, key);
	}

	boolean lookup(Node n, Integer key){
		if (n == null) {
			return false;
		}
		if (n.value == key) {
				return true;
			}
			if (n.value > key) 
				return lookup(n.left, key);

			if (n.value < key) 
				return lookup(n.right, key);	

		return false;
	}

	public static void main(String[] args) {
		BinaryTree träd = new BinaryTree();
		Random rnd = new Random();
		int loop = 11;
		double time = 0;

		int[] sizes = {10000, 1000, 2000, 4000, 8000, 16000, 32000};
		//for (int i : sizes) {
			for (int j = 0; j < loop; j++) {
				träd.add(25);
				int m = rnd.nextInt(50);
				träd.add(m);
			}
			System.out.println("vänster");
			träd.root.left.print();
			System.out.println("höger");
			träd.root.right.print();
			System.out.println("hela trädet");
			träd.root.print();

			/*int r = rnd.nextInt(i/2);

			double t0 = System.nanoTime();
			
			boolean find = träd.lookup(r);
			
			double t1 = System.nanoTime();
			time =+ (t1 -t0);
			double t = (t1 -t0);
			System.out.println("Tid det tar för lookup:\t" + t + "\tmed storleken:" + i);
		}*/
		

	}


}

