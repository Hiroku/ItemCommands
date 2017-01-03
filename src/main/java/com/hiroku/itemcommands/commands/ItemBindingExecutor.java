package com.hiroku.itemcommands.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

public class ItemBindingExecutor implements CommandExecutor
{
	/**
	 * Sends a message to a CommandSender if the parameter is not a blank String. Copied from ObliqueCore
	 * @param sender The console or the EntityPlayerMP instance representing the player the message should be delivered to
	 * @param message The message itself or blank if no message should be delivered
	 */
	public static void sendMessage(CommandSource sender, String message) 
	{
		if (message != null && !message.equals(""))
			sender.sendMessage(Text.of(embedColours(message)));
	}
	/**
	 * Conversion method to replace the ampersand colours with the EnumTextFormatting format. Copied from ObliqueCore
	 * @param str The String that needs to be operated on
	 * @return the modified String
	 */
	private static String embedColours(String str)
	{
		char curColour = '-';
		char curFormat = '-';
		for (int i = 0; i < str.length(); i++)
		{
			if (str.charAt(i) == '&')
			{
				if (str.charAt(i+1) != ' ')
				{
					char code = str.charAt(i+1);
					if (code == '0' || code == '1' || code == '2' || code == '3' || code == '4' || code == '5' || code == '6'
							|| code == '7' || code == '8' || code == '9' || code == 'a' || code == 'b' || code == 'c' || code == 'd'
							|| code == 'e' || code == 'f')
					{
						curColour = code;
					}
					else if (code == 'k' || code == 'l' || code == 'm' || code == 'n' || code == 'o')
					{
						curFormat = code;
					}
					else if (code == 'r')
					{
						curColour = '-';
						curFormat = '-';
					}
				}
			}
			else if (str.charAt(i) == ' ')
			{
				String charsToAdd = "";
				if (curColour != '-')
					charsToAdd += "&" + curColour;
				if (curFormat != '-')
					charsToAdd += "&" + curFormat;
				str = str.substring(0, i) + " " + charsToAdd + str.substring(i+1);
				i += charsToAdd.length();
			}
		}
		str = str.replaceAll("&0", TextColors.BLACK + "");
		str = str.replaceAll("&1", TextColors.DARK_BLUE + "");
		str = str.replaceAll("&2", TextColors.DARK_GREEN + "");
		str = str.replaceAll("&3", TextColors.DARK_AQUA + "");
		str = str.replaceAll("&4", TextColors.DARK_RED + "");
		str = str.replaceAll("&5", TextColors.DARK_PURPLE + "");
		str = str.replaceAll("&6", TextColors.GOLD + "");
		str = str.replaceAll("&7", TextColors.GRAY + "");
		str = str.replaceAll("&8", TextColors.DARK_GRAY + "");
		str = str.replaceAll("&9", TextColors.BLUE + "");
		str = str.replaceAll("&a", TextColors.GREEN + "");
		str = str.replaceAll("&b", TextColors.AQUA + "");
		str = str.replaceAll("&c", TextColors.RED + "");
		str = str.replaceAll("&d", TextColors.LIGHT_PURPLE + "");
		str = str.replaceAll("&e", TextColors.YELLOW + "");
		str = str.replaceAll("&f", TextColors.WHITE + "");
		
		str = str.replaceAll("&k", TextStyles.OBFUSCATED + "");
		str = str.replaceAll("&l", TextStyles.BOLD + "");
		str = str.replaceAll("&m", TextStyles.STRIKETHROUGH + "");
		str = str.replaceAll("&n", TextStyles.UNDERLINE + "");
		str = str.replaceAll("&o", TextStyles.ITALIC + "");
		
		str = str.replaceAll("&r", TextColors.RESET + "");
		
		return str;
	}
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
	{
		src.sendMessage(Text.of(TextColors.GRAY, "/itembinding <get | add [delaySeconds] <command> | remove | reload>"));
		return CommandResult.success();
	}
}
