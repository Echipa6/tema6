package Controller;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;

import com.sun.javafx.geom.Vec2d;

import Usefull.Axes;
import Usefull.Plot;
import View.MainWindow;
import View.TopMenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class OurScene {
	private Scene scene;
	double xCoordinate[];
	double yCoordinate[];
	Integer coordinateCount;
	

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}


	public OurScene()
	{
		
		TopMenu topMenu= new TopMenu();
		xCoordinate= new double[4];
		yCoordinate= new double[4];
		coordinateCount=0;


		Axes axes = new Axes(600, 500, 1, 1);

		Plot plot=new Plot(axes);
		
		
		MainWindow mainWindow= new MainWindow(plot,topMenu.getHbox());
		MenuController menuController= new MenuController(mainWindow,plot);
		topMenu.setMyMenu(menuController);

		scene=new Scene(mainWindow.getRoot(), Color.rgb(35, 39, 50));

		scene.widthProperty().addListener(new ChangeListener<Number>() {
			@Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {

				plot.setAxes(new Axes(newSceneWidth.intValue()-60,(int)mainWindow.getRoot().getHeight()-60,1,1));
				System.out.println("Width: " + newSceneWidth);
			}
		});
		scene.heightProperty().addListener(new ChangeListener<Number>() {
			@Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {

				plot.setAxes(new Axes((int)mainWindow.getRoot().getWidth()-60,newSceneHeight.intValue()-60,1,1));
				System.out.println("Height: " + newSceneHeight);
			}
		});
        

      
		 mainWindow.getLayout().addEventFilter(MouseEvent.MOUSE_CLICKED, 
	              new EventHandler<MouseEvent>() {
	          public void handle(MouseEvent e ) {
	          	  
	          	xCoordinate[coordinateCount]=(e.getX()-axes.getPrefWidth()/2)*(axes.getXAxis().getUpperBound() - 
	                    axes.getXAxis().getLowerBound())/axes.getPrefWidth() ;
	          	
	                
	          	yCoordinate[coordinateCount]=(axes.getPrefHeight() / 2- e.getY())*(axes.getYAxis().getUpperBound() - 
	                    axes.getYAxis().getLowerBound())/axes.getPrefHeight();
	          	coordinateCount++;
	          	if(coordinateCount>=4)
	          	{
	          		coordinateCount=0;
	          		PolynomialFunctionLagrangeForm p;
	          		p = new PolynomialFunctionLagrangeForm(xCoordinate, yCoordinate);
	          		double coeff[]=null;
	          		try{
	          			coeff=p.getCoefficients();
	          		}catch(Exception exception)
	          		{
	          			Alert alert = new Alert(AlertType.INFORMATION);
	        			alert.setTitle("Exceptie!");
	        			alert.setHeaderText("Exceptie!");
	        			alert.setContentText("Algoritmul folosit nu a putut gasi o functie. Incearca alte puncte");

	        			alert.showAndWait();
	        			return;
	          		}
	                String f=Double.toString(coeff[0]);
	                
	                for(int i=1;i<coeff.length;i++)
	                {
	                	if(coeff[i]>0)
	                	{
	                		f+="+";
	                	}
	                	f+=coeff[i]+"*x^"+i;
	                }
	                menuController.drawButtonPressed(f,topMenu.getColorPicker1().getValue().toString(),topMenu.getStroke().getSelectionModel().getSelectedItem().intValue());
	          	}
          };
      });


	}


}
