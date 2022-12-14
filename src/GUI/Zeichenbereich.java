package GUI;

import DATA.Function;
import DATA.FunctionContainer;
import DATA.Polynom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Zeichenbereich extends JPanel {

	public int sizeX, sizeY;
	public Point ursprung;
	public Point previousMousePosition = new Point(0, 0);

	public int pixelProEinheit;
	final int ppE_max = 200;
	final int ppE_min = 30;
	final int ppE_delta = 7; //Zoom Speed

	public int unitLineLength = 10;
	public int renderIntervall = 10;

	private FunctionContainer fContainer;

	public Zeichenbereich(int sizeX, int sizeY, int ppE) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		ursprung = new Point(sizeX / 2, sizeY / 2);
		pixelProEinheit = ppE;

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		zeichneUrsprung(g);
		zeichneKoordinatenSystem(g);
		g.setColor(Color.RED);

		fContainer = FunctionContainer.instance();
		double[] d = {0, 0, 1};
		fContainer.addFunction(new Polynom(d));
		double[] d2 = {0, 0, 0, 1};
		fContainer.addFunction(new Polynom(d2));
		fContainer.renderAll(g, this);
		//drawFunction(new Polynom(d), g);

	}


	private void zeichneUrsprung(Graphics g) {
		g.drawOval((int) ursprung.getX() - 5, (int) ursprung.getY() - 5, 10, 10);
	}

	private void zeichneKoordinatenSystem(Graphics g) {
		for(int posX = ursprung.x % pixelProEinheit; posX < sizeX; posX += pixelProEinheit) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(
					posX, 0,
					posX, sizeX
			);
			g.setColor(Color.BLACK);
			g.drawLine(
					posX, ursprung.y + unitLineLength / 2,
					posX, ursprung.y - unitLineLength / 2
			);

			int i = (posX - ursprung.x) / pixelProEinheit;
			if(i != 0)
				g.drawString(i + "", posX - 3, ursprung.y + 2 * unitLineLength);
		}
		if(ursprung.getX() > 0 && ursprung.getX() < sizeX) {
			g.drawLine((int) ursprung.getX(), 0,  (int) ursprung.getX(), sizeY);
		}

		for(int posY = ursprung.y % pixelProEinheit; posY < sizeY; posY += pixelProEinheit) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(
					0, posY,
					sizeX, posY
			);
			g.setColor(Color.BLACK);
			g.drawLine(
					ursprung.x + unitLineLength / 2, posY,
					ursprung.x - unitLineLength / 2, posY
			);
			int i = -1 * (posY - ursprung.y) / pixelProEinheit;
			if(i != 0)
				g.drawString(i + "", ursprung.x - 2 * unitLineLength, posY + 5);
		}
		if(ursprung.getY() > 0 && ursprung.getY() < sizeY) {
			g.drawLine(0, (int) ursprung.getY(), sizeX, (int) ursprung.getY());
		}
	}

	public void zoomIn(MouseEvent e) {
		double zoomPercent;
		if(pixelProEinheit + ppE_delta < ppE_max) {
			zoomPercent = (pixelProEinheit + ppE_delta) / (double) (pixelProEinheit);
			pixelProEinheit += ppE_delta;
		} else {
			zoomPercent = (ppE_max) / (double) pixelProEinheit;
			pixelProEinheit = ppE_max;
		}
		ursprung.move(
				(int) (ursprung.getX() + (1 - zoomPercent) * (e.getX() - ursprung.getX())),
				(int) (ursprung.getY() + (1 - zoomPercent) * (e.getY() - ursprung.getY()))
		);
	}

	public void zoomOut(MouseWheelEvent e) {
		double zoomPercent;
		if(pixelProEinheit - ppE_delta > ppE_min) {
			zoomPercent = (pixelProEinheit - ppE_delta) / (double) (pixelProEinheit);
			pixelProEinheit -= ppE_delta;
		} else {
			zoomPercent = (ppE_min) / (double) pixelProEinheit;
			pixelProEinheit = ppE_min;
		}
		ursprung.move(
				(int) (ursprung.x + (1 - zoomPercent) * (e.getX() - ursprung.x)),
				(int) (ursprung.y + (1 - zoomPercent) * (e.getY() - ursprung.y))
		);

	}

	public void moveUrsprung(int x, int y) {
		ursprung.move(
				(int) (ursprung.getX() + x - previousMousePosition.getX()),
				(int) (ursprung.getY() + y - previousMousePosition.getY())
		);
		previousMousePosition.move(x, y);
	}

	public void setMousePosition(int x, int y) {
		previousMousePosition.move(x, y);
	}

}
