package com.hiroku.itemcommands.listeners;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InteractListener
{
	@SubscribeEvent
	public void onItemRightClick(PlayerInteractEvent.RightClickItem event)
	{
		
	}
	
	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{
		if (event.getItemStack() != null)
		{
			
		}
	}
}
