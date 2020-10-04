package com.agile.test.controller;

import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agile.test.service.CacheService;

import okhttp3.ResponseBody;

@RestController
@RequestMapping("/agile/cache")
@EnableAutoConfiguration
public class AgileController {


	@Autowired 
	private CacheService cacheService;
	

	
	@RequestMapping(value="/images", method = RequestMethod.GET)
	public ResponseEntity<ResponseBody> findImagesByPage( @RequestParam(value = "page", required = true) final String page, @RequestHeader HttpHeaders headers)  {
		HttpStatus httpStatus = HttpStatus.OK;
		ResponseBody body = null;
		try {
			body = cacheService.findImageByPage(page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseBody>(body,httpStatus);
	}

	@RequestMapping(value="/auth", method = RequestMethod.GET)
	public ResponseEntity<ResponseBody> auth()  {
		HttpStatus httpStatus = HttpStatus.OK;
		ResponseBody body = null ;
		try {
			body = cacheService.authorize();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseBody>(body,httpStatus);

	}


}
