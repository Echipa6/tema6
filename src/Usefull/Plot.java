package Usefull;
import java.util.function.Function;

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
	
	public Axes getAxes() {
		return axes;
	}

	public void setAxes(Axes axes) {
		this.axes = axes;
	}

	public Plot(Axes axes){
		this.axes=axes;
		getChildren().setAll(axes);
	}
	
	public void drawPath(String f,double xMin, double xMax, double xInc,String color, int stroke)
	{
		e = new ExpressionBuilder(f).variables("x").build();
		
		
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

        double x = xMin;
        e.setVariable("x",x);
        double y = e.evaluate();

        path.getElements().add(
                new MoveTo(
                        mapX(x, axes), mapY(y, axes)
                )
        );

        x += xInc;
        while (x < xMax) {
        	e.setVariable("x",x);
            y = e.evaluate();
            

            path.getElements().add(
                    new LineTo(
                            mapX(x, axes), mapY(y, axes)
                    )
            );

            x += xInc;
        }

        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

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
