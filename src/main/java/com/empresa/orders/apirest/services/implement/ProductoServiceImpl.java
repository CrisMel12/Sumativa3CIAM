package com.empresa.orders.apirest.services.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.orders.apirest.models.dao.IProductoDAO;
import com.empresa.orders.apirest.models.entities.Producto;
import com.empresa.orders.apirest.services.interfaces.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IProductoDAO productoDAO;
	
	@Override
	public List<Producto> findAllActivos() {
		return productoDAO.findAll();
	}

	@Override
	public List<Producto> findAllInactivos() {
		return productoDAO.findAllInactivos();
	}

	@Override
	public Producto findById(Long id) {
		return productoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDAO.save(producto);
	}

	@Override
	public Producto changeEstate(Producto producto) {
		return productoDAO.save(producto);
	}
	
	@Override
	public List<Producto> isExist(Producto producto) {
		return productoDAO.findByNombreDescripcion(producto);
	}
}
