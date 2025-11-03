package inheritance.abc;

public class C extends B {

    public C() {
      super(); // call B's no-args constructor
    }

    // an overloaded constructor (C-specific) that accepts a message
    public C( String message) {
        super(message); // call B's no-args constructor
        System.out.println( "A new C object is born!" );
    }
}
