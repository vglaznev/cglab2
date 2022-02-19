package matrix;

import java.util.function.BinaryOperator;

public class Matrix {
    private double[][] array;
    private int size;

    private void fillMatrix(double value) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = value;
            }
        }
    }

    public Matrix(int size) {
        array = new double[size][size];
        this.size = size;
        fillMatrix(0);
    }

    private void checkIndexOutOfBounds(int i, int j) {
        if (i >= size || j >= size) {
            throw new IllegalArgumentException("Index out of matrix size bounds!");
        }
    }

    public void setElement(int i, int j, double element) {
        checkIndexOutOfBounds(i, j);
        array[i][j] = element;
    }

    public double getElement(int i, int j) {
        checkIndexOutOfBounds(i, j);
        return array[i][j];
    }

    private static void checkSizesOfMatrixes(int size1, int size2) {
        if (size1 != size2) {
            throw new IllegalArgumentException("Index out of matrix size bounds!");
        }
    }

    /*private void elementByElementOperation(Matrix other, BinaryOperator<Double> operator) {
        checkSizesOfMatrixes(size, other.size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = operator.apply(array[i][j], other.array[i][j]);
            }
        }
    }

    public void add(Matrix other) {
        elementByElementOperation(other, (arg1, arg2) -> arg1 + arg2);
    }

    public void substract(Matrix other) {
        elementByElementOperation(other, (arg1, arg2) -> arg1 - arg2);
    }

    public void multiply(Matrix other){
        checkSizesOfMatrixes(size, other.size);

    }*/
}
