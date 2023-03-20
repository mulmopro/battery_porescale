public class Tolerance
{
	private double cut_tol=1E-6;
	private double fin_tol=1E-6;
	private double mesh_tol=1E-3;
	private double imp_tol=1E-10;
	private double vol_tol=0.00;
	public double cut() {return cut_tol;}
	public double fin() {return fin_tol;}
	public double mesh() {return mesh_tol;}
	public double imp() {return imp_tol;}
	public double vol() {return vol_tol;}
}
