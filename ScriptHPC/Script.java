// -------------- LITHIUM BATTERIES HALF CELL MODEL -------------- //

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;


import com.comsol.model.*;
import com.comsol.model.util.*;


class ParticlesGeometry
{
    private int numparticles;				    	public int num_particles() {return numparticles;}
    private double [] pos_x=new double[10000];		public double x_pos(int index) {return pos_x[index];}
    private double [] pos_y=new double[10000];		public double y_pos(int index) {return pos_y[index];}
    private double [] pos_z=new double[10000];		public double z_pos(int index) {return pos_z[index];}
    private double [] dim_x=new double[10000];		public double x_dim(int index) {return dim_x[index];}
    private double [] dim_y=new double[10000];		public double y_dim(int index) {return dim_y[index];}
    private double [] dim_z=new double[10000];		public double z_dim(int index) {return dim_z[index];}
    private double [] rot_x=new double[10000];		public double x_rot(int index) {return rot_x[index];}
    private double [] rot_y=new double[10000];		public double y_rot(int index) {return rot_y[index];}
    private double [] rot_z=new double[10000];		public double z_rot(int index) {return rot_z[index];}
	private int i=0;	private int j=0;	private int k=0;
	public void Read(String [] line) {}
}

class Tolerance
{
	private double cut_tol;		public double cut() {return cut_tol;}
	private double fin_tol;		public double fin() {return fin_tol;}
	private double mesh_tol;	public double mesh() {return mesh_tol;}
	private double imp_tol;		public double imp() {return imp_tol;}
	private double vol_tol;		public double vol() {return vol_tol;}
}

class Zone
{
	private int num;
	private int counter;
	private int i;
	private String [] name=new String[num];
	private String [] zone=new String[num];
	
	public void define(String text1, String text2){}
	public void replace(String text1, String text2){}
	public String select(String text) {return zone[i];}
}

class Operations
{
	public String adjsel="adjsel";		private int adjsel_c=1;
	public String ballsel="ballsel";	private int ballsel_c=1;
	public String blk="blk";			private int blk_c=1;
	public String boxsel="boxsel";		private int boxsel_c=1;
	public String comsel="comsel";		private int comsel_c=1;
	public String copy="copy";			private int copy_c=1;
	public String del="del";			private int del_c=1;
	public String difsel="difsel";		private int difsel_c=1;
	public String elp="elp";			private int elp_c=1;
	public String grp="grp";			private int grp_c=1;
	public String igf="igf";			private int igf_c=1;
	public String imp="imp";			private int imp_c=1;
	public String intsel="intsel";		private int intsel_c=1;
	public String mpart="mpart";		private int mpart_c=1;
	public String move="move";			private int move_c=1;
	public String pard="pard";			private int pard_c=1;
	public String rot="rot";			private int rot_c=1;
	public String sca="sca";			private int sca_c=1;
	public String sel="sel";			private int sel_c=1;
	public String uni="uni";			private int uni_c=1;

	public Model AdjacentSelection 
	(Model model, String label, String [] name, int entitydim, int outputdim, boolean interior, boolean exterior, String selshow, boolean add) 
	{return model;}
	public Model BallSelection
	(Model model, String label, String posx, String posy, String posz, String inputent, int entitydim, String condition, String selshow, boolean add)
	{return model;}
	public Model Block(Model model, String label, String base, String sizex, String sizey, String sizez, String posx, String posy, String posz, boolean add) {return model;}
	public Model BoxSelection (Model model, String label, int entitydim, String condition, String [] x, String inputent, String name, String selshow, boolean add) {return model;}
	public Model ComplementSelection (Model model, String label, String [] name, int entitydim, boolean add) {return model;}
	public Model Copy (Model model, String label, String name, int type, String displx, String disply, String displz, boolean add) {return model;}
	public Model Delete (Model  model, String label, String name, int type, boolean add) {return model;}
	public Model DifferenceSelection (Model model, String label, String [] name, String [] subtract, int entitydim, boolean add) {return model;}
	public Model Ellipsoide (Model model, String label, double x1, double y1, double z1, double x2, double y2, double z2, boolean add) {return model;}
	public Model Group (Model model, String label, String place) {return model;}
	public Model IgnoreFaces(Model model, String label, String name, boolean add) {return model;}
	public Model Import (Model model, String label, String stl, boolean formsolid, double tol, boolean add) {return model;}
	public Model IntersectionSelection (Model model, String label, String [] name, int entitydim, String selshow, boolean add) {return model;}
	public double MeasureCalc (Model model, String name, int level, int type) {return 0;}
	public Model Move (Model model, String label, String name, int type, String displx, String disply, String displz, boolean add) {return model;}
	public Model PartitionDomain (Model model, String label, String partwith, String name1, int type1, String name2, int type2, double tol, boolean add) {return model;}
	public Model Rotation (Model model, String label, String name, String ax, double x, double y, double z, double angle, boolean add) {return model;}
	public Model Scale (Model model, String lable, String name, String ScaleFactor, boolean add) {return model;}
	public Model Selection (Model model, String label, String name, String selshow, boolean add) {return model;}
	public Model Union (Model model, String label, String name, boolean keep, boolean intbnd, boolean add) {return model;}
}

class Materials
{
	String mat="mat";					private int mat_c=1;
	private String [] material=new String[20];	private int i=0	;
	private String intp="int";					private int intp_c=1;
	private int counter=0;
	String tmp="tmp";
	
	public Model newMaterial(Model model, String label, String [] addInput){return model;}
	
	public Model newFunc(Model model, String label, String group, String funcname, String [][] table, String interp, String extrap, String fununit, String argunit)
	{return model;}
	
	public Model setup(Model model, String label, String group, String [] set, String [] value, boolean tensor)
	{return model;}

	public Model newProperty(Model model, String label, String group, String property, String [] addInput)
	{return model;}

	public Model geomSelection(Model model, String label, String name)
	{return model;}
}

