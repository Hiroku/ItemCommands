package com.hiroku.itemcommands.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import com.hiroku.itemcommands.data.BindingRegistry;

public class Reload implements CommandExecutor
{
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
	{
		BindingRegistry.load();
		src.sendMessage(Text.of(TextColors.DARK_GREEN, "Successfully reloaded the bindings from", TextColors.RESET, " config/itemcommands.conf"));
		return CommandResult.success();
	}
}
