package com.is.web.test;

import java.util.UUID;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tools.web.postman.Request;

public class PostManRequestTest {

	@Test
	public void test() {
		Request req = new Request(UUID.randomUUID().toString(),"测试postman","");
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().enableComplexMapKeySerialization().create();
		String resultJson = gson.toJson(req);
		System.out.println(resultJson);
	}

	@Test
	public void utcDate(){

	}
}
