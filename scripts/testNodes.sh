#!/usr/bin/env bash

# get url from scripts args
url=${1}

# Do POST to REST-API /nodes
printf "\n#Do POST to %s/nodes\n" "${url}"
actualUuid=null

if curl "${url}/nodes" \
  -X POST \
  -d @./testObjects/node1.json \
  -H "Content-Type: application/json" \
  -m 5 \
  -o nodeUUID 2>/dev/null
then
  echo "POST 'node1' success!"
  actualUuid=$(tr <nodeUUID -d \")
  echo "'node1' uuid is: ${actualUuid}"
else
  echo "POST 'node1' failed!"
  exit 1
fi

# Check POSTed object -> do get
printf "\n#Do GET to %s/nodes/%s\n" "${url}" "${actualUuid}"

if curl "${url}/nodes/${actualUuid}" -s \
  -o tempNode.json \
  2>/dev/null
then
  echo 'Object from GET:'
  echo "$(<tempNode.json)"
  exit 0
else
  echo 'TEST GET FAILED'
  exit 1
fi