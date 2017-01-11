/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lapr.project.model.PhysicsConverters;

/**
 *
 * @author zero_
 */
public class ImportFlightPatternCSV {

    public ImportFlightPatternCSV() {

    }

    public static double[][] CSVImport(String filePath) {
        List<List<String>> valuesList = new ArrayList<>();
        double[][] values;
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                valuesList.add(Arrays.asList(country));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        values = parseToMatrix(valuesList);

        if (valuesList.get(0).get(1).endsWith("ft")) {
            values = altitudeFeetConverter(values, valuesList.get(0).size() - 2);
        }

        printMatrix(values);

        return values;
    }

    private static double[][] parseToMatrix(List<List<String>> values) {
        int horizontalSize = values.get(0).size() - 2;
        double[][] matrix = new double[3][horizontalSize];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                matrix[i][j] = Double.parseDouble(values.get(i).get(j + 2));
            }
        }

        return matrix;
    }

    private static double[][] altitudeFeetConverter(double[][] values, int size) {
        for (int j = 0; j < size; j++) {
            values[0][j] = PhysicsConverters.altitudeConverterFeetToMeters(values[0][j]);
        }

        return values;
    }

    private static void printMatrix(double[][] values) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 14; j++) {
                System.out.print(values[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
