package com.empresa.orders.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.empresa.orders.apirest.models.entities.Producto;
import com.empresa.orders.apirest.services.interfaces.IProductoService;
import com.empresa.orders.apirest.services.interfaces.IUploadFileService;

@CrossOrigin(origins = ".")
@RestController
@RequestMapping("/api")

public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	private Logger logger = LoggerFactory.getLogger(ProductoController.class);
	
	@GetMapping("/productos/activos")
	public List<Producto> getAllActivos(){
		return productoService.findAllActivos();
	}
	
	@GetMapping("/productos/inactivos")
	public List<Producto> getAllInactivos(){
		return productoService.findAllInactivos();
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Producto producto = null;
		Map<String, Object> response = new HashMap<>();
		try {
			producto = productoService.findById(id);
		}catch(DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage());
		}
		if(producto == null) {
			response.put("message", "El producto con ID: " .concat(id.toString().concat(" No existen en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Producto>(producto,HttpStatus.OK);
	}
	
	@PostMapping("/productos")
	public ResponseEntity<?> save(@RequestPart Producto producto, @RequestPart(name = "imagen", required = false) MultipartFile imagen) throws IOException{
		String imageNewName = "";
		Map<String, Object> response = new HashMap<>();
		
		try {
			if(productoService.isExist(producto).size() > 0 && producto.getId()== null) {
				response.put("message", "Ya existe un producto con este nombre y descripción en la base de datos");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CONFLICT);
			}else {
				if(imagen != null) {
					imageNewName = uploadService.copyFile(imagen);
					producto.setImagen(imageNewName);
				}
				productoService.save(producto);
			}
		}catch(DataAccessException e){
			response.put("message", "Error al insertar el registro en la base de datos");
			logger.error("ERROR: ".concat(e.getMessage()));
			uploadService.deleteFile(imageNewName);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Producto registrado con exito...");
		response.put("producto", producto);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> update(@RequestPart Producto producto, @PathVariable Long id, @RequestPart(name = "imagen", required = false) MultipartFile imagen)throws IOException{
		
		String imageNewName = "";
		Producto productoActual = productoService.findById(id);
		Producto productoUpdated = null;
		Map<String, Object> response = new HashMap<>();
			if(productoActual == null) {
				response.put("message", "Error: no se puede editar, el producto con ID: ".concat(id.toString().concat("no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}
			try {
				productoActual.setNombre(producto.getNombre());
				productoActual.setDescripcion(producto.getDescripcion());
				productoActual.setPrecio(producto.getPrecio());
				productoActual.setExistencia(producto.getExistencia());
				productoActual.setCategoria(producto.getCategoria());
				
				if(productoActual.getImagen() != null && productoActual.getImagen().length() > 0) {
					String imgAnterior = productoActual.getImagen();
					Path rutaImgAnterior = uploadService.getPath(imgAnterior);
					File archivoImgAnterior = rutaImgAnterior.toFile();
					if(archivoImgAnterior.exists() && archivoImgAnterior.canRead()) {
						archivoImgAnterior.delete();
					}
				}
				if(imagen != null) imageNewName = uploadService.copyFile(imagen);
				productoActual.setImagen(imageNewName);
				
				productoUpdated = productoService.save(productoActual);
			}catch(DataAccessException e) {
				response.put("message", "Error al actualizar el producto");
				logger.error("ERROR: ".concat(e.getMessage()));
				uploadService.deleteFile(imageNewName);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("message", "Producto actualizado con exito..");
			response.put("producto", productoUpdated);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/productos/change-state")
	public ResponseEntity<?> changeState(@RequestBody Producto producto, @RequestParam(name = "estado") String estado){
			
		Map<String, Object> response = new HashMap<>();
			try {
				producto.setEstado(estado);
				productoService.save(producto);
				
			}catch(DataAccessException e) {
				response.put("message", "Error al cambiar estado del producto");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("message", "El estado del producto ha sido cambiado a"+estado.charAt(1));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}