// -------------- GRAPHITE HALF CELL MODEL -------------- //

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

import com.comsol.model.*;
import com.comsol.model.util.*;


class ParticlesGeometry
{
	private int numparticles;	public int num_particles() {return numparticles;}
	private double [] pos_x;	public double x_dim(int index) {return dim_x[index];}
	private double [] pos_y;	public double y_dim(int index) {return dim_y[index];}
	private double [] pos_z;	public double z_dim(int index) {return dim_z[index];}
	private double [] dim_x;	public double x_pos(int index) {return pos_x[index];}
	private double [] dim_y;	public double y_pos(int index) {return pos_y[index];}
	private double [] dim_z;	public double z_pos(int index) {return pos_z[index];}
	private double [] rot_x;	public double x_rot(int index) {return rot_x[index];}
	private double [] rot_y;	public double y_rot(int index) {return rot_y[index];}
	private double [] rot_z;	public double z_rot(int index) {return rot_z[index];}
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
	public String dif="dif";			private int dif_c=1;
	public String elp="elp";			private int elp_c=1;
	public String grp="grp";			private int grp_c=1;
	public String imp="imp";			private int imp_c=1;
	public String intsel="intsel";		private int intsel_c=1;
	public String mpart="mpart";		private int mpart_c=1;
	public String move="move";			private int move_c=1;
	public String pard="pard";			private int pard_c=1;
	public String rot="rot";			private int rot_c=1;
	public String sca="sca";			private int sca_c=1;
	public String sel="sel";			private int sel_c=1;
	public String uni="uni";			private int uni_c=1;

	public Model AdjacentSelection (Model model, String label, String [] name, int entitydim, int outputdim, boolean interior, boolean exterior, String selshow, boolean add) {return model;}
	public Model BallSelection(Model model, String label, String posx, String posy, String posz, String inputent, int entitydim, String condition, String selshow, boolean add) {return model;}
	public Model Block(Model model, String label, String base, String sizex, String sizey, String sizez, String posx, String posy, String posz, boolean add) {return model;}
	public Model BoxSelection (Model model, String label, int entitydim, String condition, String [] x, String inputent, String name, String selshow, boolean add) {return model;}
	public Model ComplementSelection (Model model, String label, String [] name, int entitydim, boolean add) {return model;}
	public Model Copy (Model model, String label, String name, int type, String displx, String disply, String displz, boolean add) {return model;}
	public Model Delete (Model  model, String label, String name, int type, boolean add) {return model;}
	public Model Ellipsoide (Model model, String label, double x1, double y1, double z1, double x2, double y2, double z2, boolean add) {return model;}
	public Model Group (Model model, String label, String place) {return model;}
	public Model Import (Model model, String label, String stl, boolean formsolid, double tol, boolean add) {return model;}
	public Model IntersectionSelection (Model model, String label, String [] name, int entitydim, String selshow, boolean add) {return model;}
	public Model Move (Model model, String label, String name, int type, String displx, String disply, String displz, boolean add) {return model;}
	public Model PartitionDomain (Model model, String label, String partwith, String name1, int type1, String name2, int type2, double tol, boolean add) {return model;}
	public Model Rotation (Model model, String label, String name, String ax, double x, double y, double z, double angle, boolean add) {return model;}
	public Model Scale (Model model, String lable, String name, String ScaleFactor, boolean add) {return model;}
	public Model Selection (Model model, String label, String name, String selshow, boolean add) {return model;}
	public Model Union (Model model, String label, String name, boolean keep, boolean intbnd, boolean add) {return model;}
	public double VolumeCalc (Model model, String name, int level, int type) {return 0;}
}

