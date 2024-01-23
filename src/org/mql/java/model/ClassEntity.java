package org.mql.java.model;

import java.util.List;

public class ClassEntity {
    private String name;
    private String type;
    private List<MethodType> methods;
    private List<FieldType> fields;
    private String superClasse;
    private List<String> innerClasses;
    private List<String> constructors;
    private List<RelationEntity> relations;
    
    //afficher les interfaces, annotations, ennums s'ils sont définit dans cette classe.
    private List<String> interfaces;
    private List<String> annotations; 
    private List<String> ennumerations; 
   
    
	public ClassEntity(String name, List<MethodType> methods, List<FieldType> fields) {
        this.name = name;
        this.methods = methods;
        this.fields = fields;
    }
	public ClassEntity(String name, List<MethodType> methods, List<FieldType> fields,List<String> constructors,String superClasse) {
        this.name = name;
        this.methods = methods;
        this.fields = fields;
        this.constructors = constructors;
        this.superClasse = superClasse;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MethodType> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodType> methods) {
		this.methods = methods;
	}

	public List<FieldType> getFields() {
		return fields;
	}

	public void setFields(List<FieldType> fields) {
		this.fields = fields;
	}


	public String getSuperClasse() {
		return superClasse;
	}


	public void setSuperClasse(String superClasse) {
		this.superClasse = superClasse;
	}

	public List<String> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<String> interfaces) {
		this.interfaces = interfaces;
	}

	public List<String> getInnerClasses() {
		return innerClasses;
	}

	public void setInnerClasses(List<String> innerClasses) {
		this.innerClasses = innerClasses;
	}

	public List<String> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<String> constructors) {
		this.constructors = constructors;
	}
	public List<String> getAnnotations() {
		return annotations;
	}
	public void setAnnotations(List<String> annotations) {
		this.annotations = annotations;
	}
	public List<String> getEnnumerations() {
		return ennumerations;
	}
	public void setEnnumerations(List<String> ennumerations) {
		this.ennumerations = ennumerations;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<RelationEntity> getRelations() {
		return relations;
	}
	public void setRelations(List<RelationEntity> relations) {
		this.relations = relations;
	}


	// Classe interne représentant un champ
    public static class FieldType {
        private String name;
        private String type;
        private String modifier;
        private List<String> annotations;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public List<String> getAnnotations() {
			return annotations;
		}
		public void setAnnotations(List<String> annotations) {
			this.annotations = annotations;
		}
		public String getModifier() {
			return modifier;
		}
		public void setModifier(String modifier) {
			this.modifier = modifier;
		}
		
	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append(modifier).append(" ").append(type).append(" ").append(name);

	        if (annotations != null && !annotations.isEmpty()) {
	            sb.append(" (Annotations: ").append(annotations).append(")");
	        }

	        return sb.toString();
	    }
    }

    // Classe interne représentant une méthode
    public static class MethodType {
        private String name;
        private String modifier;
        private String returnType;
        private List<String> parameters;
        private List<String> annotations;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getReturnType() {
			return returnType;
		}
		public void setReturnType(String returnType) {
			this.returnType = returnType;
		}
		public List<String> getParameters() {
			return parameters;
		}
		public void setParameters(List<String> parameters) {
			this.parameters = parameters;
		}
		public List<String> getAnnotations() {
			return annotations;
		}
		public void setAnnotations(List<String> annotations) {
			this.annotations = annotations;
		}
		public String getModifier() {
			return modifier;
		}
		public void setModifier(String modifier) {
			this.modifier = modifier;
		}
		   @Override
		    public String toString() {
		        StringBuilder sb = new StringBuilder();
		        sb.append(modifier).append(" ").append(returnType).append(" ").append(name).append("(");

		        if (parameters != null && !parameters.isEmpty()) {
		            sb.append(String.join(", ", parameters));
		        }

		        sb.append(")");

		        if (annotations != null && !annotations.isEmpty()) {
		            sb.append(" (Annotations: ").append(annotations).append(")");
		        }

		        return sb.toString();
		    }
    }

}

