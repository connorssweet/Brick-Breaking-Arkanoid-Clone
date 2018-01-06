import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {

	public int map[][];
	private int width, height;
	
	public Map(int row, int col){
		map = new int[row][col];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				map[i][j] = 1;
			}
		}
		
		width = 760/col;
		height = 150/row;
	}
	
	public void draw(Graphics2D g){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if (map[i][j] > 0){
					g.setColor(Color.WHITE);
					g.fillRect(j*getWidth() + 20, i*height + 100, getWidth(), height);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.BLACK);
					g.drawRect(j*getWidth() + 20, i*height + 100, getWidth(), height);
				}
			}
		}
	}	
	
	public void setValue(int val, int row, int col){
		map[row][col] = val;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int[][] getMap(){
		return map;
	}
}