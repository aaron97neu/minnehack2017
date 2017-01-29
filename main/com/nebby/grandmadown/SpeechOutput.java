package com.nebby.grandmadown;

public class SpeechOutput 
{
	
	private StringBuilder speechOutput;
	
	public SpeechOutput()
	{
		speechOutput = new StringBuilder();
	}
	
	/**
	 * Default way to say some text.
	 * @param text
	 */
	public void text(String text)
	{
		speechOutput.append(text);
	}
	
	/**
	 * Play an mp3 audio piece.
	 * <audio src="AUDIO_SRC" />
	 * @param audioSrc : location of the mp3 file
	 */
	public void audio(String audioSrc)
	{
		speechOutput.append("<audio src=\"");
		speechOutput.append(audioSrc);
		speechOutput.append("\" />");
	}
	
	/**
	 * Pause for determined amount of time before continuing.
	 * <break strength="STRENGTH_LEVEL" />
	 * @param strength : enum variable with choices of NONE, VERY_WEAK, WEAK, MEDIUM, STRONG, VERY_STRONG
	 */
	public void pause(Strength strength)
	{
		speechOutput.append("<break strength=\"");
		speechOutput.append(strength.content);
		speechOutput.append("\" />");
	}
	
	/**
	 * Pause for determined amount of time before continuing.
	 * <break time="clamp(TIME_AMOUNT, 0, 10000)" />
	 * @param milliseconds : time in milliseconds to wait, max is 10 seconds.
	 */
	public void pause(int milliseconds)
	{
		speechOutput.append("<break time=\"");
		speechOutput.append(Math.max(Math.min(milliseconds, 10000), 0));
		speechOutput.append("\" />");
	}
	
	/**
	 * Initialize paragraph, has strong pause afterwards.
	 */
	public void startParagraph()
	{
		speechOutput.append("<p>");
	}
	
	/**
	 * End paragraph with strong pause.
	 */
	public void endParagraph()
	{
		speechOutput.append("</p>");
	}
	
	/**
	 * Say content inside a certain way.
	 * <say-as interpret-as="INTERPRET_TYPE">CONTENT</say-as>
	 * @param interpret : enum type with choices of SPELT, NUMBER, ORDINAL, DIGITS, FRACTION,
	 * UNIT, TIME, TELEPHONE, ADDRESS
	 * @param content : text to be read
	 */
	public void say(Interpret interpret, String content)
	{
		speechOutput.append("<say-as interpret-as\"");
		speechOutput.append(interpret.content);
		speechOutput.append("\">");
		speechOutput.append(content);
		speechOutput.append("</say-as>");
	}
	
	/**
	 * Say content with date format.
	 * @param format : format in any combination of d (day), m (month), and y (year)
	 * @param content : date to be read, make sure to include "/" in between portions of date.
	 */
	public void date(String format, String content)
	{
		speechOutput.append("<say-as interpret-as=\"date\" format=\"");
		speechOutput.append(format);
		speechOutput.append("\">");
		speechOutput.append(content);
		speechOutput.append("</say-as>");
	}
	
	/**
	 * Pronounce text (most likely a single word) in a certain format.
	 * @param syntax : type of pronounciation with types NOUN, PAST, VERB, OTHER (like bass has two meanings).
	 * @param content : word to be said.
	 */
	public void pronounce(Syntax syntax, String content)
	{
		speechOutput.append("<w role=\"");
		speechOutput.append(syntax.content);
		speechOutput.append("\">");
		speechOutput.append(content);
		speechOutput.append("</w>");
	}
	
	/**
	 * Call to return the full speech output.
	 */
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
		VERB("ivona:VB"), PAST("ivona:VBD"), NOUN("ivona:NN"), OTHER("ivona:SENSE_1");
		
		private final String content;
		
		private Syntax(String title)
		{
			content = title;
		}
	}
	
}
