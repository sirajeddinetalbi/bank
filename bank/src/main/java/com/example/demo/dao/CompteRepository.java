package com.example.demo.dao;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Compte;

@RepositoryRestResource
public interface CompteRepository extends JpaRepository <Compte , String> {
	
	@Query("select c from Compte c where c.dateCloture is null")
	public List<Compte> listeComptes();
}
