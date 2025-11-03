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

	public void doSomething() {
		System.out.println("A B object doing something.");
	}

    public void doSomethingDifferent() {
        System.out.println("A B object doing something *different*.");
    }

	public void doThings(){
		this.doSomething();
		super.doSomething();
		this.doSomethingDifferent();
	}
}
