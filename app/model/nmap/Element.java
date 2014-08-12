package model.nmap;

import java.awt.geom.Point2D;

public class Element extends Point2D.Float {

    private static final long serialVersionUID = 1L;
    private int id;
    private float klass;
    private float weight;

    public Element(float x, float y, int id, float klass, float weight) {
        super(x, y);
        this.id = id;
        this.klass = klass;
        this.weight = weight;
    }

    public Element(float x, float y, int id, float klass) {
        this(x, y, id, klass, 1);
    }

    public Element(float x, float y, int id) {
        this(x, y, id, 1, 1);
    }

    public Element(float x, float y) {
        this(x, y, 1, 1, 1);
    }

    public Element() {
        this(0, 0, 1, 1, 1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getKlass() {
        return klass;
    }

    public void setKlass(float klass) {
        this.klass = klass;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
