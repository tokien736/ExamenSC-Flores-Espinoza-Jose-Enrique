/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.modelo;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author PC-Jose
 */
public class jpaPersona {
    private EntityManagerFactory emf;
    private ObservableList<Persona> datoEstudiante = FXCollections.observableArrayList();    
      
    public jpaPersona() {
        this.emf = Persistence.createEntityManagerFactory("BaseDatos");
    }  
    public ObservableList<Persona> getDatoEstudiantes() {
        return datoEstudiante;
    }   
    
    public ObservableList<Persona> cargarPersonas() {
        ObservableList<Persona> datoEstudiante = FXCollections.observableArrayList();
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Persona> query = em.createQuery("SELECT p FROM Persona p", Persona.class);
            List<Persona> estudiantes = query.getResultList();

            // Agrega los estudiantes a la lista observable
            datoEstudiante.addAll(estudiantes);

            // Muestra solo el ID de las personas en la consola
            for (Persona persona : estudiantes) {
                System.out.println("ID: " + persona.getIdPersona());
            }

            return datoEstudiante;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return datoEstudiante;
    }

    
    public void insertarPersonas(Persona persona) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            
            if (persona.getIdPersona() == null) {
                
                em.persist(persona);
            } else {
                
                Persona personaExistente = em.find(Persona.class, persona.getIdPersona());

                
                personaExistente.setNombre(persona.getNombre());
                personaExistente.setApellido(persona.getApellido());
                personaExistente.setCalle(persona.getCalle());
                personaExistente.setCodigoPostal(persona.getCodigoPostal());
                personaExistente.setCuidad(persona.getCuidad());
                personaExistente.setCumpleaños(persona.getCumpleaños());
            }

            
            tx.commit();
        } catch (Exception e) {
            
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            
            em.close();
        }
    }


    public void eliminarPersona(Persona persona) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.remove(em.merge(persona));
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }    
}
