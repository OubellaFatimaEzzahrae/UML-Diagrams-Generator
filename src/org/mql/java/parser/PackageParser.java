package org.mql.java.parser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.mql.java.model.ClassEntity;
import org.mql.java.model.PackageEntity;

public class PackageParser {
	


    public static PackageEntity parsePackage(String packageName) {
        try {
            List<ClassEntity> classes = extractClasses(packageName);

            return new PackageEntity(packageName, classes);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null; 
        }
    }
    
    public static List<ClassEntity> extractClasses(String packageName) {
        List<ClassEntity> classEntities = new ArrayList<>();

        try {
            String classPath = System.getProperty("java.class.path");
            String packagePath = packageName.replace('.', File.separatorChar);

            File classDir = new File(classPath + File.separator + packagePath);
            if (classDir.exists()) {
                File[] classFiles = classDir.listFiles((dir, name) -> name.endsWith(".class"));

                if (classFiles != null) {
                    for (File classFile : classFiles) {
                        String className = packageName + '.' + classFile.getName().replace(".class", "");
                        Class<?> clazz = Class.forName(className);

                        classEntities.add(ClassParser.parseClass(clazz));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classEntities;
    }
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	public static List<ClassEntity> exxractClasses(String packageName) {
	    List<ClassEntity> classEntities = new ArrayList<>();
	    String classPath = System.getProperty("java.class.path");
	    String packageFolder = packageName.replace('.', File.separatorChar);

	    // Si vous utilisez un IDE, il est possible que vos classes soient compilées dans le répertoire "out" ou "target"
	    // À ajuster en fonction de la structure de votre projet
	    String basePath = classPath + File.separator + "out" + File.separator + "production" + File.separator;

	    File packageDirectory = new File(basePath + packageFolder);

	    if (packageDirectory.exists()) {
	        File[] classFiles = packageDirectory.listFiles((dir, name) -> name.endsWith(".class"));

	        if (classFiles != null) {
	            for (File classFile : classFiles) {
	                String className = packageName + '.' + classFile.getName().replace(".class", "");

	                try {
	                    Class<?> clazz = Class.forName(className);
	                    classEntities.add(ClassParser.parseClass(clazz));
	                } catch (ClassNotFoundException e) {
	                    System.out.println("Classe Introuvable : " + className);
	                }
	            }
	        }
	    }

	    return classEntities;
	}

    
    
    

    public static List<ClassEntity> extractClasscs(String packageName) {
        List<ClassEntity> classEntities = new ArrayList<>();
        try {
            String path = packageName.replace('.', '/');
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            java.net.URL resource = classLoader.getResource(path);

            if (resource == null) {
                return classEntities;
            }
            File directory = new File(resource.getFile());
            //classEntities.add(ClassParser.parseClass(path.getClass()));
            if (directory.exists()) {
                for (File file : directory.listFiles()) {
                    if (file.isFile() && file.getName().endsWith(".class")) {
                        String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                        Class<?> clazz = Class.forName(className);
                        new ClassParser();
						classEntities.add(ClassParser.parseClass(clazz));
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return classEntities;
    }
    public static List<ClassEntity> extractClasse(String packageName) {
    	List<ClassEntity> classEntities = new ArrayList<>();
    	String packagePath = packageName.replace('.', '/');
    	
	    // Obtenez le class loader pour charger les classes
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	
	    try {
	        // Utilisez le ClassLoader pour obtenir les URLs des ressources du package
	        Enumeration<URL> resources = classLoader.getResources(packagePath);
	        while (resources.hasMoreElements()) {
	            URL resource = resources.nextElement();
	            System.out.println(resource);
	            if (resource == null) {
	                return classEntities;
	            }
	            // Convertissez l'URL en chemin de fichier
	            File file = new File(resource.getFile());
	
	            // Parcourez les fichiers dans le répertoire du package
	            System.out.println("yarbiykhdm");
	            if (file.exists() && file.isDirectory()) {
	                File[] files = file.listFiles((dir, name) -> name.endsWith(".class"));
	                if (files != null) {
	                    for (File classFile : files) {
	                        // Obtenez le nom de la classe sans l'extension .class
	                        String className = classFile.getName().replace(".class", "");
	                        Class<?> clazz = Class.forName(className);
	                        classEntities.add(ClassParser.parseClass(clazz));
	                        System.out.println(" - " + packageName + "." + classFile.getName());
	                    }
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

        return classEntities;
    }
    
	
	
	
	
	
	
	
	
	
 /*   
    
    @SuppressWarnings("unused")
    public PackageEntity parsePackage(Package pkg) {
    	try {
    		String packageName = pkg.getName();
    		List<String> classNames = extractClassNames(pkg);
    		List<String> interfaceNames = extractInterfaceNames(pkg);
    		List<String> enumNames = extractEnumNames(pkg);
    		List<String> annotationNames = extractAnnotationNames(pkg);
    		
    		return new PackageEntity(packageName, classNames, interfaceNames, enumNames, annotationNames);
    	} catch (Exception e) {
    		// Gestion de l'exception (par exemple, log, affichage, etc.)
    		System.out.println("Error : " + e.getMessage());
    		return null; // ou lancez une exception personnalisée si nécessaire
    	}
    }	
    
    private List<String> extractClassNames(Package pkg) {
    	List<String> classNames = new ArrayList<>();
    	for (Class<?> clazz : pkg.getClasses()) {
    		classNames.add(clazz.getName());
    	}
    	return classNames;
    }
    
    private List<String> extractInterfaceNames(Package pkg) {
    	List<String> interfaceNames = new ArrayList<>();
    	for (Class<?> clazz : pkg.getInterfaces()) {
    		interfaceNames.add(clazz.getName());
    	}
    	return interfaceNames;
    }
    
    private List<String> extractEnumNames(Package pkg) {
    	List<String> enumNames = new ArrayList<>();
    	for (Class<?> clazz : pkg.getEnumTypes()) {
    		enumNames.add(clazz.getName());
    	}
    	return enumNames;
    }
    
    private List<String> extractAnnotationNames(Package pkg) {
    	List<String> annotationNames = new ArrayList<>();
    	for (Annotation annotation : pkg.getAnnotations()) {
    		annotationNames.add(annotation.annotationType().getName());
    	}
    	return annotationNames;
    }
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	
	    public PackageEntity parsePackage(String packageName) {
	        // Création de l'entité PackageEntity avec le nom du package, les classes et les interfaces
	    	List<ClassEntity> classes = new ArrayList<ClassEntity>(); 
	    	List<ClassEntity> interfaces = new ArrayList<ClassEntity>();
	        return new PackageEntity(packageName, classes);
	    }
	
	
	
	
	 public static PackageEntity parsePackagee(String projectPath) {
	        File projectDir = new File(projectPath);
	        if (!projectDir.isDirectory()) {
	            throw new IllegalArgumentException("Le chemin spécifié n'est pas un répertoire de projet valide.");
	        }
	        // Récupérer la liste des fichiers dans le répertoire du projet
	        File[] files = projectDir.listFiles();
	        if (files == null) {
	            throw new RuntimeException("Erreur lors de la récupération des fichiers du répertoire du projet.");
	        }
	        List<ClassEntity> classEntities = new ArrayList<>();
	        // Parcourir les fichiers pour extraire les informations sur les classes
	        for (File file : files) {
	            if (file.isFile() && file.getName().endsWith(".java")) {
	                String className = file.getName().replace(".java", "");
	                ClassEntity classEntity = new ClassEntity(className, new ArrayList<>(), new ArrayList<>());
	                classEntities.add(classEntity);
	            }
	        }
	        // Créer une entité Package avec les classes extraites
	        PackageEntity packageEntity = new PackageEntity(projectDir.getName(), classEntities);

	        return packageEntity;
	    }




	public PackageEntity parsePackage(String name, List<ClassEntity> subClasses, List<ClassEntity> subInterfaces,
			List<PackageEntity> subPackages) {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
