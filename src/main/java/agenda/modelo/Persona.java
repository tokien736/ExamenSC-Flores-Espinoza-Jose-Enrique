package agenda.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author PC-Jose
 */

@Entity
@Table(catalog = "agendaFX", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Persona.seleccionaTodos", query = "SELECT P FROM Persona p"),
    @NamedQuery(name = "Persona.seleccionaPorId", query = "SELECT P FROM Persona P WHERE P.idPersona = :idPersona")})
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPersona", nullable = false)
    private  Integer idPersona;
    
    @Column(name = "nombre", length = 50)
    private  String nombre;
    
    @Column(name = "apellido", length = 50)    
    private  String apellido;
    
    @Column(name = "calle", length = 150)
    private  String calle;
    
    @Column(name = "codigoPostal")    
    private  Integer codigoPostal;
    
    @Column(name = "cuidad", length = 100)    
    private  String cuidad;
    
    @Column(name = "cumpleaños")    
    private  LocalDate cumpleaños;
    
    //Constructor vacio

    public Persona() {
        this(null, null,null, null,null,null);
    }
    
    //Constructor con datos inicializados
    public Persona(String nombre, String apellido, String calle, Integer codigoPostal, String cuidad, LocalDate cumpleaños) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.cuidad = cuidad;
        this.cumpleaños = cumpleaños;
    }
    
    
    
    //Getter and Setter

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCuidad() {
        return cuidad;
    }

    public void setCuidad(String cuidad) {
        this.cuidad = cuidad;
    }

    public LocalDate getCumpleaños() {
        return cumpleaños;
    }

    public void setCumpleaños(LocalDate cumpleaños) {
        this.cumpleaños = cumpleaños;
    }
    // Propiedades de JavaFX 
    public IntegerProperty idProperty() {
        if (idPersona != null) {
            return new SimpleIntegerProperty(idPersona.intValue());
        } else {
            return new SimpleIntegerProperty(0); // Valor por defecto en caso de que idPersona sea nulo
        }
    }
    
    public StringProperty nombreProperty(){
        return  new SimpleStringProperty(nombre);
    }
    
    public StringProperty apellidoProperty(){
        return  new SimpleStringProperty(apellido);
    }

    public StringProperty calleProperty(){
        return  new SimpleStringProperty(calle);
    }   
    
    public IntegerProperty codigoPostalProperty(){
        return new SimpleIntegerProperty(codigoPostal);
    }
    
    public StringProperty cuidadProperty(){
        return  new SimpleStringProperty(cuidad);
    }    
    
    public ObjectProperty<LocalDate> estudianteProperty() {
        return new SimpleObjectProperty<>(cumpleaños);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "agenda.modelo.Persona[ idPersona=" + idPersona + " ]";
    }    

}
