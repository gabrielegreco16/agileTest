package com.agile.test.service;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Service
public class CacheServiceImpl implements CacheService {

	private final OkHttpClient httpClient = new OkHttpClient();

	private String token;

	@Override
	public ResponseBody authorize() throws Exception {

		// form parameters
		// form parameters
		FormBody formBody = new FormBody.Builder().add("apiKey", "23567b218376f79d9415").build();

		Request request = new Request.Builder().url("http://interview.agileengine.com/auth").post(formBody).build();

		Response response = httpClient.newCall(request).execute();
		if (!response.isSuccessful()) {
			throw new IOException("Unexpected code " + response);
		}
		return response.body();
		// Get response body

	}

	@Override
	public ResponseBody findImageByPage(String page) throws Exception {

		// form parameters
		// form parameters
		token = getToken(false);
		Response response = null;
		for (int i = 0; i < 3; i++) {
			FormBody formBody = new FormBody.Builder().add("apiKey", "23567b218376f79d9415").build();

			Request request = new Request.Builder().header(" Authorization", "Bearer " + token)
					.url("http://interview.agileengine.com/images?page=" + page).post(formBody).build();

			response = httpClient.newCall(request).execute();
			// i would be better to put a try catch no with this way..
			if (!response.isSuccessful()) {
				if (response.code() == 401) {
					token = getToken(true);
				}
				throw new IOException("Unexpected code " + response);
			}
			break;
		}
		return response.body();
		// TODO Auto-generated method stub

	}

	private String getToken(boolean newToken) throws Exception {

		if (newToken || Objects.isNull(token)) {
			return this.authorize().toString();
		} else {
			return this.token;
		}
	}

}
