package com.hiroku.itemcommands.commands;

import java.util.ArrayList;

import com.hiroku.itemcommands.data.BindingRegistry;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeHooks;

public class ItemBindingExecutor extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "itembinding";
	}
	
	private static ArrayList<String> options = new ArrayList<String>();
	
	static
	{
		options.add("get");
		options.add("add");
		options.add("remove");
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
		return "/itembinding <get | add <command> | remove>";
	}

	/**
	 * Sends a message to a CommandSender if the parameter is not a blank String. Copied from ObliqueCore
	 * @param sender The console or the EntityPlayerMP instance representing the player the message should be delivered to
	 * @param message The message itself or blank if no message should be delivered
	 */
	private void sendMessage(ICommandSender sender, String message) 
	{
		if (message != null && !message.equals(""))
			sender.addChatMessage(ForgeHooks.newChatWithLinks(embedColours(message)));
	}
	/**
	 * Conversion method to replace the ampersand colours with the EnumTextFormatting format. Copied from ObliqueCore
	 * @param str The String that needs to be operated on
	 * @return the modified String
	 */
	private String embedColours(String str)
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
		str = str.replaceAll("&0", TextFormatting.BLACK + "");
		str = str.replaceAll("&1", TextFormatting.DARK_BLUE + "");
		str = str.replaceAll("&2", TextFormatting.DARK_GREEN + "");
		str = str.replaceAll("&3", TextFormatting.DARK_AQUA + "");
		str = str.replaceAll("&4", TextFormatting.DARK_RED + "");
		str = str.replaceAll("&5", TextFormatting.DARK_PURPLE + "");
		str = str.replaceAll("&6", TextFormatting.GOLD + "");
		str = str.replaceAll("&7", TextFormatting.GRAY + "");
		str = str.replaceAll("&8", TextFormatting.DARK_GRAY + "");
		str = str.replaceAll("&9", TextFormatting.BLUE + "");
		str = str.replaceAll("&a", TextFormatting.GREEN + "");
		str = str.replaceAll("&b", TextFormatting.AQUA + "");
		str = str.replaceAll("&c", TextFormatting.RED + "");
		str = str.replaceAll("&d", TextFormatting.LIGHT_PURPLE + "");
		str = str.replaceAll("&e", TextFormatting.YELLOW + "");
		str = str.replaceAll("&f", TextFormatting.WHITE + "");
		
		str = str.replaceAll("&k", TextFormatting.OBFUSCATED + "");
		str = str.replaceAll("&l", TextFormatting.BOLD + "");
		str = str.replaceAll("&m", TextFormatting.STRIKETHROUGH + "");
		str = str.replaceAll("&n", TextFormatting.UNDERLINE + "");
		str = str.replaceAll("&o", TextFormatting.ITALIC + "");
		
		str = str.replaceAll("&r", TextFormatting.RESET + "");
		
		return str;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		if (sender instanceof MinecraftServer)
			sendMessage(sender, "&cYou can't run this command from the console, dummy");
		else if (args.length == 0)
		{
			sendMessage(sender, "&cNot enough parameters.");
			sendMessage(sender, getCommandUsage(sender));
		}
		else
		{
			if (options.contains(args[0].toLowerCase()))
			{
				EntityPlayerMP player = (EntityPlayerMP)sender;
				if (player.getHeldItemMainhand() == null)
					sendMessage(sender, "&cYou aren't holding any item");
				else
				{
					String item = player.getHeldItemMainhand().getItem().getUnlocalizedName();
					switch(args[0].toLowerCase())
					{
						case "get": 
						{
							ArrayList<String> commands = BindingRegistry.getCommands(item);
							sendMessage(sender, "&eCommands for: " + item);
							if (commands.size() == 0)
								sendMessage(sender, "&eNone.");
							else
								for (String command : commands)
									sendMessage(sender, "&e" + command);
						}
						case "add":
						{
							if (args.length < 2)
							{
								sendMessage(sender, "&cNot enough arguments");
								sendMessage(sender, getCommandUsage(sender));
							}
							else
							{
								String command = args[1].replace("/", "");
								for (int i = 2 ; i < args.length ; i++)
									command += " " + args[i];
								BindingRegistry.addBinding(item, command);
								BindingRegistry.save();
								sendMessage(sender, "&2Successfully registered command binding.");
								execute(server, sender, new String[]{"get"});
							}
						}
						case "remove": 
						{
							ArrayList<String> commands = BindingRegistry.getCommands(item);
							int amount = commands.size();
							if (amount == 0)
								sendMessage(sender, "&cNo bindings exist for " + item);
							else
							{
								BindingRegistry.clearBindings(item);
								BindingRegistry.save();
								sendMessage(sender, "&2Successfully cleared all " + amount + " binding/s from " + item);
							}
						}
					}
				}
			}
			else
			{
				sendMessage(sender, "&cInvalid parameter/s");
				sendMessage(sender, getCommandUsage(sender));
			}
		}
	}

}
