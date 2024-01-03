package org.mql.java.model;

import java.util.List;

public class PackageEntity {
    private String name;
    private List<ClassEntity> classes;

    public PackageEntity(String name, List<ClassEntity> classes) {
        this.name = name;
        this.classes = classes;
    }

    public PackageEntity(String packageName, List<String> classNames, List<String> interfaceNames,
			List<String> enumNames, List<String> annotationNames) {
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


}