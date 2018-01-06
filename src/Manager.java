import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Manager extends JPanel implements ActionListener, KeyListener {
	private boolean isRunning;
	private int delay, playerX, ballX, ballY, ballDirX, ballDirY, score, totalBricks;
	private Timer timer;
	private Map map;
	
	public Manager() {
		isRunning = false;
		this.setDefaultValues();
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void setDefaultValues(){
		playerX = 350;
		ballX = 400;
		ballY = 400;
		ballDirX = 1;
		ballDirY = -1;
		totalBricks = 64;
		score = 0;
		delay = 4;
		map = new Map(4,16);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 800, 800);
		map.draw((Graphics2D)g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 3, 800);
		g.fillRect(0, 0, 800, 3);
		g.fillRect(791, 0, 3, 800);
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("Score: " +score,650,50);
		g.setColor(Color.WHITE);
		g.fillRect(playerX, 550, 100, 8);
		g.setColor(Color.WHITE);
		g.fillOval(ballX, ballY, 20, 20);
		
		if(ballY > 700){
			isRunning = false;
			ballDirX = 0;
			ballDirY = 0;
			g.setColor(Color.WHITE);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("YOU LOST. Score:  " +score, 250, 400);
			g.drawString("Hit Enter to Try Again", 275, 450);	
		}
		
		if(totalBricks == 0){
			isRunning = false;
			ballDirX = 0;
			ballDirY = 0;
			g.setColor(Color.WHITE);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("YOU WIN. Score:  " +score, 250, 400);
			g.drawString("Hit Enter to Try Again", 275, 450);
		}
		g.dispose();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if (playerX >= 700) {
				playerX = 700;
			} else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if (playerX <= 0) {
				playerX = 0;
			} else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(!isRunning){
				setDefaultValues();
				repaint();
			}
		}
	}

	public void moveRight(){
		isRunning = true;
		playerX += 30;
	}
	
	public void moveLeft(){
		isRunning = true;
		playerX -= 30;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(isRunning){
			
			if(new Rectangle(ballX, ballY, 20,20).intersects(new Rectangle(playerX,550,100,8))){
				ballDirY *= -1;
			}
			
			X: for(int i = 0; i < map.getMap().length; i++){ //ADD GETMAP
				for(int j = 0; j < map.getMap()[0].length; j++){
					if(map.getMap()[i][j] > 0){
						int brickX = j*map.getWidth() + 20;
						int brickY = i*map.getHeight() + 100;
						int brickWidth = map.getWidth();
						int brickHeight = map.getHeight();
						
						Rectangle rectangle = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ball = new Rectangle(ballX,ballY,20,20);
						Rectangle brick = rectangle;
						
						if(ball.intersects(brick)){
							map.setValue(0, i, j);
							totalBricks--;
							score++;
						
							if(ballX + 19 <= brick.getX() || ballX + 1 >=brick.getX() + brick.getWidth()){
								ballDirX *= -1;
							} else {
								ballDirY *= -1;
							}
							break X;
						}
					}
				}
			}
			ballX += ballDirX;
			ballY += ballDirY;
			if(ballX < 0){
				ballDirX *= -1;	
			}
			if(ballY < 0){
				ballDirY *= -1;	
			}
			if(ballX > 775){
				ballDirX *= -1;	
			}
		}
		repaint();
	}
}