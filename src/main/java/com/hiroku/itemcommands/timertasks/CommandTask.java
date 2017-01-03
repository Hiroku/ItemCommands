package com.hiroku.itemcommands.timertasks;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.hiroku.itemcommands.data.BindingRegistry;
import com.hiroku.itemcommands.data.BoundCommand;

public class CommandTask extends TimerTask
{
	public final String item;
	public final String playerName;
	
	public CommandTask(String item, String playerName)
	{
		this.item = item;
		this.playerName = playerName;
	}
	
	@Override
	public void run()
	{
		ArrayList<BoundCommand> commands = BindingRegistry.getCommands(item);
		if (!commands.isEmpty())
			for (BoundCommand command : commands)
				new Timer().schedule(new DelayedExecuteTask(command.command.replaceAll("PLAYER", playerName)), command.delaySeconds * 1000);
	}
}
