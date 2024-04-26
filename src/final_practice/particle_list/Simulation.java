package final_practice.particle_list;

public class Simulation {

    private Particle[] particles;
    private double dt;

    public Simulation(Particle[] particles, double dt) {
        this.particles = particles;
        this.dt = dt;
    }

    public void updatePositions() {
        for (Particle p : particles) {
            p.move(dt);
            if (p.getPosition() < 0) {
                p.setVelocity(-p.getVelocity());
            } else if (p.getPosition() > 1) {
                p.setVelocity(-p.getVelocity());
            }
        }
    }

    // collisions
    public void updateVelocities() {
        for (int i = 0; i < particles.length; i++) {
            for (int j = i + 1; j < particles.length; j++) {
                double distance = particles[i].getDistance(particles[j]);
                if (distance < 0.01) {
                    double temp = particles[i].getVelocity();
                    particles[i].setVelocity(particles[j].getVelocity());
                    particles[j].setVelocity(temp);
                }
            }
        }
    }
    // Coulomb force
    public void updateVelocitiesCoulomb() {
        for (int i = 0; i < particles.length; i++) {
            for (int j = 0; j < particles.length; j++) {
                if (i == j) {
                    continue;
                }
                double distance = particles[i].getDistance(particles[j]);
                Particle p1 = particles[i];
                Particle p2 = particles[j];
                int sign;
                if (p1.getPosition() < p2.getPosition()) {
                    // p1 is to the left of p2
                    sign = -1;
                } else {
                    // p1 is to the right of p2
                    sign = 1;
                }
                double force = - 0.01 / (distance * distance);
                p1.setVelocity(p1.getVelocity() - 0.5 * sign * force * dt);
                p2.setVelocity(p2.getVelocity() + 0.5 * sign * force * dt);
            }
        }
    }

    public void sortParticles() {
        for (int i = 0; i < particles.length; i++) {
            for (int j = i + 1; j < particles.length; j++) {
                if (particles[i].getPosition() > particles[j].getPosition()) {
                    Particle temp = particles[i];
                    particles[i] = particles[j];
                    particles[j] = temp;
                }
            }
        }
    }

    public void printParticles() {
        int counter = 0;
        System.out.print("|");
        for (double d = -dt; d < 1+dt; d += 0.01) {
            if (counter < particles.length && Math.abs(particles[counter].getPosition() - d) < 0.01) {
                System.out.print("o");
                counter++;
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("|");
    }

}
