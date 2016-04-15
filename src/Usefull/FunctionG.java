package Usefull;



public class FunctionG  {

	@Override
	public String toString() {
		return "FunctionG [function=" + function + ", color=" + color + ", stroke=" + stroke + "]";
	}

	private String function;
	private String color;
	private String stroke;
	
	public FunctionG()
	{
		
	}
	public FunctionG(String function, String color, String stroke )
	{
		this.function=function;
		this.color=color;
		this.stroke=stroke;
	}

	
	public String getFunction() {
		return function;
	}

	public String getColor() {
		return color;
	}

	public String getStroke() {
		return stroke;
	}
	
	public void setFunction(String function) {
		this.function = function;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setStroke(String stroke) {
		this.stroke = stroke;
	}
}
