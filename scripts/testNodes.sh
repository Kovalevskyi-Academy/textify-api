#!/bin/bash
set -Eeuo pipefail

# get url from scripts args
url=${1}

# Do POST to REST-API /nodes
printf "\n#Do POST to %s/nodes\n" "${url}"
actualUuid=null

responseStatus="no"
failAttempt=0
waitTime=3
while [ "${responseStatus}" != "201" ]
do
  sleep "${waitTime}"s
  echo "-> attempt # ${failAttempt}"
  #printf "WORKDIR: %s" "$(ls ./*/)"
  curl \
    --url "${url}/nodes" \
    --data @./testObjects/node1.json \
    -H "Content-Type: application/json" \
    -w "%{http_code}" > responceNode.txt \
    -o nodeUUID.txt \
    -m 15 \
    -v || true #2>/dev/null
  responseStatus=$(<responceNode.txt)
  printf "\n responseStatus: %s\n" "${responseStatus}"
  if [ "${failAttempt}" -gt 10 ]
  then
    echo "POST node1 FAILED after ${failAttempt} !"
    exit 1
  fi
  failAttempt=$(( "${failAttempt}" + 1))
done
printf "\nnode1 POST SUCCESS, UUID: %s\n" "$(tr <nodeUUID.txt -d \")"

# Check POSTed object -> do get
responseStatus="no"
failAttempt=0
waitTime=3
while [ "${responseStatus}" != "200" ]
do
  sleep "${waitTime}"s
  actualUuid=$(tr <nodeUUID.txt -d \")
  printf "\n#Do GET to %s/nodes/%s   → attempt#%s\n" "${url}" "${actualUuid}" "${failAttempt}"
  curl "${url}/nodes/${actualUuid}" -s \
    -o tempNode.json \
    -w "%{http_code}" > responceNode.txt \
    -m "${waitTime}" \
    -v || true
    #2>/dev/null
  responseStatus=$(<responceNode.txt)
  if [ "${failAttempt}" -gt 5 ]
    then
      echo "GET node1 FAILED after ${failAttempt} !"
      exit 1
  fi
  failAttempt=$(( "${failAttempt}" + 1))
done

echo "GET node1 SUCCESS after ${failAttempt} !"
printf "\nobject from GET: %s\n" "$(<tempNode.json)"