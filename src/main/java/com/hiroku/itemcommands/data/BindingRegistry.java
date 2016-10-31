package com.hiroku.itemcommands.data;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.item.Item;

public class BindingRegistry
{
	/** Maps Item to a list of commands binded */
	private static HashMap<Item, ArrayList<String>> binding = new HashMap<Item, ArrayList<String>>(); 
	
	public static void load()
	{
		//TODO
	}
	
	public static void save()
	{
		//TODO
	}
	
	public ArrayList<String> getCommands(Item item)
	{
		if (binding.containsKey(item))
			return binding.get(item);
		return new ArrayList<String>();
	}
	
	public static boolean clearBindings(Item item)
	{
		return binding.remove(item) != null;
	}
}
