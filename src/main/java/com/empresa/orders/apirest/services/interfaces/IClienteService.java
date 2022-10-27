package com.empresa.orders.apirest.services.interfaces;

import java.util.List;
import com.empresa.orders.apirest.models.entities.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
	public List<Cliente> isExist(Cliente cliente);

}
