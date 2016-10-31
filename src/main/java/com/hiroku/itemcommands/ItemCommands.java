package com.hiroku.itemcommands;

import com.hiroku.itemcommands.commands.ItemBindingExecutor;
import com.hiroku.itemcommands.data.BindingRegistry;

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
		BindingRegistry.load();
	}
	
	@EventHandler
	public void onServerStart(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new ItemBindingExecutor());
	}
}
