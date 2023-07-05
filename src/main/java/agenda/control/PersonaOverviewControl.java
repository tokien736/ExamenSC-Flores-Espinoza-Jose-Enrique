package agenda.control;

import agenda.modelo.Persona;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private Label nombreLabel;
    @FXML
    private Label apellidoLabel;
    @FXML
    private Label calleLabel;
    @FXML
    private Label codigoPostalLable;
    @FXML
    private Label cuidadLabel;
    @FXML
    private Label cumpleañosLabel;
    
    //Referenciar el dato de aplicacion
    private App app;    
    /**
     * El contructor
     * El constructor se llama antes que el método initialize().
     */
    public PersonaOverviewControl(){                
    }
    /**
     * Inicializa la clase de controlador. Este método se llama automáticamente
     * después de que se haya cargado el archivo fxml.
     */    
    @FXML
    private void initialize(){
        // Inicializar la tabla de personas con las dos columnas.
        nombreColumna.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellidoColumna.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());                
        
        // Borrar detalles de la persona.
        
        showPersonaDetalles(null);
        
        // Escuche los cambios de selección y muestre los detalles de la persona cuando cambie.

        personaTabla.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonaDetalles(newValue));
    }
    /**
     * Es llamado por la aplicación principal para devolver una referencia a sí mismo.
     *
     * @param app
     */   
    public void setApp(App app){
        this.app = app;
        // Agregar datos de la lista observable a la tabla
        personaTabla.setItems(app.getPersonaDatos());
    }
    
    /**
    * Rellena todos los campos de texto para mostrar detalles sobre la persona.
    * Si la persona especificada es nula, se borran todos los campos de texto.
    *
    * @param persona la persona o nulo
    */
    private void showPersonaDetalles(Persona persona){
        if(persona != null){
            // Rellena las etiquetas con información del objeto persona.
            nombreLabel.setText(persona.getNombre());
            apellidoLabel.setText(persona.getApellido());
            calleLabel.setText(persona.getCalle());
            codigoPostalLable.setText(Integer.toString(persona.getCodigoPostal()));
            cuidadLabel.setText(persona.getCuidad());
            // TODO: ¡Necesitamos una forma de convertir el cumpleaños en una cadena!
            cumpleañosLabel.setText(DateUtil.format(persona.getCumpleaños()));
        }else{
            nombreLabel.setText("");
            apellidoLabel.setText("");
            calleLabel.setText("");
            codigoPostalLable.setText("");
            cuidadLabel.setText("");
            cumpleañosLabel.setText("");
            
        }
    }
    /**
    * Se llama cuando el usuario hace clic en el botón Eliminar.
    */
    @FXML
    private void handleEliminarPersona() {
        int selectedIndex = personaTabla.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personaTabla.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(app.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    /**
    * Se llama cuando el usuario hace clic en el botón nuevo. Abre un cuadro de diálogo para editar.
    * detalles para una nueva persona.
    */
    @FXML
    private void handleNuevaPersona() {
        Persona tempPerson = new Persona();
        boolean okClicked = app.showPersonEditDialog(tempPerson);
        if (okClicked) {
            app.getPersonaDatos().add(tempPerson);
        }
    }
    /**
      * Se llama cuando el usuario hace clic en el botón de edición. Abre un cuadro de diálogo para editar.
      * datos de la persona seleccionada.
      */   
    @FXML
    private void handleEditarPersona() {
        Persona selectedPerson = personaTabla.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = app.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonaDetalles(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(app.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
