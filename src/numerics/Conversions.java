package numerics;

public class Conversions {
    public static void main(String[] args) {
        System.out.println("1/3 == " + 1/3); // 0
        System.out.println("1/3.0 == " + 1/3.0); // 0.33333...
        System.out.println("(double) 1/3 == " + (double) 1/3); // 0.3333...
        System.out.println("(double) (1/3) == " + (double) (1/3)); // 0.0
        int i = (int) 12.3;
        System.out.println("i == " + i); // 12
        // int j = 12.3; Does not work 
        float f = 12;
        System.out.println("f == " + f); // 12.0
        System.out.println("sin(i) == " + Math.sin(i)); // -0.5365729180004349

        int big = 1234567890;
        float approxbig = big;
        System.out.println("big == " + big);             // 1234567890
        System.out.println("approxbig == " + approxbig); // 1234567940

        byte b = 100;
        System.out.println("(byte) (b*10)  == " + (byte) (b*10)); // -24

        System.out.println("(int) 1e20f  == " + (int) (1e20f)); // 2147483647
        System.out.println("(float) -1e100  == " + (float) -1e100); // -Infinity
        System.out.println("(float) 1e-50  == " + (float) 1e-50); // 0.0
    }
}
