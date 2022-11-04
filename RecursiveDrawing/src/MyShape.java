import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JFrame;

import org.w3c.dom.css.Rect;

public class MyShape extends AbstractShape {
	Point p, q, r;
	Color color;
	public MyShape(Point p, Point q, Point r, Color color) {
		maxLevel = 10;
		this.p = p;
		this.q = q;
		this.r = r;
		this.color = color;
		children = new MyShape[3];
		for (int i = 0; i<children.length; i++)
			children[i] = null;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
    	if (this.children[0] == null) {
    		Polygon newP = new Polygon();
    		newP.addPoint(this.p.x, this.p.y);
    		newP.addPoint(this.q.x, this.q.y);
    		newP.addPoint(this.r.x, this.r.y);
    		g.drawPolygon(newP);
    		
    		
    	}
    	else {
    		for (int i = 0; i<children.length; i++) {
    			this.children[i].draw(g);	
    		}
    	}
	}
	public Point[] createTriangle(Point p, Point q) {
		Point[] newTriangle = new Point[3];
		Point c = new Point((int)(p.x + (q.x-p.x) / 3.0), (int)(p.y + (q.y-p.y) / 3.0));
		Point a = new Point((int)(p.x + 2 * (q.x-p.x) / 3.0), (int)(p.y + 2 * (q.y-p.y) / 3.0));
		Point b = new Point();
		b.x = (int)(a.x + (c.x - a.x) * Math.cos(Math.PI / 3.0) + (c.y - a.y) * Math.sin(Math.PI / 3.0));
		b.y = (int)(a.y - (c.x - a.x) * Math.sin(Math.PI / 3.0) + (c.y - a.y) * Math.cos(Math.PI / 3.0));
		newTriangle[0] = b;
		newTriangle[1] = a;
		newTriangle[2] = c;
		return newTriangle;
	}
	
	public void drawWhiteLine(Graphics g, Point a, Point b) {
		
	}
	 @Override
	 public void createChildren() {

		Point[] t1 = createTriangle(p, q);
		 Point[] t2 = createTriangle(q, r);
		 Point[] t3 = createTriangle(p, r);
		 children[0] = new MyShape(t1[0], t1[1], t1[2], this.color);
		 children[1] = new MyShape(t2[0], t2[1], t2[2], this.color);
		 children[2] = new MyShape(t3[0], t3[1], t3[2], this.color);
		 
	 }
	 @Override
	 public void update(int value) {
		 
		 
	 }
	 
}
