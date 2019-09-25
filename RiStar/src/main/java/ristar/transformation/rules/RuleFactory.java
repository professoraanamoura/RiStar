/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.transformation.rules;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anamm
 */
public class RuleFactory {
    public static List<Rule> getListRules(){
        List<Rule> rules = new ArrayList<Rule>();
        rules.add(new Rule0());
        rules.add(new Rule1());
        rules.add(new Rule2());
        rules.add(new Rule3());
        rules.add(new Rule4());
        rules.add(new Rule5());
        rules.add(new Rule6());
        rules.add(new Rule7());
        rules.add(new Rule8());        
        return rules;
    }
}
