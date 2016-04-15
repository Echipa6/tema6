package Usefull;



public class FunctionG  {

	@Override
	public String toString() {
		return "FunctionG [function=" + function + ", color=" + color + ", stroke=" + stroke + "]";
	}

	private String function;
	private String color;
	private Integer stroke;
	
	public String getFunction() {
		return function;
	}

	public String getColor() {
		return color;
	}

	public int getStroke() {
		return stroke;
	}
	public FunctionG()
	{
		
	}
	public FunctionG(String function, String color, int stroke )
	{
		this.function=function;
		this.color=color;
		this.stroke=stroke;
	}
}
