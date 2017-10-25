/*
 * Copyright (C) 2017 Worldline, Inc.
 *
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * https://github.com/SimplyUb/MultiChainJavaAPI/blob/master/LICENSE
 *
 */
package multichain.command;

import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import multichain.command.MultiChainCommand;
import multichain.command.MultichainException;
import multichain.object.Block;

/**
 * @author Ub - H. MARTEAU
 * @version 2.0
 */
public class BlockCommandTest {
	static MultiChainCommand multiChainCommand;
	private static final String COMMAND_GET_BALANCE = "getbalance";
	private static final String COMMAND_GET_INFO = "getinfo";
	private static final String COMMAND_GET_NEW_ADDRESS = "getnewaddress";

	/**
	 *
	 */
	public BlockCommandTest() {
		// TODO Auto-generated constructor stub
	}

	private static JSONObject invokeRPC() {
		@SuppressWarnings("deprecation")
		
	
		
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

	/*public Double getBalance(String chainName, String account) {
		String[] params = { account };
		JSONObject json = invokeRPC(UUID.randomUUID().toString(),
				COMMAND_GET_BALANCE, Arrays.asList(params));
		return (Double) json.get("result");
	}

	public String getNewAddress(String chainName, String account) {
		String[] params = { account };
		JSONObject json = invokeRPC(UUID.randomUUID().toString(),
				COMMAND_GET_NEW_ADDRESS, Arrays.asList(params));
		return (String) json.get("result");
	}

	public JSONObject getInfo(String chainName) {
		JSONObject json = invokeRPC(UUID.randomUUID().toString(),
				COMMAND_GET_INFO, null);
		return (JSONObject) json.get("result");
	}
	*/
	private static void testgetBlock() {
		Block result = null;
		try {
			result = multiChainCommand.getBlockCommand().getBlock(1);
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Result Block(1) (default verbose) :" + result);
		System.out.println("");

		try {
			result = multiChainCommand.getBlockCommand().getBlock(1, true);
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Result Block(1) (default verbose=true) :" + result);
		System.out.println("");

		try {
			result = multiChainCommand.getBlockCommand().getBlock(1, false);
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Result Block(1) (default verbose=false) :" + result);

		System.out.println("");
		System.out.println("");

		//
		try {
			result = multiChainCommand.getBlockCommand().getBlock(
					"0074ba967e7f7c3b04ed0429b796900136d82b52ecf44d5878f1f2dfdf5e1fb8");
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out
				.println("Result Block(0074ba967e7f7c3b04ed0429b796900136d82b52ecf44d5878f1f2dfdf5e1fb8) (default verbose) :"
						+ result);
		System.out.println("");

		try {
			result = multiChainCommand.getBlockCommand().getBlock(
					"0074ba967e7f7c3b04ed0429b796900136d82b52ecf44d5878f1f2dfdf5e1fb8", true);
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out
				.println("Result Block(0074ba967e7f7c3b04ed0429b796900136d82b52ecf44d5878f1f2dfdf5e1fb8) (default verbose=true) :"
						+ result);
		System.out.println("");

		try {
			result = multiChainCommand.getBlockCommand().getBlock(
					"0074ba967e7f7c3b04ed0429b796900136d82b52ecf44d5878f1f2dfdf5e1fb8", false);
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out
				.println("Result Block(0074ba967e7f7c3b04ed0429b796900136d82b52ecf44d5878f1f2dfdf5e1fb8) (default verbose=false) :"
						+ result);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("--- Start of AddressCommandTest ---");

		// BlockChain TestCommand has to be created and started before
		multiChainCommand = new MultiChainCommand("172.17.0.3", "9243", "multichainrpc",
				"Cojw4D6uo9fLnKdGLj7qH9WNqLftqXi15hWLhXt9q2HC");

		invokeRPC();
    		
				//testgetBlock();

		System.out.println("--- End of AddressCommandTest ---" + multiChainCommand);
	}

}
