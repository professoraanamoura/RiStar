
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.transformation.transformations;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparsermodel.declarators.VariableSymbolDeclarator;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import ristar.elements.istar.Actor;
import ristar.elements.istar.ActorType;
import ristar.elements.istar.Contribution;
import ristar.elements.istar.IntentionalType;
import ristar.elements.istar.MeansEnd;
import ristar.elements.istar.Model;
import ristar.elements.istar.Property;
import ristar.elements.istar.Value;
import ristar.elements.istar.SRElement;
import ristar.elements.istar.TaskDecomposition;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ristar.transformation.rules.Rule;

/**
 *
 * @author anamm
 */
public class Transformacao {

    private static JSONObject global_struct = new JSONObject();
    private JSONArray global_array = new JSONArray();
    private JSONArray global_array_goal_model = new JSONArray();
    private String[] dataTypes = {"byte", "short", "int", "long", "float", "double", "boolean", "char", "Byte",
        "Short", "Int", "Long", "Float", "Double", "Boolean", "Char", "String"};
    private Model modelo;
    private List<ClassOrInterfaceDeclaration> selectedClasses = new ArrayList<ClassOrInterfaceDeclaration>();
    private List<Rule> selectedRules = new ArrayList<Rule>();
    private List<JSONObject> selectedSIGs = new ArrayList<JSONObject>();
    private String javaFileLocation;
    private String sigFileLocation;

    FileFilter javaFiles = new FileFilter() {
        public boolean accept(File file) {
            return file.getName().endsWith(".java") || file.isDirectory(); // return .url files
        }
    };

    public List<JSONObject> getSelectedSIGs() {
        return Collections.unmodifiableList(selectedSIGs);
    }

    public void setSelectedSIGs(List<JSONObject> selectedSIGs) {
        this.selectedSIGs = selectedSIGs;
    }

    
    public String getSigFileLocation() {
        return sigFileLocation;
    }

    public void setSigFileLocation(String sigFileLocation) {
        this.sigFileLocation = sigFileLocation;
    }

    public List<Rule> getSelectedRules() {
        return Collections.unmodifiableList(selectedRules);
    }

    public void setSelectedRules(List<Rule> selectedRules) {
        this.selectedRules = selectedRules;
    }

    public String getJavaFileLocation() {
        return javaFileLocation;
    }

    public void setJavaFileLocation(String javaFileLocation) {
        this.javaFileLocation = javaFileLocation;
    }

    public List<ClassOrInterfaceDeclaration> getSelectedClasses() {
        return Collections.unmodifiableList(selectedClasses);
    }

    public void setSelectedClasses(List<ClassOrInterfaceDeclaration> selectedClasses) {
        this.selectedClasses = selectedClasses;
    }

    public String getLanguage() {
        File location = new File(javaFileLocation);
        if (location.listFiles(javaFiles).length > 0) {
            return "Java";
        }
        return "Unknow";
    }

    public void parser() throws Exception {
        modelo = new Model();

        // Configura JavaParser para a resolução de tipos
        CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedTypeSolver);
        JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
        List<ClassOrInterfaceDeclaration> classes = new ArrayList<ClassOrInterfaceDeclaration>();
        try {
            for (Rule rule : this.getSelectedRules()) {
                rule.setModel(modelo);       
                rule.setSelectedSIG(this.getSelectedSIGs());
                for (ClassOrInterfaceDeclaration selectedClass : this.getSelectedClasses()) {
                    selectedClass.accept(rule, null);
                }
            }
            
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Model getModelo() {
        return this.modelo;
    }

    public List<ClassOrInterfaceDeclaration> getListaClasses(String path) throws Exception {
        //Connfigurando Type resolver
        
        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver(), new JavaParserTypeSolver(path));
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
        
        //gerando a lista de classes
        File location = new File(path);
        List<ClassOrInterfaceDeclaration> classes = new ArrayList<ClassOrInterfaceDeclaration>();
        try {
            for (File javaFile : location.listFiles(javaFiles)) {

                if (!javaFile.isDirectory()) {
                    FileInputStream inputStream = new FileInputStream(javaFile.getAbsolutePath());
                    CompilationUnit cu = JavaParser.parse(inputStream);
                    cu.findAll(ClassOrInterfaceDeclaration.class).forEach(be -> {
                        classes.add(be);
                    });
                } else {
                    for (ClassOrInterfaceDeclaration classe : getListaClasses(javaFile.getAbsolutePath())) {
                        classes.add(classe);
                    }
                }

            }
        } catch (FileNotFoundException fnfEx) {
            Logger.getLogger(Transformacao.class.getName()).log(Level.SEVERE, null, fnfEx);
        } catch (Exception ex) {
            Logger.getLogger(Transformacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classes;
    }
    public JSONObject parseSIG() throws Exception {
        JSONObject jsonObject=null;
        //Cria o parse de tratamento
        JSONParser parser = new JSONParser();
        try {
            //Salva no objeto JSONObject o que o parse tratou do arquivo
            jsonObject = (JSONObject) parser.parse(new FileReader(""));                         
            System.out.printf(
                    "Nome: %s\nSobrenome: %s\nEstado: %s\nPais: %s\n");
         
        //Trata as exceptions que podem ser lançadas no decorrer do processo
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        return jsonObject;
    }

    
    
}
