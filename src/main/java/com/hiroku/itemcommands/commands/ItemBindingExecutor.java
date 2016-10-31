package com.hiroku.itemcommands.commands;

import java.util.ArrayList;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class ItemBindingExecutor extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "itembinding";
	}

	@Override
	public ArrayList<String> getCommandAliases()
	{
		ArrayList<String> aliases = new ArrayList<String>();
		aliases.add("itemBinding");
		aliases.add("ItemBinding");
		aliases.add("Itembinding");
		return aliases;
	}
	
	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "/itembinding <get | set <command> | remove>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		//TODO execute
	}

}
