#!/usr/bin/env bash
###!/bin/sh


# required dependency install for parsing JSON
# apk add --no-cache jq -q
#actualResponse=$(curl "http://${ip}:8080/messages/5" -s 2>/dev/null | jq -r '.message')
#jq '.nodeTitle = "UPGRADED title of first node"' temp.json > temp$.json && mv temp$.json temp.json

ip=0
secondsPassed=1
failTime=20
while [[ ! "${ip}" =~ ^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$ ]]
do
  if [ "${secondsPassed}" -ge "${failTime}" ]
    then
    echo "After ${failTime} seconds I fill fail connection with service!"
    exit 1
  fi
  sleep "${secondsPassed}"s
  ip=$(kubectl get service rest-api -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
  printf "\n#Current ip is: %s\n" "${ip}"
  secondsPassed=$(( "${secondsPassed}" + "${secondsPassed}" ))
done

url="http://${ip}:8080"

# REST check availability
printf "\n#Do GET request to %s/test\n" "${url}"
exitCode=1
failAttempt=0
waitTime=3
while [ "${exitCode}" -ne 0 ]
do
  sleep "${waitTime}"s
  echo "-> attempt # ${failAttempt}"
  curl "${url}/test" -m "${waitTime}" -v
  exitCode=$?
  failAttempt=$(( "${failAttempt}" + 1))
  if [ "${failAttempt}" -gt 50 ]
  then
    echo "REST check availability FAILED after ${failAttempt} !"
    exit 1
  fi
done
printf "\n#REST check availability SUCCESS!#\n"

# run test Nodes
. "$(dirname "$0")"/testNodes.sh "${url}"
sleep 3s

# run test Stories
. "$(dirname "$0")"/testStories.sh "${url}"