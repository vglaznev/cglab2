package model;

import matrix.Matrix;

public record HomogeneousCoordinates(double u1, double u2, double u3) {
    public double getX() {
        return u1 / u3;
    }

    public double getY() {
        return u2 / u3;
    }

    public static HomogeneousCoordinates of(double[] array) {
        if (array.length != 3) {
            throw new IllegalArgumentException("Array must include only 3 values!");
        } else if (array[2] == 0) {
            throw new IllegalArgumentException("Third component cannot be 0!");
        }
        return new HomogeneousCoordinates(array[0], array[1], array[2]);
    }

    public static HomogeneousCoordinates multiplyWithMatrix(Matrix matrix, HomogeneousCoordinates coordinates) {
        if (matrix.getSize() != 3) {
            throw new IllegalArgumentException("Matrix must have size equaled 3!");
        }

        double[] result = new double[3];
        for (int i = 1; i <= 3; i++) {
            result[i - 1] =
                    matrix.getElement(i, 1) * coordinates.u1() +
                    matrix.getElement(i, 2) * coordinates.u2() +
                    matrix.getElement(i, 3) * coordinates.u3();
        }
        return HomogeneousCoordinates.of(result);
    }
}
