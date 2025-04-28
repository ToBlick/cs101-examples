package inheritance.abc;

public class A {
	
	public A() {
		System.out.println( "A new A object is born!" );
		this.message = "Hello!";
	}
	
    public A(int i) {
        System.out.println( "A new A object is born!" );
        this.message = "Hello!";
    }
    
    public A(String s) {
    	this.message = s;
        System.out.println( "A new A object is born!" );
    }

    protected String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        if (message.length() > 0) this.message = message;
    }

    public void doSomething() {
        System.out.println("An A object doing something.");
    }
}