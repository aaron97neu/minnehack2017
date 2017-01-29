package com.nebby.grandmadown;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

public class IntentHandler 
{
	private Medication pill;

	public SpeechletResponse addPill(Intent intent, Session session) {
		SpeechOutput output = new SpeechOutput();
		output.text("Okay, I will add it to your list of medication. What type of medication is it");
		pill = new Medication();

		SpeechOutput reprompt = new SpeechOutput();
		reprompt.text("What type of medication is it");

		return newAskResponse(output.toString(), reprompt.toString());
	}

	public SpeechletResponse namePill(Intent intent, Session session) {
		SpeechOutput output = new SpeechOutput();
		output.text("Okay, I've update your medication list. What time would you like to take it");
		if (pill == null) {
			pill = new Medication();
		}
		pill.setName(intent.getSlot("Name").getValue());

		SpeechOutput reprompt = new SpeechOutput();
		reprompt.text("What time would you like to take it");

		return newAskResponse(output.toString(), reprompt.toString());
	}

	public SpeechletResponse timePill(Intent intent, Session session) {
		SpeechOutput output = new SpeechOutput();
		pill.setTime(intent.getSlot("Time").getValue());
		
		try
		{
			String url = "http://ec2-54-172-226-18.compute-1.amazonaws.com:8888/addPill";

			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(url);

			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("name", pill.getName()));
			params.add(new BasicNameValuePair("time", pill.getTime()));
			request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			
			HttpResponse res = client.execute(request);
			String response = EntityUtils.toString(res.getEntity(), "UTF-8");
			
			output.text("Ok, adding " + pill.getName() + " to your pill list. I will do my best to remind you!");
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
			String url = "http://ec2-54-172-226-18.compute-1.amazonaws.com:8888/takePill";

			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(url);

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
				output.text(response);
			else
				output.text("You have taken no pills today.");
		}
		catch(Exception e)
		{
			output.text("I'm sorry, I couldn't get your information to the cloud.");
		}

		return newTellResponse(output.toString());
	}

	public SpeechletResponse clear(Intent intent, Session session)
	{
		SpeechOutput output = new SpeechOutput();
		try
		{
			String url = "http://ec2-54-172-226-18.compute-1.amazonaws.com:8888/clear";

			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(url);

			HttpResponse res = client.execute(request);
			
			output.text("Cleared");
		}
		catch(Exception e)
		{
			output.text("I'm sorry, I couldn't get your information to the cloud.");
		}
		return newTellResponse(output.toString());
	}

	public SpeechletResponse checkup(Intent intent, Session session) {
		SpeechOutput output = new SpeechOutput();
		output.text("");
		try
		{
			String url = "http://ec2-54-172-226-18.compute-1.amazonaws.com:8888/clear";

			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);


			HttpResponse res = client.execute(request);

			output.text("Checkup Started!");
		}
		catch(Exception e)
		{
			output.text("I'm sorry, we couldn't connect but I am sure everything is fine");
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
