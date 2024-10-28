package object_orientation.logistic_map;

public class Population {

    private double p = 1.0;
    private double r = 3.0;
    
    public Population(double p0, double r) {
        p = p0;
        this.r = r;
    }

    public double getValue() {
        return p;
    }

    public void update() {
        p = r * p * (1 - p);
    }

    public void print() {
        System.out.println("The current population value is " + p);
    }

    public int findOscillations(int n) {
        double[] populationTrajectory = new double[2*n+1];
        populationTrajectory[0] = this.p;
        for (int i = 0; i < 2*n; i++) {
            this.update();
            populationTrajectory[i+1] = this.getValue();
        }
        double[] lastN = new double[n];
        for (int i = 0; i < n; i++) {
            lastN[i] = populationTrajectory[n-i];
        }
        if (variance(lastN) < 0.001) {
            return 1;
        }
        double[] lastNodd = new double[n/2];
        double[] lastNeven = new double[n/2];
        int j = 0;
        int i = 0;
        while (j < n/2) {
            lastNeven[j] = populationTrajectory[2*n-i];
            lastNodd[j] = populationTrajectory[2*n-i-1];
            j++;
            i += 2;
        }
        if (variance(lastNeven) < 0.001 && variance(lastNodd) < 0.001) {
            return 2;
        }
        return 0;
    }

    private static double mean(double[] p) {
        double sum = 0;
        double n = p.length;
        for (int i = 0; i < p.length; i++) {
            sum += p[i];
        }
        return sum / n;
    }

    private static double variance(double[] p) {
        double m = mean(p);
        double sum = 0;
        for (int i = 0; i < p.length; i++) {
            sum += (p[i] - m) * (p[i] - m);
        }
        return sum /p.length;
    }
}
