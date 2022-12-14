package com.empresa.orders.apirest.services.implement;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.orders.apirest.models.dao.IClienteDAO;
import com.empresa.orders.apirest.models.entities.Cliente;
import com.empresa.orders.apirest.services.interfaces.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDAO clienteDAO;
	
	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>)clienteDAO.findAll();
	}

	@Override
	public Cliente findById(Long id) {
		return clienteDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDAO.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDAO.deleteById(id);
	}

	@Override
	public List<Cliente> isExist(Cliente cliente) {
		return clienteDAO.findByNombreEmail(cliente);
	}
}
