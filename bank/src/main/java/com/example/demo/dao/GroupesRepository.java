package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Compte;
import com.example.demo.model.Groupes;

@RepositoryRestResource
public interface GroupesRepository extends JpaRepository <Groupes , Long>{

}
