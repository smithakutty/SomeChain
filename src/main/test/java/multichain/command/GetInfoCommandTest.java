/*
 * Copyright (C) 2017 Worldline, Inc.
 *
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * https://github.com/SimplyUb/MultiChainJavaAPI/blob/master/LICENSE
 *
 */
package multichain.command;

import multichain.command.MultiChainCommand;
import multichain.command.MultichainException;

/**
 * @author Ub - H. MARTEAU
 * @version 2.0
 */
public class GetInfoCommandTest {
	static MultiChainCommand multiChainCommand;

	/**
	 *
	 */
	public GetInfoCommandTest() {
		// TODO Auto-generated constructor stub
	}

	private static void testgetInfo() {
		String result = "";
		try {
			result = multiChainCommand.getChainCommand().getInfo();
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Result :" + result);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("--- Start of AddressCommandTest ---");

		// BlockChain TestCommand has to be created and started before
		multiChainCommand = new MultiChainCommand("localhost", "2748", "multichainrpc",
				"7CaUnmUEdwh3umvABeDLReKb57XkJByZvk1tDNeoYCG3");

		testgetInfo();

		System.out.println("--- End of AddressCommandTest ---");
	}

}
