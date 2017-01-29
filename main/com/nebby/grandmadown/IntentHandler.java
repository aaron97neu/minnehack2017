package com.nebby.grandmadown;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

public class IntentHandler 
{
	public SpeechletResponse addPill(Intent intent, Session session) {
		SpeechOutput output = new SpeechOutput();
		output.text("Okay, I will add it to your list of medication");

		SpeechOutput reprompt = new SpeechOutput();
		reprompt.text("What type of medication is it");

		return newAskResponse(output.toString(), reprompt.toString());
	}

	public SpeechletResponse namePill(Intent intent, Session session) {
		SpeechOutput output = new SpeechOutput();
		output.text("Okay, I've update your medication list");

		SpeechOutput reprompt = new SpeechOutput();
		reprompt.text("How often do you need to take it");

		return newAskResponse(output.toString(), reprompt.toString());
	}

	public SpeechletResponse timePill(Intent intent, Session session) {
		SpeechOutput output = new SpeechOutput();
		output.text("Okay, what time would you like to take it");

		return newTellResponse(output.toString());
	}

	public SpeechletResponse takePills(Intent intent, Session session)
	{

		SpeechOutput output = new SpeechOutput();
		output.text("Glad to hear!");


		SpeechOutput reprompt = new SpeechOutput();
		reprompt.text("You need to take your medication now");

		return newTellResponse(output.toString());

	}

	public SpeechletResponse pillsTaken(Intent intent, Session session)
	{
		SpeechOutput output = new SpeechOutput();
		output.text("You have not taken your pills today");

		SpeechOutput reprompt = new SpeechOutput();
		reprompt.text("You need to take your medication now");

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
