package com.hiroku.itemcommands.listeners;

import com.hiroku.itemcommands.ItemCommands;
import com.hiroku.itemcommands.timertasks.CommandTask;

import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InteractListener
{
	@SubscribeEvent
	public void onItemRightClick(PlayerInteractEvent.RightClickItem event)
	{
		if (event.getWorld().isRemote || event.getHand() == EnumHand.OFF_HAND)
			return;
		
		ItemCommands.timer.schedule(new CommandTask(event.getItemStack().getItem(), event.getEntityPlayer().getName()), 0);
	}
	
	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{
		if (event.getWorld().isRemote || event.getHand() == EnumHand.OFF_HAND)
			return;
		if (event.getItemStack() != null)
			ItemCommands.timer.schedule(new CommandTask(event.getItemStack().getItem(), event.getEntityPlayer().getName()), 0);
	}
}
