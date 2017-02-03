package com.hiroku.itemcommands.data;

public class BoundCommand
{
	public final String command;
	public final int delaySeconds;
	public final boolean consumeItem;
	
	public BoundCommand(String command, int delaySeconds, boolean consumeItem)
	{
		this.command = command;
		this.delaySeconds = delaySeconds;
		this.consumeItem = consumeItem;
	}
}
