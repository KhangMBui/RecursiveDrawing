import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public abstract class AbstractShape implements Shape {

	protected int level;
	protected int maxLevel;
	protected AbstractShape[] children;
	protected Color color;

	public AbstractShape() {
		level = 1;
	}

	// The fields may be initialized by the AbstractShape constructor with the
	// values
	// received from the super() calls in the constructors of the concrete shape
	// classes.
	// For instance, the SierpinskiTriangle constructor may call the AbstractShape
	// constructor with
	// the a max level value of 10 and a children array length of 3
	// Alternatively the fields may be initialized in the concrete class
	// constructors (they are visible by
	// the concrete classes since they are declared protected)

	public void draw(Graphics g) {

	}
	public Color getRandomColor() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float s = rand.nextFloat();
		return new Color(r, g, s);
	}

	@Override
	public boolean addLevel() {
		// Base case: no children
		if (children[0] == null) {
			//Check if reaches max level
			if (level < maxLevel) {
				//If not, create children and set their levels
				this.createChildren();
				for (int i = 0; i < children.length; i++) {
					children[i].level = level + 1;
				}
				return true;
			}
			//Max level: return False
			else {
				return false;
			}
		} 
		//If have children:
		else {
			boolean b = true;
			// recursion (call addLevel to children), go to the children and repeat the steps above
			for (int i = 0; i < children.length; i++) {
				b = b && children[i].addLevel();
			}
			return b;
		}
	}

	@Override
	public boolean removeLevel() {
		//Base case: have children
		if (children[0] != null) {
			//Check if there are grandchildren
			//If there are not: set all the children to null
			if (children[0].children[0] == null) {
				for (int i = 0; i < children.length; i++) {
					children[i] = null;
				}
				return true;
			} 
			//If there are grand children, go to the grandchildren and repeat and steps above
			else {
				boolean b = true;
				for (AbstractShape sh : children) {
					b = b && sh.removeLevel();
				}
				return b;
			}
		} else {
			return false;
		}
	}	
	@Override
	public int countShapes() {
		if (this.children[0] == null) {
			return 1;
		}
		else {
			int c = 1;
			for (int i = 0; i<children.length; i++) {
				c+= children[i].countShapes();
			}
			return c;
		}
	}
	public void update(int value) {

	}

	

}
