package matrix;

import java.util.function.BinaryOperator;

public class MatrixOperation extends Matrix{

    private static void checkSizesOfMatrixes(int size1, int size2) {
        if (size1 != size2) {
            throw new IllegalArgumentException("Index out of matrix size bounds!");
        }
    }

    private static Matrix elementByElementOperation(Matrix a, Matrix b, BinaryOperator<Double> operator) {
        checkSizesOfMatrixes(a.size, b.size);
        Matrix result = a;
        result.elementByElementOperation(b, operator);
        return result;
    }

    public static Matrix sum(Matrix a, Matrix b) {
        return elementByElementOperation(a, b, (arg1, arg2) -> arg1 + arg2);
    }

    public static Matrix subtraction(Matrix a, Matrix b) {
        return elementByElementOperation(a, b, (arg1, arg2) -> arg1 - arg2);
    }

    public static Matrix sumAll(Matrix... matrixes) {
        for (int i = 0; i < matrixes.length - 1; i++) {
            checkSizesOfMatrixes(matrixes[i].size, matrixes[i + 1].size);
        }
        Matrix result = matrixes[0];
        for (int i = 1; i < matrixes.length; i++) {
            result
        }
    }
}