public class Script{

public static void main(String[] args) throws IOException
{
	int i=0;
	int k=0;

	boolean liion = false;
	boolean lis = true;

	if ((liion && lis) || (!liion && !lis)) {
		System.out.println("Select one chemistry!");
		return;
	}

	if (liion)
		System.out.println("Creating a model for Lithium-Ion batteries");
	if (lis)
		System.out.println("Creating a model for Lithium-Sulfur batteries");

	String currentDir = new File(".").getAbsolutePath();
	// Remove the trailing '.' character
	currentDir = currentDir.substring(0, currentDir.length() - 1);
	// Now 'currentDir' holds the path of the current directory
	System.out.println("Current Directory: " + currentDir);

	String folder = "Parameters/";
	// String outFile = "out.txt";
	// String filePath1 = currentDir + outFile;

	// FileWriter file = new FileWriter(filePath1);
	// BufferedWriter output = new BufferedWriter(file);
			
	// Model creation //
	Model model = ModelUtil.create("Model");

	model.component().create("TestCase", true);

	// Time interval //
	String time="range(0,0.001/C_rate,0.95/C_rate)";
	
	// Definition of the tollerances //
	Tolerance tol;
	tol = new Tolerance();
	
	// Zones definition //
	Zone z;
	z=new Zone();

	// Parameters //
	String paramFile="";
	String condFile="";
	String geomFile="";
	if (liion) {
		paramFile = "lithium_ion_chemico_physical_parameters.txt";
		condFile = "lithium_ion_operative_conditions.txt";
		geomFile = "lithium_ion_geometric_details.txt";
	}

	if (lis) {
		paramFile = "lithium_sulfur_chemico_physical_parameters.txt";
		condFile = "lithium_sulfur_operative_conditions.txt";
		geomFile = "lithium_sulfur_geometric_details.txt";
	}

	model = ParameterValues(model, currentDir, folder, paramFile, condFile, geomFile);
	System.out.println("Parameter Values loaded");

	// All Domain Variables //
	if (lis) {
		String varFile = "lithium_sulfur_all_domains_variables.txt";
		String filePath2 = currentDir + folder + varFile;
		model.component("TestCase").variable().create("var1");
		model.component("TestCase").variable("var1").loadFile(filePath2);
	}

	// Geometry //
	ParticlesGeometry pg;
	pg = new ParticlesGeometry();
	String l1="";
	String partFile="";
	if (liion) {
		partFile = "geometry_liion.txt";
	}
	if (lis) {
		partFile = "geometry_lis.txt";
	}
	String filePath6 = currentDir + folder + partFile;
	BufferedReader br1 = new BufferedReader(new FileReader(filePath6));
	do{
		l1=br1.readLine();
		String [] line=l1.split(" ",0);
		pg.Read(line);
		i+=1;
	}while(l1.equals("EOF")==false);
	model = GeometryConstruction(model, z, pg, tol, liion, lis);
	System.out.println("Geometry Construction done");

	if (liion) {
		// Materials //
		String [][] ExpVoltage=new String[10000][2];
		String [][] Voltage=new String[100][2];
		double dt=0.01;
		
		String l2="";
		String eeqFile = "C20.txt";
		String filePath7 = currentDir + folder + eeqFile;
		BufferedReader br2 = new BufferedReader(new FileReader(filePath7));
		
		i=0;
		while((l2=br2.readLine())!=null)
		{
			String [] line2=new String[2];
			line2=l2.split("\t",0);
			ExpVoltage[i][0]=line2[0];
			ExpVoltage[i][1]=line2[1];
			i+=1;
		}
		
		i=1;k=1;
		Voltage[0][0]=ExpVoltage[0][0];
		Voltage[0][1]=ExpVoltage[0][1];
		do{
			if(dt>Double.parseDouble(ExpVoltage[i][0]) && dt<Double.parseDouble(ExpVoltage[i+1][0]))
			{
				Voltage[k][0]=ExpVoltage[i][0];
				Voltage[k][1]=ExpVoltage[i][1];
				dt=dt+0.01;
				k+=1;
			}
			i+=1;
		}while(dt<1.0);
		Voltage[99][0]=ExpVoltage[2472][0];
		Voltage[99][1]=ExpVoltage[2472][1];
		
		String [][] D=new String[10000][2];
		String l3="";
		String diffFile = "D.txt";
		String filePath8 = currentDir + folder + diffFile;
		BufferedReader br3 = new BufferedReader(new FileReader(filePath8));
		i=0;
		while((l3=br3.readLine())!=null)
		{
			String [] line3=new String[2];
			line3=l3.split("\t",0);
			D[i][0]=line3[0];
			D[i][1]=line3[1];
			i+=1;
		}
		
		model = MaterialsDefinition(model, z, Voltage, D);
		System.out.println("Materials Definition done");

	}

	// Physics //
	model = PhysicsDefinition(model, z, liion, lis);
	System.out.println("Physics Definition done");
	
	// Mesh //
	model = MeshConstruction(model, z, liion, lis);
	System.out.println("Mesh Definition set");
	
	// Definizione del caso di studio //
	model = TestStudy(model, time, tol, liion, lis);
	System.out.println("TestStudy created");
	
	// Costruzione della mesh //
	model.component("TestCase").mesh("mesh1").run();

	boolean problem = model.component("TestCase").mesh("mesh1").hasProblems();
	String [] problemFeatures = model.component("TestCase").mesh("mesh1").problems();
	MeshFeature problematicFeature = model.component("TestCase").mesh("mesh1").feature(problemFeatures[0]);
	String [] errors = problematicFeature.errors();
	
	// for (String tag : errors) {
	// 	String errorMessage = problematicFeature.problem(tag).message();
	// 	System.out.println(errorMessage);
	// 	if (problematicFeature.problem(tag).hasSelection()) {
	// 	MeshSelection sel = problematicFeature.problem(tag).selection();
	// 	System.out.println("Selection: " + sel);
	// 	}
	// 	String[] problemDetails = problematicFeature.problem(tag).problems();
	// 	for (String tag2 : problemDetails) {
	// 	MeshProblemFeature detail = problematicFeature.problem(tag).problem(tag2);
	// 	// Recursively analyze subproblems
	// 	analyzeProblems(detail);
	// 	}
	// }

	// String msg1 = model.component("TestCase").mesh("mesh1").feature("ftet1").problem("error").getString("message"); 
	// System.out.println(msg1);
	// String det1 = model.component("TestCase").mesh("mesh1").feature("ftet1").problem("error").getString("details"); 
	// System.out.println(det1);
	// String msg2 = model.component("TestCase").mesh("mesh1").feature("ftet1").problem("error").problem("error_a").getString("message"); 
	// System.out.println(msg2);
	// String det2 = model.component("TestCase").mesh("mesh1").feature("ftet1").problem("error").problem("error_a").getString("details"); 
	// System.out.println(det2);

	// MeshFeatureList features = model.component("TestCase").mesh("mesh1").feature();
	// System.out.println(features);

	System.out.println("Mesh Construction done");
}

public static Model ParameterValues(Model model, String currentDir, String folder, String paramFile, String condFile, String geomFile) {
		
	// Chemico-physical parameters //
	model.param().label("Chemico-physical parameters ");
	String filePath3 = currentDir + folder + paramFile;
	model.param().loadFile(filePath3);

	// Operative Conditions //
	model.param().create("par2");
	model.param("par2").label("Operative Conditions");
	String filePath4 = currentDir + folder + condFile;
	model.param("par2").loadFile(filePath4);


	// Geometric details //
	model.param().create("par3");
	model.param("par3").label("Geometric details");
	String filePath5 = currentDir + folder + geomFile;
	model.param("par3").loadFile(filePath5);

	
	return model;
}

public static Model GeometryConstruction(Model model, Zone z, ParticlesGeometry pg, Tolerance tol, boolean liion, boolean lis) {
		
	int i=1;
	int b=0;
	int d=1;
	int j=0;
	
	double volume_pard;
	double volume_block;
	double area_block;
	double volume_particle;
	double volume_pard1;
	double volume_pard2;
	double electrode_height=60;
	
	String adjsel="adjsel";		int adjsel_c=1;
	String ballsel="ballsel";	int ballsel_c=1;
	String blk="blk";			int blk_c=1;
	String comsel="comsel";		int comsel_c=1;
	String copy="copy";			int copy_c=1;
	String del="del";			int del_c=1;
	String dif="dif";			int dif_c=1;
	String elp="elp";			int elp_c=1;
	String grp="grp";			int grp_c=1;
	String imp="imp";			int imp_c=1;
	String mpart="mpart";		int mpart_c=1;
	String move="move";			int move_c=1;
	String pard="pard";			int pard_c=1;
	String rot="rot";			int rot_c=1;
	String sca="sca";			int sca_c=1;
	String sel="sel";			int sel_c=1;
	String uni="uni";			int uni_c=1;
	
	String tmp;
	String ControlBlock;
	
	String [] Particles=new String[pg.num_particles()];
	
	boolean formsolid=true;			// Set this flag to "true" to form a solid from a stl geometry //
	boolean creation=true;			// Set this flag to "true" to create the particles directly in COMSOL //
	boolean CBD=false;				// Set this flag to "true" to consider the carbon binder domain //
	boolean volume_control=true;	// Set this flag to "true" to control the portion of each particle inside the electrolyte //
	boolean all_particles=true;		// Set this flag to "true" to create all the particles before cutting the system //
	
	Operations op;
	op=new Operations();
	
	model.component("TestCase").geom().create("geom1",3);
	
	// Definition of the block that will become the final system //
	model=op.Block(model, "Block 0", "center", "xmax/sf", "ymax/sf", "zmax/sf", "0", "0", "(zmin+zmax*0.5)/sf", false);
	tmp=op.blk;

	// Selection and renaming of the System //
	model=op.Selection(model, "Initial Domain", op.blk, "all", false);
	z.define(op.sel, "Initial Domain");
	
	volume_block=op.MeasureCalc(model, z.select("Initial Domain"), 3, 0);

	model=op.Group(model, "All Particles", z.select("Initial Domain"));
		
	for (i=0;i<pg.num_particles();i++)
	{
		
		if (pg.z_pos(i)<electrode_height)
		{
			// Creation of the particle //
			if (liion)
				model=op.Ellipsoide(model, "Ellipsoide "+String.valueOf(i+1), pg.x_dim(i), pg.y_dim(i), pg.z_dim(i), pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), true);
			if (lis)
				model=op.Ellipsoide(model, "Ellipsoide "+String.valueOf(i+1), (pg.x_dim(i)*1.05), (pg.y_dim(i)*1.05), (pg.z_dim(i)*1.05), pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), true);
				
			// Selection and renaming of the particle //
			model=op.Selection(model, "Particle "+String.valueOf(i+1), op.elp, "off", true);
			
			// Rotation of the particle //
			if (liion) {
				model=op.Rotation(model, "Rotate "+String.valueOf(i+1)+"x", op.sel, "x", pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), pg.x_rot(i), true);
				model=op.Rotation(model, "Rotate "+String.valueOf(i+1)+"y", op.sel, "y", pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), pg.y_rot(i), true);
				model=op.Rotation(model, "Rotate "+String.valueOf(i+1)+"z", op.sel, "z", pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), pg.z_rot(i), true);
			}
		}
	}
	
	model=op.Group(model, "Geometric Operations", z.select("Initial Domain"));
	
	// Selection of all particles //
	model=op.ComplementSelection(model, "All Particles", new String[]{z.select("Initial Domain")}, -1, true);
	
	// Cut and partition of the system through the particles //
	model=op.PartitionDomain(model, "Partition Domains", "object", z.select("Initial Domain"), 0, op.comsel, 0, 1E-8, true);
	tmp=op.pard;
	
	// Deletion of all particles //
	model=op.Delete(model, "Delete "+String.valueOf(i+1), op.comsel, -1, true);
	
	// Copy of the system, we'll obtain system 1 and 2 //
	model=op.Copy(model, "Copy", tmp, 1, "20", "0", "0", true);
	
	// Selection of Electrolyte 1 //
	model=op.BallSelection(model, "Electrolyte 1", "0", "0", "zmax/sf", "all", 3, "intersects", "off", true);
	
	// Selection of Electrode 1 //
	model=op.AdjacentSelection(model, "Electrode Block 1", new String[]{op.ballsel}, 3, 3, false, false, "off", true);
	
	// Deletion of Electrolyte 1 //
	model=op.Delete(model, "Delete Electrolyte 1", op.ballsel, 3, true);
	
	// Union of the particles //
	model=op.Union(model, "Particle Union", op.del, false, false, true);
	
	// Selection the Electrolyte 2 //
	model=op.BallSelection(model, "Electrolyte 2", "20", "0", "zmax/sf", "all", 3, "intersects", "off", true);
	
	// Selection of Electrode 2 //
	model=op.AdjacentSelection(model, "Electrode Block 2", new String[]{op.ballsel}, 3, 3, false, false, "off", true);
	
	// Deletion of Electrode 2 //
	model=op.Delete(model, "Delete Electrode 2", op.adjsel, 3, true);
	
	// Move Electrolyte 2 to the original position //
	model=op.Move(model, "Move", op.del, 3, "-20", "0", "0", true);
	
	// In this way we had obtained two object: the electrolyte-object and the particles-object. //
	// This step is necessary to avoid that COMSOL identify an interface between two particles that are in contact. //
	
	// Scaling the system from meters to micrometers //
	model=op.Scale(model, "Scaling", z.select("Initial Domain"), "sf", true);
	
	// Costruction of a solid block //
	model=op.Block(model, "Limit Block", "center", "xmax", "ymax", "zmin", "0", "0", "zmax+zmin*0.5", true);
	
	// Selection and renaming of the Limit Block //
	model=op.Selection(model, "Limit Block Selection", op.blk, "off", true);
	
	// Cut and partition of the system through the block //
	model=op.PartitionDomain(model, "System Readjustment", "object", z.select("Initial Domain"), 0, op.sel, 0, tol.cut(), true);
	
	// Selection of the block and the cut zone //
	model=op.BallSelection(model, "Cut Selection", "0", "0", "zmax+zmin*0.5", "all", 3, "intersects", "off", true);
	
	// Deletion of the block and the cut zone //
	model=op.Delete(model, "Delete Limit Block", op.ballsel, 3, true);
	
	// Costruction of the block to individuate the top surface of the particles //
	model=op.Block(model, "Current Collector Block", "center", "xmax", "ymax", "cc_thickness", "0", "0", "zmin-cc_thickness*0.5", true);
	
	// Construction of the Separator //
	model=op.Block(model, "Separator Block", "center", "xmax", "ymax", "sep_thickness", "0", "0", "zmax+sep_thickness*0.5", true);
	
	// Form the final union //
	model.component("TestCase").geom("geom1").feature("fin").set("repairtoltype", "relative");
	model.component("TestCase").geom("geom1").feature("fin").set("repairtol", tol.fin());
	model.component("TestCase").geom("geom1").run("fin");
	i=50;

	model=op.BoxSelection (model, "Remove Details 1", 3, "intersects", 
	new String[]{"-xmax/4", "xmax/4", "-ymax/4", "ymax/4","-zmax","+zmax"}, "all", "", "on", false);

	model=op.ComplementSelection(model, "Remove Details 2", new String[]{op.boxsel}, 3, false);

	model=op.AdjacentSelection(model, "Remove Details 3", new String[]{op.comsel}, 3, 2, true, true, "on", false);
	tmp=op.adjsel;

	model=op.BallSelection(model, "Remove Details 4", "0", "0", "zmin-cc_thickness", "all", 3, "intersects", "off", false);

	model=op.AdjacentSelection(model, "Remove Details 5", new String[]{op.ballsel}, 3, 2, false, true, "off", false);

	model=op.DifferenceSelection(model, "Remove Details 6",  new String[]{tmp},  new String[]{op.adjsel}, 2, false);

	model=op.IgnoreFaces(model, "Remove Details 7", op.difsel, false);

	// Selection and renaming of the different zones of the system //
	
	// Separator //
	model=op.BallSelection(model, "Separator", "0", "0", "zmax+sep_thickness*0.5", "all", 3, "intersects", "on", false);
	z.define(op.ballsel, "Separator");
	
	// Lithium Foil //
	model=op.BoxSelection (model, "Lithium Foil", 2, "allvertices", 
	new String[]{"-xmax/2", "xmax/2", "-ymax/2", "ymax/2","zmax+sep_thickness","zmax+sep_thickness"}, "all", "", "on", false);
	z.define(op.boxsel, "Lithium Foil");
	
	// Current Collector //
	model=op.BallSelection(model, "Current Collector", "0", "0", "zmin-cc_thickness", "all", 3, "intersects", "off", false);
	z.define(op.ballsel,"Current Collector");
	
	model=op.AdjacentSelection(model, "Collector Boundaries", new String[]{z.select("Current Collector")}, 3, 2, false, true, "off", false);
	z.define(op.adjsel, "Collector Boundaries");
	
	// Electrolyte //	
	model=op.BallSelection(model, "Electrolyte", "0", "0", "zmax*0.99", "all", 3, "intersects", "on", false);
	z.define(op.ballsel, "Electrolyte");
	
	model=op.AdjacentSelection(model, "Electrolyte Boundaries", new String[]{z.select("Electrolyte")}, 3, 2, false, true, "on", false);
	z.define(op.adjsel, "Electrolyte Boundaries");
	tmp=z.select("Current Collector");
	
	// Electrode //
	model=op.AdjacentSelection(model, "Electrode", new String[]{z.select("Electrolyte"), z.select("Separator"), tmp}, 3, 3, false, true, "on", false);
	z.define(op.adjsel, "Electrode");
	
	model=op.AdjacentSelection(model, "Electrode Boundaries", new String[]{z.select("Electrode")}, 3, 2, false, true, "on", false);
	z.define(op.adjsel, "Electrode Boundaries");
	
	model=op.IntersectionSelection(model, "Electrode Surface", new String[]{z.select("Electrode Boundaries"), z.select("Electrolyte Boundaries")}, 2, "on", false);
	z.define(op.intsel, "Electrode Surface");
	
	// Interface between Particles and Current Collector //
	model=op.IntersectionSelection(model, "Electrode Current Collector", 
	new String[]{z.select("Electrode Boundaries"), z.select("Collector Boundaries")}, 2, "on", false);
	z.define(op.intsel, "Electrode Current Collector");
	
	// Half Cell //
	if (liion) {
		model=op.ComplementSelection(model, "Lithium-Ion Half Cell", new String[]{z.select("Current Collector")}, 3, false);
		z.define(op.comsel, "Lithium-Ion Half Cell");
	}
	if (lis) {
		model=op.ComplementSelection(model, "Lithium-Sulfur Half Cell", new String[]{z.select("Current Collector")}, 3, false);
		z.define(op.comsel, "Lithium-Sulfur Half Cell");
	}
	
	// Electrolyte and Separator //
	model=op.ComplementSelection(model, "Electrolyte Separator", new String[]{z.select("Current Collector"), z.select("Electrode")}, 3, false);
	z.define(op.comsel, "Electrolyte Separator");

	// Measurement of the total Electrode volume and surface //
	volume_block=op.MeasureCalc(model, z.select("Electrode"), 3, 0);
	model.param("par3").set("Vp",	String.valueOf(volume_block)+" [m^3]", "Total Electrode Volume"); 
	if (lis) {
		area_block=op.MeasureCalc(model, z.select("Electrode Surface"), 2, 0);
		model.param("par3").set("Ap",	String.valueOf(area_block)+" [m^2]", "Total Electrode Surface"); 
	}

	return model;
}
	
