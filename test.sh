#!/bin/bash

# Bash script to build and invoke all functions in this example app.
# Chris Joakim, 2016/04/20

./build.sh

./run.sh display_queue_info

./run.sh send_messages_to_queue 10

./run.sh read_messages_from_queue 4

./run.sh display_queue_info

echo 'done'
