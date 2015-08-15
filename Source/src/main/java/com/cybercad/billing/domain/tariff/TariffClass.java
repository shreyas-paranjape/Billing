package com.cybercad.billing.domain.tariff;
// Generated 13 Aug, 2015 2:58:27 AM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cybercad.billing.domain.conn.ConnectionType;

/**
 * TariffClass generated by hbm2java
 */
@Entity
@Table(name="tariff_class"
    ,catalog="billing"
)
public class TariffClass  implements java.io.Serializable {


     private int id;
     private String description;
     private Date effectiveDate;
     private List<TariffSlab> tariffSlabs ;
     private Set<ConnectionType> connectionTypes = new HashSet<ConnectionType>(0);
     private Date terminationDate;


 	public TariffSlab getHighestSlab(float units) {
 		for (TariffSlab tariffSlab : tariffSlabs) {
 			if (units / tariffSlab.getToUnit() <= 1) {
 				return tariffSlab;
 			}
 		}
 		// Beyond last slab
 		return tariffSlabs.get(tariffSlabs.size() - 1);
 	}

 	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public double getChargesUntilSlab(float units) {
 		double charges = 0;
 		for (TariffSlab slab : tariffSlabs) {
 			if (units / slab.getToUnit() <= 1) {
 				charges += slab.getSlabCharges();
 			}
 		}
 		return charges;
 	}

 	public double getChargesPerDay(float unitsPerDay) {
 		final TariffSlab highestSlab = getHighestSlab(unitsPerDay);
 		final double highestSlabCharges = getChargesUntilSlab(highestSlab
 				.getToUnit());
 		final float offsetUnits = unitsPerDay - highestSlab.getFromUnit();
 		final double offsetCharges = highestSlab.getCharges(offsetUnits);
 		return highestSlabCharges + offsetCharges;
 	}
 	
    public TariffClass() {
    }

	
    public TariffClass(int id) {
        this.id = id;
    }
    public TariffClass(int id, String description, Date effectiveDate, List<TariffSlab> tariffSlabs, Set<ConnectionType> connectionTypes) {
       this.id = id;
       this.description = description;
       this.effectiveDate = effectiveDate;
       this.tariffSlabs = tariffSlabs;
       this.connectionTypes = connectionTypes;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="description", length=45)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="effective_date", length=10)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }
    
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tariffClass")
    public List<TariffSlab> getTariffSlabs() {
        return this.tariffSlabs;
    }
    
    public void setTariffSlabs(List<TariffSlab> tariffSlabs) {
        this.tariffSlabs = tariffSlabs;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tariffClass")
    public Set<ConnectionType> getConnectionTypes() {
        return this.connectionTypes;
    }
    
    public void setConnectionTypes(Set<ConnectionType> connectionTypes) {
        this.connectionTypes = connectionTypes;
    }




}


