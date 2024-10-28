package object_orientation.logistic_map;

public class Main {
    
    public static void main(String[] args) {
        
        Population testPopulation = new Population(0.5, 3.5);

        int n = 20;
        for (int i = 0; i < n; i++) {
            testPopulation.print();
            testPopulation.update();
        }

        System.out.println("The findOscillations method returns " + testPopulation.findOscillations(100));

    }

}
