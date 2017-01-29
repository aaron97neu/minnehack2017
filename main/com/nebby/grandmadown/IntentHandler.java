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
		output.text("This is the add Pill output");
		
		SpeechOutput reprompt = new SpeechOutput();
		output.text("This is the add Pill repromt");
		
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
