# spring-boot-solace-poc

POC for implementing Solace PubSub+ using Spring-boot.

# how to run 

 1. Install Solace PubSub+ using the PubSubStandard_singleNode.yaml docker compose file as shown below

    > docker-compose -f PubSubStandard_singleNode.yaml -d

 2. Then start the application using the following command
 
   > gradlew bootRun

# what to expect

The POC implements a basic Solace API based consumer and a producer. Producer will push a message every second, upto 10 messages and consumer will listen to a topic and print the message on console.
