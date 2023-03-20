
/** 
* This class contains some operetions to built a geometry on COMSOL
*/

import com.comsol.model.*;
import com.comsol.model.util.*;

class Operations
{
	String adjsel="adjsel";		private int adjsel_c=1;
	String ballsel="ballsel";	private int ballsel_c=1;
	String blk="blk";			private int blk_c=1;
	String boxsel="boxsel";		private int boxsel_c=1;
	String comsel="comsel";		private int comsel_c=1;
	String copy="copy";			private int copy_c=1;
	String del="del";			private int del_c=1;
	String dif="dif";			private int dif_c=1;
	String elp="elp";			private int elp_c=1;
	String grp="grp";			private int grp_c=1;
	String imp="imp";			private int imp_c=1;
	String intsel="intsel";		private int intsel_c=1;
	String mpart="mpart";		private int mpart_c=1;
	String move="move";			private int move_c=1;
	String pard="pard";			private int pard_c=1;
	String rot="rot";			private int rot_c=1;
	String sca="sca";			private int sca_c=1;
	String sel="sel";			private int sel_c=1;
	String uni="uni";			private int uni_c=1;
	
	public Model AdjacentSelection (Model model, String label, String [] name, int entitydim, int outputdim, boolean interior, boolean exterior, String selshow, boolean add)
	{
		adjsel="adjsel"+String.valueOf(adjsel_c);
		model.component("TestCase").geom("geom1").create(adjsel, "AdjacentSelection");
		model.component("TestCase").geom("geom1").feature(adjsel).label(label);
		model.component("TestCase").geom("geom1").feature(adjsel).set("input", name);
		model.component("TestCase").geom("geom1").feature(adjsel).set("entitydim", entitydim);
		model.component("TestCase").geom("geom1").feature(adjsel).set("outputdim", outputdim);
		if (outputdim==2)
		{
			model.component("TestCase").geom("geom1").feature(adjsel).set("interior", interior);
			model.component("TestCase").geom("geom1").feature(adjsel).set("exterior", exterior);
		}
		model.component("TestCase").geom("geom1").feature(adjsel).set("selshow", selshow);
		model.component("TestCase").geom("geom1").run(adjsel);
		adjsel_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(adjsel);
		return model;
	}
	
	public Model BallSelection(Model model, String label, String posx, String posy, String posz, String inputent, int entitydim, String condition, String selshow, boolean add)
	{
		ballsel="ballsel"+String.valueOf(ballsel_c);
		model.component("TestCase").geom("geom1").create(ballsel, "BallSelection");
		model.component("TestCase").geom("geom1").feature(ballsel).label(label);
		model.component("TestCase").geom("geom1").feature(ballsel).set("posx", posx);
		model.component("TestCase").geom("geom1").feature(ballsel).set("posy", posy);
		model.component("TestCase").geom("geom1").feature(ballsel).set("posz", posz);
		model.component("TestCase").geom("geom1").feature(ballsel).set("inputent", inputent);
		model.component("TestCase").geom("geom1").feature(ballsel).set("entitydim", entitydim);
		model.component("TestCase").geom("geom1").feature(ballsel).set("condition", condition);
		model.component("TestCase").geom("geom1").feature(ballsel).set("selshow", selshow);
		model.component("TestCase").geom("geom1").run(ballsel);
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(ballsel);
		ballsel_c+=1;
		return model;
	}
	
	public Model Block (Model model, String label, String base, String sizex, String sizey, String sizez, String posx, String posy, String posz, boolean add)
	{
		blk="blk"+String.valueOf(blk_c);
		model.component("TestCase").geom("geom1").create(blk, "Block");
		model.component("TestCase").geom("geom1").feature(blk).label(label);
		model.component("TestCase").geom("geom1").feature(blk).set("base", base);
		model.component("TestCase").geom("geom1").feature(blk).setIndex("size", sizex, 0);
		model.component("TestCase").geom("geom1").feature(blk).setIndex("size", sizey, 1);
		model.component("TestCase").geom("geom1").feature(blk).setIndex("size", sizez, 2);
		model.component("TestCase").geom("geom1").feature(blk).setIndex("pos", posx, 0);
		model.component("TestCase").geom("geom1").feature(blk).setIndex("pos", posy, 1);
		model.component("TestCase").geom("geom1").feature(blk).setIndex("pos", posz, 2);
		model.component("TestCase").geom("geom1").run(blk);
		blk_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(blk);
		return model;
	}
	
