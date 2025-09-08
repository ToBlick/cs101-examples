package compilation;

public class NoConstantFolding {
    public static void main(String[] args) {
        final int x = 1_000;
        final int y = Integer.parseInt(args[0]);
        
        // This cannot get folded - it is unknown at compile time
        int a = x * y;
        
        System.out.println("a = " + a);
    }
}
