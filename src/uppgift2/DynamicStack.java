package uppgift2;

public class DynamicStack extends stack {

	int[] stack;
	int top = 0;
	int size = 4;
	public DynamicStack() {
	stack = new int[size];
	}

	public void push(int val) {
		if (top == stack.length) {
			int[] tempstack = new int[stack.length*2];
			for (int i = 0; i < stack.length; i++) {
				tempstack[i] = stack[i];
			}
			stack = new int[tempstack.length];

			for (int i = 0; i < tempstack.length; i++) {
				stack[i] = tempstack[i];
			}
			tempstack = null;
		}
		stack[top] = val;
		top++;
	}
	
	public int pop(){
		if (top == stack.length/4) {
			int[] tempstack = new int[stack.length/4];

			for (int i = 0; i < tempstack.length; i++) {
				tempstack[i] = stack[i];
			}
			stack = new int[tempstack.length];

			for (int i = 0; i < tempstack.length; i++) {
				stack[i] = tempstack[i];
			}
			tempstack = null;
		}

		top--;
		return stack[top];
	}
}


