package com.hiroku.itemcommands.listeners;

import java.util.ArrayList;
import java.util.Timer;

import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.item.inventory.InteractItemEvent;
import org.spongepowered.api.item.inventory.ItemStack;

import com.hiroku.itemcommands.data.BindingRegistry;
import com.hiroku.itemcommands.data.BoundCommand;
import com.hiroku.itemcommands.timertasks.DelayedExecuteTask;

public class InteractListener
{
	@Listener
	public void onItemRightClick(InteractItemEvent.Secondary.MainHand event, @Root Player player)
	{
		ArrayList<BoundCommand> commands = BindingRegistry.getCommands(event.getItemStack().getType().getName());
		if (!commands.isEmpty())
		{
			boolean remove = false;
			for (BoundCommand command : commands)
			{
				new Timer().schedule(new DelayedExecuteTask(command.command.replaceAll("PLAYER", player.getName())), command.delaySeconds * 1000);
				if (command.consumeItem)
					remove = true;
			}
			if (remove)
			{
				ItemStack heldStack = player.getItemInHand(HandTypes.MAIN_HAND).get();
				if (heldStack.getQuantity() == 1)
					player.setItemInHand(HandTypes.MAIN_HAND, null);
				else
				{
					heldStack.setQuantity(heldStack.getQuantity() - 1);
					player.setItemInHand(HandTypes.MAIN_HAND, heldStack);
				}
			}
		}
//		ItemCommands.timer.schedule(new CommandTask(event.getItemStack().getType().getName(), player.getName()), 0);
	}
}
