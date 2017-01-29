package com.nebby.grandmadown;

public class SpeechOutput 
{
	
	private StringBuilder speechOutput;
	
	public SpeechOutput()
	{
		speechOutput = new StringBuilder();
	}
	
	public void text(String text)
	{
		
	}
	
	public void audio(String audioSrc)
	{
		
	}
	
	public void pause(Strength strength, int milliseconds)
	{
		
	}
	
	public void paragraph(String text)
	{
		
	}
	
	public void say(Interpret interpret, String content)
	{
		
	}
	
	public void date(String format, String content)
	{
		
	}
	
	public void pronouce(Syntax type, String content)
	{
		
	}
	
	public String toString()
	{
		return "<speak>" + speechOutput.toString() + "</speech>";
	}
	
	public static enum Strength
	{
		NONE("none"), VERY_WEAK("x-weak"), WEAK("weak"), MEDIUM("medium"), STRONG("strong"), VERY_STRONG("x-strong");
		
		private final String content;
		
		private Strength(String title)
		{
			content = title;
		}
	}

	public static enum Interpret
	{
		SPELT("characters"), NUMBER("cardinal"), ORDINAL("ordinal"), DIGITS("digits"), 
		FRACTION("fraction"), UNIT("unit"), TIME("time"), TELEPHONE("telephone"),
		ADDRESS("address");
		
		private final String content;
		
		private Interpret(String title)
		{
			content = title;
		}
	}
	
	public static enum Syntax
	{
		
	}
	
}