public static Model MaterialsDefinition(Model model, Zone z, String [][] Voltage, String [][] D) throws IOException {

	Materials mt;
	mt=new Materials();
	int j=0;
	String [] T=new String[9];
	String [][] table;
	boolean default_V=false;	

	// New material: Graphite //
	model=mt.newMaterial(model, "Graphite", new String[]{"temperature", "concentration"});
	model=mt.setup(model, "Graphite", "def", 
	new String[]{"T_ref", "T2", "csmax", "soc"}, 
	new String[]{"318[K]", "min(393.15,max(T,223.15))", "31507[mol/m^3]", "c/csmax"}, false);
	
	// Graphite: Diffusion coefficient //
	T=new String[]
	{"D_int1(soc)", "0", "0",
	 "0", "D_int1(soc)", "0", 
	 "0", "0", "D_int1(soc)"};
	model=mt.setup(model, "Graphite", "def", new String[]{"diffusion"}, T, true);
	model=mt.newFunc(model, "Graphite", "def", "D_int1", D, "piecewisecubic", "linear", "m^2/s", "");

	// Graphite: Electrical conductivity //
	T=new String[]
	{"100[S/m]", "0", "0",
	 "0", "100[S/m]", "0",
	  "0", "0", "100[S/m]"};
	model=mt.setup(model, "Graphite", "def", new String[]{"electricconductivity"}, T, true);

	// Graphite: Equilibrium potential //
	model=mt.newProperty(model, "Graphite", "ElectrodePotential", "Equilibrium potential", new String[]{"temperature"});
	model=mt.setup(model, "Graphite", "ElectrodePotential", new String[]{"Eeq", "cEeqref", "soc", "dEeqdT"}, new String[]{"Eeq_int1(soc)", "31507[mol/m^3]", "c/csmax", "0"}, false);
	model=mt.newFunc(model, "Graphite", "ElectrodePotential", "Eeq_int1", Voltage, "piecewisecubic", "linear", "V", "");
	
	// Graphite: SOC definition //
	model=mt.newProperty(model, "Graphite", "OperationalSOC", "Operational electrode state-of-charge", new String[]{"none"});
	model=mt.setup(model, "Graphite", "OperationalSOC", new String[]{"socmax", "socmin"}, new String[]{"0.98", "0.0"}, false);

	// Graphite: Geometry selection //
	model=mt.geomSelection(model, "Graphite", "geom1_"+z.select("Electrode"));

	// New material: Lithium //
	model=mt.newMaterial(model, "Lithium", new String[]{"none"});
	
	// Lithium: Electrical conductivity //
	T=new String[]
	{"1/(92.8[n\u03a9*m])", "0", "0", 
	"0", "1/(92.8[n\u03a9*m])", "0", 
	"0", "0", "1/(92.8[n\u03a9*m])"};
	model=mt.setup(model, "Lithium", "def", new String[]{"electricconductivity"}, T, true);
	
	// Lithium: Equilibrium potential //
	model=mt.newProperty(model, "Lithium", "ElectrodePotential", "Equilibrium potential", new String[]{"none"});
	model=mt.setup(model, "Lithium", "ElectrodePotential", 
	new String[]{"Eeq", "dEeqdT", "cEeqref"}, 
	new String[]{"0[V]", "0[V/K]", "0[M]"}, false);
	
	// Lithium: Geometry selection //
	model=mt.geomSelection(model, "Lithium", "geom1_"+z.select("Lithium Foil"));

	
	// New material: Electrolyte //
	model=mt.newMaterial(model, "Electrolyte", new String[]{"temperature", "concentration"});
	model=mt.setup(model, "Electrolyte", "def", 
	new String[]{"T_ref", "T2"}, 
	new String[]{"298[K]", "min(393.15,max(T,223.15))"}, false);
	
	// Electrolyte: Diffusion coefficient //
	table=new String[][]
	{{"200", "3.9e-10/(1-200*59e-6)"}, 
	{"500", "4.12e-10/(1-500*59e-6)"}, 
	{"800", "4e-10/(1-800*59e-6)"}, 
	{"1000", "3.8e-10/(1-1000*59e-6)"}, 
	{"1200", "3.50e-10/(1-1200*59e-6)"}, 
	{"1600", "2.68e-10/(1-1600*59e-6)"}, 
	{"2000", "1.9e-10/(1-2000*59e-6)"}};
	T=new String[]
	{"DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", 
	"0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", 
	"0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))"};
	model=mt.newFunc(model, "Electrolyte", "def", "DL_int1", table, "piecewisecubic", "linear", "m^2/s", "");
	model=mt.setup(model, "Electrolyte", "def", new String[]{"diffusion"}, T, true);

	// Electrolyte: Electrolyte conductivity //
	table=new String[][]
	{{"0", "1e-6"}, 
	{"200", "0.455"}, 
	{"500", "0.783"}, 
	{"800", "0.935"}, 
	{"1000", "0.95"}, 
	{"1200", "0.927"}, 
	{"1600", "0.78"}, 
	{"2000", "0.60"}, 
	{"2200", "0.515"}};
	T=new String[]
	{"sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", 
	"0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", 
	"0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))"};
	model=mt.newProperty(model, "Electrolyte", "ElectrolyteConductivity", "Electrolyte conductivity", new String[]{"temperature", "concentration"});
	model=mt.newFunc(model, "Electrolyte", "ElectrolyteConductivity", "sigmal_int1", table, "piecewisecubic", "linear", "S/m", "");
	model=mt.setup(model, "Electrolyte", "ElectrolyteConductivity", new String[]{"sigmal"}, T, true);
	model=mt.setup(model, "Electrolyte", "ElectrolyteConductivity", 
	new String[]{"T_ref2", "T3"},
	new String[]{"298[K]", "min(393.15,max(T,223.15))"}, false);

	// Electrolyte: Species Properties //
	model=mt.newProperty(model, "Electrolyte", "SpeciesProperties", "Species Properties", new String[]{"concentration"});
	model=mt.setup(model, "Electrolyte", "SpeciesProperties", 
	new String[]{"transpNum", "fcl"},
	new String[]{"0.363", "1"}, false);

	// Electrolyte: Geometry selection //
	model=mt.geomSelection(model, "Electrolyte", "geom1_"+z.select("Electrolyte Separator"));

	return model;
}

