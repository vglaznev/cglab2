package model;

import matrix.Matrix;
import vizualization.Scene2D;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Model2D {
    private HomogeneousCoordinates[] verticesMatrix;
    private HomogeneousCoordinates[] currentVerticesMatrix;
    private int[][] edgesMatrix;

    private Matrix accumulatedAffineTransform;

    public Model2D(Path pathToMatrixOfVertices, Path pathToMatrixOfEdges) {
        try {
            verticesMatrix = covertDoubleToHomCoordinates(importFromFile(pathToMatrixOfVertices));
            edgesMatrix = convertDoubleToInt2DArray(importFromFile(pathToMatrixOfEdges));
            currentVerticesMatrix = verticesMatrix;
        } catch (IOException exception) {
            System.err.println("Something wrong with file!");
        }
        accumulatedAffineTransform = AffineTransform.IdentityMatrix();
    }

    public void applyAffineTransform(Matrix matrix) {
        accumulatedAffineTransform.multiply(matrix);
        currentVerticesMatrix = Arrays.stream(currentVerticesMatrix)
                .map(x -> HomogeneousCoordinates.multiplyWithMatrix(matrix, x))
                .toArray(HomogeneousCoordinates[]::new);
    }

    public void resetAffineTransform() {
        accumulatedAffineTransform = AffineTransform.IdentityMatrix();
        currentVerticesMatrix = verticesMatrix;
    }

    private static double[][] importFromFile(Path path) throws IOException {

        return Files.lines(path)
                .map(line -> line.trim().split("\\s+"))
                .map(strings -> Stream.of(strings).mapToDouble(Double::parseDouble).toArray())
                .toArray(double[][]::new);
    }

    public void render(Scene2D scene2D, Graphics g) {

        for (int i = 0; i < edgesMatrix.length; i++) {
            for (int j = i; j < edgesMatrix[0].length; j++) {
                if (edgesMatrix[i][j] == 1) {
                    scene2D.moveTo(currentVerticesMatrix[i].getX(), currentVerticesMatrix[i].getY());
                    scene2D.lineTo(g, currentVerticesMatrix[j].getX(), currentVerticesMatrix[j].getY());
                }
            }
        }
    }

    private static int[][] convertDoubleToInt2DArray(double[][] array) {
        return Arrays.stream(array)
                .map(x -> Arrays.stream(x).mapToInt(el -> (int) el).toArray())
                .toArray(int[][]::new);
    }

    private static double[] getColumn(double[][] array, int colIndex) {
        return IntStream.range(0, array.length)
                .mapToDouble(rowIndex -> array[rowIndex][colIndex]).toArray();
    }

    private static HomogeneousCoordinates[] covertDoubleToHomCoordinates(double[][] array) {
        return IntStream.range(0, array[0].length)
                .mapToObj(colIndex -> getColumn(array, colIndex))
                .map(HomogeneousCoordinates::of).toArray(HomogeneousCoordinates[]::new);
    }

}
