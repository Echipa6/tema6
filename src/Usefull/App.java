package Usefull;

import Controller.OurScene;
import javafx.application.Application;
import javafx.stage.Stage;




public class App extends Application {

	public static void main(String[] args) {
		
		launch();

	}

	@Override
	public void start(final Stage stage) 
	{
        
        stage.setTitle("Graphs of Functions");
        
        OurScene ourScene=new OurScene();
        
        stage.setScene(ourScene.getScene());
        stage.show();
    }


}


