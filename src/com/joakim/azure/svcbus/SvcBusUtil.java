package com.joakim.azure.svcbus;

import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.services.servicebus.ServiceBusConfiguration;
import com.microsoft.windowsazure.services.servicebus.ServiceBusContract;
import com.microsoft.windowsazure.services.servicebus.ServiceBusService;
import com.microsoft.windowsazure.services.servicebus.models.BrokeredMessage;
import com.microsoft.windowsazure.services.servicebus.models.CreateQueueResult;
import com.microsoft.windowsazure.services.servicebus.models.ListQueuesResult;
import com.microsoft.windowsazure.services.servicebus.models.QueueInfo;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveMessageOptions;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveQueueMessageResult;

/**
 * This class encapsulates all access to the Azure Service Bus.
 * 
 * Chris Joakim, 2016/04/20
 */
public class SvcBusUtil {

	// Instance variables:
	private String namespace = null;
	private String queueName = null;
	private String keyName   = null;
	private String keyValue  = null;

	// Azure-specific instance variables:
	private Configuration      config  = null;
	private ServiceBusContract service = null;

	public SvcBusUtil() {

		namespace = System.getenv("AZURE_SERVICEBUS_NAMESPACE");
		queueName = System.getenv("AZURE_SERVICEBUS_QUEUE1");
		keyName   = System.getenv("AZURE_SERVICEBUS_KEY_NAME");
		keyValue  = System.getenv("AZURE_SERVICEBUS_ACCESS_KEY");
		
		log("constructor, namespace", namespace);
		log("constructor, queueName", queueName);
		log("constructor, keyName  ", keyName);
		log("constructor, keyValue ", keyValue);

		config = ServiceBusConfiguration.configureWithSASAuthentication(
					namespace, keyName, keyValue, ".servicebus.windows.net");
		service = ServiceBusService.create(config);
	}

	public void displayQueueInfo() {

		try {
			ListQueuesResult listResult = this.service.listQueues();
			java.util.List<QueueInfo> list = listResult.getItems();
			for (int i = 0; i < list.size(); i++) {
				QueueInfo qi = list.get(i);
				String path = qi.getPath();
				long count = qi.getMessageCount();
				log("displayQueueInfo", "" + path + " -> " + count);
			}
		}
		catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void putMessageOnQueue(String msgBody, String qname) {

		String q = this.normalizedQueueName(qname);
		log("putMessageOnQueue", q + " -> " + msgBody);

		try {
			BrokeredMessage message = new BrokeredMessage(msgBody);
			service.sendQueueMessage(q, message);
		}
		catch (ServiceException e) {
			System.out.print("ServiceException encountered: ");
			System.out.println(e.getMessage());
		}
	}

	public String readMessageFromQueue(String qname) {

		String q = this.normalizedQueueName(qname);
		String msg = null;

		try {
			ReceiveMessageOptions opts = ReceiveMessageOptions.DEFAULT;
			ReceiveQueueMessageResult resultQM = service.receiveQueueMessage(q, opts);
			BrokeredMessage message = resultQM.getValue();
			if (message != null) {
				int numRead = 0;
				StringBuilder sb = new StringBuilder();

				while (numRead != -1) {
					byte[] bytes = new byte[256];
					numRead = message.getBody().read(bytes);
					sb.append(new String(bytes));
				}
				msg = sb.toString();
			}
		}
		catch (Exception e) {
			System.out.print("ServiceException encountered: ");
			System.out.println(e.getMessage());
		}
		return msg;
	}

	// private methods follow

	private String normalizedQueueName(String qname) {

		if (qname == null) {
			return this.queueName;
		} 
		else {
			return qname;
		}
	}

	private void createQueue(String qname) {

		log("createQueue, qname", qname);

		try {
			QueueInfo queueInfo = new QueueInfo(qname);
			CreateQueueResult result = service.createQueue(queueInfo);
		} catch (ServiceException e) {
			System.out.print("ServiceException encountered: ");
			System.out.println(e.getMessage());
		}
	}

	private void log(String ctxt, Object obj) {

		System.out.println("SvcBusUtil - " + ctxt + ": " + obj);
	}
}
