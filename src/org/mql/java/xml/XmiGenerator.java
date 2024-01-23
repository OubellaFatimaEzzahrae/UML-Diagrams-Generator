package org.mql.java.xml;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mql.java.model.ClassEntity;
import org.mql.java.model.PackageEntity;
import org.mql.java.model.ProjectEntity;
import org.mql.java.model.RelationEntity;
import org.mql.java.model.ClassEntity.FieldType;
import org.mql.java.model.ClassEntity.MethodType;
import org.mql.java.model.RelationEntity.RelationType;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmiGenerator {

    public static void generateProjectXmi(ProjectEntity project, String xmiFilePath) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            Element xmiElement = document.createElementNS("http://www.omg.org/XMI", "xmi:XMI");
            xmiElement.setAttribute("xmlns:xmi", "http://www.omg.org/XMI");
            xmiElement.setAttribute("xmlns:uml", "http://www.omg.org/UML/2.0");
            document.appendChild(xmiElement);

            Element projectElement = document.createElement("uml:project");
            xmiElement.appendChild(projectElement);

            Element nameElement = document.createElement("uml:name");
            nameElement.appendChild(document.createTextNode(project.getProjectName()));
            projectElement.appendChild(nameElement);

            Element packagesElement = document.createElement("uml:packages");
            projectElement.appendChild(packagesElement);

            // Ajoutez des éléments pour chaque package dans le projet
            for (PackageEntity packageEntity : project.getPackages()) {
                Element packageElement = createPackageElement(document, packageEntity);
                packagesElement.appendChild(packageElement);
            }

            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(xmiFilePath);

            transformer.transform(source, result);

            System.out.println("Écriture dans le fichier XMI réussie.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    private static Element createPackageElement(Document document, PackageEntity packageEntity) {
        Element packageElement = document.createElement("uml:Package");

        Attr nameElement = document.createAttribute("name");
        nameElement.appendChild(document.createTextNode(packageEntity.getName()));
        packageElement.setAttributeNode(nameElement);

        if(!packageEntity.getClasses().isEmpty()) {
            Element classesElement = document.createElement("uml:classes");
            packageElement.appendChild(classesElement);
        	for (ClassEntity ce : packageEntity.getClasses()) {
        		classesElement.appendChild(createClassElement(document, ce));
        	}
        }
        if(!packageEntity.getInterfaces().isEmpty()) {
            Element interfacesElement = document.createElement("uml:interfaces");
            packageElement.appendChild(interfacesElement);
        	for (ClassEntity ce : packageEntity.getInterfaces()) {
        	    createInterfaceElement(document, ce);
        	    interfacesElement.appendChild(createInterfaceElement(document, ce));

        	}
        }
        if(!packageEntity.getAnnotations().isEmpty()) {
            Element annotationsElement = document.createElement("uml:annotations");
            packageElement.appendChild(annotationsElement);
        	for (ClassEntity ce : packageEntity.getAnnotations()) {
        	    createAnnotationElement(document, ce);
        	    annotationsElement.appendChild(createAnnotationElement(document, ce));

        	}
        }
        if(!packageEntity.getEnumerations().isEmpty()) {
            Element enumerationsElement = document.createElement("uml:ennumerations");
            packageElement.appendChild(enumerationsElement);
        	for (ClassEntity ce : packageEntity.getEnumerations()) {
        	    createEnumElement(document, ce);
        	    enumerationsElement.appendChild(createEnumElement(document, ce));
        	}
        }
        return packageElement;
    }



    private static Element createClassElement(Document document, ClassEntity classEntity) {
        Element classElement = document.createElement("uml:class");

        Attr nameElement = document.createAttribute("uml:name");
        nameElement.setTextContent(classEntity.getName());
        classElement.setAttributeNode(nameElement);
        
        if(!classEntity.getFields().isEmpty()) {
            Element fieldsElement = document.createElement("uml:fields");
            classElement.appendChild(fieldsElement);
            
            for (FieldType ft : classEntity.getFields()) {
            	fieldsElement.appendChild(createFieldElement(document, ft));
            }
        }
        
        if(!classEntity.getMethods().isEmpty()) {
            Element methodsElement = document.createElement("uml:methods");
            classElement.appendChild(methodsElement);
            
            for (MethodType mt : classEntity.getMethods()) {
            	methodsElement.appendChild(createMethodElement(document,mt));
            }
        }
        
        if(!classEntity.getRelations().isEmpty()) {
            Element relationsElement = document.createElement("uml:relations");
            classElement.appendChild(relationsElement);
            
            for (RelationEntity re : classEntity.getRelations()) {
            	relationsElement.appendChild(createRelationElement(document,re));
            }
        }
        
        return classElement;
    }

    private static Element createInterfaceElement(Document document, ClassEntity classEntity) {
        Element interfaceElement = document.createElement("uml:interface");

        Attr nameElement = document.createAttribute("uml:name");
        nameElement.appendChild(document.createTextNode(classEntity.getName()));
        interfaceElement.setAttributeNode(nameElement);
        
        if(!classEntity.getFields().isEmpty()) {
            Element fieldsElement = document.createElement("uml:fields");
            interfaceElement.appendChild(fieldsElement);
            
            for (FieldType ft : classEntity.getFields()) {
            	fieldsElement.appendChild(createFieldElement(document, ft));
            }
        }
        
        if(!classEntity.getMethods().isEmpty()) {
            Element methodsElement = document.createElement("uml:methods");
            interfaceElement.appendChild(methodsElement);
            
            for (MethodType mt : classEntity.getMethods()) {
            	methodsElement.appendChild(createMethodElement(document,mt));
            }
        }
        return interfaceElement;
    }

    private static Element createEnumElement(Document document, ClassEntity classEntity) {
        Element enumElement = document.createElement("uml:enumeration");

        Attr nameElement = document.createAttribute("uml:name");
        nameElement.appendChild(document.createTextNode(classEntity.getName()));
        enumElement.setAttributeNode(nameElement);

        
        if(!classEntity.getFields().isEmpty()) {
            Element fieldsElement = document.createElement("uml:fields");
            enumElement.appendChild(fieldsElement);
            
            for (FieldType ft : classEntity.getFields()) {
            	fieldsElement.appendChild(createFieldElement(document, ft));
            }
        }
        
        if(!classEntity.getMethods().isEmpty()) {
            Element methodsElement = document.createElement("uml:methods");
            enumElement.appendChild(methodsElement);
            
            for (MethodType mt : classEntity.getMethods()) {
            	methodsElement.appendChild(createMethodElement(document,mt));
            }
        }
        
        return enumElement;
    }

    private static Element createAnnotationElement(Document document, ClassEntity classEntity) {
        Element annotationElement = document.createElement("uml:annotation");

        Attr nameElement = document.createAttribute("name");
        nameElement.appendChild(document.createTextNode(classEntity.getName()));
        annotationElement.setAttributeNode(nameElement);

        if(!classEntity.getFields().isEmpty()) {
            Element fieldsElement = document.createElement("uml:fields");
            annotationElement.appendChild(fieldsElement);
            
            for (FieldType ft : classEntity.getFields()) {
            	fieldsElement.appendChild(createFieldElement(document, ft));
            }
        }
        
        if(!classEntity.getMethods().isEmpty()) {
            Element methodsElement = document.createElement("uml:methods");
            annotationElement.appendChild(methodsElement);
            
            for (MethodType mt : classEntity.getMethods()) {
            	methodsElement.appendChild(createMethodElement(document,mt));
            }
        }
        
        
        return annotationElement;
    }
    
    
    private static Element createFieldElement(Document document, FieldType fieldType) {
    	String fName = fieldType.getName(),fModifier = fieldType.getModifier(),fType = fieldType.getType();
        	Element fieldElement = document.createElement("uml:field");
        	if(!"".equals(fName) && !"".equals(fModifier) && !"".equals(fType)) {
	        	if(!"".equals(fName)) {
	        		Attr fnameElement = document.createAttribute("uml:name");
	        		fnameElement.setTextContent(fieldType.getName());
	        		fieldElement.setAttributeNode(fnameElement);
	        	}
	        	
	        	if(!"".equals(fModifier)) {
		        	Attr fmodifierElement = document.createAttribute("uml:modifier");
		        	fmodifierElement.setTextContent(fieldType.getModifier());
		        	fieldElement.setAttributeNode(fmodifierElement);   
	        	}
	        	
	        	if(!"".equals(fType)) {
		        	Attr ftypeElement = document.createAttribute("uml:type");
		        	ftypeElement.setTextContent(fieldType.getType());
		        	fieldElement.setAttributeNode(ftypeElement);
	        	}
        	}
        	return fieldElement;
    }
    
    private static Element createMethodElement(Document document, MethodType methodType) {
    	String mName = methodType.getName(),mModifier = methodType.getModifier(),
    			mReturnType = methodType.getReturnType();
    	List<String> mParams = methodType.getParameters();
    	
    	Element methodElement = document.createElement("uml:method");
    	if(!"".equals(mName)) {
	    	Attr fnameElement = document.createAttribute("uml:name");
	    	fnameElement.setTextContent(methodType.getName());
	    	methodElement.setAttributeNode(fnameElement);
	    }
    	
    	if(!"".equals(mModifier)) {
    		Attr mModifierElement = document.createAttribute("uml:modifier");
	    	mModifierElement.setTextContent(methodType.getModifier());
	    	methodElement.setAttributeNode(mModifierElement);   
    	}
    	
    	if(!"".equals(mReturnType)) {
    		Attr mTypeElement = document.createAttribute("uml:return-type");
	    	mTypeElement.setTextContent(methodType.getReturnType());
	    	methodElement.setAttributeNode(mTypeElement);
    	}
    	
    	if(!mParams.isEmpty()) {
    		Element mParamsElement = document.createElement("uml:parameters");
	    	methodElement.appendChild(mParamsElement);
	    	for (String mp : mParams) {
				Element mParamElement = document.createElement("uml:parameter");
				mParamsElement.appendChild(mParamElement);
				Attr typeParam = document.createAttribute("uml:type");
				typeParam.setTextContent(mp);
				mParamElement.setAttributeNodeNS(typeParam);
			}
    	}
    	
    	return methodElement;
}
  
    private static Element createRelationElement(Document document, RelationEntity re) {
    	RelationType type = re.getType();
		String source = re.getSourceClass(), target = re.getTargetClass();
        	Element relationElement = document.createElement("uml:relation");
	
        	if(!"".equals(source)) {
        		Attr sourceAttr = document.createAttribute("uml:source");
        		sourceAttr.setTextContent(re.getSourceClass());
        		relationElement.setAttributeNode(sourceAttr);
        	}	
        	
	        	if(!"".equals(target)) {
	        		Attr targetAttr = document.createAttribute("uml:target");
	        		targetAttr.setTextContent(re.getTargetClass());
	        		relationElement.setAttributeNode(targetAttr);
	        	}
	        	if(!"".equals(type.toString())) {
		        	Attr typeAttr = document.createAttribute("uml:relation-type");
		        	typeAttr.setTextContent(type.toString());
		        	relationElement.setAttributeNode(typeAttr);
	        	}
        	return relationElement;
    }  
    

}
