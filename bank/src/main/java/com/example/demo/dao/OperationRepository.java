package com.example.demo.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Operation;

@RepositoryRestResource
public interface OperationRepository extends JpaRepository <Operation , Long> {
	
	@Query("select o from Operation o where o.compte.codeCompte=:x order by o.dateOperation desc")
	public List<Operation> listeOperation(@Param("x")String codecompte);
}
