package org.mql.java.parser;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.mql.java.model.ClassEntity;

public class InterfaceParser {
	public ClassEntity parseInterface(Class<?> interfaceClass) {
        String interfaceName = interfaceClass.getName();

        // Extraction des méthodes déclarées par l'interface
        Method[] methods = interfaceClass.getDeclaredMethods();

        // Création de l'entité InterfaceEntity
        ClassEntity interfaceEntity = new ClassEntity(interfaceName, extractMethodNames(methods),null);

        return interfaceEntity;
    }

    private List<String> extractMethodNames(Method[] methods) {
    	List<String> methodsList = new ArrayList<String>();
    	for(Method m : methods) {
    		methodsList.add(m.getName());
    	}
    	
    	return methodsList;
    }
}
