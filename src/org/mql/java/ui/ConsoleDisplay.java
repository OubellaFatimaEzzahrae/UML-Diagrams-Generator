package org.mql.java.ui;

import java.util.List;

import org.mql.java.model.ClassEntity;
import org.mql.java.model.PackageEntity;
import org.mql.java.model.ProjectEntity;
import org.mql.java.parser.ProjectParser;

public class ConsoleDisplay {
<<<<<<< HEAD

=======
	
>>>>>>> 0f448e7f89a57c89256f75690defd548b35ab1f2
	public ConsoleDisplay() {
		parserDisplay();
	}
	
	public void parserDisplay() {
		ProjectEntity projectE = ProjectParser.parseProject("D:\\mql\\java\\WorkSpace-Home\\Test Project");
		displayProject(projectE);
	}
	
	public void displayProject(ProjectEntity projectE) {
		List<PackageEntity> packages = projectE.getPackages();
		System.out.println("---------------------------------------------------------------");
        System.out.println("Nom du projet : " + projectE.getProjectName());
        System.out.println("__________________________ Packages __________________________");
		displayPackages(packages);
		System.out.println("_______________________________________________________________");
	}
	
	public void displayPackages(List<PackageEntity>  packagesE) {
		String marge = ">".repeat(2);
		if (packagesE.isEmpty()) {
			System.out.println("######Ouuups Empty Project!######");
		}else {
			for (PackageEntity packageE : packagesE) {
				String packageName = packageE.getName();
				System.out.println(marge+"Package : "+ packageName);
				displayClasses(packageE.getClasses());
				System.out.println("");
			}
		}
	}
	
	public void displayClasses(List<ClassEntity> classesE) {
		String marge = " ".repeat(2);
		if(classesE.size() == 0) {
			System.out.println(marge+"######  Empty Package!  ######");
		}else {
			System.out.println(marge+"........ Classes||Interfaces||Enumerations||Annotations ........");
			System.out.println(marge+"____________ Classes _____________ : ");
			for (ClassEntity classE : classesE) {
				String className = classE.getName();
				System.out.println(marge+">>Classe "+className+" : ");
				displayClassMembers(classE);
			}
			System.out.println(marge+"____________________________________");
		}
	}
	
	public void displayClassMembers(ClassEntity classE) {
		String marge = " ".repeat(8);
		//Fields
		if(!classE.getFields().isEmpty()) {
			System.out.println(marge+"____________Fields____________");
			for (var c : classE.getFields()) {
				System.out.println(marge+c.toString());
			}
			System.out.println(marge+"_______________________________");
		}
		//Methods
		if(!classE.getMethods().isEmpty()) {
			System.out.println(marge+"_____________Methods___________");
			for (String c : classE.getMethods()) {
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
		System.out.println();
	}


 }
   
