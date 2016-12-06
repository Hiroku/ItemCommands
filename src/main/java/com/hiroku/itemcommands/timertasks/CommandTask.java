package com.hiroku.itemcommands.timertasks;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.hiroku.itemcommands.data.BindingRegistry;
import com.hiroku.itemcommands.data.BoundCommand;

import net.minecraft.item.Item;

public class CommandTask extends TimerTask
{
	public final Item item;
	public final String playerName;
	
	public CommandTask(Item item, String playerName)
	{
		this.item = item;
		this.playerName = playerName;
	}
	
	@Override
	public void run()
	{
		ArrayList<BoundCommand> commands = BindingRegistry.getCommands(item.getUnlocalizedName());
		if (!commands.isEmpty())
			for (BoundCommand command : commands)
				new Timer().schedule(new DelayedExecuteTask(command), command.delaySeconds);
	}
}
