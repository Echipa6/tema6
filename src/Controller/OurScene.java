package Controller;
import java.util.Vector;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;

import com.sun.javafx.geom.Vec2d;

import Usefull.Axes;
import Usefull.Plot;
import View.MainWindow;
import View.TopMenu;
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
		
		
		Axes axes = new Axes(
                600, 500,
                -8, 8, 1,
                -6, 6, 1
        );

		Plot plot=new Plot(axes);
		//plot.drawPath("x", -8, 8, 0.1);
        
        MainWindow mainWindow= new MainWindow(plot,topMenu.getHbox());
        MenuController menuController= new MenuController(mainWindow,plot);
        topMenu.setMyMenu(menuController);
        
        scene=new Scene(mainWindow.getRoot(), Color.rgb(35, 39, 50));
        

      
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
                double coeff[]=p.getCoefficients();
                
                String f=Double.toString(coeff[0]);
                
                for(int i=1;i<coeff.length;i++)
                {
                	if(coeff[i]>0)
                	{
                		f+="+";
                	}
                	f+=coeff[i]+"*x^"+i;
                }
                menuController.drawButtonPressed(f,"0x003333" , 2);
          	}
          };
      });


           
	}
	
	
}