	public Model BoxSelection (Model model, String label, int entitydim, String condition, String [] x, String inputent, String name, String selshow, boolean add)
	{
		boxsel="boxsel"+String.valueOf(boxsel_c);
		model.component("TestCase").geom("geom1").create(boxsel, "BoxSelection");
		model.component("TestCase").geom("geom1").feature(boxsel).label(label);
		model.component("TestCase").geom("geom1").feature(boxsel).set("entitydim", entitydim);
		model.component("TestCase").geom("geom1").feature(boxsel).set("condition", condition);
		model.component("TestCase").geom("geom1").feature(boxsel).set("xmin", x[0]);
		model.component("TestCase").geom("geom1").feature(boxsel).set("xmax", x[1]);
		model.component("TestCase").geom("geom1").feature(boxsel).set("ymin", x[2]);
		model.component("TestCase").geom("geom1").feature(boxsel).set("ymax", x[3]);
		model.component("TestCase").geom("geom1").feature(boxsel).set("zmin", x[4]);
		model.component("TestCase").geom("geom1").feature(boxsel).set("zmax", x[5]);
		model.component("TestCase").geom("geom1").feature(boxsel).set("inputent", inputent);
		if (name!="")
			model.component("TestCase").geom("geom1").feature(boxsel).set("input", new String[]{name});
		model.component("TestCase").geom("geom1").feature(boxsel).set("selshow", selshow);
		model.component("TestCase").geom("geom1").run(boxsel);
		boxsel_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(boxsel);
		return model;
		
	}
		
	public Model ComplementSelection (Model model, String label, String [] name, int entitydim, boolean add)
	{
		comsel="comsel"+String.valueOf(comsel_c);
		model.component("TestCase").geom("geom1").create(comsel, "ComplementSelection");
		model.component("TestCase").geom("geom1").feature(comsel).label(label);
		model.component("TestCase").geom("geom1").feature(comsel).set("entitydim", entitydim);
		model.component("TestCase").geom("geom1").feature(comsel).set("input", name);
		model.component("TestCase").geom("geom1").run(comsel);
		comsel_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(comsel);
		return model;
	}
	
	public Model Copy (Model model, String label, String name, int type, String displx, String disply, String displz, boolean add)
	{
		copy="copy"+String.valueOf(copy_c);
		model.component("TestCase").geom("geom1").create(copy, "Copy");
		model.component("TestCase").geom("geom1").feature(copy).label(label);
		if (type==0)
			model.component("TestCase").geom("geom1").feature(copy).selection("input").named(name);
		else
			model.component("TestCase").geom("geom1").feature(copy).selection("input").set(name);
		model.component("TestCase").geom("geom1").feature(copy).set("displx", displx);
		model.component("TestCase").geom("geom1").feature(copy).set("disply", disply);
		model.component("TestCase").geom("geom1").feature(copy).set("displz", displz);
		model.component("TestCase").geom("geom1").run(copy);
		copy_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(copy);
		return model;
	}
	
	public Model Delete (Model  model, String label, String name, int type, boolean add)
	{
		del="del"+String.valueOf(del_c);
		model.component("TestCase").geom("geom1").create(del, "Delete");
		model.component("TestCase").geom("geom1").feature(del).label(label);
		if (type<0)
			model.component("TestCase").geom("geom1").feature(del).selection("input").init();
		else
			model.component("TestCase").geom("geom1").feature(del).selection("input").init(type);
		model.component("TestCase").geom("geom1").feature(del).selection("input").named(name);
		model.component("TestCase").geom("geom1").run(del);
		del_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(del);
		return model;
	}
	
