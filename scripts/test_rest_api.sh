#!/usr/bin/env bash

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
  secondsPassed=$(( "${secondsPassed}" + "${secondsPassed}" ))
done

# Do GET request
printf "\n#Do GET request all objects#\n"
exitCode=1
failAttempt=0
waitTime=20
while [ "${exitCode}" -ne 0 ]
do
  sleep "${waitTime}"s
  echo "-> attempt # ${failAttempt}"
  curl "http://${ip}:8080/messages" -m "${waitTime}"
  exitCode=$?
  failAttempt=$(( "${failAttempt}" + 1))
  if [ "${failAttempt}" -gt 20 ]
  then
    echo "GET request FAIL after ${failAttempt} !"
    exit 1
  fi
done
printf "\n#GET request success!#\n"

# Do POST to REST-API
printf "\n#Do POST#\n"
testObject="POST from Google Cloud Build Script!"
json=$(printf "{\"id\": -1, \"message\": \"%s\"}" "${testObject}")

if curl "http://${ip}:8080/messages" \
         -X POST \
         -d "${json}" \
         -H "Content-Type: application/json"
then
  echo 'POST request success'
else
  echo 'FAIL POST REQUEST!'
  exit 1
fi

# Check POSTed object
printf "\n#Do GET request agan!#\n"
# required dependency install
apk add --no-cache jq -q
actualResponse=$(curl "http://${ip}:8080/messages/5" -s 2>/dev/null | jq -r '.message')
printf "\n%s\n" "${actualResponse}"
if [ "${testObject}" = "${actualResponse}" ]; then
  echo 'TEST REST-API SUCCESS'
  exit 0
else
  echo 'TEST REST-API FAILED'
  exit 1
fi
