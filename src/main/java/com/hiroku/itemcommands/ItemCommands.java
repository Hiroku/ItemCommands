package com.hiroku.itemcommands;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ItemCommands.MODID, name = ItemCommands.NAME, version = ItemCommands.VERSION, acceptableRemoteVersions = "*")
public class ItemCommands
{
	public static final String MODID = "itemcommands";
	public static final String NAME = "Item Commands";
	public static final String VERSION = "1.0.0";
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void onServerStart(FMLServerStartingEvent event)
	{
		
	}
}
