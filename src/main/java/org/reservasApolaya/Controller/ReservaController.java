package org.reservasApolaya.Controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.reservasApolaya.model.Reserva;
import org.reservasApolaya.repository.ICarreraRepository;
import org.reservasApolaya.repository.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ReservaController {
	@Autowired
	private ICarreraRepository recar;
	
	@Autowired
	private IReservaRepository reres;
	
	@GetMapping("/reserva/cargar")
	public String cargarform(Model model) {
		model.addAttribute("reserva", new Reserva());
		model.addAttribute("lstCarreras",recar.findAll());
		
		return "reservaApolaya";
	}
	
	@PostMapping("/reserva/grabar")
	public String grabarform(@ModelAttribute Reserva reserva, Model model) {
		model.addAttribute("lstCarreras",recar.findAll());
		
		try {
			reres.save(reserva);
			model.addAttribute("success","Proceso realizado con éxito");
		} catch (Exception e) {
			model.addAttribute("error","Error al registrar producto");
		}
		return "reservaApolaya";
	}
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private ResourceLoader resourceLoader;
	@GetMapping("/reporteLista")
	public void listadoPDF(HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment; filename=\"ListadoReservas.pdf\";");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/pdf");
		try {
		String ru = resourceLoader.getResource("classpath:ListarReservas.jasper").getURI().getPath();
		JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
		OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

	@GetMapping("/graficoPDF")
	public void reporteGrafico(HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment; filename=\"GraficoReservas.pdf\";");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/pdf");
		try {
		String ru = resourceLoader.getResource("classpath:GraficoReservasMatriculas.jasper").getURI().getPath();
		JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
		OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}
