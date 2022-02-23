import java.util.Arrays;
import java.util.function.BinaryOperator;

public class MatrixOperation extends Matrix {

    private MatrixOperation() {

    }

    private static Matrix elementByElementOperation(Matrix a, Matrix b, BinaryOperator<Double> operator) {
        Matrix result = new Matrix(a);
        result.elementByElementOperation(b, operator);
        return result;
    }

    public static Matrix sum(Matrix a, Matrix b) {
        return elementByElementOperation(a, b, (arg1, arg2) -> arg1 + arg2);
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        return elementByElementOperation(a, b, (arg1, arg2) -> arg1 - arg2);
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix result = new Matrix(a);
        result.multiply(b);
        return result;
    }

    private static void checkSizesOfMatrices(Matrix... matrices) {
        if (!Arrays.stream(matrices).allMatch(matrix -> matrix.size == matrices[0].size)) {
            throw new IllegalArgumentException("Index out of matrix size bounds!");
        }
    }

    public static Matrix sumAll(Matrix... matrices) {
        checkSizesOfMatrices(matrices);
        Matrix result = new Matrix(matrices[0].size);
        Arrays.stream(matrices).forEachOrdered(result::add);
        return result;
    }

    public static Matrix multiplyAll(Matrix... matrices) {
        checkSizesOfMatrices(matrices);
        Matrix result = getEyeMatrix(matrices[0].size);
        Arrays.stream(matrices).forEachOrdered(result::multiply);
        return result;
    }
}
