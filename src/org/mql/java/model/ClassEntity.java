package org.mql.java.model;

import java.util.List;

public class ClassEntity {
    private String name;
    private List<String> methods;
    private List<String> fields;
    private String superClasse;
    private List<String> interfaces;
    private List<String> innerClasses;
    private List<String> constructors; 
   
    
	public ClassEntity(String name, List<String> methods, List<String> fields) {
        this.name = name;
        this.methods = methods;
        this.fields = fields;
    }
	public ClassEntity(String name, List<String> methods, List<String> fields,List<String> constructors,String superClasse) {
        this.name = name;
        this.methods = methods;
        this.fields = fields;
        this.constructors = constructors;
        this.superClasse = superClasse;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMethods() {
		return methods;
	}

	public void setMethods(List<String> methods) {
		this.methods = methods;
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}


	public String getSuperClasse() {
		return superClasse;
	}


	public void setSuperClasse(String superClasse) {
		this.superClasse = superClasse;
	}

	public List<String> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<String> interfaces) {
		this.interfaces = interfaces;
	}

	public List<String> getInnerClasses() {
		return innerClasses;
	}

	public void setInnerClasses(List<String> innerClasses) {
		this.innerClasses = innerClasses;
	}

	public List<String> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<String> constructors) {
		this.constructors = constructors;
	}



}

