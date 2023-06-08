package org.spring.prueba.Util;

import lombok.Getter;

@Getter
public enum MessageUtil {

	OK("Proceso con éxito", 200),
	CREATED("Creado correctamente" , 201),
	UPDATED("Actualizado correctamente" , 200),
	DELETED("Eliminado correctamente" , 200),
	BADREQUEST("Hubo un error en su petición" , 400),
	NOTFOUND("No hay datos en esta tabla" , 404),
	NOTFOUNDSALA("No se encontro la sala ingresada" , 404),
	NOTFOUNDCONPARAMETROS("No hay datos con los parametros ingresados" , 404),
	INTERNALERROR("Error en el servidor", 500),
	CONFLICT("Hubo un conflicto en su petición" , 409),
	INGRESENOMBRE("Por favor ingrese su nombre", 400),
	NOMBREEXIST("Ya existe un registro con este nombre.", 409),
	IDNOEXIST("Identificador no existe.", 409),
	JSONSCHEMA("No se pudo leer el formato JsonSchema.", 409),
	CHECKREQUEST("Esta mandando un dato erróneo, revisa y vuelva a intentar.", 400),
	;
	
	private String key;
	private int code;
	
	private MessageUtil(String key, int code) {
		this.key = key;
		this.code = code;
	}
}
