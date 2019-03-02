package pers.jueyanyingyu.characterPaintingCreater.base;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Transformer {
	static int c = 1;
	static int precision = 16;
	static double h_w = 1.5;
	static int fontSize = (int) (precision);
	public static char[] charList = "WNB8b271=\";-,_. ".toCharArray();
	public BufferedImage imageData, characterPaintingData;
	public char[][] targetData;

	public Transformer(String fileName) throws FileNotFoundException, IOException {
		BufferedImage imageTempData = ImageIO.read(new FileInputStream(fileName));
		imageData = new BufferedImage((int) (imageTempData.getWidth()*c / precision),
				(int) (imageTempData.getHeight()*c / precision), BufferedImage.TYPE_INT_RGB);
		imageData.getGraphics().drawImage(imageTempData.getScaledInstance((int) (imageTempData.getWidth()*c / precision),
				(int) (imageTempData.getHeight()*c / precision), java.awt.Image.SCALE_SMOOTH), 0, 0, null);
	}

	public void transform() throws IOException {
		int rgb, r, g, b;
		targetData = new char[imageData.getHeight()][imageData.getWidth()];
		for (int i = 0; i < imageData.getHeight(); i += 1) {
			for (int j = 0; j < imageData.getWidth(); j += 1) {
				rgb = imageData.getRGB(j, i);
				r = (rgb & 0xff0000) >> 16;
				g = (rgb & 0x00ff00) >> 8;
				b = (rgb & 0x0000ff);

				targetData[i][j] = charList[(int) ((0.3 * r + 0.59 * g + 0.11 * b)/17)];
			}
		}
		characterPaintingData = new BufferedImage((int) (imageData.getWidth() * fontSize),
				(int) (imageData.getHeight() * fontSize), BufferedImage.TYPE_INT_BGR);
		Graphics graphic = characterPaintingData.getGraphics();
		graphic.setClip(0, 0, (int) (imageData.getWidth() * fontSize), (int) (imageData.getHeight() * fontSize));
		graphic.setColor(Color.white);
		graphic.fillRect(0, 0, (int) (imageData.getWidth() * fontSize), (int) (imageData.getHeight() * fontSize));
		graphic.setColor(Color.black);
		graphic.setFont(new Font("黑体", Font.BOLD, fontSize ));
		for (int i = 0; i < imageData.getHeight(); i += 1) {
			for (int j = 0; j < imageData.getWidth(); j += 1) {
				graphic.drawString(String.valueOf(targetData[i][j]), fontSize*j, fontSize * (i + 1));
			}
			// graphic.drawChars("W", 0, imageData.getWidth() / precision, 0, 30 * i);
		}
		graphic.dispose();
		File asd = new File(
				"D:\\\\文档\\\\JavaWorkSpace\\\\CharacterPaintingCreater\\\\src\\\\pers\\\\jueyanyingyu\\\\characterPaintingCreater\\\\base\\\\test2.jpg");
		ImageIO.write(characterPaintingData, "jpg", asd);

	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Transformer a = new Transformer(
				"D:\\文档\\JavaWorkSpace\\CharacterPaintingCreater\\src\\pers\\jueyanyingyu\\characterPaintingCreater\\base\\test.jpg");
		a.transform();
	}
}
