package com.nebby.grandmadown;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class GrandmaDownSpeechletHandler extends SpeechletRequestStreamHandler 
{

    private static final Set<String> supportedApplicationIds = new HashSet<String>();
    
    static 
    {
        supportedApplicationIds.add("amzn1.ask.skill.53a91f6c-6949-44f4-b6b0-0569564280db");
    }
	
	public GrandmaDownSpeechletHandler() 
	{
		super(new GrandmaDownSpeechlet(), supportedApplicationIds);
	}

}
