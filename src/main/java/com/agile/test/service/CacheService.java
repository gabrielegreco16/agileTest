package com.agile.test.service;

import java.io.IOException;

import okhttp3.ResponseBody;

public interface CacheService {

	ResponseBody authorize() throws Exception;

	ResponseBody findImageByPage(String page) throws  Exception;
}
