package org.mql.java.parser;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mql.java.model.ClassEntity;
import org.mql.java.model.PackageEntity;

public class PackageParser {
	


	public static PackageEntity parsePackage(String packageName, String projectPath) {
	    try {
	    	PackageEntity pe =new PackageEntity(packageName);
	    	pe.setAllFiles(extractClasses(packageName, projectPath, pe));
	        return pe;
	    } catch (Exception e) {
	        System.out.println("Error : " + e.getMessage());
	        return null;
	    }
	}

	public static List<ClassEntity> extractClasses(String packageName, String projectPath , PackageEntity pe) {
	    List<ClassEntity> allFiles = new ArrayList<>();
	    List<ClassEntity> classes = new ArrayList<>();
	    List<ClassEntity> annotaions = new ArrayList<>();	 
	    List<ClassEntity> interfaces = new ArrayList<>();
	    List<ClassEntity> ennumerations = new ArrayList<>();

	    try {
	        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	        String binPath = projectPath.replace("src", "bin");
	        binPath = binPath.endsWith(File.separator + "bin") ? binPath : binPath + File.separator + "bin";

	        try (URLClassLoader urlClassLoader = new URLClassLoader(
	                new URL[]{new File(binPath).toURI().toURL()},
	                classLoader
	        )) {
	            String packagePath = packageName.replace('.', File.separatorChar);
	            Enumeration<URL> resources = urlClassLoader.getResources(packagePath);
	            Set<String> classNamesSet = new HashSet<>();

	            while (resources.hasMoreElements()) {
	                URL resource = resources.nextElement();
	                if (resource.getProtocol().equals("file")) {
	                    File classDir = new File(resource.toURI());

	                    if (classDir.exists()) {
	                        File[] classFiles = classDir.listFiles((dir, name) -> name.endsWith(".class"));

	                        if (classFiles != null) {
	                            for (File classFile : classFiles) {
	                                String className = packageName + '.' + classFile.getName().replace(".class", "");
	                                // Vérifiez si la classe a déjà été ajoutée
	                                if (classNamesSet.add(className)) {
	                                    Class<?> clazz = urlClassLoader.loadClass(className);
		                                    if(clazz.isAnnotation()) {
			                                    annotaions.add(ClassParser.parseClass(clazz));
			                                    pe.setAnnotations(annotaions);
		                                    }else if(clazz.isEnum()){
			                                    ennumerations.add(ClassParser.parseClass(clazz));
			                                    pe.setEnumerations(ennumerations);
		                                    }else if (clazz.isInterface()) {
			                                    interfaces.add(ClassParser.parseClass(clazz));
			                                    pe.setInterfaces(interfaces);
		                                    }else{
			                                    classes.add(ClassParser.parseClass(clazz));
			                                    pe.setClasses(classes);
		                            		}
	                                    	allFiles.add(ClassParser.parseClass(clazz));
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return allFiles;
	}

	
}