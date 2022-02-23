package model;

import matrix.Matrix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Model2D {
    private HomogeneousCoordinates[] verticesMatrix;
    private int[][] edgesMatrix;

    private Matrix accumulatedAffineTransform;

    public Model2D(String pathToMatrixOfVertices, String pathToMatrixOfEdges) {
        try {
            verticesMatrix = covertDoubleToHomCoordinates(importFromFile(Path.of(pathToMatrixOfVertices)));
            edgesMatrix = convertDoubleToInt2DArray(importFromFile(Path.of(pathToMatrixOfEdges)));
        } catch (IOException exception) {
            System.err.println("Something wrong with file!");
        }
        accumulatedAffineTransform = AffineTransform.IdentityMatrix();
    }

    public void applyAffineTransform(Matrix matrix){
        accumulatedAffineTransform.multiply(matrix);
    }

    public void resetAffineTransform(){
        accumulatedAffineTransform = AffineTransform.IdentityMatrix();
    }

    private static double[][] importFromFile(Path path) throws IOException {
        return Files.lines(path)
                .map(line -> line.trim().split("\\s+"))
                .map(strings -> Stream.of(strings).mapToDouble(Double::parseDouble).toArray())
                .toArray(double[][]::new);
    }

    private static int[][] convertDoubleToInt2DArray(double[][] array){
        return Arrays.stream(array)
                .map(x -> Arrays.stream(x).mapToInt(el -> (int)el).toArray())
                .toArray(int[][]::new);
    }

    private static double[] getColumn(double[][] array, int colIndex){
        return IntStream.range(0, array.length)
                .mapToDouble(rowIndex -> array[rowIndex][colIndex]).toArray();
    }

    private static HomogeneousCoordinates[] covertDoubleToHomCoordinates(double[][] array){
        return IntStream.range(0, array[0].length)
                .mapToObj(colIndex -> getColumn(array, colIndex))
                .map(HomogeneousCoordinates::of).toArray(HomogeneousCoordinates[]::new);
    }
}
