package application;
	
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.MainWindowController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	
	private Stage primaryStage;
	
	private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) throws ParserConfigurationException, SAXException, IOException {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Stroke statistics App");
		
		initRootLayout();
	}
	
//	Initializes the root layout
	public void initRootLayout() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../view/MainWindow.fxml"));
		try {
			rootLayout = (AnchorPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MainWindowController controller = loader.getController();
		controller.setMainApp(this);
		
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
