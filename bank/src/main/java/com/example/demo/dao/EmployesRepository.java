package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Employes;

@RepositoryRestResource
public interface EmployesRepository extends JpaRepository <Employes , Long> {

}
