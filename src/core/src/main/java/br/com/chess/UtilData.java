package br.com.chess;

import br.com.chess.exceptions.ServiceError;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

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

	public static LocalDateTime getDateTimeFromTimestamp(long timestamp) {
		if (timestamp == 0)
			return null;
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone
				.getDefault().toZoneId());
	}

	public static LocalDate getLocalDateFromTimestamp(long timestamp) {
		LocalDateTime date = getDateTimeFromTimestamp(timestamp);
		return date == null ? null : date.toLocalDate();
	}

	public static Date getDateFromTimestamp(long timestamp){
		LocalDate localDate = getLocalDateFromTimestamp(timestamp);
		if (localDate != null) {
			return Date.from(localDate.atStartOfDay()
					.atZone(ZoneId.systemDefault())
					.toInstant());
		}
		throw new ServiceError(UtilConstantes.DATA_INVALIDA);
	}
}
