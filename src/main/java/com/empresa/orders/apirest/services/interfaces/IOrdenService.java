package com.empresa.orders.apirest.services.interfaces;

import java.util.Date;
import java.util.List;

import com.empresa.orders.apirest.models.entities.Orden;

public interface IOrdenService {
	
	public List<Orden> findAll(Date fecha);
	
	public List<Orden> findAllDespachadas(Date fecha);
	
	public List<Orden> findAllAnuladas(Date fecha);
	
	public Orden saveOrUpdate(Orden orden);
	
	public Orden changeState(Orden orden);

	public Orden findById(Long id);
}
