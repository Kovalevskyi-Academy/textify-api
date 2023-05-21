#!/usr/bin/env bash
set -Eeuo pipefail

# get url from scripts args
url=${1}

# Do POST to REST-API /nodes
actualUuid=null
responseStatus="no"
failAttempt=0
waitTime=10
while [ "${responseStatus}" != "201" ]; do
  printf "\n#Do POST to %s/stories\n" "${url}"
  echo "-> attempt # ${failAttempt}"
  curl \
    --url "${url}/stories" \
    -X POST \
    -d @./testObjects/story1.json \
    -H "Content-Type: application/json" \
    -w "%{http_code}" \
    --m "${waitTime}" \
    -o storyUUID.txt \
    -v >responceStory.txt ||
    true #2>/dev/null

  responseStatus=$(<responceStory.txt)
  printf "\n responseStatus: %s\n" "${responseStatus}"
  if [ "${failAttempt}" -gt 10 ]; then
    echo "POST story1 FAILED after ${failAttempt} !"
    exit 1
  fi
  failAttempt=$(("${failAttempt}" + 1))
done

printf "\nPOST 'story1' SUCCESS, UUID: %s\n" "$(tr <storyUUID.txt -d \")"

# Check POSTed object -> do get
responseStatus="no"
failAttempt=0
waitTime=10
while [ "${responseStatus}" != "200" ]; do
  actualUuid=$(tr <storyUUID.txt -d \")
  printf "\n#Do GET to %s/stories/%s\n" "${url}" "${actualUuid}"
  curl "${url}/stories/${actualUuid}" \
    -o tempStory.json \
    -w "%{http_code}" \
    -m "${waitTime}" \
    -v >responceStory.txt ||
    true #2>/dev/null

  sleep "${waitTime}"s

  responseStatus=$(<responceStory.txt)
  if [ "${failAttempt}" -gt 5 ]; then
    echo "GET story1 FAILED after ${failAttempt} !"
    exit 1
  fi
  failAttempt=$(("${failAttempt}" + 1))
done

echo "GET story1 SUCCESS after ${failAttempt} !"
printf "\nobject from GET: %s\n" "$(<tempStory.json)"
