/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import lapr.project.model.Flight;
import lapr.project.model.network.Segment;

/**
 *
 * @author zero_
 */
public class ExportCSV {

    public static void writeCSVFile(String path, Flight flight) {

        try {
            File file = new File(path);

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bw = new BufferedWriter(fw);

            csvHeadLine(bw);
            for (Segment segment : flight.getPathTaken()) {
                writeSegmentLine(bw, segment);
            }
            
            bw.newLine();
            writeFinalResults(bw, calculateTotalDistance(flight), flight.getTravelingTime());
            
            bw.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println("IOException >> No file found/created");
        }
    }
    
    private static double calculateTotalDistance(Flight flight){
        double totalDistance = 0;
        for (Segment segment : flight.getPathTaken()) {
            totalDistance += segment.getDistance();
        }
        return totalDistance;
    }

    private static void csvHeadLine(BufferedWriter bw) throws IOException {
        System.out.println("Nome/ID do segmento" + ","
                + "No inicial (ID/nome)" + ","
                + "No final (ID/nome)" + ","
                + "Comprimento");
        bw.write("Nome/ID do segmento" + ","
                + "No inicial (ID/nome)" + ","
                + "No final (ID/nome)" + ","
                + "Comprimento");
        bw.newLine();
    }

    private static void writeSegmentLine(BufferedWriter bw, Segment segment) throws IOException {
        final String segmentID = segment.getId();
        final String segmentBeginningNode = segment.getBeginningNode().getName();
        final String segmentEndingNode = segment.getEndNode().getName();
        final String segmentDistance = String.valueOf(segment.getDistance());

        System.out.println(segmentID + "," + segmentBeginningNode + "," + segmentEndingNode + "," + segmentDistance);
        bw.write(segmentID + "," + segmentBeginningNode + "," + segmentEndingNode + "," + segmentDistance);
        bw.newLine();
    }
    
    private static void writeFinalResults(BufferedWriter bw, double distancia, double tempo) throws IOException{
        final String totalDistance = String.valueOf(distancia);
        final String totalTime = String.valueOf(tempo);
        
        bw.write("Distância total acumulada" + "," + "Tempo de voo acumulado");
        bw.newLine();
        bw.write(totalDistance + "," + totalTime);
        bw.newLine();
    }
}
