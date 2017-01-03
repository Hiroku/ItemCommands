package com.hiroku.itemcommands.listeners;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.InteractItemEvent;

import com.hiroku.itemcommands.ItemCommands;
import com.hiroku.itemcommands.timertasks.CommandTask;

public class InteractListener
{
	@Listener
	public void onItemRightClick(InteractItemEvent event )
	{
		if (event.getCause().root() instanceof Player)
			ItemCommands.timer.schedule(new CommandTask(event.getItemStack().getType().getName(), ((Player)event.getCause().root()).getName()), 0);
	}
}
