package inheritance.abc;

public class A {
    public A() {
        System.out.println( "A new A object is born!" );
        this.message = "Hello!";
    }

    private String message;

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