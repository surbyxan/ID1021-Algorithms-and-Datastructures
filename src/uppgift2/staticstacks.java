package uppgift2;
	
	public class staticstacks extends stack {
		int[] stack;
		int top = 0;

		public staticstacks(int size) {
			stack = new int[size];
			}

		public void push(int val) throws IndexOutOfBoundsException {
			if (top >= stack.length) {
				throw new IndexOutOfBoundsException("stack overflow");
			}
			stack[top] = val;
			top++;
		}
		public int pop() throws IndexOutOfBoundsException{
			if (top == 0) {
				throw new IndexOutOfBoundsException("No elements in stack");
			}
			top--;
			return stack[top];
		}

		
		public static void main(String[] args) {
		staticstacks s = new staticstacks(16);
		s.push(32);
		s.push(33);
		s.push(34);
		System.out.println("pop : " + s.pop());
		System.out.println("pop : " + s.pop());
		System.out.println("pop : " + s.pop());
		}
		}
		
	

