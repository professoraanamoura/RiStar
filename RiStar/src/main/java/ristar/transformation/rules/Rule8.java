/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.transformation.rules;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ristar.elements.istar.Actor;
import ristar.elements.istar.Contribution;
import ristar.elements.istar.Dependency;
import ristar.elements.istar.Dependum;
import ristar.elements.istar.DependumLink;
import ristar.elements.istar.IntentionalType;
import ristar.elements.istar.Model;
import ristar.elements.istar.SRElement;

/**
 *
 * @author anamm
 */
public class Rule8 extends Rule {

    public Rule8() {
        super(null, null);
        setRuleNumber(8);
        setRuleDescription(" Suggests qualities operationalization according to SIG.");

    }

    public Rule8(Model model, CompilationUnit cu) {
        super(model, cu);
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
        try {
            //identificando o No pai   
            String parentName = null;
            Node parentNode = n.getParentNode().get();
            while (!(parentNode instanceof ClassOrInterfaceDeclaration)) {
                parentNode = parentNode.getParentNode().get();
            }
            if (parentNode != null) {
                parentName = ((ClassOrInterfaceDeclaration) parentNode).getNameAsString();
            }
            Actor parent = (Actor) this.getModel().getNode(parentName);
            final SRElement task = parent.getNode(n.getNameAsString());

            //buscando operacionalizações em código
            for (JSONObject quality : this.getSelectedSIG()) {
                for (Object qualities : quality.keySet()) {
                    JSONArray operationalizations = (JSONArray) quality.get(qualities);
                    for (Object operationalization : operationalizations) {

                        if (n.getBody().toString().contains(operationalization.toString())) {
                            //criamdo a aqualidade
                            
                            SRElement softgoal = new SRElement();
                            softgoal.setType(IntentionalType.SoftGoal);
                            softgoal.setName(qualities.toString());
                            softgoal.setId(UUID.randomUUID());
                            parent.setSrElement(softgoal);

                            if (task != null) {
                                
                                Contribution contribution = new Contribution(task, softgoal);
                                contribution.setId(UUID.randomUUID());
                            }
                        }
                    }
                }

            }
        } catch (UnsolvedSymbolException use) {
            Logger.getLogger(Rule8.class.getName()).log(Level.SEVERE, null, use);
        } catch (Exception ex) {
            Logger.getLogger(Rule8.class.getName()).log(Level.SEVERE, null, ex);

        }

        super.visit(n, arg);
    }
}
