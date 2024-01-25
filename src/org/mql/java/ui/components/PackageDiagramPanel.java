package org.mql.java.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PackageDiagramPanel extends JPanel{
	
	private String packageName;
	private List<String> classesNames = new ArrayList<String>();
	private List<String> interfacesNames = new ArrayList<String>();
	private List<String> annotationsNames = new ArrayList<String>();
	private List<String> enumerationsNames = new ArrayList<String>();
	
	private static final long serialVersionUID = 1L;
	
	public PackageDiagramPanel(String packageName, List<String> classesNames,
			List<String> interfacesNames, List<String> annotationsNames, List<String> enumerationsNames) {
		this.packageName = packageName;
		this.classesNames = classesNames;
		this.interfacesNames = interfacesNames;
		this.annotationsNames = annotationsNames;
		this.enumerationsNames = enumerationsNames;
		
        setLayout(new GridBagLayout()); // Utilisation de GridBagLayout pour plus de flexibilité
        
        // Rectangle du nom du package
        JPanel packagePanel = createPackagePanel();
        GridBagConstraints packageConstraints = new GridBagConstraints();
        packageConstraints.gridx = 0;
        packageConstraints.gridy = 0;
        packageConstraints.anchor = GridBagConstraints.WEST;
        add(packagePanel, packageConstraints);

        // Rectangle de la liste des classes (ou autres)
        JPanel classListPanel = createClassListPanel();
        GridBagConstraints classListConstraints = new GridBagConstraints();
        classListConstraints.gridx = 0;
        classListConstraints.gridy = 1;
        classListConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(classListPanel, classListConstraints);
    }

    private JPanel createPackagePanel() {
        JPanel packagePanel = new JPanel();
        packagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel packageLabel = new JLabel("  "+this.packageName+"  ");
        packagePanel.add(packageLabel);
        
        return packagePanel;
    }

    private JPanel createClassListPanel() {
        JPanel classListPanel = new JPanel();
        classListPanel.setLayout(new GridBagLayout());
        classListPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        
        String marge = " ";
        // Ajout des labels des classes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        if(classesNames != null && !classesNames.isEmpty()) {
        	for (String className : classesNames) {
        		JLabel classLabel = new JLabel(marge.repeat(5)+"Classe : " + className+marge.repeat(5));
        		classLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        		classListPanel.add(classLabel, gbc);
        		gbc.gridy++;
        	}
        }
        if(interfacesNames != null && !interfacesNames.isEmpty()) {
        	for (String className : interfacesNames) {
        		JLabel classLabel = new JLabel(marge.repeat(5)+"Interface : " + className+marge.repeat(5));
        		classLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        		classListPanel.add(classLabel, gbc);
        		gbc.gridy++;
        	}
        }
        if(annotationsNames != null && !annotationsNames.isEmpty()) {
        	for (String className : annotationsNames) {
        		JLabel classLabel = new JLabel(marge.repeat(5)+"Annotation : " + className+marge.repeat(5));
        		classLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        		classListPanel.add(classLabel, gbc);
        		gbc.gridy++;
        	}
        }
        if(enumerationsNames != null && !enumerationsNames.isEmpty()) {
        	for (String className : enumerationsNames) {
        		JLabel classLabel = new JLabel(marge.repeat(5)+"Enum : " + className+marge.repeat(5));
        		classLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        		classListPanel.add(classLabel, gbc);
        		gbc.gridy++;
        	}
        }

        return classListPanel;
    }


	public List<String> getClassesNames() {
		return classesNames;
	}


	public void setClassesNames(List<String> classesNames) {
		this.classesNames = classesNames;
	}


	public List<String> getEnumerationsNames() {
		return enumerationsNames;
	}


	public void setEnumerationsNames(List<String> enumerationsNames) {
		this.enumerationsNames = enumerationsNames;
	}


	public List<String> getAnnotationsNames() {
		return annotationsNames;
	}


	public void setAnnotationsNames(List<String> anotationsNames) {
		this.annotationsNames = anotationsNames;
	}


	public String getPackageName() {
		return packageName;
	}


	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}


	public List<String> getInterfacesNames() {
		return interfacesNames;
	}


	public void setInterfacesNames(List<String> interfacesNames) {
		this.interfacesNames = interfacesNames;
	}
	
}