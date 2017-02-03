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
	private static HashMap<String, ArrayList<BoundCommand>> binding = new HashMap<String, ArrayList<BoundCommand>>(); 
	
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
				
				ArrayList<BoundCommand> commands = new ArrayList<BoundCommand>();
				for (int i = 1; i < splits.length; i++)
				{
					String split = splits[i];
					int delaySeconds = 0;
					boolean consumeItem = false;
					String command = "";
					if (split.contains("::"))
					{
						try
						{
							command = split.split("::")[0];
							delaySeconds = Integer.parseInt(split.split("::")[1]);
							consumeItem = Boolean.parseBoolean(split.split("::")[2]);
						}
						catch(Exception e)
						{
							;
						}
					}
					else
						command = split;
					commands.add(new BoundCommand(command, delaySeconds, consumeItem));
				}
					
				
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
				ArrayList<BoundCommand> commands = binding.get(item);
				String commandList = "";
				for (BoundCommand command : commands)
					if (command.delaySeconds != 0)
						commandList += command.command + "::" + command.delaySeconds + ",";
					else
						commandList += command.command + ",";
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
	
	public static ArrayList<BoundCommand> getCommands(String item)
	{
		if (binding.containsKey(item))
			return binding.get(item);
		return new ArrayList<BoundCommand>();
	}
	
	public static boolean clearBindings(String item)
	{
		return binding.remove(item) != null;
	}
	
	public static void addBinding(String item, String command, int delaySeconds, boolean consumeItem)
	{
		ArrayList<BoundCommand> commands = getCommands(item);
		commands.add(new BoundCommand(command, delaySeconds, consumeItem));
		binding.put(item, commands);
	}
}
