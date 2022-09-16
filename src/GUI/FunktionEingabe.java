package GUI;

import javax.swing.*;
import java.awt.*;

public class FunktionEingabe extends JDialog {
	public FunktionEingabe(Grafikrechner par, int typ) {
		super();
		this.setTitle("Funktion Hinzufügen");
		JPanel content = new JPanel();
		JLabel label = new JLabel("Funktion: ");
		JTextField input = new JTextField(20);
		content.add(label);
		content.add(input);
		this.add(content);
		JPanel btnsPanel = new JPanel();
		JButton cancelBtn = new JButton("Abbrechen");
		cancelBtn.addActionListener(e -> {
			dispose();
		});
		JButton okBtn = new JButton("Fertig");
		okBtn.addActionListener(e -> {
			par.parseFunction(input.getText(), typ);
			dispose();
		});

		btnsPanel.add(cancelBtn);
		btnsPanel.add(okBtn);
		this.add(btnsPanel, BorderLayout.SOUTH);

		this.pack();
		this.setVisible(true);
	}
}
