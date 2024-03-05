package arrays;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import cern.colt.matrix.linalg.SingularValueDecomposition;
import cern.colt.matrix.DoubleMatrix2D;

import javax.imageio.ImageIO;

import java.awt.Color;

public class Images {

    public static void main(String[] args) throws Exception{
        int[][][] catArray = readImage("data/cat.jpg");
        saveImage(sepiaFilter(catArray), "data/sepiacat.jpg");
        saveImage(vibrantFilter(catArray), "data/vibrantcat.jpg");
        saveImage(airbrushFilter(catArray, 20), "data/fuzzycat.jpg");
        saveImage(greyFilter(catArray), "data/greycat.jpg");
        saveImage(lowRankFilter(catArray, 5), "data/lowrankcat_5.jpg");
        saveImage(lowRankFilter(catArray, 10), "data/lowrankcat_10.jpg");
        saveImage(lowRankFilter(catArray, 50), "data/lowrankcat_50.jpg");
        saveImage(noiseFilter(catArray, 0.5), "data/noisycat.jpg");
        saveImage(airbrushFilter(noiseFilter(catArray, 0.5), 3), "data/denoisedcat.jpg");

        /* 
        * TODO: Rewrite the vibrant, sepia, and grey filters in one common method that takes an extra 3x3 argument for the coefficients of the color transformation matrix:
        * r' = m11 * r + m12 * g + m13 * b
        * g' = m21 * r + m22 * g + m23 * b  or RGB' = M * RGB
        * b' = m31 * r + m32 * g + m33 * b
        */

    }

