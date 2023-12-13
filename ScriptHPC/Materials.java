

import com.comsol.model.*;
import com.comsol.model.util.*;

public class Materials
{
	private String mat="mat";					private int mat_c=1;
	private String [] material=new String[20];	private int i=0	;
	private String intp="int";					private int intp_c=1;
	private int counter=0;
	
	public Model newMaterial(Model model, String label, String [] addInput)
	{
		mat="mat"+String.valueOf(mat_c);
		material[mat_c-1]=label;
		model.component("TestCase").material().create(mat, "Common");
		model.component("TestCase").material(mat).label(label);
		counter=0;
		while(counter<addInput.length && addInput[0]!="none")
		{
			model.component("TestCase").material(mat).propertyGroup("def").addInput(addInput[counter]);
			counter+=1;
		}
		mat_c+=1;
		return model;
	}
	
	public Model newFunc(Model model, String label, String group, String funcname, String [][] table, String interp, String extrap, String fununit, String argunit)
	{
		i=0;
		while(i<material.length)
		{
			if(label.equals(material[i]))
			{
				mat="mat"+String.valueOf(i+1);
				intp="int"+String.valueOf(intp_c);
				model.component("TestCase").material(mat).propertyGroup(group).func().create(intp, "Interpolation");	
				model.component("TestCase").material(mat).propertyGroup(group).func(intp).set("funcname", funcname);	  
				model.component("TestCase").material(mat).propertyGroup(group).func(intp).set("table", table);
				model.component("TestCase").material(mat).propertyGroup(group).func(intp).set("interp", interp);
				model.component("TestCase").material(mat).propertyGroup(group).func(intp).set("extrap", extrap);
				model.component("TestCase").material(mat).propertyGroup(group).func(intp).set("fununit", fununit);
				model.component("TestCase").material(mat).propertyGroup(group).func(intp).set("argunit", argunit);
				intp_c+=1;
			}
			i+=1;
		}
		return model;
	}
	
	public Model setup(Model model, String label, String group, String [] set, String [] value, boolean tensor)
	{
		i=0;
		while(i<material.length)
		{
			if(label.equals(material[i]))
			{
				if(tensor)
				{
					model.component("TestCase").material(mat).propertyGroup(group).set(set[0], value);
				}
				else
				{
					counter=0;
					while(counter<set.length)
					{
						model.component("TestCase").material(mat).propertyGroup("def").set(set[counter], value[counter]);
						counter+=1;
					}
				}
			}
			i+=1;
		}
		return model;
	}
		
	
	public Model newProperty(Model model, String label, String group, String property, String [] addInput)
	{
		i=0;
		while(i<material.length)
		{
			if(label.equals(material[i]))
			{
				model.component("TestCase").material(mat).propertyGroup().create(group, property);
				counter=0;
				while(counter<addInput.length && addInput[0]!="none")
				{
					model.component("TestCase").material(mat).propertyGroup(group).addInput(addInput[counter]);
					counter+=1;
				}
			}
			i+=1;
		}
		return model;
	}
	
	public Model geomSelection(Model model, String label, String name)
	{
		while(i<material.length)
		{
			if(label.equals(material[i]))
			{
				model.component("TestCase").material(material[i]).selection().named(name);
			}
			i+=1;
		}
		return model;
	}

}
