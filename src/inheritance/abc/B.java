package inheritance.abc;

public class B extends A {

	public B() {
		super();
		System.out.println( "A new B object is born!" );
	}
	
	public B(String str) {
		super(str);
		System.out.println( "A new B object is born!" );
	}

    public void doSomethingDifferent() {
        System.out.println("A B object doing something *different*.");
        // String myString = this.getMessage();
        // System.out.println(myString);
    }
}
