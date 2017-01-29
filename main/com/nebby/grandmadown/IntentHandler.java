package com.nebby.grandmadown;

import com.amazon.speech.slu.Intent;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

public class IntentHandler 
{
	public SpeechletResponse addPill(Intent intent, Session session) {
		SpeechOutput output = new SpeechOutput();
		output.text("Okay, I will add it to your list of medication. What type of medication is it");

		SpeechOutput reprompt = new SpeechOutput();
		reprompt.text("What type of medication is it");

		return newAskResponse(output.toString(), reprompt.toString());
	}

	public SpeechletResponse namePill(Intent intent, Session session) {
		SpeechOutput output = new SpeechOutput();
		output.text("Okay, I've update your medication list. What time would you like to take it");

		SpeechOutput reprompt = new SpeechOutput();
		reprompt.text("What time would you like to take it");

		return newAskResponse(output.toString(), reprompt.toString());
	}

	public SpeechletResponse timePill(Intent intent, Session session) {
		SpeechOutput output = new SpeechOutput();

		try
		{
			String url = "http://ec2-54-172-226-18.compute-1.amazonaws.com:8888/addPill";

			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);


			HttpResponse res = client.execute(request);
			String response = EntityUtils.toString(res.getEntity(), "UTF-8");
			
			output.text("Ok, adding " + response + " to your pill list. I will do my best to remind you!");
		}
		catch(Exception e)
		{
			output.text("I'm sorry, I couldn't get your information to the cloud.");
		}

		return newTellResponse(output.toString());
	}

	public SpeechletResponse takePills(Intent intent, Session session)
	{

		SpeechOutput output = new SpeechOutput();
		
		try
		{
			String url = "http://ec2-54-172-226-18.compute-1.amazonaws.com:8888/takePills";

			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);


			HttpResponse res = client.execute(request);
			String response = EntityUtils.toString(res.getEntity(), "UTF-8");
			
			output.text("Glad to hear, remember that you'll need to take it again in " + response);
		}
		catch(Exception e)
		{
			output.text("I'm sorry, I couldn't get your information to the cloud. Please repeat.");
		}

		return newTellResponse(output.toString());

	}

	public SpeechletResponse pillsTaken(Intent intent, Session session)
	{
		SpeechOutput output = new SpeechOutput();
		
		try
		{
			String url = "http://ec2-54-172-226-18.compute-1.amazonaws.com:8888/pillsTaken";

			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);


			HttpResponse res = client.execute(request);
			String response = EntityUtils.toString(res.getEntity(), "UTF-8");
			
			if(response.length() > 0)
				output.text("You have taken " + response);
			else
				output.text("You have taken no pills today.");
		}
		catch(Exception e)
		{
			output.text("I'm sorry, I couldn't get your information to the cloud.");
		}

		return newTellResponse(output.toString());
	}

	public SpeechletResponse newTellResponse(String output) {
		SsmlOutputSpeech outputSpeech = new SsmlOutputSpeech();
		outputSpeech.setSsml(output);

		return SpeechletResponse.newTellResponse(outputSpeech);
	}

	public SpeechletResponse newAskResponse(String output, String reprompt)
	{
		SsmlOutputSpeech outputSpeech, repromptOutputSpeech;

		outputSpeech = new SsmlOutputSpeech();
		outputSpeech.setSsml(output);

		repromptOutputSpeech = new SsmlOutputSpeech();
		repromptOutputSpeech.setSsml(reprompt);

		Reprompt r = new Reprompt();
		r.setOutputSpeech(repromptOutputSpeech);

		return SpeechletResponse.newAskResponse(outputSpeech, r);
	}

}
