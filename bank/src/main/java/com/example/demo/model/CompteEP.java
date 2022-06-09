package com.example.demo.model;


import java.util.Collection;

import java.util.Date;

import jakarta.persistence.DiscriminatorValue;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("CE")
public class CompteEP extends Compte{

	private double taux;

	public CompteEP() {
		super();
	}

	public CompteEP(String codeCompte, Date dateCreation,Date dateCloture, double solde, Client client, Employes employe,
			Collection<Operation> operations) {
		super(codeCompte, dateCreation, dateCloture, solde, client, employe, operations);
	}

	public CompteEP(double taux) {
		super();
		this.taux = taux;
	}

	public CompteEP(String codeCompte, Date dateCreation,Date dateCloture, double solde, Client client, Employes employe,
			Collection<Operation> operations, double taux) {
		super(codeCompte, dateCreation, dateCloture, solde, client, employe, operations);
		this.taux = taux;
	}
	
	
	
	

	
	
}
