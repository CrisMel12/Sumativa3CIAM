package com.empresa.orders.apirest.services.interfaces;

import java.util.List;

import com.empresa.orders.apirest.models.entities.Producto;

public interface IProductoService {
	
    public List<Producto> findAllActivos();
    
    public List<Producto> findAllInactivos();
	
	public Producto findById(Long id);
	
	public Producto save(Producto producto);
	
	public Producto changeEstate(Producto producto);
	
	public List<Producto> isExist(Producto producto);


}
