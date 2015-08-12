package com.cybercad.billing.domain;
// Generated 13 Aug, 2015 2:58:27 AM by Hibernate Tools 3.2.4.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PoliticalZone generated by hbm2java
 */
@Entity
@Table(name="political_zone"
    ,catalog="billing"
)
public class PoliticalZone  implements java.io.Serializable {


     private int id;
     private String name;
     private Character type;
     private Set<Connection> connections = new HashSet<Connection>(0);

    public PoliticalZone() {
    }

	
    public PoliticalZone(int id) {
        this.id = id;
    }
    public PoliticalZone(int id, String name, Character type, Set<Connection> connections) {
       this.id = id;
       this.name = name;
       this.type = type;
       this.connections = connections;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="name", length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="type", length=1)
    public Character getType() {
        return this.type;
    }
    
    public void setType(Character type) {
        this.type = type;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="politicalZone")
    public Set<Connection> getConnections() {
        return this.connections;
    }
    
    public void setConnections(Set<Connection> connections) {
        this.connections = connections;
    }




}


