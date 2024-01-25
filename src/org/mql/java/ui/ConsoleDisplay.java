package org.mql.java.ui;

import java.util.List;
import java.util.stream.Collectors;
import org.mql.java.model.ClassEntity;
import org.mql.java.model.ClassEntity.FieldType;
import org.mql.java.model.ClassEntity.MethodType;
import org.mql.java.model.PackageEntity;
import org.mql.java.model.ProjectEntity;
import org.mql.java.model.RelationEntity;
import org.mql.java.parser.ProjectParser;

public class ConsoleDisplay {
/*<<<<<<< HEAD

=======
	
>>>>>>> 0f448e7f89a57c89256f75690defd548b35ab1f2*/
	public ConsoleDisplay(String projectPath) {
		parserDisplay(projectPath);

	}
	
	public static void parserDisplay(String projectPath) {
		ProjectEntity projectE = ProjectParser.parseProject(projectPath);
		displayProject(projectE);
		

	}
	
	public static void displayProject(ProjectEntity projectE) {
		List<PackageEntity> packages = projectE.getPackages();
		System.out.println("---------------------------------------------------------------");
        System.out.println("Nom du projet : " + projectE.getProjectName());
        System.out.println("__________________________ Packages __________________________");
		displayPackages(packages);
		System.out.println("_______________________________________________________________");
	}
	
	public static void displayPackages(List<PackageEntity>  packagesE) {
		String marge = ">".repeat(2);
		if (packagesE.isEmpty()) {
			System.out.println("######Ouuups Empty Project!######");
		}else {
			for (PackageEntity packageE : packagesE) {
				String packageName = packageE.getName();
				System.out.println(marge+"Package : "+ packageName);
				displayClasses(packageE.getAllFiles());
			}
		}
	}
	
	public static void displayClasses(List<ClassEntity> classesE) {
	    String marge = " ".repeat(2);
	    if (classesE.isEmpty()) {
	        System.out.println(marge + "######  Empty Classe!  ######");
	    } else {
	        System.out.println(marge + "........ Classes||Interfaces||Enumerations||Annotations ........");

	        // Définir tous les types possibles
	        String[] types = {"class", "interface", "annotation", "enumeration"};

	        for (String type : types) {
	            // Filtrer les entités par type
	            List<ClassEntity> entitiesOfType = filterEntitiesByType(classesE, type);

	            // Afficher la section uniquement si la liste n'est pas vide
	            if (!entitiesOfType.isEmpty()) {
	                System.out.println(marge + "____________ " + capitalize(type) + " _____________ : ");
	                displayEntitiesByType(entitiesOfType, marge);
	                System.out.println(marge + "____________________________________\n");
	            }
	        }
	    }
	}

	// Filtrer les entités par type
	private static List<ClassEntity> filterEntitiesByType(List<ClassEntity> classes, String type) {
	    return classes.stream()
	            .filter(classEntity -> type.equals(classEntity.getType()))
	            .collect(Collectors.toList());
	}


	// Afficher les entités par type
	private static void displayEntitiesByType(List<ClassEntity> classes, String marge) {
	    for (ClassEntity classE : classes) {
	        String className = classE.getName();
	        System.out.println(marge.repeat(3) + ">>" + capitalize(classE.getType()) + " " + className + " : ");
	        displayClassMembers(classE);
	    }
	}

	// Mettre en majuscule la première lettre
	private static String capitalize(String str) {
	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	
	public static void displayClassMembers(ClassEntity classE) {
		String marge = " ".repeat(8);
		//Fields
		if(!classE.getFields().isEmpty()) {
			System.out.println(marge+"____________Fields____________");
			for (FieldType c : classE.getFields()) {
				System.out.println(marge+c.toString());
			}
			System.out.println(marge+"_______________________________");
		}
		//Methods
		if(!classE.getMethods().isEmpty()) {
			System.out.println(marge+"_____________Methods___________");
			for (MethodType c : classE.getMethods()) {
				System.out.println(marge+c.toString());
			}
			System.out.println(marge+"_______________________________");
		}		
		//Contructors
		if(!classE.getConstructors().isEmpty()) {
			System.out.println(marge+"__________Constructors_________");
			for (String c : classE.getConstructors()) {
				System.out.println(marge+c.toString());
			}
			System.out.println(marge+"_______________________________");
		}
		//Classe Mére
		if(classE.getSuperClasse() != null) {
			System.out.println(marge+"__________Super Classe_________");
				System.out.println(marge+classE.getSuperClasse());
			System.out.println(marge+"_______________________________");
		}
		//Relations
		if(!classE.getRelations().isEmpty()) {
			System.out.println(marge+"__________Relations_________");
			for (RelationEntity	r : classE.getRelations()) {
				System.out.println(marge+r.getDescription());
			}
			System.out.println(marge+"_______________________________");
		}
		System.out.println();
	}


 }
   
