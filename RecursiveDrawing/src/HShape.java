import java.awt.Color;
import java.awt.Graphics;

public class HShape extends AbstractShape {
	
	private int width = FractalDisplay.WIDTH;
	private int height = FractalDisplay.HEIGHT;
    private int topX = 0;
    private int topY = 0;
   
 
	public HShape(int topX, int topY, int W, int H, Color color) {
		if (W <= 0 || H <= 0 || color == null) {
			throw new IllegalArgumentException("Invalid square data");
		}
		this.topX = topX;
		this.topY = topY;
		this.width = W;
		this.height = H;
		this.maxLevel = 6;
		this.color = color;
		children = new HShape[7];
		for (int i = 0; i<children.length; i++) 
			children[i] = null;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		if (this.children[0] == null)  {
			int y = topY;
			for (int r = 1; r<=3; r++) {
				int x = topX;
				for (int c = 1; c<=3; c++) {
					if (c!=2 || r == 2) {
						g.fillRect(x, y, width/3, height/3);
					}
					x+= width/3;
				}
				y += height/3;
			}
		}
		else {
			for (int i = 0; i<this.children.length; i++)
				this.children[i].draw(g);
		}
	}
	 @Override
	 public void createChildren() {
		 int y = topY;
		 int i = 0;
		 for (int r = 1; r<=3; r++) {
			 int x = topX;
			 for (int c = 1; c<=3; c++) {
				 if (c!=2 || r == 2) {
					 children[i] = new HShape(x, y, width/3, height/3, getRandomColor());
					 i++;
				 }
				 x += width/3;
			 }
			 y += height/3;
		 }
	 }
	 public HShape getChildren(int i) {
		 return (HShape) this.children[i];
	 }
	 @Override
	 public void update(int value) {
		 double ratio = (((double)value)/100);
//		 HShape child = this.getChildren(3);
//		 child.topY *= ratio;
		 HShape child = (HShape) this.children[3];
		 child.topY *= ratio;
		 for (int i = 0; i<children.length; i++) {
				if (this.children[i]!= null) {
					this.children[i].update(value);
				}
		}	
		 
	 }
	 
}
