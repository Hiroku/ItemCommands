package com.hiroku.itemcommands;

import java.util.Timer;

import com.hiroku.itemcommands.commands.ItemBindingExecutor;
import com.hiroku.itemcommands.data.BindingRegistry;
import com.hiroku.itemcommands.listeners.InteractListener;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ItemCommands.MODID, name = ItemCommands.NAME, version = ItemCommands.VERSION, acceptableRemoteVersions = "*")
public class ItemCommands
{
	public static final String MODID = "itemcommands";
	public static final String NAME = "Item Commands";
	public static final String VERSION = "1.1.1";
	
	public static final Timer timer = new Timer();
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		BindingRegistry.load();
		MinecraftForge.EVENT_BUS.register(new InteractListener());
	}
	
	@EventHandler
	public void onServerStart(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new ItemBindingExecutor());
	}
}
