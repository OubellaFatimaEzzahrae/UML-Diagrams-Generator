package org.mql.java.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.mql.java.model.ClassEntity;
import org.mql.java.model.ClassEntity.FieldType;
import org.mql.java.model.ClassEntity.MethodType;
import org.mql.java.model.PackageEntity;
import org.mql.java.model.ProjectEntity;
import org.mql.java.model.RelationEntity;
import org.mql.java.model.RelationEntity.RelationType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlParser {

    public static ProjectEntity parseProjectXml(String xmlFilePath) {
        try {
            File inputFile = new File(xmlFilePath);
            //création d'un modèle d'arbre DOM à partir du fichier XML.
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //charger le fichier XML et obtenir un objet Document
            Document document = db.parse(inputFile);
            document.getDocumentElement().normalize();
            
            // Récupérer l'élément racine du document XML (project)
            Element projectElement = document.getDocumentElement();
            
            //Utilisez des méthodes telles que getElementsByTagName pour accéder aux éléments du document XML
            // Récupérer le nom du projet
            String projectName = projectElement.getElementsByTagName("name").item(0).getTextContent();
            
            List<PackageEntity> packages = new ArrayList<PackageEntity>();
            // Créer une instance de ProjectEntity
            ProjectEntity project = new ProjectEntity(projectName,packages);
            // Récupérer les packages
            NodeList packageNodes = projectElement.getElementsByTagName("package");
            for (int i = 0; i < packageNodes.getLength(); i++) {
                Element packageElement = (Element) packageNodes.item(i);
                PackageEntity packageEntity = parsePackage(document , packageElement);
                packages.add(packageEntity);
            }

            return project;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static PackageEntity parsePackage(Document document , Element packageElement) {
        // Code pour extraire les informations du package
    	//Récupérer le nom du package
    	String name = packageElement.getAttribute("name");

    	PackageEntity packageEntity = new PackageEntity(name);
    	List<ClassEntity> allFiles = new ArrayList<ClassEntity>();
    	List<ClassEntity> classes = new ArrayList<ClassEntity>();
    	List<ClassEntity> interfaces = new ArrayList<ClassEntity>();
    	List<ClassEntity> annotations = new ArrayList<ClassEntity>();
    	List<ClassEntity> enumerations = new ArrayList<ClassEntity>();
    	String[] fileTypes = {"class", "interface", "annotation", "enumeration"};
    	for (String type : fileTypes) {
    		NodeList classNodes = packageElement.getElementsByTagName(type);
    		for (int i = 0; i < classNodes.getLength(); i++) {
    			Element classElement = (Element) classNodes.item(i);
    			ClassEntity classEntity = parseClass(document, classElement);
    			classEntity.setType(type);
    			classes.add(classEntity);allFiles.add(classEntity);
    		}
		}
    	packageEntity.setAllFiles(allFiles);
    	packageEntity.setClasses(classes);
    	packageEntity.setInterfaces(interfaces);
    	packageEntity.setAnnotations(annotations);
    	packageEntity.setEnumerations(enumerations);
    	return packageEntity;
    }

    
    private static ClassEntity parseClass(Document document , Element classElement) {
        // Code pour extraire les informations du package
    	//Récupérer le nom du package
    	String name = classElement.getAttribute("name");
    	String type = "class";
    	//Récuperer les fields
    	List<FieldType> fields = new ArrayList<FieldType>();
    	NodeList fieldNodes = classElement.getElementsByTagName("field");
    	for (int i = 0; i < fieldNodes.getLength(); i++) {
    		Element fieldElement = (Element) fieldNodes.item(i);
    		FieldType field = parseField(document, fieldElement);
    		fields.add(field);
		}
    	//Récuperer les methods
    	List<MethodType> methods = new ArrayList<MethodType>();
    	NodeList methodNodes = classElement.getElementsByTagName("method");
    	for (int i = 0; i < methodNodes.getLength(); i++) {
    		Element methodElement = (Element) methodNodes.item(i);
    		MethodType method = parseMethod(document, methodElement);
    		methods.add(method);
		}
    	
    	//Récuperer les relations
    	List<RelationEntity> relations = new ArrayList<RelationEntity>();
    	NodeList relationNodes = classElement.getElementsByTagName("relation");
    	for (int i = 0; i < relationNodes.getLength(); i++) {
    		Element relationElement = (Element) relationNodes.item(i);
    		RelationEntity relation = parseRelation(document, relationElement);
    		relations.add(relation);
		}
    	
    	
    	ClassEntity classEntity = new ClassEntity(name, methods, fields);
    	classEntity.setRelations(relations);
    	classEntity.setType(type);
    	return classEntity;
    }
    
    private static FieldType parseField(Document document , Element fieldElement) {
        // Code pour extraire les informations d'un field
    	String name = fieldElement.getAttribute("name");
    	String type = fieldElement.getAttribute("type");
    	String modifier = fieldElement.getAttribute("modifier");
    	FieldType fieldType = new FieldType(name, type, modifier);

    	return fieldType;
    }
    
    
    private static MethodType parseMethod(Document document , Element methodElement) {
        // Code pour extraire les informations d'une methode
    	String name = methodElement.getAttribute("name");

    	String type = methodElement.getAttribute("type");
    	String modifier = methodElement.getAttribute("modifier");
    	List<String> params = new ArrayList<String>();
    	
    	NodeList paramNodes = methodElement.getElementsByTagName("parameter");
    	for (int i = 0; i < paramNodes.getLength(); i++) {
    		Element paramNode = (Element) paramNodes.item(i);
    		String paramType = paramNode.getAttribute("type"); 
    		params.add(paramType);
		}
    	MethodType methodType = new MethodType(name, type, modifier, params);
    	return methodType;
    }
    
    private static RelationEntity parseRelation(Document document , Element relationElement) {
        // Code pour extraire les informations d'une relation
    	String sourceClass = relationElement.getAttribute("source");
    	String targetClass = relationElement.getAttribute("target");
    	String typeString = relationElement.getAttribute("relation-type");
		RelationType type = RelationType.valueOf(typeString );
    	RelationEntity relationType = new RelationEntity(type, sourceClass, targetClass);

    	return relationType;
    }
    
}
