import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class SierpinskiTriangle extends AbstractShape {
	private Point a,b,c;
	

	public SierpinskiTriangle(Point a, Point b, Point c, Color color) {
		this.maxLevel = 10;
		this.a = a;
		this.b = b;
		this.c = c;
		this.color = color;
		children = new SierpinskiTriangle[3];
		for (int i = 0; i<children.length; i++)
			children[i] = null;
		
	}
	
	// The fields may be initialized by the AbstractShape constructor with the values
	// received from the super() calls in the constructors of the concrete shape classes.
	// For instance, the SierpinskiTriangle constructor may call the AbstractShape constructor with
	// the a max level value of 10 and a children array length of 3
	// Alternatively the fields may be initialized in the concrete class constructors (they are visible by
	// the concrete classes since they are declared protected)
	
    public void draw(Graphics g) {
    	g.setColor(this.color);
    	if (this.children[0] == null) {
    		Polygon p = new Polygon();
    		p.addPoint(this.a.x, this.a.y);
    		p.addPoint(this.b.x, this.b.y);
    		p.addPoint(this.c.x, this.c.y);
    		g.drawPolygon(p);
    	}
    	else {
    		for (int i = 0; i<children.length; i++) {
    			this.children[i].draw(g);
    		}
    	}
    	
    }
    public Point between(Point a, Point b) {
        return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
    }
	@Override
	public void createChildren() {
		Point p = between(a, b);
		Point q = between(a, c);
		Point r = between(b, c);
		children[0] = new SierpinskiTriangle(p, b, r, getRandomColor());
		children[1] = new SierpinskiTriangle(q, r, c, getRandomColor());
		children[2] = new SierpinskiTriangle(a, p, q, getRandomColor());
	}
	@Override
	public void update(int value) {
		//double ratio =  (((double) value)/100);

//		Point p = between(a, b);
//		Point q = between(a, c);
//		Point r = between(b, c);
//		p.x = (int) (a.x + (b.x - a.x)*ratio);
//		p.y = (int) (a.y + (b.y - a.y)*ratio);
//
//		q.x = (int) (a.x + (c.x - a.x)*ratio);
//		q.y = (int) (a.y + (c.y - a.y)*ratio);
//
//		r.x = (int) (b.x + (c.x - b.x)*ratio);
//		r.y = (int) (b.y + (c.y - b.y)*ratio);
//	
//		this.a = r;
//		this.b = p;
//		this.c = q;
		
//		for (int i = 0; i<children.length; i++) {
//			if (this.children[i]!= null) {
//				this.children[i].update(value);
//			}
//		}	
		
		
//		double ratio = (((double) value)/100);
//		Point p = between(a, b);
//		Point q = between(a, c);
//		Point r = between(b, c);
//		
//		
//		if (children[0] == null) {
//			
//			p.x = (int) (a.x + (b.x - a.x)*ratio);
//			p.y = (int) (a.y + (b.y - a.y)*ratio);
//
//			q.x = (int) (a.x + (c.x - a.x)*ratio);
//			q.y = (int) (a.y + (c.y - a.y)*ratio);
//
//			r.x = (int) (b.x + (c.x - b.x)*ratio);
//			r.y = (int) (b.y + (c.y - b.y)*ratio);
//		
//			this.a = r;
//			this.b = p;
//			this.c = q;
//		}
//		else {
//			for (int i = 0; i<children.length; i++) {
//				if (this.children[i]!= null) {
//					this.children[i].update(value);
//				}
//			}	
//		}
	}

}
