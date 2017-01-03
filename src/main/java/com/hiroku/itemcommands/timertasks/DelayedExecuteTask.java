package com.hiroku.itemcommands.timertasks;

import java.util.TimerTask;

import org.spongepowered.api.Sponge;

public class DelayedExecuteTask extends TimerTask
{
	private final String command;
	
	public DelayedExecuteTask(String command)
	{
		this.command = command;
	}
	@Override
	public void run()
	{
		Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command);
	}
}
