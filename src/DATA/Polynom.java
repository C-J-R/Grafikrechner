package DATA;

public class Polynom extends Function{
	private double[] koeffizienten;

	public Polynom(double[] a_n) {
			koeffizienten = a_n;
	}

	public Polynom(String s) {

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
