package org.mql.java.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ClassDiagramPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private String className;
	private String type ;
    private List<String> fields = new ArrayList<>();
    private List<String> methods = new ArrayList<>();
    

    public ClassDiagramPanel(String className, List<String> fields, List<String> methods, String type) {
        this.setClassName(className);
        this.setFields(fields);
        this.setMethods(methods);
        this.setType(type);
        
        JPanel packagePanel = createClassPanel();
        add(packagePanel);
    }
    
    private JPanel createClassPanel() {
        JPanel classPanel = new JPanel();
        classPanel.setLayout(new BoxLayout(classPanel, BoxLayout.Y_AXIS));
        classPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        String marge = " ";

        if (this.className != null) {
            JLabel classTypeLabel = new JLabel();
            JLabel classLabel = new JLabel();
            if(type != "class") {
            	
            	classTypeLabel.setText(marge.repeat(5) + "<< " + type +">>" + marge.repeat(5));
            }
            classLabel.setText(marge.repeat(5) + className + marge.repeat(5));

            classLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
            classPanel.add(classTypeLabel);
            classPanel.add(classLabel);
            // Ajout de la ligne séparatrice après le nom de la classe
        }
        classPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        if (this.fields != null && !fields.isEmpty()) {
            for (String f : fields) {
                JLabel classLabel = new JLabel(marge.repeat(5) + " " + f + marge.repeat(5));
                classLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
                classPanel.add(classLabel);

                // Ajout de la ligne séparatrice après les champs
            }
        }else {
			classPanel.add(new JLabel("  "));
		}
        classPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        if (this.methods != null && !methods.isEmpty()) {
            for (String m : methods) {
                JLabel classLabel = new JLabel(marge.repeat(5) + " " + m + marge.repeat(5));
                classLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
                classPanel.add(classLabel);

                // Ajout de la ligne séparatrice après les méthodes
            }
        }else {
			classPanel.add(new JLabel("  "));
		}

        return classPanel;
    }


    
    
    public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public List<String> getMethods() {
		return methods;
	}

	public void setMethods(List<String> methods) {
		this.methods = methods;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
