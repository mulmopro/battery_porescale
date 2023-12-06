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
