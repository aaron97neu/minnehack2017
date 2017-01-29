package com.nebby.grandmadown;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
		/*try 
		{
			BufferedReader reader = new BufferedReader(new FileReader("com/nebby/grandmadown/speechAssets/IntentFunctions.txt"));
			String line = null;
			while((line = reader.readLine()) != null)
			{
				String[] split = line.split(":");
				String intent = split[0];
				String function = split[1];

				Method intentMethod = intentHandler.getClass().getMethod(function, Intent.class, Session.class);
				intentCaller.put(intent, intentMethod);
			}
			reader.close();
		}
		catch (IOException | NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		
		network = new ClientNetwork();
		try
		{
			network.connect("", 8888);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}*/
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException
	{
		return null;
	}

	@Override
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException
	{
		Intent intent = request.getIntent();
		/*Method intentMethod = intentCaller.get(intent.getName());

		try
		{
			return (SpeechletResponse) intentMethod.invoke(request, session);
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {}*/
		
		if(intent.getName().equals("AddPill"))
		{
			return intentHandler.addPill(intent, session);
		}
		else if(intent.getName().equals("TakePills"))
		{
			return intentHandler.takePills(intent, session);
		}
		else if(intent.getName().equals("PillsTaken"))
		{
			return intentHandler.pillsTaken(intent, session);
		}

		return null;
	}

	@Override
	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException
	{

	}

}
