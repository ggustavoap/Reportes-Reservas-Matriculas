package org.reservasApolaya.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_reserva")
@Data
public class Reserva {
	@Id
	private int cod_reserva;
	
	private String nombre;
	private String apellido;
	
	@ManyToOne
	@JoinColumn(name="idcarrera", insertable = false, updatable = false)
	private Carrera carrera;
	private int idcarrera;
	
	private String fchregistro;
}
