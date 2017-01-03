package com.hiroku.itemcommands.commands;

import java.util.ArrayList;
import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import com.hiroku.itemcommands.data.BindingRegistry;
import com.hiroku.itemcommands.data.BoundCommand;

public class Remove implements CommandExecutor
{
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
	{
		if (!(src instanceof Player))
			src.sendMessage(Text.of(TextColors.DARK_RED, "You can't use this command unless you're in-game"));
		else
		{
			Optional<ItemStack> optItemStack = ((Player)src).getItemInHand(HandTypes.MAIN_HAND);
			if (!optItemStack.isPresent())
				src.sendMessage(Text.of(TextColors.DARK_RED, "You're not holding anything"));
			else
			{
				String item = optItemStack.get().getItem().getName();
				ArrayList<BoundCommand> commands = BindingRegistry.getCommands(item);
				int amount = commands.size();
				if (amount == 0)
					src.sendMessage(Text.of(TextColors.RED, "No bindings exist for ", TextColors.RESET, item));
				else
				{
					BindingRegistry.clearBindings(item);
					BindingRegistry.save();
					src.sendMessage(Text.of(TextColors.DARK_GREEN, "Successfully cleared all " + amount + " binding/s from ", TextColors.RESET, item));
				}
			}
		}
			
		return CommandResult.success();
	}
}
