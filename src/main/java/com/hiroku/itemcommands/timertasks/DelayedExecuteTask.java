package com.hiroku.itemcommands.timertasks;

import java.util.TimerTask;

import org.spongepowered.api.Sponge;

import com.hiroku.itemcommands.ItemCommands;

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
		Sponge.getScheduler().createSyncExecutor(Sponge.getPluginManager().getPlugin(ItemCommands.MODID).get()).execute(
				()-> Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command));
	}
}
