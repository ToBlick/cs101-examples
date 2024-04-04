package midterm_1_practice;

public class BaselProblem {

    public static double baselProblem(double tol) {
        double r = 0;
        double r_old = 0;
        int n = 0;

        while (Math.abs(r - r_old) >= tol * r_old || n == 0) {
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
