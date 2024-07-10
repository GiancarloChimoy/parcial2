package com.chimoy.infraccionservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;

import com.chimoy.infraccionservice.entity.*;

public interface InfraccionService {
	List<Infraccion> findAll(Pageable page);

    List<Infraccion> findByFecha(LocalDateTime fecha, Pageable page);

    Infraccion findById(ObjectId id);

    Infraccion save(Infraccion infraccion);
    List<Infraccion> findByDni(String dni);
    Infraccion update(Infraccion infraccion);
    void delete(ObjectId id);

}
