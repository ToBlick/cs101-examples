package inheritance.abc;

public class TestABC {

    public static void main(String[] args) {
    	
//    	A myA = new A("My message is A.");
//    	System.out.println( myA.getMessage() );
    	
//    	B myB = new B("My message is B.");
//        System.out.println( myB.getMessage() );

//        C myC = new C("My message is C.");
//        System.out.println( myC.getMessage() );

//        C myC = new C( "Welcome!!" );
//        System.out.println( myC.getMessage() );

//        if (myC instanceof C && 
//        		myC instanceof B && 
//        			myC instanceof A) {
//             System.out.println("It's a C, B, and A object all-in-one!");
//        }
//
//        A[] myObjs = { new A(), new B(), new C("Welcome!!") };
//        for (A a : myObjs) {
//            String message = a.getMessage();
//            System.out.println(message);
//        }
 
//        A myA = new A();
//        B myB = new B();
//        myA.doSomething();
//        myB.doSomething();
    	
        A[] myObjs = { new A(), new B(), new C("Welcome!!") };
//        for (A myObj : myObjs) {
//            myObj.doSomething(); // works fine, since they both have this method from A
//        
//            // have any B-type objects do their special behavior...
//            if (myObj instanceof B) {
//                // call B-type's unique method
//                ((B) myObj).doSomethingDifferent();
//            }
//        }
        
        B[] myBArray = new B[2];
        int i = 0;
        for (A a : myObjs) {
        	if (a instanceof B) {
        		myBArray[i] = (B) a;
        		i++;
        	}
        }
        
        for (B b : myBArray) {
        	b.doSomethingDifferent();
        }
    }

}
