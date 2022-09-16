package DATA;

import GUI.Zeichenbereich;

import java.awt.*;

public class Polynom extends Function{
	private double[] koeffizienten;

	public Polynom(double[] a_n) {
			koeffizienten = a_n;
	}

	public Polynom(String s) {

	}

	public void render(Graphics g, Zeichenbereich z) {
		Point a, b;
		a = new Point(0, 0);
		b = new Point(0, (int) (z.ursprung.y - (auswerten((0 - z.ursprung.getX()) / z.pixelProEinheit) * z.pixelProEinheit)));
		for(int posX = 0; posX < z.sizeX + z.renderIntervall; posX += z.renderIntervall) {
			a.move(b.x, b.y);
			int posY = (int) (z.ursprung.y - (auswerten((posX - z.ursprung.getX()) / z.pixelProEinheit) * z.pixelProEinheit));
			b.move(posX, posY);
			g.drawLine(a.x, a.y, b.x, b.y);
			//g.drawLine(a.x, a.y - 1, b.x, b.y - 1);
		}
	}

	//TODO: Bessere Implementation zur Auswertung
	@Override
	public double auswerten(double x) {
		double r = 0;
		for(int i = 0; i < koeffizienten.length; i++) {
			r += koeffizienten[i] * Math.pow(x, i);
		}
		return r;
	}
}
