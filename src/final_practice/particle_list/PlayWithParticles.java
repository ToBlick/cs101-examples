package final_practice.particle_list;

public class PlayWithParticles {

    public static void main(String[] args) {
        Particle[] particles = new Particle[10];
        for (int i = 0; i < particles.length; i++) {
            particles[i] = new Particle(Math.random(), 0); // for Coulomb
            // particles[i] = new Particle(Math.random(), Math.random()); // for collisions
        }
        Simulation sim = new Simulation(particles, 0.005);
        for (int i = 0; i < 100; i++) {
            sim.updatePositions();
            // sim.updateVelocities();
            sim.updateVelocitiesCoulomb();
            sim.sortParticles();
            sim.printParticles();
            System.out.println();
        }
    }

}
