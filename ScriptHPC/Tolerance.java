
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

public class Tolerance
{
	public String type="anode";
	public String time="range(0,0.001/C_rate,0.95/C_rate)";
	public int refinement=4;
	public String diff="yes";
	public String equp="yes";
	public String auto="yes";
	public double cut_tol=1E-6;
	public double fin_tol=1E-7;
	public double mesh_tol=1E-3;
	public double imp_tol=1E-10;
	public double vol_tol=0.00;
	
	public void setup() throws IOException
	{
		String inputFile = "Parameters/setup.txt";
		String currentDir = new File(".").getAbsolutePath();
		currentDir = currentDir.substring(0, currentDir.length() - 1);
		String filePath1 = currentDir + inputFile;
		FileReader file = new FileReader(filePath1);
		BufferedReader input = new BufferedReader(file);
		String l="";
		String [] line=new String[2];
		
		while((l=input.readLine())!=null)
		{
			line=l.split(" == ",0);
			if (line[0].contains("type"))
				type=line[1];
			if (line[0].contains("Time"))
				time=line[1];
			if (line[0].contains("Refinement"))
				refinement=Integer.parseInt(line[1]);
			if (line[0].contains("Diffusion"))
				diff=line[1];
			if (line[0].contains("Equilibrium"))
				equp=line[1];
			if (line[0].contains("Automatic"))
				auto=line[1];
			if (line[0].contains("Partition"))
				cut_tol=Double.parseDouble(line[1]);
			if (line[0].contains("Final"))
				fin_tol=Double.parseDouble(line[1]);
			if (line[0].contains("Mesh"))
				mesh_tol=Double.parseDouble(line[1]);
			if (line[0].contains("Import"))
				imp_tol=Double.parseDouble(line[1]);
			if (line[0].contains("Volume"))
				vol_tol=Double.parseDouble(line[1]);
		}
	}	
}
