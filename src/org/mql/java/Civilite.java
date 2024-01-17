package org.mql.java;

public enum Civilite {  // dans le fichier Civilite.java  
    MADAME("MME"), MADEMOISELLE("MLLE"), MONSIEUR("MR") ;  
    
    private String abreviation ;  
     
    //Only private is permitted for the enum constructor; .
    private Civilite(String abreviation) {  
        this.abreviation = abreviation ;  
   }  
     
    public String getAbreviation() {  
        return  this.abreviation ;  
   } 
}