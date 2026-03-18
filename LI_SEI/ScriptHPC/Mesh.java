

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

public class Mesh
{
	String ftri="ftri";				private int ftri_c=1;
	String ftet="ftet";				private int ftet_c=1;
	String last_op="tmp";			private int err_c=1;
	
	public Model freeTriangular(Model model, String label, int Refinement, int singleface)
	{
		ftri="ftri"+String.valueOf(ftri_c);
		model.component("TestCase").mesh("mesh1").create(ftri, "FreeTri");
		model.component("TestCase").mesh("mesh1").feature(ftri).label(label);
		if (singleface>0)
		{
			model.component("TestCase").mesh("mesh1").feature(ftri).selection().set(singleface);
		}
		else 
		{
			model.component("TestCase").mesh("mesh1").feature(ftri).selection().remaining();
		}
		model.component("TestCase").mesh("mesh1").feature(ftri).create("size1", "Size");
		model.component("TestCase").mesh("mesh1").feature(ftri).feature("size1").set("hauto", Refinement);
		last_op=ftri;
		model.component("TestCase").mesh("mesh1").run(ftri);
		ftri_c+=1;
		return model;
	}
	
	public Model freeTetrahedral(Model model, String label, String zone, int Refinement)
	{
		ftet="ftet"+String.valueOf(ftet_c);
		model.component("TestCase").mesh("mesh1").create(ftet, "FreeTet");
		model.component("TestCase").mesh("mesh1").feature(ftet).label(label);
		model.component("TestCase").mesh("mesh1").feature(ftet).selection().geom("geom1", 3);
		model.component("TestCase").mesh("mesh1").feature(ftet).selection().named("geom1_"+zone);
		model.component("TestCase").mesh("mesh1").feature(ftet).create("size1", "Size");
		model.component("TestCase").mesh("mesh1").feature(ftet).feature("size1").set("hauto", Refinement);
		last_op=ftet;
		model.component("TestCase").mesh("mesh1").run(ftet);
		ftet_c+=1;
		return model;
	}
	
	public Model error(Model model) throws IOException
	{
		String outFile = "error"+String.valueOf(err_c)+".txt";
		String currentDir = new File(".").getAbsolutePath();
		currentDir = currentDir.substring(0, currentDir.length() - 1);
		String filePath1 = currentDir + outFile;
		FileWriter file = new FileWriter(filePath1);
		BufferedWriter output = new BufferedWriter(file);
		int i=0;
		
		boolean problem = model.component("TestCase").mesh("mesh1").hasProblems();
		String [] problemFeatures = model.component("TestCase").mesh("mesh1").problems();
		try
		{
			for (i=0; i<problemFeatures.length; i++)
			{
				MeshFeature problematicFeature = model.component("TestCase").mesh("mesh1").feature(problemFeatures[i]);
				String [] errors = problematicFeature.errors();
				for (String tag : errors) 
				{
					String errorMessage = problematicFeature.problem(tag).message();
					System.out.println(errorMessage);
					if (problematicFeature.problem(tag).hasSelection()) 
					{
						MeshSelection sel = problematicFeature.problem(tag).selection();
						output.write(sel + "\n");
					}

					String[] problemDetails = problematicFeature.problem(tag).problems();
					for (String tag2 : problemDetails) 
					{
						String errorMessage2 = problematicFeature.problem(tag).problem(tag2).message();
						System.out.println(errorMessage2);
						if (problematicFeature.problem(tag).problem(tag2).hasSelection()) 
						{
							MeshSelection sel = problematicFeature.problem(tag).problem(tag2).selection();
							output.write(sel + "\n");
						}
					}
				}
			}
		}
		catch(Exception e){}
	
		err_c+=1;
		output.close();
		
		return model;
	}
}
