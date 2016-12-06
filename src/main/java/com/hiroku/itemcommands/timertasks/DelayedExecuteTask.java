package com.hiroku.itemcommands.timertasks;

import java.util.TimerTask;

import net.minecraftforge.fml.common.FMLCommonHandler;

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
		FMLCommonHandler.instance().getMinecraftServerInstance().commandManager.executeCommand(
				FMLCommonHandler.instance().getMinecraftServerInstance(), command);
	}

}
