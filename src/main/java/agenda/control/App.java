package agenda.control;

import agenda.modelo.Persona;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;




/**
 * JavaFX App
 */
public class App extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<Persona> personaDatos = FXCollections.observableArrayList();
    
    public App(){
        //Agregar algunos simples datos        
        personaDatos.add(new Persona("Hans", "Muster"));
        personaDatos.add(new Persona("Ruth", "Mueller"));
        personaDatos.add(new Persona("Heinz", "Kurz"));
        personaDatos.add(new Persona("Cornelia", "Meier"));
        personaDatos.add(new Persona("Werner", "Meyer"));
        personaDatos.add(new Persona("Lydia", "Kunz"));
        personaDatos.add(new Persona("Anna", "Best"));
        personaDatos.add(new Persona("Stefan", "Meier"));
        personaDatos.add(new Persona("Martin", "Mueller"));        
    }
    
    /**
     * Devuelve los datos como una lista observable de Personas.
     * @return
     */
    public ObservableList<Persona> getPersonaDatos(){
        return personaDatos;
    }    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AgendaApp");
        
        initRootLayout();
        showPersonaOverview();
 
    }
    
    /**
    * Abre un cuadro de diálogo para editar los detalles de la persona especificada. Si el usuario
    * hace clic en Aceptar, los cambios se guardan en el objeto de persona proporcionado y son verdaderos
    * es regresado.
    * 
    * @param persona the person object to be edited
    * @return verdadero si el usuario hizo clic en Aceptar, falso en caso contrario.
    * 
    */
    public boolean showPersonEditDialog(Persona persona) {
        try {
            // Cargue el archivo fxml y cree una nueva etapa para el cuadro de diálogo emergente.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/agenda/vista/EditarPersonaDialog.fxml"));
            AnchorPane pagina = (AnchorPane) loader.load();

            // Cree el escenario de diálogo.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Persona");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(pagina);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EditarPersonaDialogControl controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPersona(persona);

            // Mostrar el cuadro de diálogo y esperar hasta que el usuario lo cierre.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }    
    
    /**
     * Inicializar el root layout
     */
    private void initRootLayout() {
        try {
            // Cargar el root layout el archivo file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/agenda/vista/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Visualizar la escena que contiene el root layout 
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Ver persona overview dentro de root layout
     */
    private void showPersonaOverview() {
        try {
            // cargar persona overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/agenda/vista/PersonaOverview.fxml"));
            AnchorPane personaOverview = (AnchorPane) loader.load();
            
            //Colocar persona overview dentro en el centro de root layout
            rootLayout.setCenter(personaOverview);
            // Dar acceso al controlador a la app.
            PersonaOverviewControl controlador = loader.getController();
            controlador.setApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    /**
     * Returns the main stage
     * @return
     */
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
    

}