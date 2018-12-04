package com.example.utils;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.util.Map;

public class HttpClientUtil {

	public static PostMethod  createPostXml(Map<String, Object> params) throws Exception {
		// 创建POST请求
		PostMethod postMethod = new PostMethod(params.get("url").toString());
		RequestEntity se=new StringRequestEntity(params.get("data").toString(), "application/xml", "GBK");
		postMethod.setRequestEntity(se);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		//设置超时的时间
		int timeout=6000;
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);

		return postMethod;
	}

	public static PostMethod  createPostJson(Map<String, String> params) throws Exception {
		// 创建POST请求
		PostMethod postMethod = new PostMethod(params.get("url"));
		RequestEntity se = new StringRequestEntity(params.get("data"), "application/json", "UTF-8");
		postMethod.setRequestEntity(se);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		//设置超时的时间
		int timeout=6000;
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);

		return postMethod;
	}

	public static PostMethod  createPostJson(String data,String url) throws Exception {
		// 创建POST请求
		PostMethod postMethod = new PostMethod(url);
		RequestEntity se = new StringRequestEntity(data, "application/json", "UTF-8");
		postMethod.setRequestEntity(se);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		//设置超时的时间
		int timeout=60000;
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);

		return postMethod;
	}

	public static PostMethod  createPost(Map<String, Object> params) throws Exception {
		// 创建POST请求
		PostMethod postMethod = new PostMethod(params.get("url").toString());
		new StringRequestEntity(params.get("data").toString(), "application/xml", "GBK");
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		//设置超时的时间
		int timeout=6000;
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);

		return postMethod;
	}

}
