package uppgift2;

public abstract class stack {
	int[] stack;
	int top;
	int size;

	public abstract void push(int val);
	public abstract int pop();
}
