/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.transformation.rules;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import java.util.UUID;
import ristar.elements.istar.Actor;
import ristar.elements.istar.ActorType;
import ristar.elements.istar.Model;
import ristar.elements.istar.Property;
import ristar.elements.istar.Value;


/**
 *
 * @author anamm
 */
public class Rule0 extends Rule {

    public Rule0() {
        super(null, null);
        setRuleNumber(0);
        setRuleDescription(" Transform Classes non abstract in Agents.");
    }

    public Rule0(Model model, CompilationUnit cu) {
        super(model, cu);

    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {

        try {
            if (!(n.isInterface() || n.isAbstract())) {
                Actor ator = new Actor();
                ator.setId(UUID.randomUUID());
                if (n.isAnnotationPresent(org.jistar.core.elements.Actor.class)) {
                    
                    //ator.setName(n.getAnnotationByClass(org.jistar.core.elements.Actor.class).get().getData("name"));
                    //ator.setType(ActorType.Agent);
                } else {
                    ator.setName(n.getNameAsString());
                    ator.setType(ActorType.Agent);
                }
                Property propriedade = new Property("selected");
                Value valor = new Value(true, ator, propriedade);
                ator.addProperty(valor);
                this.getModel().addNode(ator);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.visit(n, arg); //To change body of generated methods, choose Tools | Templates.
    }

}
