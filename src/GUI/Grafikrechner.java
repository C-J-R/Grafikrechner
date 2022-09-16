package GUI;

import DATA.Function;
import DATA.Polynom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Grafikrechner extends JFrame {

	public ArrayList<Function> funcs;
	public final int POLYNOM = 0;

	public static void main(String[] args) {
		new Grafikrechner();
	}
	public Grafikrechner() {
		super("Grafikrechner");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1920, 1080);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu addMenu = new JMenu("Add");
		JMenuItem save = new JMenuItem("Datei speichern");
		fileMenu.add(save);
		JMenuItem export = new JMenuItem("Als Bild exportieren");
		fileMenu.add(export);
		JMenuItem addPolynom = new JMenuItem("Polynom hinzufügen");
		addPolynom.addActionListener(e -> {
			FunktionEingabe fIn = new FunktionEingabe(this, POLYNOM);
		});
		menuBar.add(fileMenu);
		menuBar.add(addMenu);
		addMenu.add(addPolynom);
		this.add(menuBar, BorderLayout.NORTH);

		JPanel leftPanel = new JPanel();
		//leftPanel.setBackground(new Color(200, 200, 200));
		leftPanel.setPreferredSize(new Dimension(450, 1000));
		this.add(leftPanel, BorderLayout.WEST);

		Zeichenbereich mainPanel = new Zeichenbereich(1000, 1000, 100);

		export.addActionListener(e -> {
			BufferedImage b = new BufferedImage(
					1000, 1000, BufferedImage.TYPE_INT_RGB
			);
			Graphics g = b.createGraphics();
			mainPanel.paint(g);
			g.dispose();
			try{
				ImageIO.write(b,"png",new File("test.png"));
				System.out.println("Saved Image");
			}
			catch (Exception exception) {}
		});

		mainPanel.setSize(1000, 1000);
		mainPanel.repaint();
		mainPanel.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation() > 0) {
					mainPanel.zoomOut(e);
					mainPanel.repaint();
				} else {
					mainPanel.zoomIn(e);
					mainPanel.repaint();
				}
			}
		});
		mainPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				mainPanel.moveUrsprung(e.getX(), e.getY());
				mainPanel.repaint();

			}
		});
		mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mainPanel.setMousePosition(e.getX(), e.getY());
			}
		});
		this.add(mainPanel, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel();
		//rightPanel.setBackground(new Color(200, 200, 200));
		rightPanel.setPreferredSize(new Dimension(400, 1000));
		this.add(rightPanel, BorderLayout.EAST);

		this.setVisible(true);

		funcs = new ArrayList<Function>();
	}

	public void parseFunction(String s, int typ) {
		System.out.println("Parsing: " + s);
		if(typ == POLYNOM) {
			funcs.add(new Polynom(s));
		}
	}
}
