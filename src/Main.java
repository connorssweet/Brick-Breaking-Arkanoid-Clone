import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("BrickBreaking");
		Manager game = new Manager();
		frame.add(game);
		frame.setSize(800,800);
		frame.setTitle("Break Breaking");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
