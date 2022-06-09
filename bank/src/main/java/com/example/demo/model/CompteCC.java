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
@DiscriminatorValue("CC")
public class CompteCC extends Compte {
	
	
	private double decouvert;

	public CompteCC(double decouvert) {
		super();
		this.decouvert = decouvert;
	}

	public CompteCC(String codeCompte, Date dateCreation,Date dateCloture, double solde, Client client, Employes employe,
			Collection<Operation> operations, double decouvert) {
		super(codeCompte, dateCreation, dateCloture, solde, client, employe, operations);
		this.decouvert = decouvert;
	}

	public CompteCC() {
		super();
	}

	public CompteCC(String codeCompte, Date dateCreation,Date dateCloture, double solde, Client client, Employes employe,
			Collection<Operation> operations) {
		super(codeCompte, dateCreation, dateCloture, solde, client, employe, operations);
	}

	

}
