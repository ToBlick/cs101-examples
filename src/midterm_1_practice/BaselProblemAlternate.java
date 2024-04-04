package midterm_1_practice;

public class BaselProblemAlternate {

    public static double baselProblem(double tol) {
        double r = 1;
        double r_old = 0;
        int n = 1;

        while (Math.abs(r - r_old) >= tol * r_old) {
            n++;
            r_old = r;
            r += 1.0/n/n;
        }
        return r;
    }
    
    public static void main(String[] args) {
        System.out.println(baselProblem(1e-15));
        System.out.println(Math.PI*Math.PI/6);
    }
}
