package compilation;

public class ConstantFolding {
    public static void main(String[] args) {
        final int x = 1_000;
        final int y = 2_000;
        
        // This will be folded at compile time into a constant (ldc 2000 - no imul)
        // check with 'javap -c bin/compilation/ConstantFolding.class'
        int a = x * y;
        
        System.out.println("a = " + a);
    }
}
