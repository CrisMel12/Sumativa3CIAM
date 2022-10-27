package com.empresa.orders.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.empresa.orders.apirest.models.entities.DetalleOrden;

public interface IDetalleOrdenDAO extends CrudRepository<DetalleOrden, Long> {

}
