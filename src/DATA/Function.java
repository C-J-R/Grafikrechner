package DATA;

import GUI.Zeichenbereich;

import java.awt.*;

public abstract class Function {
	public abstract void render(Graphics g, Zeichenbereich z);
	public abstract double auswerten(double x);
}
