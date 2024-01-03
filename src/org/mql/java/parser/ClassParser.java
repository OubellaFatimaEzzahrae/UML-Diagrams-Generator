package org.mql.java.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.mql.java.model.ClassEntity;

public class ClassParser {
	
    public static ClassEntity parseClass(Class<?> clazz) {
        try {
            String className = clazz.getSimpleName();
            List<String> methodNames = extractMethodsName(clazz.getDeclaredMethods());
            List<String> fieldNames = extractFieldsName(clazz.getDeclaredFields());
            List<String> constructorsNames = extractConstructorsName(clazz.getDeclaredConstructors(),className);            
            String superClasse = extractSuperClasseName(clazz);
            return new ClassEntity(className, methodNames, fieldNames, constructorsNames, superClasse);
        } catch (Exception e) {
        	System.out.println("Error : "+e.getMessage());
        	return null; 
        }
    }

	//Extraire les Attributs
	public static List<String> extractFieldsName(Field[] fields ) {
		List<String> fieldsName = new ArrayList<String>();
			for(Field f : fields) 
				fieldsName.add(f.getName());
		return fieldsName;
	}
	
	//Extraire les Méthodes
	public static List<String> extractMethodsName(Method[] fields) {
		List<String> methodsName = new ArrayList<String>();
			for(Method m : fields) {
				StringBuilder result = new StringBuilder(Modifier.toString(m.getModifiers()));
				result.append("\t"+m.getName()).append("(");
	            Parameter[] parameters = m.getParameters();
	            for (int i = 0; i < parameters.length; i++) {
	                result.append(parameters[i].getType().getSimpleName()).append("");
	                if (i < parameters.length - 1) {
	                    result.append(", ");
	                }
	            }
				methodsName.add(""+result+")");
			}
		return methodsName;
	}

	//Extraire les Interfaces Implémentés
	public static List<String> extractInterfacesName(Class<?>[] interfaces) {
		List<String> interfacesName = new ArrayList<String>();
			for(Class<?> f : interfaces) 
				interfacesName.add(f.getName());
		return interfacesName;
	}
	
	//Extraire les Constructeurs
	public static List<String> extractConstructorsName(Constructor<?>[] constructors,String className) {
		List<String> ConstructorsName = new ArrayList<String>();
		for(Constructor<?> f : constructors) {
			StringBuilder result = new StringBuilder(Modifier.toString(f.getModifiers()));
			result.append("\t"+className).append("(");
            Parameter[] parameters = f.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                result.append(parameters[i].getType().getSimpleName()).append("");
                if (i < parameters.length - 1) {
                    result.append(", ");
                }
            }
			ConstructorsName.add(""+result.append(")"));
		}
	return ConstructorsName;
	}

	//Extraire la Super Classe

	public static String extractSuperClassdeName(Class<?> clazz) {
		return clazz.getGenericSuperclass().getTypeName();
	}
	public static String extractSuperClasseName(Class<?> clazz) {
	    if (clazz != null) {
	    	if(clazz.getSuperclass() != null && !clazz.getSuperclass().getSimpleName().equals("Object")) {
	    		return clazz.getSuperclass().getSimpleName();
	    	}else {
				return null;
			}
	    } else {
	        return null; // ou une chaîne vide selon votre logique de gestion
	    }
	}
	
	//Extraire les Classes Internes
	public static List<String> extractInnerClassesName(Class<?>[] innerClasses) {
		List<String> innerClassesName = new ArrayList<String>();
		for(Class<?> f : innerClasses) 
			innerClassesName.add(f.getName());
	return innerClassesName;
	}


}
