package com.empresa.orders.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.empresa.orders.apirest.models.entities.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Long> {
	
	@Query("FROM Producto p WHERE p.nombre=:#{#producto.nombre} and p.descripcion=:#{#producto.descripcion}")
	List<Producto> findByNombreDescripcion(Producto producto);
	
	@Query("FROM Producto p WHERE p.estado= 'D' ORDER BY p.id DESC")
	List<Producto> findAll();
	
	@Query("FROM Producto p WHERE p.estado= 'I' ORDER BY p.id DESC")
	List<Producto> findAllInactivos();
	

}
