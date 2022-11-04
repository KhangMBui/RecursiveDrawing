import java.awt.Color;
import java.awt.Graphics;

public class SierpinskiCarpet extends AbstractShape {
	
	private int width = FractalDisplay.WIDTH;
	private int height = FractalDisplay.HEIGHT;
    private int topX = 0;
    private int topY = 0;
   
	public SierpinskiCarpet(int topX, int topY, int W, int H, Color color) {
		if (W <= 0 || H <= 0 || color == null) {
			throw new IllegalArgumentException("Invalid square data");
		}
		this.topX = topX;
		this.topY = topY;
		this.width = W;
		this.height = H;
		this.maxLevel = 6;
		this.color = color;
		children = new SierpinskiCarpet[8];
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
					if (!(c==2 && r == 2)) {
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
				 if (!(c==2 && r == 2)) {
					 children[i] = new SierpinskiCarpet(x, y, width/3, height/3, getRandomColor());
					 i++;
				 }
				 x += width/3;
			 }
			 y += height/3;
		 }
	 }
	 @Override
	 public void update(int value) {
		 
	 }
	 
}