	public Model Ellipsoide (Model model, String label, double x1, double y1, double z1, double x2, double y2, double z2, boolean add)
	{
		elp="elp"+String.valueOf(elp_c);
		model.component("TestCase").geom("geom1").create(elp, "Ellipsoid");
		model.component("TestCase").geom("geom1").feature(elp).set("semiaxes", new double[]{x1, y1, z1});
		model.component("TestCase").geom("geom1").feature(elp).set("pos", new double[]{x2, y2, z2});
		model.component("TestCase").geom("geom1").run(elp);
		elp_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(elp);
		return model;
	}
	
	public Model Group (Model model, String label, String place)
	{	
		grp="grp"+String.valueOf(grp_c);
		model.component("TestCase").geom("geom1").nodeGroup().create(grp);
		model.component("TestCase").geom("geom1").nodeGroup(grp).label(label);
		model.component("TestCase").geom("geom1").nodeGroup(grp).placeAfter(place);
		grp_c+=1;
		return model;
	}
	
	public Model Import (Model model, String label, String stl, boolean formsolid, double tol, boolean add)
	{
		imp="imp"+String.valueOf(imp_c);
		model.component("TestCase").geom("geom1").create(imp, "Import");
		model.component("TestCase").geom("geom1").feature(imp).label(label);
		model.component("TestCase").geom("geom1").feature(imp).set("filename", stl);
		model.component("TestCase").geom("geom1").feature(imp).set("formsolid", formsolid);
		model.component("TestCase").geom("geom1").feature(imp).set("simplifytol", tol);
		model.component("TestCase").geom("geom1").run(imp);
		imp_c+=1;
		mpart="mpart"+String.valueOf(mpart_c);
		model.mesh(mpart).feature("imp1").set("facepartition", "minimal");
		model.mesh(mpart).feature("imp1").set("stltoltype", "relative");
		model.mesh(mpart).feature("imp1").set("stltolrel", tol);
		model.mesh(mpart).feature("imp1").set("facepartition", "minimal");
		model.mesh(mpart).run("imp1");
		model.component("TestCase").geom("geom1").nodeGroup(grp).add(imp);
		mpart_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(imp);
		return model;
	}
	
	public Model IntersectionSelection (Model model, String label, String [] name, int entitydim, String selshow, boolean add)
	{
		intsel="intsel"+String.valueOf(intsel_c);
		model.component("TestCase").geom("geom1").create(intsel, "IntersectionSelection");
		model.component("TestCase").geom("geom1").feature(intsel).label(label);
		model.component("TestCase").geom("geom1").feature(intsel).set("entitydim", entitydim);
		model.component("TestCase").geom("geom1").feature(intsel).set("input",name);
		model.component("TestCase").geom("geom1").feature(intsel).set("selshow", selshow);
		model.component("TestCase").geom("geom1").run(intsel);
		intsel_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(intsel);
		return model;
	}
	
	public Model Move (Model model, String label, String name, int type, String displx, String disply, String displz, boolean add)
	{
		move="move"+String.valueOf(move_c);
		model.component("TestCase").geom("geom1").create(move, "Move");
		model.component("TestCase").geom("geom1").feature(move).selection("input").set(del);
		model.component("TestCase").geom("geom1").feature(move).set("displx", displx);
		model.component("TestCase").geom("geom1").feature(move).set("disply", disply);
		model.component("TestCase").geom("geom1").feature(move).set("displz", displz);
		model.component("TestCase").geom("geom1").run(move);
		move_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(move);
		return model;
	}
	
