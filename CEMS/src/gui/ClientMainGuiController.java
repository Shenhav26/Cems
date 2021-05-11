package gui;

import java.io.IOException;

import client.ClientController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML controller class for main screen in javaFX graphic user interface, let
 * the user interact with the main application graphic user interface.
 * 
 * @author Arikz ,Dvir ben simon
 *
 */
public class ClientMainGuiController {

	/**
	 * Value for storing the main layout of the application, in order to change the
	 * sub layout inside it.
	 */
	private static BorderPane mainPane;

	public static ClientController clientController;

	@FXML
	private Button btnSearch;

	@FXML
	private TextField tfId;

	@FXML
	private Label labelStatus;

	/**
	 * Getting the application current stage and set new main screen scene.
	 * 
	 * @param stage of javaFX application
	 */
	public void start(Stage stage) {
		try {
			setClientController(new ClientController("localhost", 5555));
			System.out.println("Connected to server");
		} catch (IOException e1) {
			System.out.println("Cant connect to server");
			e1.printStackTrace();
		}

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				System.exit(0);
			}
		});
		try {
			mainPane = (BorderPane) FXMLLoader.load(getClass().getResource("ClientMainGui.fxml"));
			Scene scene = new Scene(mainPane, 850, 500);
			stage.setScene(scene);
			stage.setTitle("CEMS");
			stage.getIcons().add(new Image("/gui/icon.png"));
			stage.show();
			LoginMenuController loginMenuController = new LoginMenuController();
			loginMenuController.start(mainPane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ClientController getClientController() {
		return clientController;
	}

	public static void setClientController(ClientController clientController) {
		ClientMainGuiController.clientController = clientController;
	}

}
