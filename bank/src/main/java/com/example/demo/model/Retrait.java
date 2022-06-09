package com.example.demo.model;

import java.util.Date;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {

	public Retrait() {
		super();
	}

	public Retrait(Long numeroOperation, Date dateOperation, double montant, Compte compte, Employes employe) {
		super(numeroOperation, dateOperation, montant, compte, employe);
	}

	
	
}
