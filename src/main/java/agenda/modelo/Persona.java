package agenda.modelo;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author PC-Jose
 */
public class Persona {
    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty calle;
    private final IntegerProperty codigoPostal;
    private final StringProperty cuidad;
    private final ObjectProperty<LocalDate> cumpleaños;
    
    
    
    /**
     * Constructor predeterminado
     */
    
    public Persona(){
        this(null,null);
    }
    
    public  Persona(String nombre, String apellido){
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        
        //Algunos datos tontos para pruebas convenientes 
        this.calle = new SimpleStringProperty("Alguna calle");
        this.codigoPostal = new SimpleIntegerProperty(1234);
        this.cuidad = new SimpleStringProperty("Alguna cuidad");
        this.cumpleaños = new SimpleObjectProperty<>(LocalDate.of(1999, 2, 21));        
    }


    public String getNombre(){
        return nombre.get();
    }
    
    public void setNombre(String nombre){
        this.nombre.set(nombre);
    }
    
    public StringProperty nombreProperty(){
        return nombre;
    }
    
    public String getApellido(){
        return apellido.get();
    }    
    
    public void setApellido(String apellido){
        this.apellido.set(apellido);
    }
    
    public StringProperty apellidoProperty(){
        return apellido;
    }
    
    public String getCalle(){
        return calle.get();
    }
    
    public void setCalle(String calle){
        this.calle.set(calle);
    }
    
    public StringProperty calleProperty(){
        return calle;
    }
    
    public int getCodigoPostal(){
        return codigoPostal.get();
    }
    
    public void setCodigoPostal(int codigoPostal){
        this.codigoPostal.set(codigoPostal);
    }
    
    public IntegerProperty codigoPostalProperty(){
        return codigoPostal;
    }
    
    public String getCuidad(){
        return cuidad.get();
    }
    
    public  void setCuidad(String cuidad){
        this.cuidad.set(cuidad);
    }
    
    public StringProperty cuidadProperty(){
        return cuidad;
    }
    
    public LocalDate getCumpleaños(){
        return cumpleaños.get();
    }
    
    public void setCumpleaños(LocalDate cumpleaños){
        this.cumpleaños.set(cumpleaños);
    }
    
    public ObjectProperty<LocalDate> cumpleañosProperty(){
        return cumpleaños;
    }
}
