/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.transformation.rules;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import ristar.elements.istar.Model;

/**
 *
 * @author anamm
 */
public class Rule extends VoidVisitorAdapter<Void> {
    private Model model;
    private List<JSONObject> selectedSIG;
    private static final Logger LOGGER = Logger.getLogger(Rule.class.getName());
    private int ruleNumber = 0;
    private String ruleDescription;

    public Rule(Model model, CompilationUnit cu) {
        this.model = model;
        try {
            Handler fileHandler = null;
            fileHandler = new FileHandler("./parser.log");
            LOGGER.addHandler(fileHandler);
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, "erro ao configursr o log.", ioe);
        }
        
    }
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<JSONObject> getSelectedSIG() {
        return Collections.unmodifiableList(selectedSIG);
    }

    public void setSelectedSIG(List<JSONObject> selectedSIG) {
        this.selectedSIG = selectedSIG;
    }

    

    public int getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(int ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }
    
    @Override
    public String toString() {
        return "Rule " + ruleNumber + " - " + ruleDescription;
    }
}
