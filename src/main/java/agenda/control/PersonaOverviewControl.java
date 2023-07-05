package agenda.control;

import agenda.modelo.Persona;
import agenda.modelo.jpaPersona;
import java.io.IOException;
import java.util.Optional;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.DateUtil;

/**
 *
 * @author PC-Jose
 */
public class PersonaOverviewControl {

    @FXML
    private TableView<Persona> personaTabla;
    @FXML
    private TableColumn<Persona, String> nombreColumna;
    @FXML
    private TableColumn<Persona, String> apellidoColumna;
    @FXML
    private Label apellidoLabel;
    @FXML
    private Label calleLabel;
    @FXML
    private Label cuidadLabel;
    @FXML
    private Label codigoPostalLable;
    @FXML
    private Label cumpleañosLabel;
    @FXML
    private Label nombreLabel;

    private jpaPersona controlador;
    private ScheduledService<Void> recargaDatosService;

    // Agrega la propiedad personaSeleccionada
    private Persona personaSeleccionada;

    private void cargarDatosPersona() {
        personaTabla.setItems(controlador.cargarPersonas());
    }

    @FXML
    private void initialize() {
        controlador = new jpaPersona();

        // Configurar las propiedades de las columnas de matricula
        nombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoColumna.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        
        // Obtener los datos de los estudiantes desde el controlador
        ObservableList<Persona> persona = controlador.cargarPersonas();
        personaTabla.setItems(persona);

        // Listener para manejar la selección de la tabla
        personaTabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                personaSeleccionada = newValue;
                mostrarDetallesPersona(personaSeleccionada);
            }
        });

        // Crear el ScheduledService para recargar los datos cada cierto tiempo
        recargaDatosService = new ScheduledService<Void>() {
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        cargarDatosPersona();
                        return null;
                    }
                };
            }
        };
        recargaDatosService.setPeriod(Duration.seconds(5)); // Configurar el intervalo de tiempo en segundos
        recargaDatosService.start();
    }


    @FXML
    private void handleNuevaPersona(ActionEvent event) {
       try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/agenda/vista/NuevaPersonaDialog.fxml"));
            Parent root = loader.load();

            // Crear una nueva escena
            Scene scene = new Scene(root);

            // Crear una nueva ventana
            Stage newStage = new Stage();
            newStage.setScene(scene);

            // Mostrar la nueva ventana
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }          
    }

   @FXML
   private void handleEditarPersona(ActionEvent event) {
       if (personaSeleccionada != null) {
           try {
               
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/agenda/vista/EditarPersonaDialog.fxml"));
               Parent root = loader.load();

               
               EditarPersonaDialogControl editarPersonaDialogController = loader.getController();

            
               editarPersonaDialogController.setPersona(personaSeleccionada);

              
               System.out.println("ID seleccionado: " + personaSeleccionada.getIdPersona());

              
               Scene scene = new Scene(root);
               Stage editStage = new Stage();
               editStage.setScene(scene);

               
               editStage.showAndWait();

               
               if (editarPersonaDialogController.isOkClicked()) {
                   cargarDatosPersona();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       } else {
          
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText(null);
           alert.setContentText("No se ha seleccionado ninguna persona.");
           alert.showAndWait();
       }
   }


 

    @FXML
    private void handleEliminarPersona(ActionEvent event) {
        
        Persona seleccionada = personaTabla.getSelectionModel().getSelectedItem();

        if (seleccionada != null) {
           
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro de que quieres eliminar a " + seleccionada.getNombre() + "?");

           
            Optional<ButtonType> result = alert.showAndWait();

           
            if (result.isPresent() && result.get() == ButtonType.OK) {
                
                controlador.eliminarPersona(seleccionada);

                // Actualizar la tabla de personas
                cargarDatosPersona();
            }
        } else {
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se ha seleccionado ninguna persona.");
            alert.showAndWait();
        }
    }

    
    private void mostrarDetallesPersona(Persona persona) {
        nombreLabel.setText(persona.getNombre());
        apellidoLabel.setText(persona.getApellido());
        calleLabel.setText(persona.getCalle());
        cuidadLabel.setText(persona.getCuidad());
        codigoPostalLable.setText(String.valueOf(persona.getCodigoPostal()));
        cumpleañosLabel.setText(DateUtil.format(persona.getCumpleaños()));

       
    }    
}
