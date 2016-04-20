#!/bin/bash

# Bash script to install the necessary Java jar files for this project.
# Chris Joakim, 2016/04/20

# ant ivy-clean-cache

rm lib/*.*

echo 'Installing jar files into lib/ per the ivy.xml file...'
ant ivy-resolve

echo 'generating classpath.sh from the list of jars in the lib/ directory...'
python classpath.py > classpath.sh

# The following steps are all optional, they're done for analysis only.

ls -al lib/     | cut -c53-999 > tmp/lib_list.txt
ls -al lib_sdk/ | cut -c53-999 > tmp/lib_sdk_list.txt

diff tmp/lib_list.txt tmp/lib_sdk_list.txt > tmp/diffs.txt

wc -l tmp/lib_list.txt
wc -l tmp/lib_sdk_list.txt
