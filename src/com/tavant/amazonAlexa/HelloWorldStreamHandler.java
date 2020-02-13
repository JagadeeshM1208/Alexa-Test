/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.tavant.amazonAlexa;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.tavant.amazonAlexa.handlers.CancelandStopIntentHandler;
import com.tavant.amazonAlexa.handlers.FallbackIntentHandler;
import com.tavant.amazonAlexa.handlers.HelloWorldIntentHandler;
import com.tavant.amazonAlexa.handlers.HelpIntentHandler;
import com.tavant.amazonAlexa.handlers.LaunchRequestHandler;
import com.tavant.amazonAlexa.handlers.SessionEndedRequestHandler;


public class HelloWorldStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new FallbackIntentHandler())
                .withSkillId("amzn1.ask.skill.c1061c17-d35f-49eb-aec7-50733fee2996")
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }

}
