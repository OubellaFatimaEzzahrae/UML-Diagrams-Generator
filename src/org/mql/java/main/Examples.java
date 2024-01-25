package org.mql.java.main;

import org.mql.java.model.ProjectEntity;
import org.mql.java.ui.ConsoleDisplay;
import org.mql.java.ui.SwingDisplayFrame;
import org.mql.java.xml.XmlParser;

public class Examples{
	
	public Examples() {
		exp02();
	}
	
	void exp01() {
		String projectPath = "\\Oubella FatimaEzzahrae-UML Diagrams Generator";
		new ConsoleDisplay(projectPath); 
		
	}

	void exp02() {
        // Exemple d'utilisation du parseur
        String xmlFilePath = "src\\resources\\outputXml.xml";
        ProjectEntity project = XmlParser.parseProjectXml(xmlFilePath);
        if (project != null) {
        	System.out.println("le projet est parsé en utilisant la classe XmlParser");
        	ConsoleDisplay.displayProject(project);
    		
    		new SwingDisplayFrame(project);
        } else {
            System.out.println("Erreur lors du chargement du fichier XML.");
        }
	}
	

	
	public static void main(String[] args) {
		new Examples();
	}
}
