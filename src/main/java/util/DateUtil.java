package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author PC-Jose
 */
public class DateUtil {
    
    /** El patrón de fecha que se utiliza para la conversión. Cambia como quieras. */
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    /** El formateador de fecha. */
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern(DATE_PATTERN);
    /**
    * Devuelve la fecha dada como una cadena bien formateada. Lo anterior definido
    * {@link DateUtil#DATE_PATTERN} se usa
    * 
    * @param date la fecha que se devolverá como una cadena
    * @return cadena formateada
    */
    public  static String format(LocalDate date){
        if(date == null){
            return null;
        }
        return DATE_FORMATTER.format(date);
    }
    /**
    * Convierte una cadena en el formato del definido {@link DateUtil#DATE_PATTERN} 
    * a un {@link LocalDate} objeto.
    * 
    * Returns null si la cadena no se puede convertir
    * 
    * @param dateString la fecha como cadena
    * @return el objeto de fecha o nulo si no se pudo convertir
    */    
    public static LocalDate parse(String dateString){
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    /**
    * Comprueba la cadena si es una fecha válida.
    *
    * @param dateString de fecha
    * @return true si la cadena es una fecha válida
    */ 
    public static boolean validDate(String dateString) {
        // Intenta analizar la cadena.
    	return DateUtil.parse(dateString) != null;
    }
    
    
}
