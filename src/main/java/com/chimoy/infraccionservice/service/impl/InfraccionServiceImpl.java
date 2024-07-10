package com.chimoy.infraccionservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chimoy.infraccionservice.entity.Infraccion;
import com.chimoy.infraccionservice.repository.InfraccionRepository;
import com.chimoy.infraccionservice.service.InfraccionService;

@Service
public class InfraccionServiceImpl implements InfraccionService {
	
	@Autowired
    private InfraccionRepository repository;
	

	@Override
	@Transactional(readOnly = true)
	public List<Infraccion> findAll(Pageable page) {
		try {
            return repository.findAll(page).toList();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	@Transactional(readOnly = true)
	public List<Infraccion> findByFecha(LocalDateTime fecha, Pageable page) {
		try {
            return repository.findByFechaContaining(fecha, page);
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	@Transactional(readOnly = true)
	public Infraccion findById(ObjectId id) {
		try {
            Infraccion registro = repository.findById(id).orElseThrow();
            return registro;
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public Infraccion save(Infraccion infraccion) {
		try {
            Infraccion registro = repository.save(infraccion);
            return registro;
        } catch (Exception e) {
            return null; // Manejar la excepción según tus necesidades
        }
	}
	 @Override
	    @Transactional(readOnly = true)
	    public List<Infraccion> findByDni(String dni) {
	        try {
	            return repository.findByDni(dni);
	        } catch (Exception e) {
	            return null;
	        }
	    }
	 
	 
	 @Override
	 public Infraccion update(Infraccion infraccion) {
	     try {
	         // Verificar si la infracción existe
	         Infraccion registro = repository.findById(infraccion.getId()).orElse(null);
	         if (registro == null) {
	             return null; // O manejar como sea necesario si la infracción no existe
	         }

	         // Realizar la actualización
	         registro.setEstado(infraccion.getEstado());
	         repository.save(registro);
	         return registro;
	     } catch (Exception e) {
	         return null; // O manejar la excepción según sea necesario
	     }
	 }


	@Override
	public void delete(ObjectId id) {
		try {
            Infraccion registro = repository.findById(id).orElseThrow();
            repository.delete(registro);
        } catch (Exception e) {
            // Manejar la excepción si es necesario
        }
		
	}

}
