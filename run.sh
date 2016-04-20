#!/bin/bash

# Bash script to execute the Java ServiceBus logic.
# Chris Joakim, 2016/04/20
#
# Example use:
# ./run.sh display_queue_info
# ./run.sh send_messages_to_queue 10
# ./run.sh read_messages_from_queue 4

source classpath.sh  # set the CLASSPATH

CLASS="com.joakim.azure.svcbus.Main"

java -Xms512m -Xmx1024m -server -cp $CLASSPATH $CLASS $1 $2 $3 $4 $5 $6 $7

echo ""