public class Script{

public static void main(String[] args) {Model model = run();}

public static Model run()
{

	// Model creation //
	Model model = ModelUtil.create("Model");
	model.modelPath("/home/alombardo/Desktop/COMSOL/SCRIPT");
	model.component().create("TestCase", true);
	
	// Time intevall //
	String time="range(0,0.25,1.5)";
	
	// Definition of the tollerances //
	Tolerance tol;
	tol = new Tolerance();
	
	// Particles details //
	ParticlesGeometry pg;
	pg = new ParticlesGeometry();
	
	// Zones definition //
	Zone z;
	z=new Zone();

	// Parameters //
	model = ParameterValues(model);

	// Geometry //
	model = GeometryConstruction(model, z, pg, tol);

	// Materials //
	model = MaterialsDefinition(model, z);

	// Physics //
	model = PhysicsDefinition(model, z);
	
	// Mesh //
	model = MeshConstruction(model, z);
	
	// Definizione del caso di studio //
	model = TestStudy(model, time, tol);
	
	return model;
	
}

public static Model ParameterValues(Model model){
		
	// Chemico-physical parameters //
	model.param().label("Chemico-physical parameters ");
	model.param().set("F", "96500[C/mol]", "Faraday Constant");
	model.param().set("eps_l_b", "0.9", "Electrolyte volume fraction in binder");
	model.param().set("eps_s_b", "0.1", "Carbon filler volume fraction in binder");
	model.param().set("csmax", "31507[mol/m^3]", "Maximum lithium concentration in particle");
	model.param().set("socmax", "0.98", "Maximum particle lithiation level");
	model.param().set("socmin", "0", "Minimum particle lithiation level");
	model.param().set("socinit", "socmin", "Initial lithiation level");	  
	model.param().set("sigma_s", "1[S/m]", "Porous conductive binder conductivity");
	model.param().set("i0_ref_Graphite", "10", "Reference exchange current density for Lihtium intercalation into graphite");
	model.param().set("i0_ref_Li", "100", "Reference exchange current density for lithium deposition/dissolution");

	// Operative Conditions //
	model.param().create("par2");
	model.param("par2").label("Operative Conditions");
	model.param("par2").set("C_rate", "0.1[1/h]", "Discharge C rate");
	model.param("par2").set("I_C", "csmax*(socmax-socmin)*F*Vp*C_rate", "Discharge current");
	model.param("par2").set("cs0", "csmax*socinit", "Initial lithium concentration in particles");
	model.param("par2").set("cl0", "1000[mol/m^3]", "Initial lithium concentration in the electrolyte");

	// Geometric details //
	model.param().create("par3");
	model.param("par3").label("Geometric details");
	model.param("par3").set("hmin",	"9.25E-8[m]", "Min mesh size");
	model.param("par3").set("hmax",	   "2E-6[m]", "Max mesh size");
	model.param("par3").set("xmin",	  " 0E-6[m]", "Electrode min x");
	model.param("par3").set("xmax",	  "30E-6[m]", "Electrode max x");
	model.param("par3").set("ymin",	  " 0E-6[m]", "Electrode min y");
	model.param("par3").set("ymax",	  "30E-6[m]", "Electrode max y");
	model.param("par3").set("zmin",	  " 5E-6[m]", "Electrode min z");
	model.param("par3").set("zmax",	  "30E-6[m]", "Electrode max z");
	model.param("par3").set("sep_thickness", "10E-6[m]", "Separator thickness");
	model.param("par3").set("cc_thickness", "5E-6[m]", "Current Collector thickness");
	
	return model;
}

public static Model GeometryConstruction(Model model, Zone z, ParticlesGeometry pg, Tolerance tol) {
		

	int i=1;
	int b=0;
	int d=1;
	
	double volume_pard;
	double volume_block;
	double volume_particle;
	double volume_pard1;
	double volume_pard2;
	
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
	
	String filepath="/home/alombardo/Desktop/COMSOL/STL/eli/";
	String stl=filepath;
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
	model=op.Block(model, "Block 0", "center", "xmax*0.2*10^6", "ymax*0.2*10^6", "zmax*0.2*10^6", "0", "0", "(zmin+zmax*0.5)*0.2*10^6", false);
	tmp=op.blk;
	
	// Selection and renaming of the System //
	model=op.Selection(model, "Initial Domain", op.blk, "all", false);
	z.define(op.sel, "Initial Domain");
	
	volume_block=op.VolumeCalc(model, z.select("Initial Domain"), 3, 0);
	
	if(CBD)
	{
		model=op.Group(model, "Carbon Binder", z.select("Initial Domain"));
		
		// Import of the Carbon-Binder (in .stl format) //
		model=op.Import(model, "Import Binder", filepath+"Binder.stl", formsolid, tol.imp(), true);
		
		// Selection and renaming of the Carbon-Binder //		
		model=op.Selection(model, "Carbon Binder", op.imp, "all", true);
		
		// Cut and partition of the system through the Carbon-Binder //
		model=op.PartitionDomain(model, "Cut CBD", "face", z.select("Initial Domain"), 0, op.sel, 0, tol.cut(), true);
		
		// Deletion of the surface //
		model=op.Delete(model, "Delete CBD", op.sel, -1, true);
	}
	
	if(all_particles)
	{
		model=op.Group(model, "All Particles", z.select("Initial Domain"));
		
		for (i=0;i<pg.num_particles();i++)
		{
			// Creation of one particle //
			model=op.Ellipsoide(model, "Ellipsoide "+String.valueOf(i+1), pg.x_dim(i), pg.y_dim(i), pg.z_dim(i), pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), true);
			
			//Selection and renaming of the particle //
			model=op.Selection(model, "Particle "+String.valueOf(i+1), op.elp, "off", true);
			
			// Rotation of the particle //
			model=op.Rotation(model, "Rotate "+String.valueOf(i+1)+"x", op.sel, "x", pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), pg.x_rot(i), true);
			model=op.Rotation(model, "Rotate "+String.valueOf(i+1)+"y", op.sel, "y", pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), pg.y_rot(i), true);
			model=op.Rotation(model, "Rotate "+String.valueOf(i+1)+"z", op.sel, "z", pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), pg.z_rot(i), true);
		}
		
		// Seletion of the particles //
		model=op.ComplementSelection(model, "All Particles", new String[]{z.select("Initial Domain")}, -1, true);
		
		// Cut and partition of the system through the particles //
		model=op.PartitionDomain(model, "Partition Domains", "object", z.select("Initial Domain"), 0, op.comsel, 0, tol.cut(), true);
		tmp=op.pard;
		
		model=op.Delete(model, "Delete "+String.valueOf(i+1), op.comsel, -1, true);
	}
	else
	{
		z.define("Particle", "");	
		// Subsequent operations will be repeated for each particle that will be part of the system //
		for(i=0; i<pg.num_particles(); i++)
		{
			model=op.Group(model, "Particle "+String.valueOf(i+1), z.select("Initial Domain"));
			
			if(creation)
			{
				// Creation of one Particle //
				model=op.Ellipsoide(model, "Ellipsoide "+String.valueOf(i+1), pg.x_dim(i), pg.y_dim(i), pg.z_dim(i), pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), true);
				
				//Selection and renaming of the particle //
				model=op.Selection(model, "Particle "+String.valueOf(i+1), op.elp, "off", true);
				z.replace("Particle", op.sel);
				
				// Rotation of the particle (x-axis) //
				model=op.Rotation(model, "Rotate "+String.valueOf(i+1)+"x", op.sel, "x", pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), pg.x_rot(i), true);
				model=op.Rotation(model, "Rotate "+String.valueOf(i+1)+"y", op.sel, "y", pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), pg.y_rot(i), true);
				model=op.Rotation(model, "Rotate "+String.valueOf(i+1)+"z", op.sel, "z", pg.x_pos(i), pg.y_pos(i), pg.z_pos(i), pg.z_rot(i), true);
			}
			else
			{
				stl=filepath+"s"+String.valueOf(i)+".stl";
				
				// Import of one Particle (in .stl format) //
				model=op.Import(model, "Import "+String.valueOf(i+1), stl, formsolid, tol.imp(), true);
				
				// Selection and ranaming of the Particle //
				model=op.Selection(model, "Particle "+String.valueOf(i+1), op.imp, "off", true);
				z.replace("Particle", op.sel);
			}
			
			if(volume_control)
			{
				// Construction of the Control Block //
				model=op.Block(model, "Block "+String.valueOf(i+1), "center", "xmax*0.2*10^6", "ymax*0.2*10^6", "zmax*0.2*10^6", "0", "0", "(zmin+zmax*0.5)*0.2*10^6", true);
				
				// Selection and renaming of the Control Block //
				model=op.Selection(model,  "Control Block "+String.valueOf(i+1), op.blk, "off", true);
				
				// Cut and partition of the Control Block through the Particle //
				model=op.PartitionDomain(model, "Partition Domain "+String.valueOf(i+1)+".1", "face", z.select("Particle"), 0, op.sel, 0, tol.cut(), true);
				
				volume_particle=op.VolumeCalc(model, op.sel, 3, 0);
				
				volume_pard=op.VolumeCalc(model, op.pard, 3, 1);
				
				// Deletion of the Control Block //
				model=op.Delete(model, "Delete "+String.valueOf(i+1)+".1", op.sel, 3, true);
				
				// Volume control //
				if((volume_block-volume_pard)/volume_particle>tol.vol())
				{
					// Cut and partition of the system through the Particle //
					model=op.PartitionDomain(model, "Partition Domain "+String.valueOf(i+1)+".2", "face", tmp, d, z.select("Particle"), 0, tol.cut(), true);
					tmp=pard;
					
					volume_pard1=op.VolumeCalc(model, tmp, 3, 1);
					
					volume_pard2=op.VolumeCalc(model, tmp, 3, 2);
					
					if(volume_pard1>volume_pard2)
						d=1;
					else
						d=2;
				}
			}
			else
			{
				// Cut and partition of the system through the Particle //
				model=op.PartitionDomain(model, "Partition Domain "+String.valueOf(i+1)+".2", "face", tmp, d, z.select("Particle"), 0, tol.cut(), true);
				tmp=pard;
				
				volume_pard1=op.VolumeCalc(model, tmp, 3, 1);
				
				volume_pard2=op.VolumeCalc(model, tmp, 3, 2);
				
				if(volume_pard1>volume_pard2)
					d=1;
				else
					d=2;
			}
			
			// Deletion of the surface //
			model=op.Delete(model, "Delete "+String.valueOf(i+1)+".2", z.select("Particle"), -1, true);
		}
	}
	
	model=op.Group(model, "Geometric Operations", z.select("Initial Domain"));
	
	// Copy of the system, we'll obtain system 1 and 2 //
	model=op.Copy(model, "Copy", tmp, 1, "20", "0", "0", true);
	
	// Selection of Electrolyte 1 //
	model=op.BallSelection(model, "Electrolyte 1", "0", "0", "zmax*0.2*10^6", "all", 3, "intersects", "off", true);
	
	// Selection of Graphite 1 //
	model=op.AdjacentSelection(model, "Graphite Block 1", new String[]{op.ballsel}, 3, 3, false, false, "off", true);
	
	// Deletion of Electrolyte 1 //
	model=op.Delete(model, "Delete Electrolyte 1", op.ballsel, 3, true);
	
	// Union of the particles //
	model=op.Union(model, "Particle Union", op.del, false, false, true);
	
	// Selection the Electrolyte 2 //
	model=op.BallSelection(model, "Electrolyte 2", "20", "0", "zmax*0.2*10^6", "all", 3, "intersects", "off", true);
	
	// Selection of Graphite 2 //
	model=op.AdjacentSelection(model, "Graphite Block 2", new String[]{op.ballsel}, 3, 3, false, false, "off", true);
	
	// Deletion of Graphite 2 //
	model=op.Delete(model, "Delete Graphite 2", op.adjsel, 3, true);
	
	// Move Electrolyte 2 to the original position //
	model=op.Move(model, "Move", op.del, 3, "-20", "0", "0", true);
	
	// In this way we had obtained two object: the electrolyte-object and the particles-object. //
	// This step is necessary to avoid that COMSOL identify an interface between two particles that are in contact. //
	
	// Scaling the system from meters to micrometers //
	model=op.Scale(model, "Scaling", z.select("Initial Domain"), "5E-6", true);
	
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
	model=op.Block(model, "Current Collector Block", "center", "xmax", "ymax", "cc_thickness", "0", "0", "zmin-cc_thickness+cc_thickness*0.5", true);
	
	// Construction of the Separator //
	model=op.Block(model, "Separator Block", "center", "xmax", "ymax", "sep_thickness", "0", "0", "zmax+sep_thickness*0.5", true);
	
	// Form the final union //
	model.component("TestCase").geom("geom1").feature("fin").set("repairtoltype", "relative");
	model.component("TestCase").geom("geom1").feature("fin").set("repairtol", tol.fin());
	model.component("TestCase").geom("geom1").run("fin");
	i=50;

	// Selection and renaming of the different zones of the system //
	
	// Separator //
	model=op.BallSelection(model, "Separator", "0", "0", "zmax+sep_thickness*0.5", "all", 3, "intersects", "on", false);
	z.define(op.ballsel, "Separator");
	
	// Lithium Foil //
	model=op.BoxSelection (model, "Lithium Foil", 2, "allvertices", new String[]{"-xmax/2","xmax/2","-ymax/2","ymax/2","zmax+sep_thickness","zmax+sep_thickness"}, "all", "", "on", false);
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
	
	if(CBD)
	{
		// Carbon Binder and Current Collector//
		model=op.AdjacentSelection(model, "CB_and_LB", new String[]{z.select("Electrolyte"), z.select("Separator")}, 3, 3, false, true, "on", false);
		z.define(op.adjsel, "CB_and_LB");
		
		tmp=z.select("CB_and_LB");
	}
	else
	{
		tmp=z.select("Current Collector");
	}
	
	// Graphite //
	model=op.AdjacentSelection(model, "Graphite", new String[]{z.select("Electrolyte"), z.select("Separator"), tmp}, 3, 3, false, true, "on", false);
	z.define(op.adjsel, "Graphite");
	
	model=op.AdjacentSelection(model, "Graphite Boundaries", new String[]{z.select("Graphite")}, 3, 2, false, true, "on", false);
	z.define(op.adjsel, "Graphite Boundaries");
	
	model=op.IntersectionSelection(model, "Graphite Surface", new String[]{z.select("Graphite Boundaries"), z.select("Electrolyte Boundaries")}, 2, "on", false);
	z.define(op.intsel, "Graphite Surface");
	
	if(CBD)
	{
		// Carbon  Binder //
		model=op.ComplementSelection(model, "Carbon Binder", new String[]{z.select("Electrolyte"), z.select("Graphite"), z.select("CurrentCollector"), z.select("Separator")}, -1, false);
		z.define(op.comsel, "Carbon Binder");
		
		model=op.AdjacentSelection(model, "Carbon Binder Boundaries", new String[]{z.select("Carbon Binder")}, 3, 2, false, true, "on", false);
		z.define(op.adjsel, "Carbon Binder Boundaries");
	}
	
	// Interface between Particles and Current Collector //
	model=op.IntersectionSelection(model, "Graphite Current Collector", new String[]{z.select("Graphite Boundaries"), z.select("Collector Boundaries")}, 2, "on", false);
	z.define(op.adjsel, "Graphite Current Collector");
	
	// Graphite Half Cell //
	model=op.ComplementSelection(model, "Graphite Half Cell", new String[]{z.select("Current Collector")}, 3, false);
	z.define(op.comsel, "Graphite Half Cell");
	
	// Electrolyte and Separator //
	model=op.ComplementSelection(model, "Electrolyte Separator", new String[]{z.select("Current Collector"), z.select("Graphite")}, 3, false);
	z.define(op.comsel, "Electrolyte Separator");

	// Mesurement of the total graphite volume //
	volume_block=op.VolumeCalc(model, z.select("Graphite"), 3, 0);
	model.param("par3").set("Vp",	String.valueOf(volume_block), "Total Electrode Volume");

	return model;
	}
	
