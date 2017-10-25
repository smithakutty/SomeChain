/*
 * Copyright (C) 2017 Worldline, Inc.
 *
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * https://github.com/SimplyUb/MultiChainJavaAPI/blob/master/LICENSE
 *
 */
package multichain.command;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author Ub - H. MARTEAU
 * @version 2.0
 */
public class MyTest {

	/**
	 *
	 */
	public MyTest() {
		// TODO Auto-generated constructor stub
	}

	private static JSONObject invokeRPC() {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		System.out.println("invokeRPC");
		JSONObject json = new JSONObject();
		JSONObject responseJsonObj = null;
		try {
			httpclient.getCredentialsProvider().setCredentials(
					new AuthScope("172.17.0.2", 6742),
					new UsernamePasswordCredentials("multichainrpc",
							"LxvTAi7R5NAFV9igrzJbTkmUd48LXLu6C2GgV2g5Nry"));
			StringEntity myEntity = new StringEntity(json.toJSONString());
			System.out.println(json.toString());
			HttpPost httppost = new HttpPost(
					"http://multichainrpc:LxvTAi7R5NAFV9igrzJbTkmUd48LXLu6C2GgV2g5Nry@172.17.0.2:6742");
			httppost.setEntity(myEntity);

			System.out.println("executing request" + httppost.getRequestLine());
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			if (entity != null) {
				System.out.println("Response content length: "
						+ entity.getContentLength());
				// System.out.println(EntityUtils.toString(entity));
			}
			JSONParser parser = new JSONParser();
			responseJsonObj = (JSONObject) parser.parse(EntityUtils
					.toString(entity));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseJsonObj;
	}

	public static void main(String[] args) {
		System.out.println("--- Start of AddressCommandTest ---");

		invokeRPC();

	}

}
