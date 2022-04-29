package com.aseds.inpt.appsecurity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aseds.inpt.appsecurity.model.Commande;


@Repository
public interface CommandeRepertoire extends JpaRepository<Commande,Integer> {

}
