package com.nebby.grandmadown;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.nebby.grandmadown.network.ClientNetwork;

public class GrandmaDownSpeechlet implements Speechlet 
{

	private Map<String, Method> intentCaller = new HashMap<String, Method>();
	private IntentHandler intentHandler = null;
	private ClientNetwork network = null;

	@Override
	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException 
	{
		intentHandler = new IntentHandler();
		System.out.println("started session");
		/*
		System.out.println("ESTABLISHING");
		try
		{
			network.connect("ec2-54-172-226-18.compute-1.amazonaws.com", 8080);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("FAILED!");
		}

		System.out.println("CONNECTED");
		network.update();
		network.validate(true);
		System.out.println("SECURED");
		 */
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException
	{
		System.out.println("Launched");

		return getWelcomeResponse();
	}

	@Override
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException
	{
		Intent intent = request.getIntent();
		System.out.println(intent.getName());

		if(intent.getName().equals("AddPill"))
		{
			return intentHandler.addPill(intent, session);
		}
		else if(intent.getName().equals("NamePill"))
		{
			return intentHandler.namePill(intent, session);
		}
		else if(intent.getName().equals("TimePill"))
		{
			return intentHandler.timePill(intent, session);
		}
		else if(intent.getName().equals("TakePills"))
		{
			return intentHandler.takePills(intent, session);
		}
		else if(intent.getName().equals("PillsTaken"))
		{
			return intentHandler.pillsTaken(intent, session);
		}
		else if(intent.getName().equals("Clear"))
		{
			return intentHandler.pillsTaken(intent, session);
		}
		else if ("AMAZON.StopIntent".equals(intent.getName())) 
		{
			PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
			outputSpeech.setText("Goodbye");

			return SpeechletResponse.newTellResponse(outputSpeech);
		}
		else if ("AMAZON.CancelIntent".equals(intent.getName()))
		{
			PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
			outputSpeech.setText("Goodbye");

			return SpeechletResponse.newTellResponse(outputSpeech);
		}
		else
		{
			throw new SpeechletException("Invalid Intent");
		}
	}

	@Override
	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException
	{
		System.out.println("ended");
	}

	private SpeechletResponse getWelcomeResponse() 
	{
		SpeechOutput output = new SpeechOutput();
		output.text("Welcome to your personal healthcare provider");
        return intentHandler.newTellResponse(output.toString());
    }

}
