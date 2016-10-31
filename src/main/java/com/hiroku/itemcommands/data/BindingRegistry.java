package com.hiroku.itemcommands.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class BindingRegistry
{
	/** Maps Item id to a list of commands binded */
	private static HashMap<String, ArrayList<String>> binding = new HashMap<String, ArrayList<String>>(); 
	
	private static final String PATH = "config/itemcommands.conf";
	
	
	public static void load()
	{
		File file = new File(PATH);
		binding.clear();
		try
		{
			if (!file.exists())
			{
				file.createNewFile();
				save();
			}
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = null;
			
			while ((line = br.readLine()) != null)
			{
				line = line.trim();
				
				if (line.startsWith("//") || line.equals(""))
					continue;
				
				String[] splits = line.split(",");
				
				ArrayList<String> commands = new ArrayList<String>();
				for (int i = 1; i < splits.length; i++)
					commands.add(splits[i]);
				
				binding.put(splits[0], commands);
			}
			
			br.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	public static void save()
	{
		try
		{
			PrintWriter pw = new PrintWriter(PATH);
			pw.println("// This is the simple registry for itemid -> command list. Avoid using this unless you have to");
			pw.println("// Much love, as always, Hiroku");
			
			// Potentially dodgy thread interaction, avoid CME's
			String[] keys = new String[binding.keySet().size()];
			binding.keySet().toArray(keys);
			
			for (String item : keys)
			{
				ArrayList<String> commands = binding.get(item);
				String commandList = "";
				for (String command : commands)
					commandList += command + ",";
				if (commandList.endsWith(","))
					commandList = commandList.substring(0, commandList.length() - 1);
				
				pw.println(item + "," + commandList);
			}
			pw.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> getCommands(String item)
	{
		if (binding.containsKey(item))
			return binding.get(item);
		return new ArrayList<String>();
	}
	
	public static boolean clearBindings(String item)
	{
		return binding.remove(item) != null;
	}
	
	public static void addBinding(String item, String command)
	{
		ArrayList<String> commands = getCommands(item);
		commands.add(command);
		binding.put(item, commands);
	}
}
