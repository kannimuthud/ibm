package com.example;

import com.example.watson.WatsonHelper;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.google.gson.JsonObject;
import com.ibm.watson.developer_cloud.assistant.v2.model.DialogRuntimeResponseGeneric;
import com.ibm.watson.developer_cloud.assistant.v2.model.MessageResponse;

/**
 * Hello FunctionApp use below command to create cloud functions
 * ibmcloud fn action create inventoryapi target/demo-function.jar --main com.example.FunctionApp
 * use below command to update it
 * ibmcloud fn action update inventoryapi target/demo-function.jar --main com.example.FunctionApp
 */
public class FunctionApp {
  public static JsonObject main(JsonObject args) {
	  
    JsonObject response = new JsonObject();
    
    WatsonHelper helper = new WatsonHelper();
    String input = args.get("input").toString();
    try {
    	MessageResponse responseMsg = helper.message(input);
    	for ( DialogRuntimeResponseGeneric generic : responseMsg.getOutput().getGeneric()) {
    		
    		response.addProperty("response", generic.getText());
    		
    	}
    	//response.addProperty("messageContext", responseMsg.getContext());
    }
    catch(Exception ex) {
    	ex.printStackTrace();
    }
    return response;
  }
}
