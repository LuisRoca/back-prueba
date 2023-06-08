package org.spring.prueba.services;

import static org.spring.prueba.Util.MessageUtil.CREATED;
import static org.spring.prueba.Util.MessageUtil.UPDATED;
import static org.spring.prueba.Util.MessageUtil.OK;
import static org.spring.prueba.Util.MessageUtil.NOTFOUND;
import static org.spring.prueba.Util.MessageUtil.BADREQUEST;
import static org.spring.prueba.Util.MessageUtil.NOMBREEXIST;
import static org.spring.prueba.Util.MessageUtil.IDNOEXIST;
import static org.spring.prueba.Util.MessageUtil.DELETED;

import org.spring.prueba.entity.Pelicula;

import java.util.ArrayList;
import java.util.List;

import org.spring.prueba.exepciones.MyException;
import org.spring.prueba.repository.PeliculaRepository;
import org.spring.prueba.request.PeliculaRequest;
import org.spring.prueba.response.OutputEntity;
import org.spring.prueba.response.PeliculaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {
	
	@Autowired
	private PeliculaRepository peliculaRepository;

	public OutputEntity<String> insertarPelicula(PeliculaRequest pelicula){
		OutputEntity<String> out = new OutputEntity<>();
		try {
			Integer x = this.peliculaRepository.existeNombre(pelicula.getNombre());
			if(x == 1)
				throw new MyException(NOMBREEXIST.getKey(), NOMBREEXIST.getCode());
			Pelicula e = new Pelicula(pelicula);
			
			this.peliculaRepository.save(e);
			return out.done(CREATED.getCode(), CREATED.getKey(), null);
		} catch (DataIntegrityViolationException e) {
			return out.failed(BADREQUEST.getCode(), e.getMessage(), null);
		} catch (MyException e) {
			return out.failed(e.getCode(), e.getMessages(), null);
		} catch (Exception e) {
			return out.error();
		}
	}
	
	public OutputEntity<String> actualizarPelicula(Pelicula pelicula){
		OutputEntity<String> out = new OutputEntity<>();
		try {
			Pelicula editar = this.peliculaRepository.buscarPorId(pelicula.getId());
			if(editar == null)
				throw new MyException(IDNOEXIST.getKey(), IDNOEXIST.getCode());
			
			Pelicula e = new Pelicula(pelicula);
			
			this.peliculaRepository.save(e);
			return out.done(UPDATED.getCode(), UPDATED.getKey(), null);
		} catch (DataIntegrityViolationException e) {
			return out.failed(BADREQUEST.getCode(), e.getMessage(), null);
		} catch (MyException e) {
			return out.failed(e.getCode(), e.getMessages(), null);
		} catch (Exception e) {
			return out.error();
		}
	}

	public OutputEntity<List<PeliculaResponse>> getPelicula() {
		OutputEntity<List<PeliculaResponse>> out = new OutputEntity<List<PeliculaResponse>>();
		try {
			List<Pelicula> list = this.peliculaRepository.findAll();
			
			if(list.isEmpty()) 
				throw new MyException(NOTFOUND.getKey(), NOTFOUND.getCode());
			
			List<PeliculaResponse> output = new ArrayList<PeliculaResponse>();
			
			for(Pelicula pelicula : list) {
				PeliculaResponse peliculaResp = new PeliculaResponse(pelicula);
				output.add(peliculaResp);
			}
			
			return out.done(OK.getCode(), OK.getKey(), output);
		} catch (MyException e) {
			return out.failed(e.getCode(), e.getMessages(), null);
		} catch (Exception e) {
			return out.error();
		}
	}
	
	public OutputEntity<String> eliminarPelicula(Integer id){
		OutputEntity<String> out = new OutputEntity<>();
		try {
			Pelicula eliminar = this.peliculaRepository.buscarPorId(id);
			if(eliminar == null)
				throw new MyException(IDNOEXIST.getKey(), IDNOEXIST.getCode());
			
			eliminar.setEstado("I");
			
			this.peliculaRepository.save(eliminar);
			return out.done(DELETED.getCode(), DELETED.getKey(), null);
		} catch (DataIntegrityViolationException e) {
			return out.failed(BADREQUEST.getCode(), e.getMessage(), null);
		} catch (MyException e) {
			return out.failed(e.getCode(), e.getMessages(), null);
		} catch (Exception e) {
			return out.error();
		}
	}
}
