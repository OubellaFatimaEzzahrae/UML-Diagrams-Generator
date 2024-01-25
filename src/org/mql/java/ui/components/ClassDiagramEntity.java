package org.mql.java.ui.components;

import org.mql.java.model.ClassEntity;

public class ClassDiagramEntity {
	private ClassDiagramPanel classDiagramPanel;
	private ClassEntity classEntity;
	
	public ClassDiagramEntity(	ClassDiagramPanel classDiagramPanel,	ClassEntity classEntity) {
		
		this.classDiagramPanel = classDiagramPanel;
		this.classEntity = classEntity;
	}

	public ClassDiagramPanel getClassDiagramPanel() {
		return classDiagramPanel;
	}

	public void setClassDiagramPanel(ClassDiagramPanel classDiagramPanel) {
		this.classDiagramPanel = classDiagramPanel;
	}

	public ClassEntity getClassEntity() {
		return classEntity;
	}

	public void setClassEntity(ClassEntity classEntity) {
		this.classEntity = classEntity;
	}
}
