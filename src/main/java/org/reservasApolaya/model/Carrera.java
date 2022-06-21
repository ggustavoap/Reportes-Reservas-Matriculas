package org.reservasApolaya.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_carreras")
@Data
public class Carrera {
	@Id
	private int idcarrera;
	private String descripcion;
}
