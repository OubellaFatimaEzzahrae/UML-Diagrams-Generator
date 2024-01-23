package org.mql.java.model;

import java.util.ArrayList;
import java.util.List;

public class PackageEntity {
    private String name;
    private List<RelationEntity> relations;
    private List<ClassEntity> allFiles;
    private List<ClassEntity> classes;
    private List<ClassEntity> interfaces;
    private List<ClassEntity> enumerations;
    private List<ClassEntity> annotations;

    public PackageEntity(String name) {
    	this.name = name;
        this.allFiles = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.interfaces = new ArrayList<>();
        this.enumerations = new ArrayList<>();
        this.annotations = new ArrayList<>();
    }
    
    
    public PackageEntity(String name, List<ClassEntity> classes) {
        this.name = name;
        this.classes = classes;
    }
    
    
    public PackageEntity(String name, List<ClassEntity> classes, List<ClassEntity> interfaces, List<ClassEntity> ennumerations, List<ClassEntity> annotations) {
        this.name = name;
        this.classes = classes;
        this.interfaces = interfaces;
        this.enumerations = ennumerations;
        this.annotations = annotations;
    }


	//Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ClassEntity> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassEntity> classes) {
		this.classes = classes;
	}

	public List<ClassEntity> getEnumerations() {
		return enumerations;
	}

	public void setEnumerations(List<ClassEntity> ennumerations) {
		this.enumerations = ennumerations;
	}

	public List<ClassEntity> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<ClassEntity> interfaces) {
		this.interfaces = interfaces;
	}

	public List<ClassEntity> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<ClassEntity> annotations) {
		this.annotations = annotations;
	}
	public List<ClassEntity> getAllFiles() {
		return allFiles;
	}
	public void setAllFiles(List<ClassEntity> allFiles) {
		this.allFiles = allFiles;
	}

	public List<RelationEntity> getRelations() {
		return relations;
	}

	public void setRelations(List<RelationEntity> relations) {
		this.relations = relations;
	}


}