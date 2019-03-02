package pers.jueyanyingyu.characterPaintingCreater.base;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class UI {
	public static void main(String[] args) {
		JFrame window = new JFrame("字符画生成器");
		window.setResizable(false);
		window.setSize(500, 500);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
