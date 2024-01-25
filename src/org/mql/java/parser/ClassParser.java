package org.mql.java.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mql.java.model.ClassEntity;
import org.mql.java.model.ClassEntity.FieldType;
import org.mql.java.model.ClassEntity.MethodType;
import org.mql.java.model.RelationEntity;
import org.mql.java.model.RelationEntity.RelationType;

public class ClassParser{
	
    public static ClassEntity parseClass(Class<?> clazz) {
        try {
            String className = clazz.getSimpleName();
            List<MethodType> methodNames = extractMethodsName(clazz.getDeclaredMethods());
            List<FieldType> fieldNames = extractFieldsName(clazz.getDeclaredFields());
            List<String> constructorsNames = extractConstructorsName(clazz.getDeclaredConstructors(),className);            
            String superClasse = extractSuperClasseName(clazz);
            ClassEntity ce = new ClassEntity(className, methodNames, fieldNames, constructorsNames, superClasse);
            //Filtrer les fichiers(classes, interfaces,.....).
            defineType(clazz, ce);
            //Extraire les différents types de relations
            if(!clazz.isAnnotation() && !clazz.isEnum() && !clazz.isInterface())
            	ce.setRelations(extractRelations(clazz));
            return ce;
        } catch (Exception e) {
        	System.out.println("Error : "+e.getMessage());
        	return null; 
        }
    }
    
	//Extraire les Attributs
	public static List<FieldType> extractFieldsName(Field[] fields ) {
		List<FieldType> fieldsType = new ArrayList<>();
			for(Field f : fields) {
		        String fieldName = f.getName();
		        if (fieldName.startsWith("$SWITCH_TABLE$") || fieldName.startsWith("this$")) {
		            continue;
		        }
				FieldType ft = new FieldType();
				ft.setName(f.getName());
				ft.setModifier(Modifier.toString(f.getModifiers()));
				ft.setType(f.getType().getSimpleName());
				fieldsType.add(ft);
			}
		return fieldsType;
	}

