package branching;

public class Ternary {

    public static void main(String[] args) {
        boolean c1 = false;
        boolean c2 = true;
        
        // c1 true -> 2
        // c1 false && c2 true -> 3
        // c1 false && c2 false -> 1
        
        int a = c1 ? 2 : (c2 ? 3 : 1);
        System.out.println(a);
    }
    
}
