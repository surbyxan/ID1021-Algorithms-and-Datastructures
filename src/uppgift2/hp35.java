package uppgift2;

import java.io.*;
public class hp35 {
	public static void main(String[] args) throws IOException {
		stack stack = new DynamicStack();
		System.out.println("HP-35 pocket calculator");
		boolean run = true;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(run) {
			System.out.print(" > ");
			String input = br.readLine();
			switch (input) {
			case "+": {
				int x = stack.pop();
				int y = stack.pop();
				stack.push(x + y);
				break; 
			}
			case "-": {
				int x = stack.pop();
				int y = stack.pop();
				stack.push(x - y);
				break;
			}
			case "*": {
				int x = stack.pop();
				int y = stack.pop();
				stack.push(x * y);
				break;
			}
			case "/": {
				int x = stack.pop();
				int y = stack.pop();
				stack.push(x / y);
				break;
			}	
			case "":
				run = false;
				break;
			default:
			Integer nr = Integer.parseInt(input);
			stack.push(nr);
			break;
			}
		}
		System.out.printf("the result is: %d\n\n", stack.pop());
		System.out.printf("I love reversed polish notation, don't you?\n");
	}
}


