package org.spring.prueba.services;

import static org.spring.prueba.Util.MessageUtil.NOTFOUNDSALA;
import static org.spring.prueba.Util.MessageUtil.OK;

import java.util.List;

import org.spring.prueba.entity.PeliculaSalaCine;
import org.spring.prueba.exepciones.MyException;
import org.spring.prueba.repository.PeliculaSalaCineRepository;
import org.spring.prueba.response.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaService {

	@Autowired
	private PeliculaSalaCineRepository peliculaSalaCineRepository;
	
	public OutputEntity<String> getSalas(String nombreSala) {
		OutputEntity<String> out = new OutputEntity<String>();
		try {
			List<PeliculaSalaCine> list = this.peliculaSalaCineRepository.buscarSalas(nombreSala);
			
			if(list.isEmpty()) 
				throw new MyException(NOTFOUNDSALA.getKey(), NOTFOUNDSALA.getCode());
			String mensaje = "";
			if(list.size() < 3) {
				mensaje ="Sala casi vacía";	
			}else if (list.size() > 3 && list.size() <= 5) {
				mensaje ="Sala casi llena";
			}else if (list.size() > 5) {
				mensaje = "Sala llena";
			}
			return out.done(OK.getCode(), OK.getKey(), mensaje);
		} catch (MyException e) {
			return out.failed(e.getCode(), e.getMessages(), null);
		} catch (Exception e) {
			return out.error();
		}
	}
}
