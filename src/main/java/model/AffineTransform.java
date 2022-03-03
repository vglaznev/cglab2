package model;

import matrix.Matrix;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class AffineTransform {
    private AffineTransform() {

    }

    public static Matrix IdentityMatrix() {
        return new Matrix(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        });
    }

    public static Matrix TranslationMatrix(double x, double y){
        return new Matrix(new double[][]{
                {1, 0, x},
                {0, 1, y},
                {0, 0, 1}
        });
    }

    public static Matrix RotationMatrix(double angle, boolean degrees) {
        if (degrees) angle = Math.toRadians(angle);
        return new Matrix(new double[][]{
                {cos(angle), -sin(angle), 0},
                {sin(angle), cos(angle), 0},
                {0, 0, 1}
        });
    }

    public static Matrix RotationMatrix(double a, double b) {
        double normCoeff = 1 / Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        return new Matrix(new double[][]{
                {a * normCoeff, -b * normCoeff, 0},
                {b * normCoeff, a * normCoeff, 0},
                {0, 0, 1}
        });
    }

    public static Matrix ScalingMatrix(double kX, double kY) {
        return new Matrix(new double[][]{
                {kX, 0, 0},
                {0, kY, 0},
                {0, 0, 1}
        });
    }

    public static Matrix ReflectionMatrixOverXAxis() {
        return new Matrix(new double[][]{
                {1, 0, 0},
                {0, -1, 0},
                {0, 0, 1}
        });
    }

    public static Matrix ReflectionMatrixOverYAxis() {
        return new Matrix(new double[][]{
                {-1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        });
    }
    public static Matrix ReflectionMatrixOfOriginPoint() {
        return new Matrix(new double[][]{
                {-1, 0, 0},
                {0, -1, 0},
                {0, 0, 1}
        });
    }

}
