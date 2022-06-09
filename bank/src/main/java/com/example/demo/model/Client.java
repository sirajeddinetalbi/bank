package com.example.demo.model;


import java.util.Collection;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Client {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codeClient;
	
	private String nomClient;
	
	
	private String email;
	
	
	@OneToMany (mappedBy="client",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Collection<Compte> comptes;

}
