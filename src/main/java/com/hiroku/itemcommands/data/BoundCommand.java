package com.hiroku.itemcommands.data;

public class BoundCommand
{
	public final String command;
	public final int delaySeconds;
	
	public BoundCommand(String command, int delaySeconds)
	{
		this.command = command;
		this.delaySeconds = delaySeconds;
	}
}
