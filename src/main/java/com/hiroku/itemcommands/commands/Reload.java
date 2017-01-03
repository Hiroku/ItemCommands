package com.hiroku.itemcommands.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import com.hiroku.itemcommands.data.BindingRegistry;

public class Reload implements CommandExecutor
{
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
	{
		BindingRegistry.load();
		ItemBindingExecutor.sendMessage(src, "&2Successfully reloaded the bindings from&r config/itemcommands.conf");
		return CommandResult.success();
	}
}
