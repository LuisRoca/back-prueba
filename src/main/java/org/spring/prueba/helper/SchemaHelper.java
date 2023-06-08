package org.spring.prueba.helper;

import static  org.spring.prueba.Util.MessageUtil.JSONSCHEMA;
import static  org.spring.prueba.Util.MessageUtil.CHECKREQUEST;
import static  org.spring.prueba.Util.MessageUtil.BADREQUEST;
import static  org.spring.prueba.Util.MessageUtil.INTERNALERROR;

import java.util.ArrayList;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.spring.prueba.exepciones.MyException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SchemaHelper {

	private Schema readJsonSchema(String path) throws MyException{
		try {
			JSONObject object = new JSONObject(new JSONTokener(SchemaHelper.class.getResourceAsStream(path)));
			return SchemaLoader.load(object);
		} catch (Exception e) {
			throw new MyException(JSONSCHEMA.getKey(), JSONSCHEMA.getCode());
		}
	}
	
	public <T> void validateJsonSchema(String ruta, T entidad) throws MyException{
		ArrayList<String> errorMessage = new ArrayList<>();
		try {
			Schema schema = this.readJsonSchema(ruta);
			String json  = new ObjectMapper().writeValueAsString(entidad);	
			schema.validate(new JSONObject(json));
			
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
			errorMessage.add(CHECKREQUEST.getKey());
			if (e.getCausingExceptions().size() > 0) {
				for (ValidationException ee : e.getCausingExceptions()) {
					errorMessage.add(ee.getMessage());
					System.out.println(ee.toString());
				}
			} else {
				System.out.println(e.toString());
				errorMessage.add(e.toString());
			}
			throw new MyException(errorMessage, CHECKREQUEST.getCode());
		} catch (MyException e) {
			System.out.println(e.getMessage());
			errorMessage.add(BADREQUEST.getKey());			
			throw new MyException(e.getMessage(), e.getCode());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new MyException(INTERNALERROR.getKey(), INTERNALERROR.getCode());
		}

	}
}
