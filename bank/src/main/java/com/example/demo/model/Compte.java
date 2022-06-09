package com.example.demo.model;


import java.util.Collection;



import java.util.Date;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type_CPT",discriminatorType=DiscriminatorType.STRING,length= 2)
public abstract class Compte {
	
	@Id
	private String codeCompte;
	
	
	private Date dateCreation;
	
	private Date dateCloture;
	
	private double solde;
	
	@ManyToOne
	@JoinColumn(name="Code_Cli")
	private Client client;
	
	
	@ManyToOne
	@JoinColumn(name="Code_Emp")
	private Employes employe;
	
	
	@OneToMany( mappedBy="compte")
	private Collection <Operation> operations;
	
}
