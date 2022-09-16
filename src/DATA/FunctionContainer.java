package DATA;

import GUI.Zeichenbereich;

import java.awt.*;
import java.util.ArrayList;

public class FunctionContainer {
	public ArrayList<Function> funcs;
	private static FunctionContainer unique = null;

	private FunctionContainer() {
		funcs = new ArrayList<Function>();
	}

	public static FunctionContainer instance() {
		if(unique == null) {
			unique = new FunctionContainer();
		}
		return unique;
	}

	public void renderAll(Graphics g, Zeichenbereich z) {
		funcs.forEach(function -> {
			function.render(g, z);
		});
	}

	public void addFunction(Function f) {
		funcs.add(f);
	}

}