public static Model PhysicsDefinition(Model model, Zone z, boolean liion, boolean lis) {
	
	if (liion) {

		boolean CBD=false;
	
		// Lithium Ion Battery Model //
		model.component("TestCase").physics().create("liion", "LithiumIonBatteryMPH", "geom1");
		model.component("TestCase").physics("liion").selection().named("geom1_"+z.select("Lithium-Ion Half Cell"));
		model.component("TestCase").physics("liion").feature("init1").set("phis", "mat1.elpot.Eeq_int1(cs0/csmax)");
		model.component("TestCase").physics("liion").feature("init1").set("cl",   "cl0");
		model.component("TestCase").physics("liion").label("Lithium-Ion Half Cell");

		// Lithium transport into the electrode //
		model.component("TestCase").physics().create("tds", "DilutedSpecies", new String[][]{{"c"}});
		model.component("TestCase").physics("tds").selection().named("geom1_"+z.select("Electrode"));
		model.component("TestCase").physics("tds").feature("init1").setIndex("initc", "cs0", 0);
		model.component("TestCase").physics("tds").prop("TransportMechanism").set("Convection", false);
		model.component("TestCase").physics("tds").create("eeic1", "ElectrodeElectrolyteInterfaceCoupling", 2);
		model.component("TestCase").physics("tds").feature("eeic1").selection().named("geom1_"+z.select("Electrode Surface"));
		model.component("TestCase").physics("tds").feature("eeic1").feature("rc1").setIndex("Vib", 1, 0);
		model.component("TestCase").physics("tds").feature("cdm1").set("DiffusionMaterialList", "mat1");
		model.component("TestCase").physics("tds").feature("cdm1").set("D_c_mat", "def");
		model.component("TestCase").physics("tds").label("Lithium in Electrode");

		// Electrode //
		model.component("TestCase").physics("liion").create("el1", "Electrode", 3);
		model.component("TestCase").physics("liion").feature("el1").selection().named("geom1_"+z.select("Electrode"));
		model.component("TestCase").physics("liion").feature("el1").set("sigma_mat", "userdef");
		model.component("TestCase").physics("liion").feature("el1").set("sigma", new int[]{100, 0, 0, 0, 100, 0, 0, 0, 100});
		model.component("TestCase").physics("liion").feature("el1").label("Electrode");
		
		// Electrode Current Collector interface //
		model.component("TestCase").physics("liion").create("ec1", "ElectrodeCurrent", 2);
		model.component("TestCase").physics("liion").feature("ec1").selection().named("geom1_"+z.select("Electrode Current Collector"));
		model.component("TestCase").physics("liion").feature("ec1").set("Its", "-I_C");
		model.component("TestCase").physics("liion").feature("ec1").set("phis0init", "mat1.elpot.Eeq_int1(cs0/csmax)");
		model.component("TestCase").physics("liion").feature("ec1").label("Electrode-CC");
		
		// 
		if(CBD)
		{
			// Carbon Binder //
			model.component("TestCase").physics("liion").create("pcb1", "PorousConductiveBinder", 3);
			model.component("TestCase").physics("liion").feature("pcb1").selection().named("geom1_"+z.select("Electrolyte"));
			model.component("TestCase").physics("liion").feature("pcb1").set("ElectrolyteMaterial", "mat3");
			model.component("TestCase").physics("liion").feature("pcb1").set("sigma_mat", "userdef");
			model.component("TestCase").physics("liion").feature("pcb1").set("sigma", new String[]{"sigma_s", "0", "0", "0", "sigma_s", "0", "0", "0", "sigma_s"});
			model.component("TestCase").physics("liion").feature("pcb1").set("epss", "eps_s_b");
			model.component("TestCase").physics("liion").feature("pcb1").set("epsl", "eps_l_b");
			model.component("TestCase").physics("liion").feature("pcb1").set("ElectricCorrModel", "NoCorr");
			model.component("TestCase").physics("liion").feature("pcb1").label("Electrolyte + CBD");
		}
		
		// Electrode-Electrolyte interface //
		model.component("TestCase").physics("liion").create("bei1", "InternalElectrodeSurface", 2);
		model.component("TestCase").physics("liion").feature("bei1").selection().named("geom1_"+z.select("Electrode Surface"));
		model.component("TestCase").physics("liion").feature("bei1").feature("er1").set("minput_concentration", "c");
		model.component("TestCase").physics("liion").feature("bei1").feature("er1").set("MaterialOption", "mat1");
		model.component("TestCase").physics("liion").feature("bei1").feature("er1").set("ElectrodeKinetics", "LithiumInsertion");
		model.component("TestCase").physics("liion").feature("bei1").feature("er1").set("i0_ref", "i0_ref_Graphite [A/m^2]");
		model.component("TestCase").physics("liion").feature("bei1").label("Electrode/Electrolyte Interface");

		// Separator //
		model.component("TestCase").physics("liion").create("sep1", "Separator", 3);
		model.component("TestCase").physics("liion").feature("sep1").selection().named("geom1_"+z.select("Separator"));
		model.component("TestCase").physics("liion").feature("sep1").set("ElectrolyteMaterial", "mat3");
		model.component("TestCase").physics("liion").feature("sep1").label("Separator");

		// Lithium electrode //
		model.component("TestCase").physics("liion").create("es1", "ElectrodeSurface", 2);
		model.component("TestCase").physics("liion").feature("es1").selection().named("geom1_"+z.select("Lithium Foil"));
		model.component("TestCase").physics("liion").feature("es1").feature("er1").set("i0_ref", "i0_ref_Li");
		model.component("TestCase").physics("liion").feature("es1").label("Lithium Foil");

		model.component("TestCase").physics("tds").feature("eeic1").feature("rc1").set("iloc_src", "root.TestCase.liion.bei1.er1.iloc");
	}

	if (lis) {

		// Tertiary Current Distribution, Nernst-Planck Model // 
		model.component("TestCase").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", new String[][]{{"S8", "S8_2m", "S6_2m", "S4_2m", "S2_2m", "S_2m", "Li_1p", "A_1m"}});
		model.component("TestCase").physics("tcd").label("Lithium-Sulfur Half Cell");
		model.component("TestCase").physics("tcd").selection().named("geom1_"+z.select("Lithium-Sulfur Half Cell"));
		model.component("TestCase").physics("tcd").prop("SpeciesProperties").set("FromElectroneutrality", 8);

		// Species Charges //
		model.component("TestCase").physics("tcd").feature("sp1").label("Species Charges");
		model.component("TestCase").physics("tcd").feature("sp1").setIndex("z", -2, 1);
		model.component("TestCase").physics("tcd").feature("sp1").setIndex("z", -2, 2);
		model.component("TestCase").physics("tcd").feature("sp1").setIndex("z", -2, 3);
		model.component("TestCase").physics("tcd").feature("sp1").setIndex("z", -2, 4);
		model.component("TestCase").physics("tcd").feature("sp1").setIndex("z", -2, 5);
		model.component("TestCase").physics("tcd").feature("sp1").setIndex("z", 1, 6);
		model.component("TestCase").physics("tcd").feature("sp1").setIndex("z", -1, 7);

		// Electrolyte //
		model.component("TestCase").physics("tcd").feature("ice1").label("Electrolyte");
		model.component("TestCase").physics("tcd").feature("ice1").set("D_S8", new String[]{"D_S8", "0", "0", "0", "D_S8", "0", "0", "0", "D_S8"});
		model.component("TestCase").physics("tcd").feature("ice1").set("D_S8_2m", new String[]{"D_S8_2m", "0", "0", "0", "D_S8_2m", "0", "0", "0", "D_S8_2m"});
		model.component("TestCase").physics("tcd").feature("ice1").set("D_S6_2m", new String[]{"D_S6_2m", "0", "0", "0", "D_S6_2m", "0", "0", "0", "D_S6_2m"});
		model.component("TestCase").physics("tcd").feature("ice1").set("D_S4_2m", new String[]{"D_S4_2m", "0", "0", "0", "D_S4_2m", "0", "0", "0", "D_S4_2m"});
		model.component("TestCase").physics("tcd").feature("ice1").set("D_S2_2m", new String[]{"D_S2_2m", "0", "0", "0", "D_S2_2m", "0", "0", "0", "D_S2_2m"});
		model.component("TestCase").physics("tcd").feature("ice1").set("D_S_2m", new String[]{"D_S_2m", "0", "0", "0", "D_S_2m", "0", "0", "0", "D_S_2m"});
		model.component("TestCase").physics("tcd").feature("ice1").set("D_Li_1p", new String[]{"D_Li_1p", "0", "0", "0", "D_Li_1p", "0", "0", "0", "D_Li_1p"});
		model.component("TestCase").physics("tcd").feature("ice1").set("D_A_1m", new String[]{"D_A_1m", "0", "0", "0", "D_A_1m", "0", "0", "0", "D_A_1m"});

		// Initial Values Electrolyte //
		model.component("TestCase").physics("tcd").feature("init1").label("Initial Values Electrolyte");
		model.component("TestCase").physics("tcd").feature("init1").setIndex("initc", "c_Li_1p_ref", 6);
		model.component("TestCase").physics("tcd").feature("init1").set("initphis", "E0");

		// Initial Values Electrode //
		model.component("TestCase").physics("tcd").create("init2", "init", 3);
		model.component("TestCase").physics("tcd").feature("init2").label("Initial Values Electrode");
		model.component("TestCase").physics("tcd").feature("init2").selection().set(3);
		model.component("TestCase").physics("tcd").feature("init2").selection().named("geom1_"+z.select("Electrode"));
		model.component("TestCase").physics("tcd").feature("init2").setIndex("initc", "c_S8_ref", 0);
		model.component("TestCase").physics("tcd").feature("init2").setIndex("initc", "c_S8_2m_ref", 1);
		model.component("TestCase").physics("tcd").feature("init2").setIndex("initc", "c_S6_2m_ref", 2);
		model.component("TestCase").physics("tcd").feature("init2").setIndex("initc", "c_S4_2m_ref", 3);
		model.component("TestCase").physics("tcd").feature("init2").setIndex("initc", "c_S2_2m_ref", 4);
		model.component("TestCase").physics("tcd").feature("init2").setIndex("initc", "c_S_2m_ref", 5);
		model.component("TestCase").physics("tcd").feature("init2").set("initphis", "E0");

		// Electrode //		
		model.component("TestCase").physics("tcd").create("ece1", "Electrode", 3);
		model.component("TestCase").physics("tcd").feature("ece1").label("Sulfur");
		model.component("TestCase").physics("tcd").feature("ece1").selection().named("geom1_"+z.select("Electrode"));
		model.component("TestCase").physics("tcd").feature("ece1").set("sigma_mat", "userdef");
		model.component("TestCase").physics("tcd").feature("ece1").set("sigma", new String[]{"sigma_s", "0", "0", "0", "sigma_s", "0", "0", "0", "sigma_s"});

		// Electrode Current Collector interface //
		model.component("TestCase").physics("tcd").create("ec1", "ElectrodeCurrent", 2);
		model.component("TestCase").physics("tcd").feature("ec1").label("Sulfur-CC");
		model.component("TestCase").physics("tcd").feature("ec1").selection().named("geom1_"+z.select("Electrode Current Collector"));
		model.component("TestCase").physics("tcd").feature("ec1").set("Its", "-i_Crate");
		model.component("TestCase").physics("tcd").feature("ec1").set("phis0init", "E0");

		// Electrode-Electrolyte interface //
		model.component("TestCase").physics("tcd").create("bei1", "InternalElectrodeSurface", 2);
		model.component("TestCase").physics("tcd").feature("bei1").label("Electrode-Electrolyte Interface");
		model.component("TestCase").physics("tcd").feature("bei1").selection().named("geom1_"+z.select("Electrode Surface"));
		model.component("TestCase").physics("tcd").feature("bei1").set("Species", new String[][]{{"Li2S_s"}, {"S8_s"}});
		model.component("TestCase").physics("tcd").feature("bei1").set("rhos", new String[][]{{"rho_Li2S_s"}, {"rho_S8_s"}});
		model.component("TestCase").physics("tcd").feature("bei1").set("Ms", new String[][]{{"M_Li2S_s"}, {"M_S8_s"}});
		// Electrochemical and Dissolution/Precipitation reactions //
		// 1/2 S8 + e_m --> 1/2 S8_2m //
		model.component("TestCase").physics("tcd").feature("bei1").feature("er1").selection().named("geom1_"+z.select("Electrode Surface"));
		model.component("TestCase").physics("tcd").feature("bei1").feature("er1").setIndex("Vi0", "-1/2", 0);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er1").setIndex("Vi0", "1/2", 1);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er1").set("Eeq_ref", "Eeq_1_ref");
		model.component("TestCase").physics("tcd").feature("bei1").feature("er1").set("i0_ref", "i0_1_ref");
		// 3/2 S8_2m + e_m --> 2 S6_2m //
		model.component("TestCase").physics("tcd").feature("bei1").create("er2", "ElectrodeReaction", 2);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er2").selection().named("geom1_"+z.select("Electrode Surface"));
		model.component("TestCase").physics("tcd").feature("bei1").feature("er2").setIndex("Vi0", "-3/2", 1);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er2").setIndex("Vi0", 2, 2);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er2").set("Eeq_ref", "Eeq_2_ref");
		model.component("TestCase").physics("tcd").feature("bei1").feature("er2").set("i0_ref", "i0_2_ref");
		// S6_2m + e_m --> 3/2 S4_2m //
		model.component("TestCase").physics("tcd").feature("bei1").create("er3", "ElectrodeReaction", 2);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er3").selection().named("geom1_"+z.select("Electrode Surface"));
		model.component("TestCase").physics("tcd").feature("bei1").feature("er3").setIndex("Vi0", -1, 2);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er3").setIndex("Vi0", "3/2", 3);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er3").set("Eeq_ref", "Eeq_3_ref");
		model.component("TestCase").physics("tcd").feature("bei1").feature("er3").set("i0_ref", "i0_3_ref");
		// 1/2 S4_2m + e_m --> S2_2m //
		model.component("TestCase").physics("tcd").feature("bei1").create("er4", "ElectrodeReaction", 2);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er4").selection().named("geom1_"+z.select("Electrode Surface"));
		model.component("TestCase").physics("tcd").feature("bei1").feature("er4").setIndex("Vi0", "-1/2", 3);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er4").setIndex("Vi0", 1, 4);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er4").set("Eeq_ref", "Eeq_4_ref");
		model.component("TestCase").physics("tcd").feature("bei1").feature("er4").set("i0_ref", "i0_4_ref");
		// 1/2 S2_2m + e_m --> S_2m //
		model.component("TestCase").physics("tcd").feature("bei1").create("er5", "ElectrodeReaction", 2);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er5").selection().named("geom1_"+z.select("Electrode Surface"));
		model.component("TestCase").physics("tcd").feature("bei1").feature("er5").setIndex("Vi0", "-1/2", 4);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er5").setIndex("Vi0", 1, 5);
		model.component("TestCase").physics("tcd").feature("bei1").feature("er5").set("Eeq_ref", "Eeq_5_ref");
		model.component("TestCase").physics("tcd").feature("bei1").feature("er5").set("i0_ref", "i0_5_ref");
		// Li_1p + S_2m --> Li2S(s) //
		model.component("TestCase").physics("tcd").feature("bei1").create("nfr1", "NonFaradaicReactions", 2);
		model.component("TestCase").physics("tcd").feature("bei1").feature("nfr1").label("Non-Faradaic Reactions - Li2S(s)");
		model.component("TestCase").physics("tcd").feature("bei1").feature("nfr1").selection().named("geom1_"+z.select("Electrode Surface"));
		model.component("TestCase").physics("tcd").feature("bei1").feature("nfr1").setIndex("species", true, 5);
		model.component("TestCase").physics("tcd").feature("bei1").feature("nfr1").setIndex("J0", "-R_Li2S_s", 5);
		model.component("TestCase").physics("tcd").feature("bei1").feature("nfr1").setIndex("species", true, 6);
		model.component("TestCase").physics("tcd").feature("bei1").feature("nfr1").setIndex("J0", "-2*R_Li2S_s", 6);
		model.component("TestCase").physics("tcd").feature("bei1").feature("nfr1").setIndex("R", "R_Li2S_s", 0, 0);
		// S8(s) --> S8 //
		model.component("TestCase").physics("tcd").feature("bei1").create("nfr2", "NonFaradaicReactions", 2);
		model.component("TestCase").physics("tcd").feature("bei1").feature("nfr2").label("Non-Faradaic Reactions - S8(s)");
		model.component("TestCase").physics("tcd").feature("bei1").feature("nfr2").selection().named("geom1_"+z.select("Electrode Surface"));
		model.component("TestCase").physics("tcd").feature("bei1").feature("nfr2").setIndex("species", true, 0);
		model.component("TestCase").physics("tcd").feature("bei1").feature("nfr2").setIndex("J0", "-R_S8_s", 0);
		model.component("TestCase").physics("tcd").feature("bei1").feature("nfr2").setIndex("R", "R_S8_s", 1, 0);


		// Separator //
		model.component("TestCase").physics("tcd").create("sep1", "Separator", 3);
		model.component("TestCase").physics("tcd").feature("sep1").label("Separator");
		model.component("TestCase").physics("tcd").feature("sep1").selection().named("geom1_"+z.select("Separator"));
		model.component("TestCase").physics("tcd").feature("sep1").set("D_S8", new String[]{"D_S8", "0", "0", "0", "D_S8", "0", "0", "0", "D_S8"});
		model.component("TestCase").physics("tcd").feature("sep1").set("D_S8_2m", new String[]{"D_S8_2m", "0", "0", "0", "D_S8_2m", "0", "0", "0", "D_S8_2m"});
		model.component("TestCase").physics("tcd").feature("sep1").set("D_S6_2m", new String[]{"D_S6_2m", "0", "0", "0", "D_S6_2m", "0", "0", "0", "D_S6_2m"});
		model.component("TestCase").physics("tcd").feature("sep1").set("D_S4_2m", new String[]{"D_S4_2m", "0", "0", "0", "D_S4_2m", "0", "0", "0", "D_S4_2m"});
		model.component("TestCase").physics("tcd").feature("sep1").set("D_S2_2m", new String[]{"D_S2_2m", "0", "0", "0", "D_S2_2m", "0", "0", "0", "D_S2_2m"});
		model.component("TestCase").physics("tcd").feature("sep1").set("D_S_2m", new String[]{"D_S_2m", "0", "0", "0", "D_S_2m", "0", "0", "0", "D_S_2m"});
		model.component("TestCase").physics("tcd").feature("sep1").set("D_Li_1p", new String[]{"D_Li_1p", "0", "0", "0", "D_Li_1p", "0", "0", "0", "D_Li_1p"});
		model.component("TestCase").physics("tcd").feature("sep1").set("D_A_1m", new String[]{"D_A_1m", "0", "0", "0", "D_A_1m", "0", "0", "0", "D_A_1m"});
		model.component("TestCase").physics("tcd").feature("sep1").set("epsl", 0.4);

		// Lithium electrode //
		model.component("TestCase").physics("tcd").create("es1", "ElectrodeSurface", 2);
		model.component("TestCase").physics("tcd").feature("es1").label("Lithium Foil");
		model.component("TestCase").physics("tcd").feature("es1").selection().named("geom1_"+z.select("Lithium Foil"));
		// Li --> Li_1p + e_m //
		model.component("TestCase").physics("tcd").feature("es1").feature("er1").selection().named("geom1_"+z.select("Lithium Foil"));
		model.component("TestCase").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 6);
		model.component("TestCase").physics("tcd").feature("es1").feature("er1").set("Eeq_ref", "Eeq_Li_ref");
		model.component("TestCase").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0_Li_ref");
	}

	return model;
}

