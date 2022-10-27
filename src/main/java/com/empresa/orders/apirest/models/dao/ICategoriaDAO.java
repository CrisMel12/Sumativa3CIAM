package com.empresa.orders.apirest.models.dao;

import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.empresa.orders.apirest.models.entities.Categoria;

public interface ICategoriaDAO extends CrudRepository<Categoria,Long>{
	
	List<Categoria> findByNombreIgnoreCase(String cadena);

}
