/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.gui;

import ristar.transformation.transformations.Transformacao;
import ristar.transformation.transformations.transformacaoPiStar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author anamm
 */
public class TransformationGUI extends javax.swing.JFrame {

    PrintWriter gravarArq;
    String javaDirLocation = null;
    String javaFileLocation = null;
    private Transformacao transformation;
    private String[] dataTypes = {"byte", "short", "int", "long", "float", "double", "boolean", "char", "Byte",
        "Short", "Int", "Long", "Float", "Double", "Boolean", "Char", "String"};
    
    FileFilter javaFiles = new FileFilter() {
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true; // return directories for recursion
                //return false;
            }

            return file.getName().endsWith(".java"); // return .url files
        }
    };

    /**
     * Creates new form TransformacaoGUI
     */
    public TransformationGUI() {
        this.setUndecorated(true);
        initComponents();
        this.setSize(1250, 700);
        this.setState(JFrame.NORMAL);
        //configura o logger
        
    }

    public TransformationGUI(Transformacao transformation) {
        this();
        this.transformation = transformation;
        lblLinguagem.setText("Language: " + transformation.getLanguage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblLinguagem = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbSaida = new javax.swing.JComboBox<>();
        btnParser = new javax.swing.JButton();
        btnSalvarSaida = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSaida = new javax.swing.JTextArea();
        btnPrevious = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setOpaque(false);

        lblLinguagem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblLinguagem.setText("Linguagem: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Output:");

        cbSaida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "piStar" }));

        btnParser.setText("parser");
        btnParser.setPreferredSize(new java.awt.Dimension(81, 25));
        btnParser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParserActionPerformed(evt);
            }
        });

        btnSalvarSaida.setText("Salvar");
        btnSalvarSaida.setPreferredSize(new java.awt.Dimension(81, 25));
        btnSalvarSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarSaidaActionPerformed(evt);
            }
        });

        txtSaida.setColumns(20);
        txtSaida.setRows(5);
        jScrollPane1.setViewportView(txtSaida);

        btnPrevious.setText("Previous");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnClose.setText("close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSalvarSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnParser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLinguagem, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addComponent(cbSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblLinguagem)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnParser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnClose)
                        .addComponent(btnPrevious))
                    .addComponent(btnSalvarSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(227, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(210, 100, 1020, 580);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IstarReverseBackground.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarSaidaActionPerformed
        try {

            JFileChooser fileChooser = new JFileChooser();
            if (((String) cbSaida.getSelectedItem()).equalsIgnoreCase("PiStar")) {
                fileChooser.setFileFilter(new FileNameExtensionFilter("PiStar files *.txt", "txt"));
                fileChooser.setAcceptAllFileFilterUsed(false);
            }
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File arquivo = fileChooser.getSelectedFile();
                Path caminho = Paths.get(arquivo.getPath());
                Charset utf8 = StandardCharsets.UTF_8;
                BufferedWriter escritor = Files.newBufferedWriter(caminho, utf8);
                escritor.write(txtSaida.getText());
                escritor.flush();
            }
        } catch (Exception ex) {
            Logger.getLogger(TransformationGUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erro ao tentar salvar o arquivo de saída.", "RiStar", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnSalvarSaidaActionPerformed

    private void btnParserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParserActionPerformed

        try {

            transformation.parser();
        } catch (Exception ex) {
            Logger.getLogger(TransformationGUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        //t.convertToGoalModel();
        txtSaida.removeAll();
        if (((String) cbSaida.getSelectedItem()).equalsIgnoreCase("PiStar")) {
            transformacaoPiStar piStar = new transformacaoPiStar();
            txtSaida.append(piStar.convertTopiStarGoalModel(transformation.getModelo()));
        } else {
            txtSaida.append("\nModelo: \n");
            txtSaida.append(transformation.getModelo().toString());
        }

        /*lstElementos.setText(" ");
        BufferedWriter buffWrite = null;
            try {
                
               // buffWrite = new BufferedWriter(new FileWriter(javaFileLocation));
                //buffWrite.append("abri");
                File location = new File(javaDirLocation);
                lstElementos.append("istar.clearModel();\n");
                lstElementos.append("var actor;\n");
		for (File javaFile : location.listFiles(javaFiles)) {
                        //Lista arquivo encontrado
                        
                        
                        FileInputStream inputStream = new FileInputStream(javaFile.getAbsolutePath());
                        CompilationUnit cu = JavaParser.parse(inputStream);
			List<Node> cuChildNodes = cu.getChildrenNodes();
                        //lstElementos.append("Child nodes: "+cuChildNodes+"\n\n\n");
			List<TypeDeclaration> listOfTypeDeclarations = cu.getTypes();
                        
			JSONObject objintclass = new JSONObject();
                        
                        for (Node cuChildNode : cuChildNodes) {
                           

					if (cuChildNode instanceof ClassOrInterfaceDeclaration) {
						ClassOrInterfaceDeclaration cid = (ClassOrInterfaceDeclaration) cuChildNode;
                                                
						JSONArray methodArray = new JSONArray();
						JSONArray extend = new JSONArray();
						JSONArray fieldArray = new JSONArray();
						JSONArray selfstruct = new JSONArray();
						JSONArray implement = new JSONArray();
						JSONArray usesstruct = new JSONArray();
						JSONArray usesStruct = new JSONArray();
						JSONArray association = new JSONArray();
                                                
                                                objintclass.put("CIName", cid.getName());
						objintclass.put("CIType", cid.toString().contains("interface")?"Interface":"Class");
						objintclass.put("extend", extend);
						objintclass.put("implements", implement);
						objintclass.put("selfstruct", selfstruct);
						objintclass.put("usesStruct", usesstruct);
						objintclass.put("Association", association);
                                                String typeIstarElement = cid.toString().contains("interface")?"Role":"Actor";
                                                
                                                lstElementos.append("actor = istar.add"+typeIstarElement+"(10,10,'"+cid.getName()+"');\n");
						if (!cid.toString().contains("interface")) {
							if (cid.getExtends() != null) {
								for (Node Extends : cid.getExtends()) {

									extend.put(Extends);
								}

								objintclass.put("extend", extend);

							}

							if (cid.getImplements() != null) {
								for (Node Implements : cid.getImplements()) {
									implement.put(Implements);

								}
								objintclass.put("implements", implement);

							}

							// objclass//push to Main Array

						}
                                                
                                                List<BodyDeclaration> members = cid.getMembers();

                                                boolean flag = false;
                                                
                                                //int JSONpointer;

                                                for (int i = 0; i < members.size(); i++) {
                                                    BodyDeclaration member = members.get(i);

                                                    //JSONObject fieldmethod = new JSONObject();
                                                    JSONObject field = new JSONObject();
                                                    JSONObject method = new JSONObject();
                                                    //JSONpointer = 0;
                                                    //----- Field -> Resources ----
                                                    if (member instanceof FieldDeclaration) {
							// types can be primitive/nonPrimitive
							
							flag = true;
							FieldDeclaration fd = (FieldDeclaration) member;
							int modifiers = fd.getModifiers();
							List<Node> listNodes = fd.getChildrenNodes();
							String fieldType = fd.getType().toString();
							String fieldvaraiable = "";

							String submitURLModifier = "#";

							switch (modifiers) {
							case 2:
								submitURLModifier = "-";
								break;
							case 3:
								submitURLModifier = "+";
								break;
							case 4:
								submitURLModifier = "#";
								break;
							}
							int fieldTypePrim = 0;
							for (Node refTypeNode : listNodes) {

								if (refTypeNode instanceof ReferenceType) {
									ReferenceType refType = (ReferenceType) refTypeNode;
									// System.out.println("Ref +: "+refType);
									fieldTypePrim = 2;

									for (String str : dataTypes) {
										if (refType.toString().contains(str)) {
											fieldTypePrim = 3;
											fieldType = refType.getType().toString() + "(*)";
											if (refType.getType().toString().equals("String")) {
												fieldType = "String";
											}
										}

									}

								}

								else if (refTypeNode instanceof PrimitiveType) {
									fieldTypePrim = 1;

								}

								else if (refTypeNode instanceof VariableDeclarator) {
									VariableDeclarator varDec = (VariableDeclarator) refTypeNode;
									fieldvaraiable = varDec.toString();

								}

							}

							field.put("fieldType", fieldType);

							// System.out.println(fd.getType().toString());

							field.put("fieldTypePrim", fieldTypePrim);

							field.put("fieldmodifier", submitURLModifier);
							field.put("fieldvaraiable", fieldvaraiable);
							fieldArray.put(field);
							lstElementos.append("actor.embedNode(istar.addResource(598,29,'"+fieldvaraiable+"'));\n");
                                                        //##### Method -> Task #####
						}else if (member instanceof MethodDeclaration) {

							flag = false;

							String methodName = ((MethodDeclaration) member).getName().toString();
							String methodType = ((MethodDeclaration) member).getType().toString();

							// System.out.println(((MethodDeclaration)
							// bd).getType().toString());

							MethodDeclaration methodDeclaration = (MethodDeclaration) member;
							List<Parameter> parameters = methodDeclaration.getParameters();
							List<TypeParameter> typeParameters = methodDeclaration.getTypeParameters();

							// System.out.println("Here"+methodDeclaration.getParameters());

							JSONArray methodParameters = new JSONArray();
							//JSONArray methodpara = new JSONArray();
							//JSONArray methodParam = new JSONArray();
							for (Parameter parameter : parameters) {

								JSONObject methodParameterObject = new JSONObject();
								String mType = "Primitive";

								String mName = parameter.getType().toString();

								String[] mVarArray = parameter.toString().split("\\s+");
								String mVar = mVarArray[1];
								boolean prim = false;
								for (String str : dataTypes) {
									if (mName.toString().contains(str)) {

										mType = "PrimitiveArray";
										prim = true;
									}

								}
								if (!prim) {

									mType = "genericType";
								}

								mName = mName.replace("<", "ï¼œ").replace(">", "ï¼ž").replace("[", "ï¼»").replace("]", "ï¼½");

								methodParameterObject.put("mName", mName);
								methodParameterObject.put("mType", mType);
								methodParameterObject.put("mVar", mVar);
								methodParameters.put(methodParameterObject);

							}
							// Determine the Type of Parameters

							int modifiers = methodDeclaration.getModifiers();
							String methodModifier = "";
							switch (modifiers) {
							case 1:methodModifier = "+";break;
							case 2:methodModifier = "-";break;
							case 4:methodModifier = "#";break;
							}
							method.put("methodType", methodType);
							method.put("methodmodifier", methodModifier);
							method.put("methodName", methodName);
							method.put("methodParameter", methodParameters);
							methodArray.put(method);
                                                        lstElementos.append("actor.embedNode(istar.addTask(598,29,'"+methodName+"'));\n");

                                                }else if (member instanceof ConstructorDeclaration) {

							ConstructorDeclaration constructor = (ConstructorDeclaration) member;

							List<Parameter> consParams = constructor.getParameters();

							String name = constructor.getName().toString();

							JSONArray parameterArray = new JSONArray();
							// String methodType = ((ConstructorDeclaration)
							// bd).getType().toString();

							for (Parameter parameter : consParams) {
								JSONObject constParameter = new JSONObject();

								String mName = parameter.getType().toString();

								String[] mVarArray = parameter.toString().split("\\s+");
								String mVar = mVarArray[1];								
								String mType = "Primitive";
								boolean prim = false;
								for (String str : dataTypes) {
									if (mName.toString().contains(str)) {

										mType = "PrimitiveArray";
										prim = true;
									}
								}
								if (!prim) {

									mType = "genericType";
								}

								mName = mName.replace("<", "ï¼œ").replace(">", "ï¼ž").replace("[", "ï¼»").replace("]", "ï¼½");

								// System.out.print("reoa"+mName);
								constParameter.put("mName", mName);
								constParameter.put("mType", mType);
								constParameter.put("mVar", mVar);

								parameterArray.put(constParameter);
							}

							int modifiers = ((ConstructorDeclaration) member).getModifiers();
							// System.out.println(modifiers);
							String methodModifier = "";

							switch (modifiers) {
							case 1:
								methodModifier = "+";
								break;
							case 2:
								methodModifier = "-";
								break;
							case 4:
								methodModifier = "#";
								break;
							}

							method.put("methodType", "");
							method.put("methodmodifier", methodModifier);
							method.put("methodName", name);
							method.put("methodParameter", parameterArray);
							methodArray.put(method);
                                                        lstElementos.append("var taskConstructor = actor.embedNode(istar.addTask(598,29,'"+name+"'));\n");
                                                        lstElementos.append("var goalConstructor = actor.embedNode(istar.addGoal(598,29,'"+name+"'));\n");
                                                        lstElementos.append("istar.addOrRefinementLink(taskConstructor, goalConstructor);");
						}       
				}                                                
                                objintclass.put("Fields", fieldArray);        
				objintclass.put("Methods", methodArray);
                                global_array.put(objintclass);
                                
                                
                        }
                        global_struct.put("global_struct", global_array);
                }       
                
                //buffWrite.close();
                }
            } catch (ParseException exParse) {
                exParse.printStackTrace();
                        
            }catch (IOException ex) {
                ex.printStackTrace();
                        
            }
         */
    }//GEN-LAST:event_btnParserActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        RuleSelectionGUI ruleSelectionGUI = new RuleSelectionGUI(transformation);
        ruleSelectionGUI.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCloseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransformationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransformationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransformationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransformationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransformationGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnParser;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnSalvarSaida;
    private javax.swing.JComboBox<String> cbSaida;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLinguagem;
    private javax.swing.JTextArea txtSaida;
    // End of variables declaration//GEN-END:variables
}