public static Model MaterialsDefinition(Model model, Zone z)
{

	// Graphite //
	model.component("TestCase").material().create("mat1", "Common");
	model.component("TestCase").material("mat1").label("Graphite, LixC6 MCMB (Positive, Li-ion Battery)");
	model.component("TestCase").material("mat1").propertyGroup("def").addInput("temperature");
	model.component("TestCase").material("mat1").propertyGroup("def").addInput("concentration");
	model.component("TestCase").material("mat1").propertyGroup("def").set("T_ref", "318[K]");
	model.component("TestCase").material("mat1").propertyGroup("def").descr("T_ref", "");
	model.component("TestCase").material("mat1").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
	model.component("TestCase").material("mat1").propertyGroup("def").descr("T2", "");
	model.component("TestCase").material("mat1").propertyGroup("def").set("csmax", "31507[mol/m^3]");
	model.component("TestCase").material("mat1").propertyGroup("def").descr("csmax", "");
	model.component("TestCase").material("mat1").propertyGroup().create("ElectrodePotential", "Equilibrium potential");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").addInput("temperature");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").set("Eeq", "");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").set("dEeqdT", "");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").func().create("int1", "Interpolation");	
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").func("int1").set("funcname", "Eeq_int1");	  
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").func("int1").set("table", new String[][]{
		{"0"   , "2.781186612"}, 
		{"0.01", "1.520893224"}, 
		{"0.02", "0.893922607"}, 
		{"0.03", "0.581284406"}, 
		{"0.04", "0.42452844"}, 
		{"0.05", "0.344895805"}, 
		{"0.06", "0.303146342"}, 
		{"0.07", "0.279578072"}, 
		{"0.08", "0.264093089"}, 
		{"0.09", "0.251347845"}, 
		{"0.1", "0.238588379"}, 
		{"0.11", "0.224803164"}, 
		{"0.12", "0.210294358"}, 
		{"0.13", "0.196408586"}, 
		{"0.14", "0.184624188"}, 
		{"0.15", "0.175188157"}, 
		{"0.16", "0.167373311"}, 
		{"0.17", "0.160452107"}, 
		{"0.18", "0.154025412"}, 
		{"0.19", "0.147948522"}, 
		{"0.2", "0.142214997"}, 
		{"0.21", "0.13688271"}, 
		{"0.22", "0.132033114"}, 
		{"0.23", "0.127747573"}, 
		{"0.24", "0.124091616"}, 
		{"0.25", "0.121103387"}, 
		{"0.26", "0.11878567"}, 
		{"0.27", "0.117102317"}, 
		{"0.28", "0.115980205"}, 
		{"0.29", "0.115317054"}, 
		{"0.3", "0.114993965"}, 
		{"0.31", "0.114890105"}, 
		{"0.32", "0.114886278"}, 
		{"0.33", "0.114884619"}, 
		{"0.34", "0.114873068"}, 
		{"0.35", "0.114824904"}, 
		{"0.36", "0.114644725"}, 
		{"0.37", "0.114372614"}, 
		{"0.38", "0.114017954"}, 
		{"0.39", "0.11359371"}, 
		{"0.4", "0.11311133"}, 
		{"0.41", "0.112575849"}, 
		{"0.42", "0.111980245"}, 
		{"0.43", "0.111297682"}, 
		{"0.44", "0.110470149"}, 
		{"0.45", "0.109393081"}, 
		{"0.46", "0.107900592"}, 
		{"0.47", "0.10576964"}, 
		{"0.48", "0.102783317"}, 
		{"0.49", "0.09889031"}, 
		{"0.5", "0.094391564"}, 
		{"0.51", "0.089921069"}, 
		{"0.52", "0.086112415"}, 
		{"0.53", "0.083265315"}, 
		{"0.54", "0.081326247"}, 
		{"0.55", "0.080074892"}, 
		{"0.56", "0.07928329"}, 
		{"0.57", "0.078778765"}, 
		{"0.58", "0.078447703"}, 
		{"0.59", "0.078220432"}, 
		{"0.6", "0.078055641"}, 
		{"0.61", "0.077929111"}, 
		{"0.62", "0.077826563"}, 
		{"0.63", "0.077739397"}, 
		{"0.64", "0.077662227"}, 
		{"0.65", "0.077591472"}, 
		{"0.66", "0.077524557"}, 
		{"0.67", "0.077459463"}, 
		{"0.68", "0.077394455"}, 
		{"0.69", "0.077327934"}, 
		{"0.7", "0.077258337"}, 
		{"0.71", "0.077184077"}, 
		{"0.72", "0.077103499"}, 
		{"0.73", "0.077014851"}, 
		{"0.74", "0.076916258"}, 
		{"0.75", "0.07680571"}, 
		{"0.76", "0.07668104"}, 
		{"0.77", "0.07653992"}, 
		{"0.78", "0.076379839"}, 
		{"0.79", "0.076198086"}, 
		{"0.8", "0.075991699"}, 
		{"0.81", "0.075757371"}, 
		{"0.82", "0.075491288"}, 
		{"0.83", "0.075188813"}, 
		{"0.84", "0.07484398"}, 
		{"0.85", "0.074448647"}, 
		{"0.86", "0.07399118"}, 
		{"0.87", "0.073454466"}, 
		{"0.88", "0.072812991"}, 
		{"0.89", "0.072028722"}, 
		{"0.9", "0.071045433"}, 
		{"0.91", "0.069780996"}, 
		{"0.92", "0.068116222"}, 
		{"0.93", "0.065874599"}, 
		{"0.94", "0.062770873"}, 
		{"0.95", "0.058253898"}, 
		{"0.96", "0.051075794"}, 
		{"0.97", "0.038790069"}, 
		{"0.98", "0.020172191"}
		});
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").set("soc", "c/cEeqref");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").descr("soc", "");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").func("int1").set("interp", "piecewisecubic");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").func("int1").set("extrap", "linear");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").func("int1").set("fununit", new String[]{"V"});
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").func("int1").set("argunit", new String[]{""});
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").func().create("int2", "Interpolation"); 
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").func("int2").set("funcname", "dEeqdT_int1");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").func("int2").set("table", new String[][]{
		{"0", "3.0e-4"}, 
		{"0.17", "0"}, 
		{"0.24", "-6e-5"}, 
		{"0.28", "-1.6e-4"}, 
		{"0.5", "-1.6e-4"}, 
		{"0.54", "-9e-5"}, 
		{"0.71", "-9e-5"}, 
		{"0.85", "-1.0e-4"}, 
		{"1.0", "-1.2e-4"}
		});
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").func("int2").set("fununit", new String[]{"V/K"});
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").func("int2").set("argunit", new String[]{""});;
	model.component("TestCase").material("mat1").propertyGroup().create("OperationalSOC", "Operational electrode state-of-charge");
	model.component("TestCase").material("mat1").propertyGroup("OperationalSOC").set("socmax", "0.98");
	model.component("TestCase").material("mat1").propertyGroup("OperationalSOC").set("socmin", "0");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").set("Eeq", "Eeq_int1(soc)+dEeqdT_int1(soc)*(T-298[K])");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").set("dEeqdT", "dEeqdT_int1(soc)");
	model.component("TestCase").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "31507[mol/m^3]");
	model.component("TestCase").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{
		"100[S/m]", 
		"0", 
		"0", 
		"0", 
		"100[S/m]", 
		"0", 
		"0", 
		"0", 
		"100[S/m]"
		});
	model.component("TestCase").material("mat1").propertyGroup("def").set("diffusion", new String[]{
		"1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", 
		"0", 
		"0", 
		"0", 
		"1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", 
		"0", 
		"0", 
		"0", 
		"1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]"
		});
	model.component("TestCase").material("mat1").selection().named("geom1_"+z.select("Graphite"));

	// Lithium //
	model.component("TestCase").material().create("mat2", "Common");
	model.component("TestCase").material("mat2").label("Lithium Metal, Li (Negative, Li-ion Battery)");
	model.component("TestCase").material("mat2").propertyGroup("def").set("electricconductivity", "");
	model.component("TestCase").material("mat2").propertyGroup().create("ElectrodePotential", "Equilibrium potential");
	model.component("TestCase").material("mat2").propertyGroup("ElectrodePotential").set("Eeq", "");
	model.component("TestCase").material("mat2").propertyGroup("ElectrodePotential").set("dEeqdT", "");
	model.component("TestCase").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "");
	model.component("TestCase").material("mat2").propertyGroup("ElectrodePotential").set("Eeq", "0[V]");
	model.component("TestCase").material("mat2").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
	model.component("TestCase").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "0[M]");
	model.component("TestCase").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{
		"1/(92.8[n\u03a9*m])", 
		"0", 
		"0", 
		"0", 
		"1/(92.8[n\u03a9*m])", 
		"0", 
		"0", 
		"0", 
		"1/(92.8[n\u03a9*m])"
		});	  
	model.component("TestCase").material("mat2").selection().named("geom1_"+z.select("Lithium Foil"));

	// Separator & Carbon Binder/Electrolyte //
	model.component("TestCase").material().create("mat3", "Common");
	model.component("TestCase").material("mat3").label("LiPF6 in 3:7 EC:EMC (Liquid, Li-ion Battery)");
	model.component("TestCase").material("mat3").propertyGroup("def").addInput("concentration");
	model.component("TestCase").material("mat3").propertyGroup("def").addInput("temperature");
	model.component("TestCase").material("mat3").propertyGroup("def").set("T_ref", "298[K]");
	model.component("TestCase").material("mat3").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
	model.component("TestCase").material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
	model.component("TestCase").material("mat3").propertyGroup("def").func("int1").set("funcname", "DL_int1");
	model.component("TestCase").material("mat3").propertyGroup("def").func("int1").set("table", new String[][]{
		{"200", "3.9e-10/(1-200*59e-6)"}, 
		{"500", "4.12e-10/(1-500*59e-6)"}, 
		{"800", "4e-10/(1-800*59e-6)"}, 
		{"1000", "3.8e-10/(1-1000*59e-6)"}, 
		{"1200", "3.50e-10/(1-1200*59e-6)"}, 
		{"1600", "2.68e-10/(1-1600*59e-6)"}, 
		{"2000", "1.9e-10/(1-2000*59e-6)"}
		});
	model.component("TestCase").material("mat3").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
	model.component("TestCase").material("mat3").propertyGroup("def").func("int1").set("fununit", new String[]{"m^2/s"});
	model.component("TestCase").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{""});
	model.component("TestCase").material("mat3").propertyGroup("def").set("diffusion", new String[]{
		"DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", 
		"0", 
		"0", 
		"0", 
		"DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", 
		"0", 
		"0", 
		"0", 
		"DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))"
		});
	model.component("TestCase").material("mat3").propertyGroup().create("ElectrolyteConductivity", "Electrolyte conductivity");
	model.component("TestCase").material("mat3").propertyGroup("ElectrolyteConductivity").addInput("concentration");
	model.component("TestCase").material("mat3").propertyGroup("ElectrolyteConductivity").addInput("temperature");
	model.component("TestCase").material("mat3").propertyGroup("ElectrolyteConductivity").func().create("int1", "Interpolation");
	model.component("TestCase").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1").set("funcname", "sigmal_int1");
	model.component("TestCase").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1").set("table", new String[][]{
		{"0", "1e-6"}, 
		{"200", "0.455"}, 
		{"500", "0.783"}, 
		{"800", "0.935"}, 
		{"1000", "0.95"}, 
		{"1200", "0.927"}, 
		{"1600", "0.78"}, 
		{"2000", "0.60"}, 
		{"2200", "0.515"}
		});
	model.component("TestCase").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1").set("interp", "piecewisecubic");
	model.component("TestCase").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1").set("fununit", new String[]{"S/m"});
	model.component("TestCase").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1").set("argunit", new String[]{""});
	model.component("TestCase").material("mat3").propertyGroup("ElectrolyteConductivity").set("sigmal", new String[]{
	"sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", 
	"0", 
	"0", 
	"0", 
	"sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", 
	"0", 
	"0", 
	"0", 
	"sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))"
	});
	model.component("TestCase").material("mat3").propertyGroup("ElectrolyteConductivity").set("T_ref2", "298[K]");
	model.component("TestCase").material("mat3").propertyGroup("ElectrolyteConductivity").set("T3", "min(393.15,max(T,223.15))");
	model.component("TestCase").material("mat3").propertyGroup().create("SpeciesProperties", "Species properties");
	model.component("TestCase").material("mat3").propertyGroup("SpeciesProperties").addInput("concentration");
	model.component("TestCase").material("mat3").propertyGroup("SpeciesProperties").set("transpNum", "0.363");
	model.component("TestCase").material("mat3").propertyGroup("SpeciesProperties").set("fcl", "1");
	model.component("TestCase").material("mat3").selection().named("geom1_"+z.select("Electrolyte Separator"));

	return model;
}

