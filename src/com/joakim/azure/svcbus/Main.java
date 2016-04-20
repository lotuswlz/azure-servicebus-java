package com.joakim.azure.svcbus;

import com.joakim.azure.svcbus.SvcBusUtil;

/**
 * This class is the entry-point to thie example code; it is invoked from the command-line.
 * 
 * Chris Joakim, 2016/04/20
 */

public class Main {

	public static void main(String[] args) {

		SvcBusUtil util = null;

		for (int i = 0; i < args.length; i++) {
			log("arg " + i + " -> " + args[i]);
		}

		if (args.length > 0) {
			String func = args[0].toLowerCase();
			String qname = null; // use default for now
			Integer count = new Integer(1);

			switch (func) {
			case "display_queue_info":
				util = new SvcBusUtil();
				util.displayQueueInfo();
				break;
			case "send_messages_to_queue":
				count = Integer.parseInt(args[1].toLowerCase());
				util = new SvcBusUtil();
				for (int i = 0; i < count; i++) {
					String msg = "java message " + i + "-" + System.currentTimeMillis();
					util.putMessageOnQueue(msg, qname);
					pause(10);
				}
				break;
			case "read_messages_from_queue":
				count = Integer.parseInt(args[1].toLowerCase());
				util = new SvcBusUtil();
				boolean continueToRead = true;
				for (int i = 0; i < count; i++) {
					if (continueToRead) {
						String msg = util.readMessageFromQueue(qname);
						if (msg == null) {
							continueToRead = false;
						}
						log("message: " + msg);
						pause(10);
					}
				}
				break;
			default:
				printUsage("uknown function: " + func);
			}
		} else {
			printUsage(null);
		}
	}

	private static void pause(long ms) {

		try {
			Thread.sleep(ms);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void printUsage(String msg) {

		if (msg != null) {
			log(msg);
		}
		log("Usage:");
		log("  display_queue_info");
		log("  send_messages_to_queue 20");
		log("  read_messages_from_queue 10");
	}

	private static void log(Object obj) {

		if (obj != null) {
			System.out.println("Main: " + obj.toString());
		}
	}
}
