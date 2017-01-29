package com.nebby.grandmadown;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

public class IntentHandler 
{
	public SpeechletResponse addPill(Intent intent, Session session)
	{
		SpeechOutput output = new SpeechOutput();
		output.text("Okay, I will add it to your list of medication");


		SpeechOutput reprompt = new SpeechOutput();
		reprompt.text("What time everyday should you take it");

		if (intent.getSlot("Name") == null) {
			reprompt.text("What is the name of the medication");
		}
		
		return newAskResponse(output.toString(), reprompt.toString());
	}

	public SpeechletResponse takePills(Intent intent, Session session)
	{
		SpeechOutput output = new SpeechOutput();
		output.text("Glad to hear!");


		SpeechOutput reprompt = new SpeechOutput();
		reprompt.text("You need to take your medication now");

		return newAskResponse(output.toString(), reprompt.toString());
	}

	public SpeechletResponse pillsTaken(Intent intent, Session session)
	{
		SpeechOutput output = new SpeechOutput();
		output.text("You have not taken your pills today");

		SpeechOutput reprompt = new SpeechOutput();
		reprompt.text("You need to take your medication now");

		return newAskResponse(output.toString(), reprompt.toString());
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
