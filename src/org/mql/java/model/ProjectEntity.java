package org.mql.java.model;

import java.util.List;

public class ProjectEntity {
	
	private String projectName;
	private List<PackageEntity> packages;
	
	public ProjectEntity(String projectName, List<PackageEntity> packages) {
        this.setProjectName(projectName);
        this.setPackages(packages);
	}

	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public List<PackageEntity> getPackages() {
		return packages;
	}

	public void setPackages(List<PackageEntity> packages) {
		this.packages = packages;
	}
}
