public class ParticlesGeometry
{
    private int numparticles;
    private double [] pos_x=new double[10000];
    private double [] pos_y=new double[10000];
    private double [] pos_z=new double[10000];
    private double [] dim_x=new double[10000];
    private double [] dim_y=new double[10000];
    private double [] dim_z=new double[10000];
    private double [] rot_x=new double[10000];
    private double [] rot_y=new double[10000];
    private double [] rot_z=new double[10000];
	private int i=0;	private int j=0;	private int k=0;
	
    public void Read(String [] line) 
    {        
		if(line[0].equals("P)"))
		{
			pos_x[i]=Double.parseDouble(line[1]);
			pos_y[i]=Double.parseDouble(line[2]);
			pos_z[i]=Double.parseDouble(line[3]);
			i+=1;
		}
		if(line[0].equals("D)"))
		{
			dim_x[j]=Double.parseDouble(line[1]);
			dim_y[j]=Double.parseDouble(line[2]);
			dim_z[j]=Double.parseDouble(line[3]);
			j+=1;
		}
		if(line[0].equals("R)"))
		{
			rot_x[k]=Double.parseDouble(line[1]);
			rot_y[k]=Double.parseDouble(line[2]);
			rot_z[k]=Double.parseDouble(line[3]);
			k+=1;
		}
		numparticles=i;
	}
	public double Zprojection(double x_rot, double y_rot, double x_dim, double y_dim, double z_dim, double z_pos)
	{
		// Convert angles from degrees to radians
		double xRotRad = Math.toRadians(x_rot);
		double yRotRad = Math.toRadians(y_rot);
	
		// Precompute trigonometric values for the rotations
		double cosX = Math.cos(xRotRad), sinX = Math.sin(xRotRad);
		double cosY = Math.cos(yRotRad), sinY = Math.sin(yRotRad);
	
		// Compute the combined rotation matrix elements (only the relevant ones)
		double r20 = -sinY;                // Element [2,0] of the combined rotation matrix
		double r21 = sinX * cosY;          // Element [2,1] of the combined rotation matrix
		double r22 = cosX * cosY;          // Element [2,2] of the combined rotation matrix
	
		// Compute the projection along the z-axis using the relevant matrix components
		double projection = z_dim * Math.sqrt(
			r20 * r20 * (x_dim * x_dim) / (z_dim * z_dim) +  // Contribution from the x-dimension
			r21 * r21 * (y_dim * y_dim) / (z_dim * z_dim) +  // Contribution from the y-dimension
			r22 * r22);                                      // Contribution from the z-dimension
	
		// Return the final z-projection, including the z-position offset
		return z_pos + projection;
	}
	
	public int num_particles() {return numparticles;}
	public double x_pos(int index) {return pos_x[index];}
	public double y_pos(int index) {return pos_y[index];}
	public double z_pos(int index) {return pos_z[index];}
	public double x_dim(int index) {return dim_x[index];}
	public double y_dim(int index) {return dim_y[index];}
	public double z_dim(int index) {return dim_z[index];}
	public double x_rot(int index) {return rot_x[index];}
	public double y_rot(int index) {return rot_y[index];}
	public double z_rot(int index) {return rot_z[index];}
}
