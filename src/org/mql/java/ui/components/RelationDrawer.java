package org.mql.java.ui.components;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.mql.java.model.RelationEntity.RelationType;

public class RelationDrawer extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private ClassDiagramPanel source;
	private ClassDiagramPanel target;
	private String typeAssociation;
	
	public RelationDrawer(ClassDiagramPanel source, ClassDiagramPanel target, String typeAssociation) {
		this.source = source;
		this.target = target;
		this.typeAssociation = typeAssociation;
		createRelationDrawer();
	}


	public void createRelationDrawer() {
		if(typeAssociation.equals(RelationType.ASSOCIATION.toString())) {
			Graphics g = getGraphics();
			g.drawLine(source.getX(), source.getY(), target.getX(), target.getY());
			
		}else if(typeAssociation.equals(RelationType.AGGREGATION.toString())) {
			Graphics g = getGraphics();
			g.drawLine(source.getX(), source.getY(), target.getX(), target.getY());
			
		}else if(typeAssociation.equals(RelationType.COMPOSITION.toString())) {
			Graphics g = getGraphics();
			g.drawLine(source.getX(), source.getY(), target.getX(), target.getY());
			
		}else if(typeAssociation.equals(RelationType.UTILISATION.toString())) {
			Graphics g = getGraphics();
			g.drawLine(source.getX(), source.getY(), target.getX(), target.getY());
			
		}
	}
	
	public static void main(String[] args) {
        JFrame f = new JFrame("exemple");

        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
	}

	
	
	
	
	
	
	
	
	
	
	
	

	public ClassDiagramPanel getSource() {
		return source;
	}

	public void setSource(ClassDiagramPanel source) {
		this.source = source;
	}

	public ClassDiagramPanel getTarget() {
		return target;
	}

	public void setTarget(ClassDiagramPanel target) {
		this.target = target;
	}

	public String getTypeAssociation() {
		return typeAssociation;
	}

	public void setTypeAssociation(String typeAssociation) {
		this.typeAssociation = typeAssociation;
	}
}
