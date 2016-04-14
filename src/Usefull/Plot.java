package Usefull;
import java.util.Vector;
import java.util.function.Function;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;

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
	public Vector<Double> xCoordinate;
    public Vector<Double> yCoordinate;
	
	public Axes getAxes() {
		return axes;
	}

	public void setAxes(Axes axes) {
		this.axes = axes;
	}

	public Plot(Axes axes){
		this.axes=axes;
		getChildren().setAll(axes);
		xCoordinate= new Vector<Double>();
        yCoordinate= new Vector<Double>();
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
//    public void drawPath2(double[] xrow, double yrow[],double xMin, double xMax, double xInc,String color, int stroke)
//	{
//		//e = new ExpressionBuilder(f).variables("x").build();
//    	PolynomialFunctionLagrangeForm p;
//        
//        p = new PolynomialFunctionLagrangeForm(xrow, yrow);
//        double coeff[]=p.getCoefficients();
//        
//        for(int i=0;i<coeff.length;i++)
//        {
//        	System.out.println(coeff[i]);
//        }
//        Path path = new Path();
//        //path.setStroke(Color.Double.parseDouble(color));
//        Color c=Color.web(color);
//        path.setStroke(c);
//        //path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 0.6));
//        path.setStrokeWidth(stroke);
//
//        path.setClip(
//                new Rectangle(
//                        0, 0, 
//                        axes.getPrefWidth(), 
//                        axes.getPrefHeight()
//                )
//        );
//
//        double x = xMin;
//        double y=0;
//		try {
//			y = p.value(x);
//		} catch (FunctionEvaluationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//        path.getElements().add(
//                new MoveTo(
//                        mapX(x, axes), mapY(y, axes)
//                )
//        );
//
//        x += xInc;
//        while (x < xMax) {
//            try {
//				y = p.value(x);
//			} catch (FunctionEvaluationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            
//
//            path.getElements().add(
//                    new LineTo(
//                            mapX(x, axes), mapY(y, axes)
//                    )
//            );
//
//            x += xInc;
//        }
//
//        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
//        setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
//        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
//
//        getChildren().add(path);
//	}
}
