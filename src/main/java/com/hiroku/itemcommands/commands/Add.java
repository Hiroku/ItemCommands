package com.hiroku.itemcommands.commands;

import java.util.Optional;

import org.spongepowered.api.Sponge;
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

public class Add implements CommandExecutor
{
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
	{
		Optional<String> optCommand = args.getOne("Command");
		Optional<Integer> optDelay = args.getOne("Delay");
		Optional<Boolean> optConsume = args.getOne(Text.of("consumeItem"));
		
		if (!optCommand.isPresent())
			src.sendMessage(Text.of(TextColors.RED, "Error. Missing argument: Command"));
		else
		{
			String command = optCommand.get().replaceAll("/", "");
			if (src instanceof Player)
			{
				Optional<ItemStack> optItem = ((Player)src).getItemInHand(HandTypes.MAIN_HAND);
				if (optItem.isPresent())
				{
					int delaySeconds = optDelay.isPresent() ? optDelay.get() : 0;
					boolean consumeItem = optConsume.isPresent() ? optConsume.get() : false;
					BindingRegistry.addBinding(optItem.get().getItem().getName(), command, delaySeconds, consumeItem);
					BindingRegistry.save();
					src.sendMessage(Text.of(TextColors.DARK_GREEN, "Successfully registered command binding"));
					Sponge.getCommandManager().process(src, "itembinding get");
				}
				else
					src.sendMessage(Text.of(TextColors.DARK_RED, "You aren't holding an item"));
			}
			else
				src.sendMessage(Text.of(TextColors.DARK_RED, "You can't fire this command unless you are a player in game"));
		}
		return CommandResult.success();
	}

}
