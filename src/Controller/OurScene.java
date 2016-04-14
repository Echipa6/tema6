package Controller;
import Usefull.Axes;
import Usefull.Plot;
import View.MainWindow;
import View.TopMenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class OurScene {
	private Scene scene;
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	

	public OurScene()
	{
		TopMenu topMenu= new TopMenu();
		
		
		Axes axes = new Axes(600, 500, 1, 1);

		Plot plot=new Plot(axes);
		//plot.drawPath("x", -8, 8, 0.1);
        
        MainWindow mainWindow= new MainWindow(plot,topMenu.getHbox());
        MenuController menuController= new MenuController(mainWindow,plot);
        topMenu.setMyMenu(menuController);
        
        scene=new Scene(mainWindow.getRoot(), Color.rgb(35, 39, 50));
        /************************/
        
        scene.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	
		    	plot.setAxes(new Axes(newSceneWidth.intValue()-60,(int)mainWindow.getRoot().getHeight()-60,1,1));
		    	//plot.getChildren().setAll(new Axes(newSceneWidth.intValue()-60,(int)mainWindow.getRoot().getHeight()-60,1,1));
		    	System.out.println("Width: " + newSceneWidth);
		    }
		});
		scene.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		    
		    	plot.setAxes(new Axes((int)mainWindow.getRoot().getWidth()-60,newSceneHeight.intValue()-60,1,1));
		    	//plot.getChildren().setAll(new Axes((int)mainWindow.getRoot().getWidth()-60,newSceneHeight.intValue()-60,1,1));
		    	System.out.println("Height: " + newSceneHeight);
		    }
		});
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, 
                new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e ) {
            	System.out.println(e.getX());
            	System.out.println(e.getY());
            };
        });

           
	}
	
	
}
