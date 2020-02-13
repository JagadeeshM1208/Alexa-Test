package com.tavant.amazonAlexa;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.amazon.ask.Skill;
import com.amazon.ask.builder.StandardSkillBuilder;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.model.services.util.JacksonSerializer;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.util.IOUtils;
import com.tavant.amazonAlexa.handlers.CancelandStopIntentHandler;
import com.tavant.amazonAlexa.handlers.FallbackIntentHandler;
import com.tavant.amazonAlexa.handlers.HelloWorldIntentHandler;
import com.tavant.amazonAlexa.handlers.HelpIntentHandler;
import com.tavant.amazonAlexa.handlers.LaunchRequestHandler;
import com.tavant.amazonAlexa.handlers.SessionEndedRequestHandler;

public class SampleRequestHandler implements RequestStreamHandler{
	
	private final Skill skill;
	private final JacksonSerializer serializer;
	public SampleRequestHandler(){
		skill = new StandardSkillBuilder()
				.addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(), 
                        new FallbackIntentHandler())
                .withSkillId("amzn1.ask.skill.c1061c17-d35f-49eb-aec7-50733fee2996")
				.build();
		serializer = new JacksonSerializer();
	}

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context arg2) throws IOException {
		String request  = IOUtils.toString(input);
		RequestEnvelope deserializedReq = new JacksonSerializer().deserialize(request, RequestEnvelope.class);
		ResponseEnvelope responseEnvolep = skill.invoke(deserializedReq);
		byte[] response = serializer.serialize(responseEnvolep).getBytes(StandardCharsets.UTF_8);
		output.write(response);		
	}

}
