package com.hiroku.itemcommands.timertasks;

import java.util.TimerTask;

import com.hiroku.itemcommands.data.BoundCommand;

import net.minecraftforge.fml.common.FMLCommonHandler;

public class DelayedExecuteTask extends TimerTask
{
	private final BoundCommand command;
	
	public DelayedExecuteTask(BoundCommand command)
	{
		this.command = command;
	}
	@Override
	public void run()
	{
		FMLCommonHandler.instance().getMinecraftServerInstance().commandManager.executeCommand(
				FMLCommonHandler.instance().getMinecraftServerInstance(), command.command);
	}

}
