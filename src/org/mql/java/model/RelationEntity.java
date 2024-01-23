package org.mql.java.model;

public class RelationEntity {
    public enum RelationType {
    	ASSOCIATION,
        COMPOSITION,
        AGGREGATION,
        UTILISATION,
        EXTENSION,
    }

    private RelationType type;
    private String description;
    private String sourceClass;
    private String targetClass;

    // Constructeur
    public RelationEntity(RelationType type, String sourceClass, String targetClass) {
        this.type = type;
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    public void display() {
        System.out.println("----------------------");
        System.out.println("Type: " + type);
        System.out.println("Source Class: " + sourceClass);
        System.out.println("Target Class: " + targetClass);
        System.out.println("Description: " + description);
        System.out.println("----------------------");
    }
    
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
	    switch (this.type) {
	        case AGGREGATION:
	            return String.format(type+": La classe %s est agrégée avec la classe %s.", sourceClass, targetClass);
	        case COMPOSITION:
	            return String.format(type+": La classe %s est composée avec la classe %s.", sourceClass, targetClass);
	        case EXTENSION:
	            return String.format(type+": La classe %s extends la classe %s.", sourceClass, targetClass);
	        case UTILISATION:
	            return String.format(type+": La classe %s use la classe %s.", sourceClass, targetClass);
	        case ASSOCIATION:
	            return String.format(type+": La classe %s est en association avec la classe %s.", sourceClass, targetClass);
	        default:
	            return null; 
	    }
	}


    public RelationType getType() {
		return type;
	}

	public void setType(RelationType type) {
		this.type = type;
	}

	public String getSourceClass() {
		return sourceClass;
	}

	public void setSourceClass(String sourceClass) {
		this.sourceClass = sourceClass;
	}

	public String getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}
}
