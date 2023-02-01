#!/usr/bin/env bash

# get url from scripts args
url=${1}

# Do POST to REST-API /nodes
printf "\n#Do POST to %s/stories\n" "${url}"
actualUuid=null

if curl "${url}/stories" \
  -X POST \
  -d @./testObjects/story1.json \
  -H "Content-Type: application/json" \
  -m 5 \
  -o storyUUID 2>/dev/null
then
  echo "POST 'story1' success!"
  actualUuid=$(tr <storyUUID -d \")
  echo "'story1' uuid is: ${actualUuid}"
else
  echo "POST 'story1' failed!"
  exit 1
fi

# Check POSTed object -> do get
printf "\n#Do GET to %s/stories/%s\n" "${url}" "${actualUuid}"

if curl "${url}/stories/${actualUuid}" -s \
  -o tempStory.json \
  2>/dev/null
then
  echo 'Object from GET:'
  echo "$(<tempStory.json)"
  exit 0
else
  echo 'TEST GET FAILED'
  exit 1
fi