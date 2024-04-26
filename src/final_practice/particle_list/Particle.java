package final_practice.particle_list;

class Particle {
    private double x;
    private double v;
    public Particle(double x, double v) {
        this.x = x;
        this.v = v;
    }
    public double getPosition() {
        return x;
    }
    public double getVelocity() {
        return v;
    }
    public void move(double dt) {
        x += v * dt;
    }
    public void setVelocity(double v) {
        this.v = v;
    }
    public double getDistance(Particle other) {
        return Math.sqrt((x - other.getPosition())*(x - other.getPosition()));
    }
}
