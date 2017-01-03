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

public class Get implements CommandExecutor
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
				ItemBindingExecutor.sendMessage(src, "&eCommands for:&r " + item);
				if (commands.size() == 0)
					ItemBindingExecutor.sendMessage(src, "&eNone.");
				else
					for (BoundCommand command : commands)
						ItemBindingExecutor.sendMessage(src, "&e/" + command.command + "&6. Delay: " + command.delaySeconds);
			}
		}
		return CommandResult.success();
	}
}