	public Model PartitionDomain (Model model, String label, String partwith, String name1, int type1, String name2, int type2, double tol, boolean add)
	{
		pard="pard"+String.valueOf(pard_c);
		model.component("TestCase").geom("geom1").create(pard, "PartitionDomains");
		model.component("TestCase").geom("geom1").feature(pard).set("partitionwith", partwith+"s");
		if (type1==0)
			model.component("TestCase").geom("geom1").feature(pard).selection("domain").named(name1);
		else
			model.component("TestCase").geom("geom1").feature(pard).selection("domain").set(name1, type1);
		if (type1==0)
			model.component("TestCase").geom("geom1").feature(pard).selection(partwith).named(name2);
		else
			model.component("TestCase").geom("geom1").feature(pard).selection(partwith).set(name2, type2);
		model.component("TestCase").geom("geom1").feature(pard).set("repairtoltype", "relative");
		model.component("TestCase").geom("geom1").feature(pard).set("repairtol", tol);
		model.component("TestCase").geom("geom1").run(pard);
		pard_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(pard);
		return model;
	}
	
	public Model Rotation (Model model, String label, String name, String ax, double x, double y, double z, double angle, boolean add)
	{
		rot="rot"+String.valueOf(rot_c);
		model.component("TestCase").geom("geom1").create(rot, "Rotate");
		model.component("TestCase").geom("geom1").feature(rot).label(label);
		model.component("TestCase").geom("geom1").feature(rot).selection("input").named(name);
		model.component("TestCase").geom("geom1").feature(rot).set("axistype", ax);
		model.component("TestCase").geom("geom1").feature(rot).set("pos", new double[]{x, y, z});
		model.component("TestCase").geom("geom1").feature(rot).set("rot", angle);
		model.component("TestCase").geom("geom1").run(rot);
		rot_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(rot);
		return model;
	}
	
	public Model Scale (Model model, String label, String name, String ScaleFactor, boolean add)
	{
		sca="sca"+String.valueOf(sca_c);
		model.component("TestCase").geom("geom1").create(sca, "Scale");
		model.component("TestCase").geom("geom1").feature(sca).label(label);
		model.component("TestCase").geom("geom1").feature(sca).selection("input").named(name);
		model.component("TestCase").geom("geom1").feature(sca).set("isotropic", ScaleFactor);
		model.component("TestCase").geom("geom1").run(sca);
		sca_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(sca);
		return model;		
	}
	
	public Model Selection (Model model, String label, String name, String selshow, boolean add)
	{
		sel="sel"+String.valueOf(sel_c);
		model.component("TestCase").geom("geom1").create(sel, "ExplicitSelection");
		model.component("TestCase").geom("geom1").feature(sel).label(label);
		model.component("TestCase").geom("geom1").feature(sel).selection("selection").init();
		model.component("TestCase").geom("geom1").feature(sel).selection("selection").set(name);
		model.component("TestCase").geom("geom1").feature(sel).set("selshow", selshow);
		model.component("TestCase").geom("geom1").run(sel);
		sel_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(sel);
		return model;
	}
	
	public Model Union (Model model, String label, String name, boolean keep, boolean intbnd, boolean add)
	{
		uni="uni"+String.valueOf(uni_c);
		model.component("TestCase").geom("geom1").create(uni, "Union");
		model.component("TestCase").geom("geom1").feature(uni).label(label);
		model.component("TestCase").geom("geom1").feature(uni).selection("input").set(name);
		model.component("TestCase").geom("geom1").feature(uni).set("intbnd", intbnd);
		model.component("TestCase").geom("geom1").feature(uni).set("keep", keep);
		model.component("TestCase").geom("geom1").run(uni);
		uni_c+=1;
		if (add)
			model.component("TestCase").geom("geom1").nodeGroup(grp).add(uni);
		return model;
	}
	public double VolumeCalc (Model model, String name, int level, int type)
	{
		model.component("TestCase").geom("geom1").measure().selection().init(level);
		if (type==0)
			model.component("TestCase").geom("geom1").measure().selection().named(name);
		else
			model.component("TestCase").geom("geom1").measure().selection().set(name, type);
		return model.component("TestCase").geom("geom1").measure().getVolume();
	}
}
