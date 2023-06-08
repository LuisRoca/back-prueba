package org.spring.prueba.services;

import static org.spring.prueba.Util.MessageUtil.NOTFOUNDCONPARAMETROS;
import static org.spring.prueba.Util.MessageUtil.OK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.prueba.entity.Pelicula;
import org.spring.prueba.entity.PeliculaSalaCine;
import org.spring.prueba.entity.SalaCine;
import org.spring.prueba.exepciones.MyException;
import org.spring.prueba.repository.PeliculaRepository;
import org.spring.prueba.repository.PeliculaSalaCineRepository;
import org.spring.prueba.repository.SalaCineRepository;
import org.spring.prueba.response.OutputEntity;
import org.spring.prueba.response.PeliculaSalaCineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PeliculaSalaCineService {

	@Autowired
	private PeliculaSalaCineRepository peliculaSalaCineRepository;
	
	@Autowired
	private PeliculaRepository peliculaRepository;
	
	@Autowired
	private SalaCineRepository salaCineRepository;
	
	public OutputEntity<PeliculaSalaCineResponse> consultarPeliculaNombreIdSala(String nombrePelicula, Integer idSala) {
		OutputEntity<PeliculaSalaCineResponse> out = new OutputEntity<PeliculaSalaCineResponse>();
		try {
			PeliculaSalaCine pelicula = this.peliculaSalaCineRepository.buscarPorNombrePeliculaIdSala(nombrePelicula, idSala);
			System.out.println(new ObjectMapper().writeValueAsString(pelicula));
			if(pelicula == null) 
				throw new MyException(NOTFOUNDCONPARAMETROS.getKey(), NOTFOUNDCONPARAMETROS.getCode());
			
			Pelicula encontrarPelicula = this.peliculaRepository.buscarPorId(pelicula.getPelicula().getId());
			SalaCine encontrarSala = this.salaCineRepository.buscarPorId(pelicula.getSalaCine().getId());
			
			PeliculaSalaCineResponse peliculaSala = new PeliculaSalaCineResponse();
			peliculaSala.setNombrePelicula(encontrarPelicula.getNombre());
			peliculaSala.setDuracion(encontrarPelicula.getDuracion());
			peliculaSala.setNombreSala(encontrarSala.getNombre());
			peliculaSala.setFechaPublicacion(pelicula.getFechaPublicacion());
			peliculaSala.setFechaFin(pelicula.getFechaFin());
			return out.done(OK.getCode(), OK.getKey(), peliculaSala);
		} catch (MyException e) {
			return out.failed(e.getCode(), e.getMessages(), null);
		} catch (Exception e) {
			return out.error();
		}
	}
	
	public OutputEntity<List<Map<String, Object>>> consultarPeliculasCantidad() {
		OutputEntity<List<Map<String, Object>>> out = new OutputEntity<List<Map<String, Object>>>();
		try {
			Object[][] pelicula = this.peliculaSalaCineRepository.buscarPeliculasCantidad();
			System.out.println(new ObjectMapper().writeValueAsString(pelicula));
			if(pelicula == null) 
				throw new MyException(NOTFOUNDCONPARAMETROS.getKey(), NOTFOUNDCONPARAMETROS.getCode());
			List<Map<String, Object>> dataResponse = new ArrayList<>();
			
			// Conversion a objeto
	        Arrays.stream(pelicula).forEach(data -> {
	        	Date fecha = (Date) data[2];
	        	Map<String, Object> dataObjeto = new HashMap<>();
	        	dataObjeto.put("nombrePelicula", String.valueOf(data[0]));
	        	dataObjeto.put("cantidad", String.valueOf(data[1]));
	        	dataObjeto.put("fecha", fecha);
	        	dataResponse.add(dataObjeto);
	        });
			
			return out.done(OK.getCode(), OK.getKey(), dataResponse);
		} catch (MyException e) {
			return out.failed(e.getCode(), e.getMessages(), null);
		} catch (Exception e) {
			return out.error();
		}
	}
}
