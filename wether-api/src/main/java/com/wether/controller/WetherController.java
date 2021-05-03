package com.wether.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@RestController
@RequestMapping("api")
public class WetherController {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
    LoadingCache<String, String> cache;
	
	@RequestMapping(method=RequestMethod.GET, path="/get/{cityName}")
	public ResponseEntity<String> getWetherByCity(@PathVariable String cityName){
		System.out.println("Received->"+cityName);
		cache.getUnchecked(cityName);
		String val = cache.getIfPresent(cityName);
		String r = null;
		if(val == null  || val.equals(cityName.toUpperCase())){
			String url = "http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid=c895fa0923ff60aa3897ce7e367492d6";
			r = restTemplate.getForObject(url, String.class);
			cache.put(cityName, r);
		}else{
			r = val;
		}
		ResponseEntity<String> rtn = new ResponseEntity<>(r,null, HttpStatus.OK);
		return rtn;
	}

	@RequestMapping(method=RequestMethod.GET, path="/get/{cityName}/{country}")
	public ResponseEntity<String> getWetherByCityAndCountry(@PathVariable String cityName, @PathVariable String country){
		System.out.println("Received->"+cityName+" "+country);
		String key = cityName+country;
		cache.getUnchecked(key);
		String val = cache.getIfPresent(key);
		String r = null;
		if( val == null || val.equals(key.toUpperCase())){
			String url = "http://api.openweathermap.org/data/2.5/weather?q="+cityName+","+country+"&appid=c895fa0923ff60aa3897ce7e367492d6";
			r = restTemplate.getForObject(url, String.class);
			cache.put(key, r);
		}else{
			r = val;
		}
		ResponseEntity<String> rtn = new ResponseEntity<>(r,null, HttpStatus.OK);
		return rtn;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/get/latlon/{lat}/{lon}")
	public ResponseEntity<String> getWetherByLatAndLon(@PathVariable String lat, @PathVariable String lon){
		System.out.println("Received->"+lat +" "+lon);
		String key = lat+lon;
		cache.getUnchecked(key);
		String val = cache.getIfPresent(key);
		String r = null;
		if( val == null || val.equals(key.toUpperCase())){
			String url = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=c895fa0923ff60aa3897ce7e367492d6";
			r = restTemplate.getForObject(url, String.class);
			cache.put(key, r);
		}else{
			r = val;
		}
		ResponseEntity<String> rtn = new ResponseEntity<>(r,null, HttpStatus.OK);
		return rtn;
	}
}