    public static int[][][] readImage(String path) throws Exception{
        BufferedImage image = ImageIO.read(new File(path));
        int width = image.getWidth();
        int height = image.getHeight();
        int[][][] values = new int[width][height][3];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(image.getRGB(j, i));
                values[i][j][0] = color.getRed();
                values[i][j][1] = color.getGreen();
                values[i][j][2] = color.getBlue(); 
            }
        }

        return values;
    }

    public static int[][][] sepiaFilter(int[][][] data) {
        int width = data.length;
        int height = data[0].length;

        int[][][] out = new int[width][height][3];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Calculate sepia color:    red                green                   blue
                int r = (int) Math.round(0.393 * data[i][j][0] + 0.769 * data[i][j][1] + 0.189 * data[i][j][2]);
                int g = (int) Math.round(0.349 * data[i][j][0] + 0.686 * data[i][j][1] + 0.168 * data[i][j][2]);
                int b = (int) Math.round(0.272 * data[i][j][0] + 0.534 * data[i][j][1] + 0.131 * data[i][j][2]);

                out[i][j][0] = cutToByte(r);
                out[i][j][1] = cutToByte(g);
                out[i][j][2] = cutToByte(b);
            }
        }

        return out;
    }

    public static int[][][] greyFilter(int[][][] data) {
        int width = data.length;
        int height = data[0].length;

        int[][][] out = new int[width][height][3];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Calculate grey color:    red                green                   blue
                int r = (int) Math.round(0.299 * data[i][j][0] + 0.587 * data[i][j][1] + 0.114 * data[i][j][2]);

                out[i][j][0] = cutToByte(r);
                out[i][j][1] = out[i][j][0];
                out[i][j][2] = out[i][j][0];
            }
        }

        return out;
    }

    public static int[][][] vibrantFilter(int[][][] data) {
        int width = data.length;
        int height = data[0].length;

        int[][][] out = new int[width][height][3];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Calculate vibrant color:
                int r = (int) Math.round(1.5 * data[i][j][0]);
                int g = (int) Math.round(1.3 * data[i][j][1]);
                int b = (int) Math.round(1.1 * data[i][j][2]);

                out[i][j][0] = cutToByte(r);
                out[i][j][1] = cutToByte(g);
                out[i][j][2] = cutToByte(b);
            }
        }

        return out;
    }

    public static int[][][] noiseFilter(int[][][] data, double sigma) {
        int width = data.length;
        int height = data[0].length;

        int[][][] out = new int[width][height][3];

        Random rng = new Random();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int c = 0; c < 3; c++) {
                    int color = (int) Math.round(data[i][j][c] * ( 1 + sigma * rng.nextGaussian() ));
                    out[i][j][c] = cutToByte(color);
                }
            }
        }

        return out;
    }

    public static int[][][] airbrushFilter(int[][][] data, int iterations) {
        int width = data.length;
        int height = data[0].length;

        int[][][] out = new int[width][height][3];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < 3; k++) {
                    int iplus = i + 1 < width ? i + 1 : width - 1;
                    int jplus = j + 1 < height ? j + 1 : height - 1;
                    int iminus = i - 1 > 0 ? i - 1 : 0;
                    int jminus = j - 1 > 0 ? j - 1 : 0;
                    out[i][j][k] = (int) Math.round((1 * data[iminus][jplus][k]  + 2 * data[i][jplus][k]  + 1 * data[iplus][jplus][k] +
                                                     2 * data[iminus][j][k]      + 4 * data[i][j][k]      + 2 * data[iplus][j][k] +
                                                     1 * data[iminus][jminus][k] + 2 * data[i][jminus][k] + 1 * data[iplus][jminus][k]) / 16.0 );
                }
            }
        }

        if (iterations > 1) {
            return airbrushFilter(out, iterations - 1);
        } else {
            return out;
        }
    }

    public static int[][][] lowRankFilter(int[][][] data, int k) {
        int width = data.length;
        int height = data[0].length;

        int[][][] out = new int[width][height][3];

        double[][][] colors = new double[3][width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int c = 0; c < 3; c++) {
                    colors[c][i][j] = data[i][j][c] / 255.0;
                }
            }
        }

        DoubleMatrix2D[] colorMatrices = new DoubleMatrix2D[3];
        SingularValueDecomposition[] svds = new SingularValueDecomposition[3];
        for (int i = 0; i < 3; i++) {
            colorMatrices[i] = new cern.colt.matrix.impl.DenseDoubleMatrix2D(colors[i]);
            svds[i] = new SingularValueDecomposition(colorMatrices[i]);
        }

        double[][][] colorApproxs = new double[3][width][height];
        for (int i = 0; i < 3; i++) {
            double[][] U = svds[i].getU().toArray();
            double[][] V = svds[i].getV().toArray();
            double[] S = svds[i].getSingularValues();
            colorApproxs[i] = lowRankProduct(U, S, V, k);
        }
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int c = 0; c < 3; c++) {
                    int r = (int) Math.round(colorApproxs[c][i][j] * 255.0);
                    out[i][j][c] = cutToByte(r);
                }
            }
        }

        return out;
    }

    public static void saveImage(int[][][] data, String path) throws Exception {
        int width = data.length;
        int height = data[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
              int rgb = (int) data[j][i][0];
              rgb = (rgb << 8) + data[j][i][1]; 
              rgb = (rgb << 8) + data[j][i][2];
              image.setRGB(i, j, rgb);
           }
        }
      
        File outputFile = new File(path);
        ImageIO.write(image, "jpg", outputFile);
    }

    public static int cutToByte(int value) {
        return value > 255 ? 255 : (value < 0 ? 0 : value);
    }

    /* Linear Algebra below */

    public static double[] matrixVectorProduct(double[][] matrix, double[] vector) {
        double[] result = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j] * vector[j];
            }
            result[i] = sum;
        }
        return result;
    }

    public static double euclideanNorm(double[] vector) {
        double sum = 0;
        for (int i = 0; i < vector.length; i++) {
            sum += vector[i] * vector[i];
        }
        return Math.sqrt(sum);
    }

    public static double[] randomNormedVector(int n) {
        double[] vector = new double[n];
        for (int i = 0; i < n; i++) {
            vector[i] = Math.random();
        }
        for (int i = 0; i < n; i++) {
            vector[i] = vector[i] / euclideanNorm(vector);
        }
        return vector;
    }

    public static double[][] matrixMatrixProduct(double[][] matrix1, double[][] matrix2) {
        double[][] result = new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                double sum = 0;
                for (int k = 0; k < matrix1[i].length; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public static double[][] lowRankProduct(double[][] U, double[] S, double[][] V, int k) {
        double[][] result = new double[U.length][V[0].length];
        for (int i = 0; i < U.length; i++) {
            for (int j = 0; j < V[0].length; j++) {
                double sum = 0;
                for (int l = 0; l < k; l++) {
                    sum += U[i][l] * S[l] * V[j][l];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public static double dotProduct(double[] u, double[] v) {
        double sum = 0;
        for (int i = 0; i < u.length; i++) {
            sum += v[i] * u[i];
        }
        return sum;
    }

    public static double[] scalarVectorProduct(double scalar, double[] vector) {
        double[] result = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            result[i] = scalar * vector[i];
        }
        return result;
    }

    public static double[] addVectors(double[] u, double[] v) {
        double[] result = new double[u.length];
        for (int i = 0; i < u.length; i++) {
            result[i] = u[i] + v[i];
        }
        return result;
    }

    public static double[][] transpose(double[][] matrix) {
        double[][] result = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }
}