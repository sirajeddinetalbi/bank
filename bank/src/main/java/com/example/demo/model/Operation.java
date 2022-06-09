package com.example.demo.model;

import java.util.Date;



import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(length=1)
public abstract class Operation{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numeroOperation;
	
	
	private Date dateOperation;
	
	
	private double montant;
	
	@ManyToOne
	@JoinColumn(name="Code_CPT")
	private Compte compte;
	
	
	@ManyToOne
	@JoinColumn(name="Code_Employe")
	private Employes employe;

}
