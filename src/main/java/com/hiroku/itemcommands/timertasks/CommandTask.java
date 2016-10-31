package com.hiroku.itemcommands.timertasks;

import java.util.ArrayList;
import java.util.TimerTask;

import com.hiroku.itemcommands.data.BindingRegistry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;

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
		ArrayList<String> commands = BindingRegistry.getCommands(item.getUnlocalizedName());
		if (!commands.isEmpty())
			for (String command : commands)
				FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager().executeCommand(
						FMLCommonHandler.instance().getMinecraftServerInstance(), command.replaceAll("PLAYER", playerName));
	}

}
