package com.empresa.orders.apirest.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="clientes", schema = "public", catalog = "db_orders")

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min=3,max=100)
	@Column(name="nombre", nullable=false, length=100)
	private String nombre;
	
	@Column(name="telefono", nullable=true, length=15)
	private String telefono;
	
	
	@NotEmpty
	@Email
	@Column(name="correo", nullable=false, length=80, unique=true)
	private String correo;

	@Column(name="direccion", nullable=false, length=200)
	private String direccion;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@Column(name="estado", nullable=false, length=1)
	private String estado;
	
	//Relacion de 1:N con Orden
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REFRESH, orphanRemoval = true)
	@JsonBackReference
	private List<Orden> ordenes = new ArrayList<>();
	
	@PrePersist
	public void setCreateAt(){
		this.createAt = new Date();
		this.estado = "A";
	}
	
	//Getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
