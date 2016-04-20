
Links:
- GitHub Repo  https://github.com/Azure/azure-sdk-for-java
- API Javadocs http://azure.github.io/azure-sdk-for-java/

- https://azure.microsoft.com/en-us/documentation/articles/service-bus-java-how-to-use-queues/
- https://azure.microsoft.com/en-us/documentation/articles/azure-toolkit-for-eclipse-installation/
- http://search.maven.org

Eclipse:
- Help -> Install New Software
- Enter URL  http://dl.microsoft.com/eclipse
- Install the Azure Toolkit for Java


$ ant ivy-resolve
$ ant 
$ java -jar svcbus.jar display_queue_info
