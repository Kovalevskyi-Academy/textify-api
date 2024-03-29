#!/usr/bin/env bash
set -Eeuo pipefail
###!/bin/sh

# required dependency install for parsing JSON
# apk add --no-cache jq -q
#actualResponse=$(curl "http://${ip}:8080/messages/5" -s 2>/dev/null | jq -r '.message')
#jq '.nodeTitle = "UPGRADED title of first node"' temp.json > temp$.json && mv temp$.json temp.json
printf "THIS IS IP FROM FILE: %s\n" "$(tr </workspace/ip.txt -d \")"
ip=$(tr </workspace/ip.txt -d \")
url="http://${ip}:8080"

# REST check availability
printf "\n#Do GET request to %s/test\n" "${url}"
responseStatus="no"
failAttempt=0
waitTime=10
while [ "${responseStatus}" != "200" ]; do
  echo "-> attempt # ${failAttempt}"
  curl "${url}/test" -m "${waitTime}" -w "%{http_code}" >responce.txt || true
  sleep "${waitTime}"s
  responseStatus=$(<responce.txt)
  printf "\n responseStatus: %s\n" "${responseStatus}"
  failAttempt=$(("${failAttempt}" + 1))
  if "${failAttempt}" -gt 10
  then
    echo "REST simple check availability FAILED after ${failAttempt} !"
    exit 1
  fi
done
printf "\n#REST check availability SUCCESS!#\n"

# run test Nodes
printf "\n RUN testNodes.sh \n"
. "$(dirname "$0")"/testNodes.sh "${url}"
sleep 3s

# run test Stories
printf "\n RUN testStories.sh \n"
. "$(dirname "$0")"/testStories.sh "${url}"

sleep 3s
printf "\nALL TEST IS OK\n"
