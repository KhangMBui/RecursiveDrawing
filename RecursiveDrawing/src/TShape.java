import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class TShape extends AbstractShape {
	protected Point a,b,c;
	protected Color color;
	

	public TShape(Point a, Point b, Point c, Color color) {
		this.maxLevel = 10;
		this.a = a;
		this.b = b;
		this.c = c;
		this.color = color;
		children = new TShape[3];
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
		children[0] = new TShape(p, b, r, getRandomColor());
		children[1] = new TShape(q, r, c, getRandomColor());
		children[2] = new TShape(a, p, q, getRandomColor());
	}
	@Override
	public void update(int value) {
		int ratio = (value)/100;
		for (int i = 0; i<children.length; i++) {
			Point p = between(a, b);
			Point q = between(a, c);
			Point r = between(b, c);
			p.x = a.x + (b.x - a.x)*ratio;
			p.y = a.y + (b.y - a.y)*ratio;
			
			q.x = a.x + (c.x - a.x)*ratio;
			q.y = a.y + (c.y - a.y)*ratio;
			
			r.x = b.x + (c.x - b.x)*ratio;
			r.y = b.y + (c.y - b.y)*ratio;
			
			
			
		}	
		if (children[0] == null) {
			Point p = between(a, b);
			Point q = between(a, c);
			Point r = between(b, c);
			p.x = a.x + (b.x - a.x)*ratio;
			p.y = a.y + (b.y - a.y)*ratio;
			
			q.x = a.x + (c.x - a.x)*ratio;
			q.y = a.y + (c.y - a.y)*ratio;
			
			r.x = b.x + (c.x - b.x)*ratio;
			r.y = b.y + (c.y - b.y)*ratio;
			
			this.a = r;
			this.b = p;
			this.c = q;
		}
		else {
			for (int i = 0; i<children.length; i++) {
				this.children[i].update(value);
				
			}		
		}
	}

}