	//Extraire les Méthodes
	public static List<MethodType> extractMethodsName(Method[] methods) {
		List<MethodType> methodsType = new ArrayList<MethodType>();
			for(Method m : methods) {
		        String methodName = m.getName();
		        if (methodName.startsWith("$SWITCH_TABLE$") || methodName.startsWith("this$")) {
		            continue;
		        }
				MethodType mt = new MethodType();
				mt.setName(m.getName());
				mt.setModifier(Modifier.toString(m.getModifiers()));
				mt.setReturnType(m.getReturnType().getSimpleName());
	            Class<?>[] parameters =  m.getParameterTypes();
	            List<String> params = new ArrayList<>();
	            for (Class<?> parameter : parameters) {
	                params.add(parameter.getTypeName().getClass().getSimpleName());
	            }
	            mt.setParameters(params);
	            methodsType.add(mt);
			}
		return methodsType;
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
            Class<?>[] parameters = f.getParameterTypes();
            for (int i = 0; i < parameters.length; i++) {
                result.append(parameters[i].getTypeName().getClass().getSimpleName()).append("");
                if (i < parameters.length - 1) {
                    result.append(", ");
                }
            }
			ConstructorsName.add(""+result.append(")"));
		}
	return ConstructorsName;
	}

	//Extraire la Super Classe
	public static String extractSuperClasseName(Class<?> clazz) {
	    if (clazz != null) {
	    	if(clazz.getSuperclass() != null && !clazz.getSuperclass().getSimpleName().equals("Object")) {
	    		return clazz.getSuperclass().getSimpleName();
	    	}else {
				return null;
			}
	    } else {
	        return null; 
	    }
	}
	
	//Extraire les Classes Internes
	public static List<String> extractInnerClassesName(Class<?>[] innerClasses) {
		List<String> innerClassesName = new ArrayList<String>();
		for(Class<?> f : innerClasses) 
			innerClassesName.add(f.getName());
	return innerClassesName;
	}
	
	
	//Définire le type de classe(Class, Interface, Ennum , Annotation)
	private static boolean defineType(Class<?> clazz, ClassEntity ce) {
        if(clazz.isAnnotation()) {
        	ce.setType("annotation");
        }else if(clazz.isEnum()){
        	ce.setType("ennumeration");

        }else if (clazz.isInterface()) {
        	ce.setType("interface");
        	
        }else{
        	ce.setType("class");
		}
		return true;
	}
	
    //Ectraire les différentes types de relations
	public static List<RelationEntity> extractRelations(Class<?> clazz) {
		List<RelationEntity> relations = new ArrayList<>();
		RelationEntity re;
        // Détection de relations d'extension
		String superClass = extractSuperClasseName(clazz);
        if ( superClass!= null) {
            re = new RelationEntity(RelationType.EXTENSION, clazz.getSimpleName(), superClass);
            relations.add(re);
        }
     // Détection de relations de composition / agrégation
        Set<Class<?>> nonDoublons = new HashSet<>();
        for (Field field : clazz.getDeclaredFields()) {
            Class<?> fieldType = field.getType();
            // Vérifier si ce type de champ a déjà été traité
            if (!nonDoublons.contains(fieldType) && !fieldType.isPrimitive() && !fieldType.equals(String.class)) {
                if (isAggregation(field)) {
                    Class<?> elementType = getAggregationElementType(field);
                    if(!elementType.isPrimitive()) {
	                    re = new RelationEntity(RelationType.AGGREGATION, clazz.getSimpleName(), elementType.getSimpleName());
	                    relations.add(re);
                    }
                }
                else if (isAssociation(clazz,field)) {
                    re = new RelationEntity(RelationType.ASSOCIATION, clazz.getSimpleName(), fieldType.getSimpleName());
                    relations.add(re);
                } else if (isComposition(field)) {
                    re = new RelationEntity(RelationType.COMPOSITION, clazz.getSimpleName(), fieldType.getSimpleName());
                    relations.add(re);
                } 
                nonDoublons.add(fieldType);
            }
        }
        nonDoublons.clear();
        // Détection de relations d'utilisation
        for (Method method : clazz.getDeclaredMethods()) {
            for (Class<?> parameterType : method.getParameterTypes()) {
                if (!nonDoublons.contains(parameterType) &&
                    !parameterType.isPrimitive() &&
                    !parameterType.equals(String.class)) {  // Ajout de la condition pour exclure String
                    re = new RelationEntity(RelationType.UTILISATION, clazz.getSimpleName(), parameterType.getSimpleName());
                    relations.add(re);
                    nonDoublons.add(parameterType);
                }
            }
        }

        nonDoublons.clear();

		return relations;
	}
	
	private static boolean isAssociation(Class<?> clazz, Field field) {
	    Set<Class<?>> processedTypes = new HashSet<>();
	    Class<?> fieldClass = field.getType();
	    if (!processedTypes.contains(fieldClass) && !fieldClass.isPrimitive()) {
	        processedTypes.add(fieldClass);
	        return Arrays.stream(fieldClass.getDeclaredFields())
	                .anyMatch(innerField -> innerField.getType().equals(clazz));
	    }
	    return false;
	}



	private static boolean isAggregation(Field field) {
	    return Collection.class.isAssignableFrom(field.getType()) || field.getType().isArray();
	}

	private static Class<?> getAggregationElementType(Field field) {
	    if (field.getType().isArray()) {
	        return field.getType().getComponentType();
	    } else if (Collection.class.isAssignableFrom(field.getType())) {
	        Type genericType = field.getGenericType();
	        if (genericType instanceof ParameterizedType) {
	            Type[] typeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
	            if (typeArguments.length > 0) {
	                if (typeArguments[0] instanceof Class) {
	                    return (Class<?>) typeArguments[0];
	                }
	            }
	        }
	    }
	    return null;
	}
	
	private static boolean isComposition(Field field) {
	    return !field.getType().isPrimitive() && !field.getType().isArray() && !Collection.class.isAssignableFrom(field.getType());
	}


}
