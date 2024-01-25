package org.mql.java.ui.components;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.mql.java.model.ClassEntity;
import org.mql.java.model.PackageEntity;
import org.mql.java.model.ProjectEntity;
import org.mql.java.parser.ProjectParser;

public class PackageDiagram extends JPanel{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		String projectPath = "D:\\mql\\java\\WorkSpace-Home\\Oubella FatimaEzzahrae-UML Diagrams Generator";
        JFrame f = new JFrame("exemple");
        f.add(new PackageDiagram(ProjectParser.parseProject(projectPath)));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
	}
    public PackageDiagram(ProjectEntity project) {
        // Création d'un JPanel pour contenir les PackageDiagramPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));  // Ajustez le nombre de colonnes selon vos besoins

        for (PackageEntity pe : project.getPackages()) {
            // Création de l'exemple de diagramme de package
            PackageDiagramPanel packageDiagramPanel = new PackageDiagramPanel(pe.getName(), getClasses(pe), getInterfaces(pe), getAnnotations(pe), getEnumerations(pe));
            panel.add(packageDiagramPanel);
        }

        // Création d'une JScrollPane pour le JPanel
        JScrollPane scrollPane = new JScrollPane(panel);

        // Ajout de la JScrollPane au JPanel
        add(scrollPane);
    }
	
	
	public static List<String> getClasses(PackageEntity pe) {
		List<String> classes = new ArrayList<String>();
		 for (ClassEntity ce : pe.getClasses()) {
			classes.add(ce.getName());
		}
		 return classes;
	}
	
	public static List<String> getInterfaces(PackageEntity pe) {
	    List<String> interfaces = new ArrayList<String>();
	    for (ClassEntity ie : pe.getInterfaces()) {
	        interfaces.add(ie.getName());
	    }
	    return interfaces;
	}
	
	public static List<String> getAnnotations(PackageEntity pe) {
	    List<String> annotations = new ArrayList<String>();
	    for (ClassEntity ae : pe.getAnnotations()) {
	        annotations.add(ae.getName());
	    }
	    return annotations;
	}


	public static List<String> getEnumerations(PackageEntity pe) {
	    List<String> enumerations = new ArrayList<String>();
	    for (ClassEntity ee : pe.getEnumerations()) {
	        enumerations.add(ee.getName());
	    }
	    return enumerations;
	}
	
}