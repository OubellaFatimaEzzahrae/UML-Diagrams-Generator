package org.mql.java.ui.components;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.mql.java.model.ClassEntity;
import org.mql.java.model.ClassEntity.FieldType;
import org.mql.java.model.ClassEntity.MethodType;
import org.mql.java.parser.ProjectParser;
import org.mql.java.model.PackageEntity;
import org.mql.java.model.RelationEntity;

public class ClassDiagram extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private List<ClassDiagramEntity> cdes = new ArrayList<ClassDiagramEntity>();
	private List<RelationDrawer> rds = new ArrayList<RelationDrawer>();
	
    public static void main(String[] args) {
        String projectPath = "D:\\mql\\java\\WorkSpace-Home\\Oubella FatimaEzzahrae-UML Diagrams Generator";
        JFrame f = new JFrame("exemple");

        f.add(new ClassDiagram(ProjectParser.parseProject(projectPath).getPackages()));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public ClassDiagram(List<PackageEntity> packagesEntity) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 3));  
        for (PackageEntity pe : packagesEntity) {
            for (ClassEntity ce : pe.getAllFiles()) {
                // Création de l'exemple de diagramme de package
            	if(! "".equals(ce.getName())) {
            		ClassDiagramPanel classDiagramPanel = new ClassDiagramPanel(ce.getName(), getFields(ce), getMethods(ce),ce.getType());
            		ClassDiagramEntity cde = new ClassDiagramEntity(classDiagramPanel, ce);
            		cdes.add(cde);
            		panel.add(classDiagramPanel);
            	}
            	//createClassDiagram();
            }
        }
        // Création d'une JScrollPane pour le JPanel
        JScrollPane scrollPane = new JScrollPane(panel);

        // Ajout de la JScrollPane au JPanel
        add(scrollPane);
    }
    
    public  void createClassDiagram() {
    	Set<RelationEntity> res = new HashSet<RelationEntity>();
    	for (ClassDiagramEntity cep : cdes) {
    		ClassEntity ce = cep.getClassEntity();
    		for (RelationEntity re : ce.getRelations()) {
				res.add(re);
			}
    	}
    	
    	for (RelationEntity re : res) {
			String source = re.getSourceClass(),target = re.getTargetClass(),type = re.getType().toString();
    			searchClassDiagram(source);
    			new RelationDrawer(searchClassDiagram(source), searchClassDiagram(target),type);
		}
    }
    
    private ClassDiagramPanel searchClassDiagram(String className) {
    	ClassDiagramPanel cp;
 		for (ClassDiagramEntity cep : cdes) {
 			if(cep.getClassDiagramPanel().getName() == className) {
 				cp = cep.getClassDiagramPanel();
 				return cp;
 			}
		}
 		return null;
	}
	
	public static List<String> getFields(ClassEntity ce) {
		List<String> fields = new ArrayList<String>();
		String signatureField = "" , modifier = "" , name = "";
		 for (FieldType f : ce.getFields()) {
			 name = f.getName();
			 if(f.getModifier().contains("public")) {
				 modifier = "+ ";
			 }else if(f.getModifier().contains("private")) {
				 modifier = " - ";				 
			 }else if(f.getModifier().contains("protected")) {
				 modifier = " # ";				 
			 }
			 signatureField = modifier + name ; 
			 fields.add(signatureField);
		}
		 return fields;
	}
	
	public static List<String> getMethods(ClassEntity ce) {
	    List<String> methods = new ArrayList<String>();
		String signatureField = "" , modifier = "" , name = "", typeReturn = "" , params ="";
	    for (MethodType m : ce.getMethods()) {
			 name = m.getName(); typeReturn = m.getReturnType();
			 if(m.getModifier().contains("public")) {
				 modifier = "+ ";
			 }else if(m.getModifier().contains("private")) {
				 modifier = " - ";				 
			 }else if(m.getModifier().contains("protected")) {
				 modifier = " # ";				 
			 }
			 List<String> parameters = new ArrayList<String>();
			 parameters = m.getParameters();
			 for(int i =0 ; i < parameters.size() ; i++ ) {
				 params = params+parameters.get(i);
				 if(i < parameters.size()-1) {
					 params = params+",";
				 }
			 }
			 //signatureField = modifier + name + "("+params+") : " + typeReturn; 
			 signatureField = modifier + name + "() : " + typeReturn; 
	    	methods.add(signatureField);
	   
	    }
	    return methods;
	}

	public List<RelationDrawer> getRds() {
		return rds;
	}

	public void setRds(List<RelationDrawer> rds) {
		this.rds = rds;
	}


	
	

}
