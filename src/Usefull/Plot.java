package Usefull;
import java.util.ArrayList;
import java.util.Vector;
import java.util.function.Function;
import java.util.List;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Plot extends Pane {
    
	private Expression e;
	private Axes axes;
	private List<FunctionG> functions;
	public Vector<Double> xCoordinate;
    public Vector<Double> yCoordinate;
	
	public Axes getAxes() {
		return axes;
	}

	public void setAxes(Axes axes) {
		this.axes = axes;
		this.getChildren().setAll(axes);
		for(FunctionG f:functions)
		{
			drawPath(f.getFunction(),0.01,f.getColor(),f.getStroke());
		}
	}

	public Plot(Axes axes){
		this.functions=new ArrayList<FunctionG>();
		this.axes=axes;
		getChildren().setAll(axes);
		xCoordinate= new Vector<Double>();
        yCoordinate= new Vector<Double>();
	}
	
	public void addFunction(String f,String color, int stroke)
	{
		functions.add(new FunctionG(f,color,stroke));	
		drawPath(f,0.01,color,stroke);
	}
	
	public void clearFunctions(){
		functions.clear();
	}
	public void drawPath(String f, double xInc,String color, int stroke)
	{
		try{
		e = new ExpressionBuilder(f).variables("x").build();
		}catch(IllegalArgumentException e)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Exceptie!");
			alert.setHeaderText(null);
			alert.setContentText("Pentru a desena graficul unei functii, aceasta nu trebuie sa fie valida. Verifica daca functia este corecta.");

			alert.showAndWait();
			return;
		}
			
        Path path = new Path();
        //path.setStroke(Color.Double.parseDouble(color));
        Color c=Color.web(color);
        path.setStroke(c);
        //path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 0.6));
        path.setStrokeWidth(stroke);

        path.setClip(
                new Rectangle(
                        0, 0, 
                        axes.getPrefWidth(), 
                        axes.getPrefHeight()
                )
        );

        double x = axes.getxMin();
        e.setVariable("x",x);
        double y = e.evaluate();

        path.getElements().add(
                new MoveTo(
                        mapX(x, axes), mapY(y, axes)
                )
        );

        x += xInc;
        while (x < axes.getxMax()) {
        	e.setVariable("x",x);
            y = e.evaluate();
            

            path.getElements().add(
                    new LineTo(
                            mapX(x, axes), mapY(y, axes)
                    )
            );

            x += xInc;
        }

       // setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
       // setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
       // setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        getChildren().add(path);
	}
	
    private double mapX(double x, Axes axes) {
        double tx = axes.getPrefWidth() / 2;
        double sx = axes.getPrefWidth() / 
           (axes.getXAxis().getUpperBound() - 
            axes.getXAxis().getLowerBound());

        return x * sx + tx;
    }

    private double mapY(double y, Axes axes) {
        double ty = axes.getPrefHeight() / 2;
        double sy = axes.getPrefHeight() / 
            (axes.getYAxis().getUpperBound() - 
             axes.getYAxis().getLowerBound());

        return -y * sy + ty;
    }

}
