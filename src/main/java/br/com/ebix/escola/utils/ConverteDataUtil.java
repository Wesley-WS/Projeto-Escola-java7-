package br.com.ebix.escola.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConverteDataUtil {
	public static Calendar converterDateParaCalendar(java.sql.Date date) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(date);
		return calendario;
	}
	
	public static Calendar converterStringParaCalendar(String dataStr) {
		try {
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dataStr);
			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);
			return dataNascimento;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static java.sql.Date converterCalendarParaDatesql(Calendar calendar) {
		return new java.sql.Date(calendar.getTimeInMillis());
	}
	
	public static java.util.Date converterCalendarParaDate(Calendar calendar) {
		return new java.util.Date(calendar.getTimeInMillis());
	}
}
