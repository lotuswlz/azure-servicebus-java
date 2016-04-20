#!/bin/bash

./run.sh display_queue_info

./run.sh send_messages_to_queue 10

./run.sh read_messages_from_queue 4

echo 'done'

