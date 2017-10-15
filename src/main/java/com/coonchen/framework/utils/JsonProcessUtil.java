package com.coonchen.framework.utils;



import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonProcessUtil {
	private static ObjectMapper mapper;

	public static synchronized ObjectMapper getMapperInstance() {
		if (mapper == null) {
			mapper = new ObjectMapper();
		}
		mapper.disable(Feature.values());  
		return mapper;
	}

	public static String beanToJson(Object obj) {
		ObjectMapper mapper = JsonProcessUtil.getMapperInstance();
		try {
			mapper.configure(Feature.QUOTE_FIELD_NAMES,true);
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			return null;
		}
	}
	public static void beanToJson(Writer out,Object obj) {
		ObjectMapper mapper = JsonProcessUtil.getMapperInstance();
		try {
			mapper.configure(Feature.QUOTE_FIELD_NAMES,true);
			mapper.writeValue(out, obj);
		} catch (Exception e) {
		}
	}
	
	public static <T>T jsonToBean(String json, Class<T> valueType) {
		T obj = null;
		ObjectMapper mapper = JsonProcessUtil.getMapperInstance();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			obj = (T) mapper.readValue(json, valueType);
		} catch (Exception e) {
		}
		return obj;
	}
	public static <T> T jsonToBean(InputStream json, Class<T> valueType) {
		T obj = null;
		ObjectMapper mapper = JsonProcessUtil.getMapperInstance();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			obj = (T) mapper.readValue(json, valueType);
		} catch (JsonMappingException e) {
		} catch (JsonParseException e) {
		} catch (IOException e) {
		}finally{ 
			if(json!=null)
				try {
					json.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return obj;
	}
	public static void main(String[] args) throws IOException {
		String str = "{\"age\":100.00}";
		Map<String,String> map = (Map<String,String>)jsonToBean(str,Map.class);
		System.out.println(map);
		
			/*String s = beanToJson(list);
			System.out.println(s);
			
			List user1 = jsonToBean(s, List.class);
			System.out.println(user1.getClass());
			System.out.println(user1.get(0).getClass());*/
		//Map<String,Object> map = new HashMap<String,Object>();
		/*Map maperror = new HashMap();
		map.put("validate_error",maperror);
		maperror.put("name","不能为空!");
		maperror.put("sex","不能为空!");*/
		/*List list = new ArrayList();
		list.add("jackson");
		list.add("simon");
		map.put("name",list);
		list = new ArrayList();
		list.add("china");
		list.add("usa");
		map.put("nationality",list);*/
		//map.put("username",new String[]{"3","2"});
/*		map.put("age","1111");
		System.out.println(beanToJson(map));*/
		/*map.put("321", "value");
		PrintWriter out = new PrintWriter(System.out);
		T t = new T();
		t.setKey("keys");
		t.setValue("values");
		
		T t1 = new T();
		t1.setKey("keys1");
		t1.setValue("values1\nf}\"");
		List list = new ArrayList();
		list.add(t);
		list.add(t1);
		beanToJson(out,list);*/
	/*	if (mapper == null) {
			mapper = new ObjectMapper();
		}
		JsonEncoding encoding = JsonEncoding.UTF8;
		mapper.disable(DeserializationConfig.Feature.values());  
		JsonGenerator generator = mapper.getJsonFactory().createJsonGenerator(System.out,encoding);

		if (mapper.isEnabled(DeserializationConfig.Feature.INDENT_OUTPUT)) {
			generator.useDefaultPrettyPrinter();
		}

		if (prefixJson) {
			generator.writeRaw("{} && ");
//		}
			map.put("test", "321");
	 mapper.writeValue(generator,map);*/
		
	}
	public static class T{
		private String key;
		private String value;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public static void main(String[] args) {
			User user = new User();
			user.setAge(12);
			//user.setUsername("name");
			PrintWriter out = new PrintWriter(System.out);
			JsonProcessUtil.beanToJson(out,user);
			String s  =  "{\"username\":\"name\",\"age\":12}";
			User user1= JsonProcessUtil.jsonToBean(s,User.class);
			System.out.println(user1.getAge());
			
			

		}
	} 
	public static class User{
//		private String username;
		private int age;
		/*public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}*/
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	}
}
