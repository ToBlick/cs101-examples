package arrays;

import java.awt.image.BufferedImage;
import java.io.File;
import cern.colt.matrix.linalg.SingularValueDecomposition;
import cern.colt.Arrays;
import cern.colt.matrix.DoubleMatrix2D;

import javax.imageio.ImageIO;

import java.awt.Color;

public class Images {

    public static void main(String[] args) throws Exception{
        int[][][] data = readImage("data/cat.jpg");
        saveImage(sepiaFilter(data), "data/sepiacat.jpg");
        saveImage(vibrantFilter(data), "data/vibrantcat.jpg");
        saveImage(airbrushFilter(airbrushFilter(airbrushFilter(data))), "data/fuzzycat.jpg");
        saveImage(greyFilter(data), "data/greycat.jpg");
        saveImage(lowRankFilter(data, 5), "data/lowrankcat_5.jpg");
        saveImage(lowRankFilter(data, 50), "data/lowrankcat_10.jpg");
        saveImage(lowRankFilter(data, 100), "data/lowrankcat_50.jpg");
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
                int r = (int) (0.393 * data[i][j][0] + 0.769 * data[i][j][1] + 0.189 * data[i][j][2]);
                int g = (int) (0.349 * data[i][j][0] + 0.686 * data[i][j][1] + 0.168 * data[i][j][2]);
                int b = (int) (0.272 * data[i][j][0] + 0.534 * data[i][j][1] + 0.131 * data[i][j][2]);

                out[i][j][0] = r > 255 ? 255 : r;
                out[i][j][1] = g > 255 ? 255 : g;
                out[i][j][2] = b > 255 ? 255 : b;
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
                int r = (int) (0.299 * data[i][j][0] + 0.587 * data[i][j][1] + 0.114 * data[i][j][2]);

                out[i][j][0] = r > 255 ? 255 : r;
                out[i][j][1] = r > 255 ? 255 : r;
                out[i][j][2] = r > 255 ? 255 : r;
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
                int r = (int) (1.5 * data[i][j][0]);
                int g = (int) (1.3 * data[i][j][1]);
                int b = (int) (1.1 * data[i][j][2]);

                out[i][j][0] = r > 255 ? 255 : r;
                out[i][j][1] = g > 255 ? 255 : g;
                out[i][j][2] = b > 255 ? 255 : b;
            }
        }

        return out;
    }

    public static int[][][] airbrushFilter(int[][][] data) {
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

        return out;
    }


    public static int[][][] lowRankFilter(int[][][] data, int k) {
        int width = data.length;
        int height = data[0].length;

        int[][][] out = new int[width][height][3];

        double[][] red = new double[width][height];
        double[][] green = new double[width][height];
        double[][] blue = new double[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                red[i][j] = data[i][j][0] / 255.0;
                green[i][j] = data[i][j][1] / 255.0;
                blue[i][j] = data[i][j][2] / 255.0;
            }
        }

        DoubleMatrix2D redMatrix = new cern.colt.matrix.impl.DenseDoubleMatrix2D(red);
        DoubleMatrix2D greenMatrix = new cern.colt.matrix.impl.DenseDoubleMatrix2D(green);
        DoubleMatrix2D blueMatrix = new cern.colt.matrix.impl.DenseDoubleMatrix2D(blue);

        SingularValueDecomposition svdRed = new SingularValueDecomposition(redMatrix);
        SingularValueDecomposition svdGreen = new SingularValueDecomposition(greenMatrix);
        SingularValueDecomposition svdBlue = new SingularValueDecomposition(blueMatrix);

        double[][] redU = svdRed.getU().toArray();
        double[][] redV = svdRed.getV().toArray();
        double[] redS = svdRed.getSingularValues();
        double[][] greenU = svdGreen.getU().toArray();
        double[][] greenV = svdGreen.getV().toArray();
        double[] greenS = svdGreen.getSingularValues();
        double[][] blueU = svdBlue.getU().toArray();
        double[][] blueV = svdBlue.getV().toArray();
        double[] blueS = svdBlue.getSingularValues();

        // A is approx. by A_k = U_k * S_k * V_k^T

        double[][] redApprox = lowRankProduct(redU, redS, redV, k);
        double[][] greenApprox = lowRankProduct(greenU, greenS, greenV, k);
        double[][] blueApprox = lowRankProduct(blueU, blueS, blueV, k);
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int r = (int) Math.round(redApprox[i][j] * 255.0);
                int g = (int) Math.round(greenApprox[i][j] * 255.0);
                int b = (int) Math.round(blueApprox[i][j] * 255.0);

                out[i][j][0] = r > 255 ? 255 : r;
                out[i][j][1] = g > 255 ? 255 : g;
                out[i][j][2] = b > 255 ? 255 : b;
                out[i][j][0] = out[i][j][0] < 0 ? 0 : out[i][j][0];
                out[i][j][1] = out[i][j][1] < 0 ? 0 : out[i][j][1];
                out[i][j][2] = out[i][j][2] < 0 ? 0 : out[i][j][2];
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