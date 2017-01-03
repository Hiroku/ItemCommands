package com.hiroku.itemcommands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import com.hiroku.itemcommands.commands.Get;
import com.hiroku.itemcommands.commands.ItemBindingExecutor;
import com.hiroku.itemcommands.commands.Reload;
import com.hiroku.itemcommands.commands.Remove;
import com.hiroku.itemcommands.commands.Add;
import com.hiroku.itemcommands.data.BindingRegistry;
import com.hiroku.itemcommands.listeners.InteractListener;

@Plugin(id = ItemCommands.MODID, name = ItemCommands.NAME, version = ItemCommands.VERSION, authors = "Hiroku", description = "Simple item command binding tool")
public class ItemCommands
{
	public static final String MODID = "itemcommands";
	public static final String NAME = "Item Commands";
	public static final String VERSION = "1.1.1";
	
	public static final Timer timer = new Timer();
	
	@Listener
	public void onPreInit(GameInitializationEvent event)
	{
		BindingRegistry.load();
		Sponge.getEventManager().registerListeners(this, new InteractListener());
		
		HashMap<List<String>, CommandSpec> subCommands = new HashMap<List<String>, CommandSpec>();
		
		subCommands.put(Arrays.asList("get", "Get"), CommandSpec.builder().permission("itemcommands.get").description(Text.of("Gets the commands bound to the currently held item")).executor(new Get()).build());
		subCommands.put(Arrays.asList("add", "Add"), CommandSpec.builder().permission("itemcommands.add").description(Text.of("Holds the ")).arguments(GenericArguments.optional(GenericArguments.integer(Text.of("Delay"))), GenericArguments.remainingJoinedStrings(Text.of("Command"))).description(Text.of("Adds a command binding to the currently held item")).executor(new Add()).build());
		subCommands.put(Arrays.asList("reload", "Reload"), CommandSpec.builder().permission("itemcommands.reload").description(Text.of("Reloads the config")).executor(new Reload()).build());
		subCommands.put(Arrays.asList("remove", "Remove"), CommandSpec.builder().permission("itemcommands.remove").description(Text.of("Removes the commands for the held item")).executor(new Remove()).build());
		
		Sponge.getCommandManager().register(this, CommandSpec.builder().children(subCommands).executor(new ItemBindingExecutor()).build(), "itembinding", "itemBinding", "Itembinding", "ItemBinding");
	}
}
