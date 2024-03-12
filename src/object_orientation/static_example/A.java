package static_example;

public class A {
	public A() {
		counter++;
	}
	
	static int counter = 0;
	String message = "hello";
	
	public static void printSomething() {
		//System.out.println(this.message);
	}
	
	public void printSomethingElse() {
		System.out.println(A.counter);
		System.out.println(this.counter);
	}
}
