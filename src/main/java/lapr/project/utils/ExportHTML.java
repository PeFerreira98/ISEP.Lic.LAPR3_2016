/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.File;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Flight;
import lapr.project.model.network.Segment;

/**
 *
 * @author Tiago
 */
public class ExportHTML {

    private static final Logger LOG = Logger.getLogger("ExportHTMLLog");
    private static Flight flight;

    /**
     *
     * @param filePath
     * @param flight
     * @param airnetwork
     */
    public ExportHTML(String filePath, Flight flight) {
        this.flight = flight;

        try {
            Formatter out = new Formatter(new File(filePath));

            ExportHTML.criarFicheiro(out);
            ExportHTML.headComTitulo(out, filePath, "LAPR3");

            ExportHTML.criarTable(out);
            ExportHTML.headTable(out);
            for (Segment segment : this.flight.getPathTaken()) {
                ExportHTML.lineTable(out, segment);
            }
            ExportHTML.fecharTable(out);

            ExportHTML.criarTable(out);
            ExportHTML.finalTable(out, flight.getEnergyConsumption(), flight.getTravelingTime());
            ExportHTML.fecharTable(out);

            ExportHTML.fecharFx(out, "Projecto realizado por: Ana Neves, Marcos Dourado, Pedro Ferreira, Joao Fernandes, Tiago Lameirao");

        } catch (Exception e) {
            LOG.log(Level.INFO, filePath, e);
        }

    }

    /**
     *
     * @param string
     * @throws Exception
     */
    public static void criarFicheiro(Formatter out) throws Exception {
        out.format("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">%n<html>%n");
    }

    /**
     *
     * @param out
     * @param nameFile
     * @param titulo
     * @throws Exception
     */
    public static void headComTitulo(Formatter out, String nameFile, String titulo) throws Exception {
        out.format("<head>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">"
                + "<style type=\"text/css\">"
                + "table {"
                + "    width:100%%;"
                + "}"
                + "table, th, td {"
                + "    border: 1px solid black;"
                + "    border-collapse: collapse;"
                + "}"
                + "th, td {"
                + "    padding: 5px;"
                + "    text-align: left;"
                + "}"
                + "table#t01 tr:nth-child(even) {"
                + "    background-color: #eee;"
                + "}"
                + "table#t01 tr:nth-child(odd) {"
                + "   background-color:#fff;"
                + "}"
                + "table#t01 th    {"
                + "    background-color: #38090b;"
                + "    color: white;"
                + "}"
                + "</style>"
                + "<title>%s</title>"
                + "<link rel=\"icon\" type=\"image/ico\" href=\"http://www.isep.ipp.pt/favicon.ico\">"
                + "<link href=\"https://www.ipp.pt/logo-ipp.png\">"
                + "<style type=\"text/css\">"
                + " p.c5 {color: black; text-align: right}"
                + " span.c4 {color: black}"
                + " p.c3 {color: black; text-align: center}"
                + " p.c2 {text-align: center}"
                + " div.c1 {text-align: center}"
                + "</style>"
                + "</head>", nameFile);
        abrirBody(out);
        out.format("<div><img src=\"https://portal.isep.ipp.pt/INTRANET/info/PublicDocumentDownload.aspx?f=357146\" alt=\"Logotipo ISEP\"></div>"
                + "<div class=\"c1\">"
                + "<h1>%s</h1>"
                + "</div>", titulo);
    }

    /**
     *
     *
     * @param out
     * @throws Exception
     */
    public static void abrirBody(Formatter out) throws Exception {
        out.format("<body >%n");
    }

    /**
     *
     * @param out
     * @param caption
     * @param name1
     * @param name2
     * @throws Exception
     */
    public static void criarTable(Formatter out) throws Exception {
        out.format("<div>" + "<table id=\"t01\">");
    }

    public static void headTable(Formatter out) {
        out.format("<tr>"
                + "<th>Nome/ID do segmento</th>"
                + "<th>No inicial (ID/nome)</th>"
                + "<th>No final (ID/nome)</th>"
                + "<th>Comprimento</th>"
                + "<th>Velocidade (TAS) no inicio do segmento</th>"
                + "<th>Altitude no inicio do segmento</th>"
                + "<th>Velocidade (TAS) no fim do segmento</th>"
                + "<th>Altitude no fim do segmento</th>"
                + "<th>Combustivel gasto no segmento (Kg)</th>"
                + "<th>Combustivel remanescente no fim do segmento</th>"
                + "</tr>"
        );
    }

    public static void lineTable(Formatter out, Segment segment) {
        out.format("<tr>"
                + "<th>%s</th>" + "<th>%s</th>" + "<th>%s</th>" + "<th>%s</th>"
                //                + "<th>%s</th>" + "<th>%s</th>" + "<th>%s</th>" 
                //                + "<th>%s</th>" + "<th>%s</th>" + "<th>%s</th>"
                + "</tr>",
                segment.getId(),
                segment.getBeginningNode().getName(),
                segment.getEndNode().getName(),
                segment.getDistance()
        //                0, 0, 0, 0, 0, 0
        );
    }

    public static void finalTable(Formatter out, double distancia, double tempo) {
        out.format("<tr>"
                + "<th>Distancia total acumulada</th>" + "<th>Tempo de voo acumulado</th>"
                + "</tr>"
                + "<tr>"
                + "<th>%s</th>" + "<th>%s</th>"
                + "</tr>",
                String.valueOf(distancia),
                String.valueOf(tempo)
        );
    }

    /**
     *
     * @param out
     */
    public static void fecharTable(Formatter out) {
        out.format("</table>"
                + "</div>"
                + "<span class=\"c4\"><br>"
                + "<br>"
                + "<br>"
                + "<br>"
                + "<br>"
                + "<br>"
                + "<br>"
                + "<br>"
                + "<br>"
                + "<br></span>");
    }

    public static void lastLine(Formatter out, double distancia, double tempo) {
        //  Distância total acumulada
        //  Tempo de voo acumulado
        out.format("<div>");
        out.format("<p><title>Distância total acumulada: %d</title></p>", distancia);
        out.format("<p><title>Tempo de voo acumulado: %d</title></p>", tempo);
        out.format("</div>");
    }

    /**
     *
     *
     * @param out .
     * @throws Exception
     */
    public static void fecharFx(Formatter out, String str) throws Exception {
        out.format("<div style=\"float: left; background-color:whyte; border:1px solid black; color:black; margin:20px; padding:20px;\">"
                + "<h3>%s</h3>"
                + "<p>"
                + "LAPR3<br>"
                + "</div>"
                + "</body>"
                + "</html>", str);
        out.close();
    }
}
