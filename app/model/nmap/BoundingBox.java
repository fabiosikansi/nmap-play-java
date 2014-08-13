package model.nmap;

import java.awt.geom.Rectangle2D;

public class BoundingBox extends Rectangle2D.Float {

    private static final long serialVersionUID = 1L;
    private Element element;

    public BoundingBox(float x, float y, float w, float h, Element element) {
        super(x, y, w, h);
        this.element = element;
    }

    public BoundingBox(float x, float y, float w, float h) {
        this(x, y, w, h, null);
    }
    
    public void setBounds(Rectangle2D bound){
        this.x = (float)bound.getX();
        this.y = (float)bound.getY();
        this.width = (float)bound.getWidth();
        this.height = (float)bound.getHeight();
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
    
    public Box toBox() {
    	Box b = new Box(this.x, this.y, this.width, this.height, this.element.getKlass(),this.element.x,this.element.y);
    	return b;
    }
}
