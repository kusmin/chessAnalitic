package br.com.chess;

import br.com.chess.exceptions.ServiceError;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class UtilData {

	private UtilData() {
		throw new ServiceError("Classe de utilitário");
	}

	public static SimpleDateFormat formatoData() {
		return new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public static SimpleDateFormat formatoDataCompleta() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	public static SimpleDateFormat formatoDataCompletaParticipante() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public static SimpleDateFormat formatoDataParticipante() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}
	
	public static DateTimeFormatter formatoDataParticipanteDateTime() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd");
	}

}