public static Model MeshConstruction(Model model, Zone z, boolean liion, boolean lis) {
	
	model.component("TestCase").mesh().create("mesh1");
	
	// General mesh features //
	model.component("TestCase").mesh("mesh1").feature("size").set("custom", true);
	model.component("TestCase").mesh("mesh1").feature("size").set("hmax", "hmax");
	model.component("TestCase").mesh("mesh1").feature("size").set("hmin", "hmin");
	model.component("TestCase").mesh("mesh1").feature("size").set("hgrad", 1.5);
	model.component("TestCase").mesh("mesh1").feature("size").set("hcurve", 0.6);
	model.component("TestCase").mesh("mesh1").feature("size").set("hnarrow", 0.5);
	
	// Electrolyte mesh features //
	model.component("TestCase").mesh("mesh1").create("siz1", "Size");
	model.component("TestCase").mesh("mesh1").feature("siz1").label("Electrolyte");
	model.component("TestCase").mesh("mesh1").feature("siz1").selection().named("geom1_"+z.select("Electrolyte"));
	model.component("TestCase").mesh("mesh1").feature("siz1").set("custom", true);
	model.component("TestCase").mesh("mesh1").feature("siz1").set("hmax", "hmax");
	model.component("TestCase").mesh("mesh1").feature("siz1").set("hmin", "hmin");
	model.component("TestCase").mesh("mesh1").feature("siz1").set("hgrad", 1.5);
	model.component("TestCase").mesh("mesh1").feature("siz1").set("hcurve", 0.6);
	model.component("TestCase").mesh("mesh1").feature("siz1").set("hnarrow", 0.5);
	
	model.component("TestCase").mesh("mesh1").create("siz2", "Size");
	model.component("TestCase").mesh("mesh1").feature("siz2").label("Electrode");
	model.component("TestCase").mesh("mesh1").feature("siz2").selection().named("geom1_"+z.select("Electrode"));
	model.component("TestCase").mesh("mesh1").feature("siz2").set("custom", true);
	model.component("TestCase").mesh("mesh1").feature("siz2").set("hmax", "hmax");
	model.component("TestCase").mesh("mesh1").feature("siz2").set("hmin", "hmin");
	model.component("TestCase").mesh("mesh1").feature("siz2").set("hgrad", 1.5);
	model.component("TestCase").mesh("mesh1").feature("siz2").set("hcurve", 0.6);
	model.component("TestCase").mesh("mesh1").feature("siz2").set("hnarrow", 0.5);
	
	model.component("TestCase").mesh("mesh1").create("ftet1", "FreeTet");
	model.component("TestCase").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
	if (liion)
		model.component("TestCase").mesh("mesh1").feature("ftet1").selection().named("geom1_"+z.select("Lithium-Ion Half Cell"));
	if (lis)
		model.component("TestCase").mesh("mesh1").feature("ftet1").selection().named("geom1_"+z.select("Lithium-Sulfur Half Cell"));
	
	return model;
}

