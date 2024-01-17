package org.mql.java.parser;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.mql.java.model.PackageEntity;
import org.mql.java.model.ProjectEntity;

public class ProjectParser {

	   public static ProjectEntity parseProject(String rootPath) {
	        try {
	            File projectRootName = new File(rootPath);
	            File projectRoot = new File(rootPath + "\\src");
	            if (!projectRoot.isDirectory()) {
	                throw new IllegalArgumentException("Le chemin spécifié ne correspond pas à un répertoire de projet.");
	            }
	            String projectName = projectRootName.getName();
	            List<PackageEntity> packages = extractPackages(projectRoot, projectRoot.getAbsolutePath());
	            return new ProjectEntity(projectName, packages);
	        } catch (Exception e) {
	            System.out.println("Error : " + e.getMessage());
	            return null;
	        }
	    }

	    private static List<PackageEntity> extractPackages(File projectRoot, String projectPath) {
	        List<PackageEntity> packageEntities = new ArrayList<>();
	        extractPackagesRecursively(projectRoot, packageEntities, projectPath, "");
	        return packageEntities;
	    }

	    private static void extractPackagesRecursively(File currentDirectory, List<PackageEntity> packageEntities, String projectPath, String currentPackage) {
	        File[] files = currentDirectory.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.isDirectory()) {
	                    if (file.listFiles() != null && file.listFiles().length == 0) {
	                        // Le répertoire est vide, nous pouvons ajouter le package
	                        String packageName = currentPackage.isEmpty() ? file.getName() : currentPackage + "." + file.getName();
	                        addPackageIfNotExists(packageEntities, packageName, projectPath);
	                    } else {
	                        // Le répertoire n'est pas vide, poursuivre la récursivité
	                        String packageName = currentPackage.isEmpty() ? file.getName() : currentPackage + "." + file.getName();
	                        extractPackagesRecursively(file, packageEntities, projectPath, packageName);
	                    }
	                } else if (file.getName().endsWith(".java")) {
	                	//System.out.println(file.getName());
	                    // Fichier Java trouvé, ajouter le package uniquement s'il n'existe pas déjà
	                    addPackageIfNotExists(packageEntities, currentPackage, projectPath);
	                }
	            }
	        }
	    }

	    private static void addPackageIfNotExists(List<PackageEntity> packageEntities, String packageName, String projectPath) {
	        // Vérifier si le package existe déjà dans la liste
	        if (packageEntities.stream().noneMatch(p -> p.getName().equals(packageName))) {
	            // Le package n'existe pas encore, ajoutez-le
	            packageEntities.add(PackageParser.parsePackage(packageName, projectPath));
	        }
	    }

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    


}