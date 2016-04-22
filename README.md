# azure-servicebus-java

Azure ServiceBus example app, implemented in Java.

Chris Joakim, 2016/04/20

## links

- GitHub Repo  https://github.com/Azure/azure-sdk-for-java
- API Javadocs http://azure.github.io/azure-sdk-for-java/
- Article      https://azure.microsoft.com/en-us/documentation/articles/service-bus-java-how-to-use-queues/
- Eclipse      https://azure.microsoft.com/en-us/documentation/articles/azure-toolkit-for-eclipse-installation/
- Maven        http://search.maven.org

## azure setup

- In Azure Portal
- New -> Hybrid Integration -> Service Bus
- Create a namespace, such as "joakimbus1"
- In the "Configure" tab, capture the Policy Name and Key values
- Create a queue, such as "joakimqueue1"

## host and project setup

Set environment variables per the Azure Service Bus configuration values
captured above.  Note, the use of environment variables is a recommended
practice vs otherwise hard-coding these values in the application code.

See "the twelve-factor app" http://12factor.net

```
AZURE_SERVICEBUS_KEY_NAME=RootManageSharedAccessKey
AZURE_SERVICEBUS_ACCESS_KEY=<your key value>
AZURE_SERVICEBUS_NAMESPACE=<your namespace>
AZURE_SERVICEBUS_QUEUE1=<your queue name>
```

To install the necessary Java jar files, run the following script
which uses Apache Ivy to determine the necessary jars.

```
./setup.sh
```

## eclipse ide

Install the "Azure Toolkit for Java":
- Help -> Install New Software
- Enter URL  http://dl.microsoft.com/eclipse
- Select and install the "Azure Toolkit for Java"

## compile the examples

Execute Apache Ant as follows, which creates the "svcbus.jar" file.

```
$ ant
```

## execute the examples

See run.sh

```
$ ./run.sh display_queue_info
$ ./run.sh send_messages_to_queue 10
$ ./run.sh read_messages_from_queue 4
```

Invoke all of the above functions:

```
$ ./test.sh
```
