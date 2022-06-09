package com.example.demo.model;

import java.util.Collection;






import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class Employes{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codeEmploye;
	
	
	private String nomEmploye;
	
	
	private String email;
	
	@ManyToOne
	@JoinColumn(name="Code_emp_sup")
	private Employes EmployesSup;
	
	
	@ManyToMany
	@JoinTable(name="Emp_Gr")
	private Collection<Groupes> groupes;

}
