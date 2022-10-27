package com.empresa.orders.apirest.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="detalle_ordenes", schema = "public", catalog = "db_orders")
public class DetalleOrden implements Serializable  {
private static final long serialVersionUID = 1L;
	
@Id
@Column(name = "id", nullable = false)
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

@Column(name = "cantidad", nullable = false)
private Integer cantidad;

@ManyToOne
@JoinColumn(name = "producto_id", referencedColumnName = "id", nullable = false)
private Producto producto;

@ManyToOne
@JsonBackReference
@JoinColumn(name = "orden_id", referencedColumnName = "id", nullable = false)
private Orden orden;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public Integer getCantidad() {
			return cantidad;
		}
		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}
		
		public Producto getProducto() {
			return producto;
		}
		public void setProducto(Producto producto) {
			this.producto = producto;
		}
		
		public Orden getOrden() {
			return orden;
		}
		public void setOrden(Orden orden) {
			this.orden = orden;
		}					
}
