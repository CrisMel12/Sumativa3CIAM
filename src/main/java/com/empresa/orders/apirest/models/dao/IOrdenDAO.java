package com.empresa.orders.apirest.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.empresa.orders.apirest.models.entities.Orden;

public interface IOrdenDAO extends CrudRepository<Orden, Long> {
	
	@Query("FROM Orden o WHERE o.estado = 'R' ORDER BY o.fecha DESC")
	List<Orden> findAllRecibidas();
	
	@Query("FROM Orden o WHERE o.estado = 'D' ORDER BY o.fecha DESC")
	List<Orden> findAllDespachadas();
	
	@Query("FROM Orden o WHERE o.estado = 'A' ORDER BY o.fecha DESC")
	List<Orden> findAllAnuladas();
	
	@Query("FROM Orden o WHERE o.estado = 'R' AND o.fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY o.fecha DESC")
	List<Orden> findAllRecibidasWithRangoFechas(@Param("fechaInicio") Date fechaInicio,@Param("fechaFin") Date fechaFin);
	
	@Query("FROM Orden o WHERE o.estado = 'D' AND o.fechaDespacho BETWEEN :fechaInicio AND :fechaFin ORDER BY o.fecha DESC")
	List<Orden> findAllDespachadasWithRangoFechas(@Param("fechaInicio") Date fechaInicio,@Param("fechaFin") Date fechaFin);
	
	@Query("FROM Orden o WHERE o.estado = 'A' AND o.fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY o.fecha DESC")
	List<Orden> findAllAnuladasWithRangoFechas(@Param("fechaInicio") Date fechaInicio,@Param("fechaFin") Date fechaFin);
}
