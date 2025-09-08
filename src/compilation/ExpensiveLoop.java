package compilation;

public class ExpensiveLoop {
    // Run this with
    // java -cp bin -Xint compilation.ExpensiveLoop 10000000
    // (no optimizations - interpreted)
    // Then run it with optimizations:
    // java -cp bin -Xint compilation.ExpensiveLoop 10000000
    public static void main(String[] args) {
        long sum = 0;
        long start = System.nanoTime();
        int N = Integer.parseInt(args[0]);
        
        for (long i = 0; i < N; i++) {
            sum += i;
        }
        
        long end = System.nanoTime();
        System.out.println("Sum : " + sum + ", Time: " + (end - start)/1e6 + " ms");
    }
}