public static Model TestStudy(Model  model, String time, Tolerance tol, boolean liion, boolean lis) {

	model.component("TestCase").view("view1").hideObjects().create("hide1");
	model.component("TestCase").view("view1").hideObjects("hide1").init(3);
	model.component("TestCase").view("view1").hideObjects("hide1").add("fin", 1);
	
	model.component("TestCase").probe().create("var1", "GlobalVariable");
	model.component("TestCase").probe("var1").label("Global Variable Probe - E cell");
	if (liion)
		model.component("TestCase").probe("var1").set("expr", "liion.phis0_ec1");
	if (lis)
		model.component("TestCase").probe("var1").set("expr", "phis");
	model.component("TestCase").probe("var1").set("descractive", true);
	model.component("TestCase").probe("var1").set("descr", "Cell voltage");
	
	model.study().create("std1");
	
	model.study("std1").create("cdi", "CurrentDistributionInitialization");
	model.study("std1").feature("cdi").set("solnum", "auto");
	model.study("std1").feature("cdi").set("notsolnum", "auto");
	if (liion) {
		model.study("std1").feature("cdi").setSolveFor("/physics/liion", true);
		model.study("std1").feature("cdi").setSolveFor("/physics/tds", false);
	}
	if (lis)
		model.study("std1").feature("cdi").setSolveFor("/physics/tcd", true);
	model.study("std1").feature("cdi").set("initType", "secondary");

	model.study("std1").create("time", "Transient");
	model.study("std1").feature("time").set("initialtime", "0");
	model.study("std1").feature("time").set("solnum", "auto");
	model.study("std1").feature("time").set("notsolnum", "auto");
	if (liion) {
		model.study("std1").feature("time").setSolveFor("/physics/liion", true);
		model.study("std1").feature("time").setSolveFor("/physics/tds", true);
	}
	if (lis)
		model.study("std1").feature("time").setSolveFor("/physics/tcd", true);
	model.study("std1").feature("time").set("tunit", "h");
	model.study("std1").feature("time").set("usertol", false);
	model.study("std1").feature("time").set("tlist", time);
	
	return model;
}

}
//ENDFILE
