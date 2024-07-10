package com.chimoy.infraccionservice.entity;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "infraccion")
public class Infraccion {
	@Id
    private ObjectId id;

    @NotNull
    @Size(min = 8, max = 8)
    private String dni;

    @NotNull
    private LocalDateTime fecha;

    @NotNull
    @Size(max = 20)
    private String tipo_infraccion;

    @NotNull
    @Size(max = 200)
    private String ubicacion;

    @Size(max = 255)
    private String descripcion;

    @NotNull
    @Digits(integer = 6, fraction = 2)
    private BigDecimal monto_multa;

    @NotNull
    @Size(max = 20)
    private String estado;
    
    public Infraccion() {
        
    }
    public Infraccion(String id) {
        this.id = new ObjectId(id);
    }

}
