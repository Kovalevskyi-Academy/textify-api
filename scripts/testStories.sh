#!/usr/bin/env bash
set -Eeuo pipefail

# get url from scripts args
url=${1}

# Do POST to REST-API /nodes
printf "\n#Do POST to %s/stories\n" "${url}"
actualUuid=null

curl "${url}/stories" \
  -X POST \
  -d @./testObjects/story1.json \
  -H "Content-Type: application/json" \
  -w "%{http_code}" > responce.txt \
  -m 10 \
  -o storyUUID.txt \
  -v || true #2>/dev/null

responseStatus=$(<responce.txt)
if [ "${responseStatus}" = "201" ]
then
  printf "\nPOST 'story1' SUCCESS!\n"
  actualUuid=$(tr <storyUUID.txt -d \")
  echo "'story1' uuid is: ${actualUuid}"
else
  echo "POST 'story1' FAILED!"
  printf "\nStory UUID: %s\n" "${actualUuid}"
  printf "\nResponse STATUS: %s\n" "${responseStatus}"
  exit 1
fi

# Check POSTed object -> do get
printf "\n#Do GET to %s/stories/%s\n" "${url}" "${actualUuid}"

curl "${url}/stories/${actualUuid}" \
  -o tempStory.json \
  -w "%{http_code}" > responce.txt \
  -v || true #2>/dev/null

responseStatus=$(<responce.txt)
if [ "${responseStatus}" = "200" ]
then
  printf "\nGET 'story1' SUCCESS!\n"
  echo 'Object from GET:'
  echo "$(<tempStory.json)"
else
  echo 'TEST GET story1 FAILED'
  exit 1
fi