package matrix;

import java.util.Arrays;
import java.util.function.BinaryOperator;

public class Matrix {
    protected double[][] array;
    protected int size;

    private void fillMatrix(double value) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = value;
            }
        }
    }

    public Matrix() {
        array = null;
        size = 0;
    }

    public Matrix(Matrix other) {
        array = other.array;
        size = other.size;
    }

    public Matrix(int size) {
        array = new double[size][size];
        this.size = size;
        fillMatrix(0);
    }

    public Matrix(double[][] array) {
        if (array == null || array[0].length != array.length) {
            throw new IllegalArgumentException();
        }
        this.array = array;
        size = array.length;
    }

    public static Matrix getEyeMatrix(int size) {
        Matrix eye = new Matrix(size);
        for (int i = 0; i < size; i++) {
            eye.array[i][i] = 1;
        }
        return eye;
    }

    private void checkIndexOutOfBounds(int i, int j) {
        if (i > size || j > size || i <= 0 || j <= 0) {
            throw new IndexOutOfBoundsException("Index out of matrix size bounds!");
        }
    }

    public void setElement(int i, int j, double element) {
        checkIndexOutOfBounds(i, j);
        array[i - 1][j - 1] = element;
    }

    public double getElement(int i, int j) {
        checkIndexOutOfBounds(i, j);
        return array[i - 1][j - 1];
    }

    public int getSize(){
        return size;
    }

    private void checkSizesOfMatrices(Matrix other) {
        if (size != other.size) {
            throw new IllegalArgumentException("Index out of matrix size bounds!");
        }
    }

    protected void elementByElementOperation(Matrix other, BinaryOperator<Double> operator) {
        checkSizesOfMatrices(other);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = operator.apply(array[i][j], other.array[i][j]);
            }
        }
    }

    public void add(Matrix other) {
        elementByElementOperation(other, (el1, el2) -> el1 + el2);
    }

    public void subtract(Matrix other) {
        elementByElementOperation(other, (el1, el2) -> el1 - el2);
    }

    public void multiply(Matrix other) {
        checkSizesOfMatrices(other);
        double[][] newArray = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    newArray[i][j] += array[i][k] * other.array[k][j];
                }
            }
        }
        this.array = newArray;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(array).replace("], ", "]\n");
    }

    public static void main(String[] args) {
        Matrix one = new Matrix(new double[][]{{4, -1, 3}, {4, -2, -6}, {2, 0, 3}});
        Matrix two = new Matrix(new double[][]{{5, 3, -7}, {-1, 6, -3}, {2, -4, 1}});

        System.out.println(one);
        System.out.println(two);
        System.out.println(MatrixOperation.multiply(one, two));


    }
}