public static Model PhysicsDefinition(Model model, Zone z){
	
	boolean CBD=false;
	
	// Lithium Ion Battery Model //
	model.component("TestCase").physics().create("liion", "LithiumIonBatteryMPH", "geom1");
	model.component("TestCase").physics("liion").selection().named("geom1_"+z.select("Graphite Half Cell"));
	model.component("TestCase").physics("liion").feature("init1").set("phis", "mat1.elpot.Eeq_int1(cs0/csmax)");
	model.component("TestCase").physics("liion").feature("init1").set("cl",   "cl0");
	model.component("TestCase").physics("liion").label("Graphitic Half Cell");

	// Lithium transport into the electrode //
	model.component("TestCase").physics().create("tds", "DilutedSpecies", new String[][]{{"c"}});
	model.component("TestCase").physics("tds").selection().named("geom1_"+z.select("Graphite"));
	model.component("TestCase").physics("tds").feature("init1").setIndex("initc", "cs0", 0);
	model.component("TestCase").physics("tds").prop("TransportMechanism").set("Convection", false);
	model.component("TestCase").physics("tds").create("eeic1", "ElectrodeElectrolyteInterfaceCoupling", 2);
	model.component("TestCase").physics("tds").feature("eeic1").selection().named("geom1_"+z.select("Graphite Surface"));
	model.component("TestCase").physics("tds").feature("eeic1").feature("rc1").setIndex("Vib", 1, 0);
	model.component("TestCase").physics("tds").feature("cdm1").set("DiffusionMaterialList", "mat1");
	model.component("TestCase").physics("tds").feature("cdm1").set("D_c_mat", "def");
	model.component("TestCase").physics("tds").label("Lithium in Graphite");

	// Graphitic electrode //
	model.component("TestCase").physics("liion").create("el1", "Electrode", 3);
	model.component("TestCase").physics("liion").feature("el1").selection().named("geom1_"+z.select("Graphite"));
	model.component("TestCase").physics("liion").feature("el1").set("sigma_mat", "userdef");
	model.component("TestCase").physics("liion").feature("el1").set("sigma", new int[]{100, 0, 0, 0, 100, 0, 0, 0, 100});
	model.component("TestCase").physics("liion").feature("el1").label("Graphite");
	model.component("TestCase").physics("liion").create("ec1", "ElectrodeCurrent", 2);
	model.component("TestCase").physics("liion").feature("ec1").selection().named("geom1_"+z.select("Graphite Current Collector"));
	model.component("TestCase").physics("liion").feature("ec1").set("Its", "-I_C");
	model.component("TestCase").physics("liion").feature("ec1").set("phis0init", "mat1.elpot.Eeq_int1(cs0/csmax)");
	model.component("TestCase").physics("liion").feature("ec1").label("Graphite-CC interface");
	
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
	
	// Electrod-Electrolyte interface //
	model.component("TestCase").physics("liion").create("bei1", "InternalElectrodeSurface", 2);
	model.component("TestCase").physics("liion").feature("bei1").selection().named("geom1_"+z.select("Graphite Boundaries"));
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

	return model;		
}

