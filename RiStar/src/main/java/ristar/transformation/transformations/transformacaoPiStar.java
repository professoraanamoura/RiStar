/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.transformation.transformations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import ristar.elements.istar.Actor;
import ristar.elements.istar.ActorType;
import ristar.elements.istar.Contribution;
import ristar.elements.istar.Dependum;
import ristar.elements.istar.DependumLink;
import ristar.elements.istar.IntentionalType;
import ristar.elements.istar.Model;
import ristar.elements.istar.Node;
import ristar.elements.istar.SRElement;
import ristar.elements.istar.Link;
import ristar.elements.istar.MeansEnd;
import ristar.elements.istar.TaskDecomposition;

/**
 *
 * @author anamm
 */
public class transformacaoPiStar {

    public String convertTopiStarGoalModel(Model modelo) {
        StringBuffer saida = new StringBuffer("{\n"
                + "  \"actors\": [\n");
        int id = 1, x = 10, y = 10;
        for (Node no : modelo.getNodes()) {
            if (no instanceof Actor) {
                Actor ator = (Actor) no;
                String tipoAtor = (ator.getType().name().equals("General")) ? "Actor" : ator.getType().name();
                saida.append("    \n{\n"
                        + "      \"id\": \"" + ator.getId() + "\",\n"
                        + "      \"text\": \"" + ator.getName() + "\",\n"
                        + "      \"type\": \"istar." + tipoAtor + "\",\n"
                        + "      \"x\": " + x + ",\n"
                        + "      \"y\": " + y + ",\n"
                        + "      \"nodes\": [\n");

                for (SRElement element : ator.getSrElements()) {
                    String istarType = element.getType().name();
                    switch (element.getType().name()) {
                        case "SoftGoal":
                            istarType = "Quality";
                            break;
                        default:
                            istarType = element.getType().name();
                    }
                    saida.append("    \n{\n"
                            + "      \"id\": \"" + element.getId() + "\",\n"
                            + "      \"text\": \"" + element.getName() + "\",\n"
                            + "      \"type\": \"istar." + istarType + "\",\n"
                            + "      \"x\": " + x + ",\n"
                            + "      \"y\": " + y + "},");

                    x += 20;

                }
                saida = saida.deleteCharAt(saida.length() - 1);
                saida.append("]},");
                x += 50;
                y += 50;

            }
        }
        saida = saida.deleteCharAt(saida.length() - 1);
        saida.append("  ],\n"
                + " \"dependencies\": [\n");
        //Realiza o mapeamento dos links
        for (Node no : modelo.getNodes()) {
            if (no instanceof Dependum) {
                Dependum d = (Dependum) no;

                String istarType = d.getType().name();
                switch (d.getType().name()) {
                    case "SoftGoal":
                        istarType = "Quality";
                        break;
                    default:
                        istarType = d.getType().name();
                }

                for (Link link : d.getLinks()) {

                    saida.append("    \n{\n"
                            + "      \"id\": \"" + link.getId() + "\",\n"
                            + "      \"text\": \"" + d.getName() + "\",\n"
                            + "      \"type\": \"istar." + istarType + "\",\n"
                            + "      \"x\": " + x + ",\n"
                            + "      \"y\": " + y + ",\n"
                            + "      \"source\": \"" + link.getFrom().getId() + "\",\n"
                            + "      \"target\": \"" + link.getTo().getId() + "\"},");
                    x += 5;
                    y += 5;
                }
            }
        }
        saida = saida.deleteCharAt(saida.length() - 1);
        saida.append(" ],\n\"links\": [\n");
        //Realiza o mapeamento dos links
        for (Node no : modelo.getNodes()) {
            if (no instanceof Actor) {
                Actor ator = (Actor) no;
                for (SRElement element : ator.getSrElements()) {
                    for (Link link : element.getLinks()) {

                        String type = "";
                        if (link instanceof DependumLink) {
                            type = "DependencyLink";
                        }
                        if (link instanceof MeansEnd) {
                            type = "OrRefinementLink";
                        }
                        if ((link instanceof TaskDecomposition) && (((SRElement) link.getTo()).getType() == IntentionalType.Task)) {
                            type = "AndRefinementLink";

                        }
                        if ((link instanceof TaskDecomposition) && (element.getType() == IntentionalType.Resource)) {
                            type = "NeededByLink";
                        }
                        if ((link instanceof Contribution)) {
                            type = "ContributionLink";
                        }
                        System.out.println("\n"+link.getId()+" : "+element+" -> "+type+" -> "+link.getTo().getName());
                        saida.append("    \n{\n"
                                + "      \"id\": \"" + link.getId() + "\",\n"
                                + "      \"type\": \"istar." + type + "\",\n"
                                + (link instanceof Contribution ? "\"label\": \"help\"," : "")
                                + "      \"source\": \"" + element.getId() + "\",\n"
                                + "      \"target\": \"" + link.getTo().getId() + "\"},");
                    }
                }
            }
        }
        saida = saida.deleteCharAt(saida.length() - 1);
        DateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        Date date = new Date();

        saida.append("],\n"
                + "\"display\": { ");
        for (Node no : modelo.getNodes()) {
            if (no instanceof Actor) {
                if (no.getProperty("selected") == null) {
                    saida.append("\n\""+no.getId()+"\": {"
                            + "      \"collapsed\": true"
                            + "    },");
                }
            }
        }
        saida = saida.deleteCharAt(saida.length() - 1);
        saida.append("},\n"
                + "  \"tool\": \"pistar.1.0.0\",\n"
                + "  \"istar\": \"2.0\",\n"
                + "  \"saveDate\": \"" + dateFormat.format(date) + "\",\n"
                + "  \"diagram\": {\n"
                + "    \"width\": 2806.5,\n"
                + "    \"height\": 1172\n"
                + "  }\n"
                + "} ");
        return saida.toString();
    }

}
