package com.aseds.inpt.appsecurity.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name="Commandes")
public class Commande implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public Commande() {
		
	}
	public Commande(Integer id, String date) {
		super();
		this.id = id;
		this.date = date;
	
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable= false)
    private String date;
	
	@ManyToOne()
	@JoinColumn(name="clientId")
    private Client client;
    
    
    
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

    
}