public static Model MeshConstruction(Model model, Zone z){
	
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
	model.component("TestCase").mesh("mesh1").feature("siz2").label("Graphite");
	model.component("TestCase").mesh("mesh1").feature("siz2").selection().named("geom1_"+z.select("Graphite"));
	model.component("TestCase").mesh("mesh1").feature("siz2").set("custom", true);
	model.component("TestCase").mesh("mesh1").feature("siz2").set("hmax", "hmax");
	model.component("TestCase").mesh("mesh1").feature("siz2").set("hmin", "hmin");
	model.component("TestCase").mesh("mesh1").feature("siz2").set("hgrad", 1.5);
	model.component("TestCase").mesh("mesh1").feature("siz2").set("hcurve", 0.6);
	model.component("TestCase").mesh("mesh1").feature("siz2").set("hnarrow", 0.5);
	
	model.component("TestCase").mesh("mesh1").create("ftet1", "FreeTet");
	model.component("TestCase").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
	model.component("TestCase").mesh("mesh1").feature("ftet1").selection().named("geom1_"+z.select("Graphite Half Cell"));
	model.component("TestCase").mesh("mesh1").run();
	
	return model;
}

public static Model TestStudy(Model  model, String time, Tolerance tol)
{
	model.component("TestCase").view("view1").hideObjects().create("hide1");
	model.component("TestCase").view("view1").hideObjects("hide1").init(3);
	model.component("TestCase").view("view1").hideObjects("hide1").add("fin", 1);
	
	model.component("TestCase").probe().create("var1", "GlobalVariable");
	model.component("TestCase").probe("var1").label("Global Variable Probe - E cell");
	model.component("TestCase").probe("var1").set("expr", "liion.phis0_ec1");
	model.component("TestCase").probe("var1").set("descractive", true);
	model.component("TestCase").probe("var1").set("descr", "Cell voltage");
	
	model.study().create("std1");
	
	model.study("std1").create("cdi", "CurrentDistributionInitialization");
	model.study("std1").feature("cdi").set("solnum", "auto");
	model.study("std1").feature("cdi").set("notsolnum", "auto");
	model.study("std1").feature("cdi").setSolveFor("/physics/liion", true);
	model.study("std1").feature("cdi").setSolveFor("/physics/tds", false);
	model.study("std1").feature("cdi").set("initType", "secondary");

	model.study("std1").create("time", "Transient");
	model.study("std1").feature("time").set("initialtime", "0");
	model.study("std1").feature("time").set("solnum", "auto");
	model.study("std1").feature("time").set("notsolnum", "auto");
	model.study("std1").feature("time").setSolveFor("/physics/liion", true);
	model.study("std1").feature("time").setSolveFor("/physics/tds", true);
	model.study("std1").feature("time").set("tunit", "h");
	model.study("std1").feature("time").set("usertol", true);
	model.study("std1").feature("time").set("rtol", tol.mesh());
	model.study("std1").feature("time").set("tlist", time);
	
	return model;
}

}
//ENDFILE
