package com.empresa.orders.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empresa.orders.apirest.models.entities.Cliente;

public interface IClienteDAO extends JpaRepository<Cliente,Long>{
	
	@Query("FROM Cliente c WHERE c.nombre=:#{#cliente.nombre} and c.correo=:#{#cliente.correo}")
	List<Cliente> findByNombreEmail(Cliente cliente);	
}
          






