package model.nmap;

public class Box {
	
	public float x;
	public float y;
	public float w;
	public float h;
	public float klass;
	public float elemx;
	public float elemy;
	
	public Box() {
		
	}
	
	public Box(float x, float y, float w, float h, float klass, float elemx, float elemy) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.klass = klass;
		this.elemx = elemx;
		this.elemy = elemy;
	}
	
	public String toString() {
		return "[" + this.x + "," + this.y + "," + this.w + "," + this.h + "]";
	}
	
}
