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
public class Rule1 extends Rule {

    

    public Rule1() {
        super(null, null);
        setRuleNumber(1);
        setRuleDescription(" Transform Interfaces and abstract classes in Roles.");
    }

    public Rule1(Model model, CompilationUnit cu) {
        super(model, cu);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {

        try {

            if (n.isInterface() || n.isAbstract()) {
                Actor ator = new Actor();
                ator.setId(UUID.randomUUID());
                ator.setName(n.getNameAsString());
                ator.setType(ActorType.Role);
                Property propriedade = new Property("selected");
                Value valor = new Value(true,ator,propriedade);
                ator.addProperty(valor);
                this.getModel().addNode(ator);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.visit(n, arg); //To change body of generated methods, choose Tools | Templates.
    }

    

}
