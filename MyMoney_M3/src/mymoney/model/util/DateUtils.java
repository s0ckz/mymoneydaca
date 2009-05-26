/**
 * 
 */
package mymoney.model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mymoney.model.exceptions.InvalidDateException;

/**
 * Classe que contem metodos para manusear datas.
 * 
 * @see Date
 */
public class DateUtils {

	public static final String FORMATO_DATA_PADRAO = "dd/MM/yyyy HH:mm:ss";

	public static final DateFormat DATE_FORMATTER = new SimpleDateFormat(
			FORMATO_DATA_PADRAO);

	/**
	 * Cria uma nova data de acordo com o formato de data padrao adotado, que eh
	 * <code>"dd/MM/yyyy HH:mm:ss"</code>.
	 * 
	 * @see SimpleDateFormat
	 * @param date
	 *            Data a ser criada.
	 * @return Uma nova data
	 * @throws InvalidDateException
	 *             Caso a data nao siga o formato padrao adotado
	 */
	public static Date createDate(String date) throws InvalidDateException {
		try {
			return DATE_FORMATTER.parse(date);
		} catch (ParseException e) {
			throw new InvalidDateException();
		}
	}

	/**
	 * Metodo para formatar uma data no formato padrao.
	 * 
	 * @see DateUtils#createDate(String)
	 * @param date
	 *            Data a ser formatada.
	 * @return Uma string no formato <code>"dd/MM/yyyy HH:mm:ss"</code>.
	 */
	public static String toString(Date date) {
		return DATE_FORMATTER.format(date);
	}

}
