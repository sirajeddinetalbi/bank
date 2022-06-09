package com.example.demo.model;

import java.util.Date;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {

	public Versement() {
		super();
	}

	public Versement(Long numeroOperation, Date dateOperation, double montant, Compte compte, Employes employe) {
		super(numeroOperation, dateOperation, montant, compte, employe);
	}

	
	
}
