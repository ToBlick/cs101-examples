package inheritance.abc;

public class TestABC {

    public static void main(String[] args) {

        B myB = new B();
        System.out.println( myB.getMessage() );

        // C myC = new C( "Welcome!!" );
        // System.out.println( myC.getMessage() );

        // if (myC instanceof C && myC instanceof B && myC instanceof A) {
        //     System.out.println("It's a C, B, and A object all-in-one!");
        // }

        A[] myObjs = { new A(), new B(), new C("Welcome!!") };
        for (A myA : myObjs) {
            String message = myA.getMessage();
            System.out.println(message);
        }

        A myA = new A();
        myA.doSomething();
        myB.doSomething();

        for (A myObj : myObjs) {
            myObj.doSomething(); // works fine, since they both have this method from A
        
            // have any B-type objects do their special behavior...
            if (myObj instanceof B) {
                // first cast to B-type
                B sameObjsAsBType = (B) myObj;
                // now call B-type's unique method
                sameObjsAsBType.doSomethingDifferent();
            }
        }


    }

}
