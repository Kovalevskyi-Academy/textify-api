#!/usr/bin/env bash
set -Eeuo pipefail

ip=0
secondsPassed=1
failTime=20
while [[ ! "${ip}" =~ ^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$ ]]; do
  if [ "${secondsPassed}" -ge "${failTime}" ]; then
    echo "After ${failTime} seconds I fill fail connection with service!"
    exit 1
  fi
  sleep "${secondsPassed}"s
  ip=$(kubectl get service -n dev-ns rest-api -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
  printf "\n#Current ip is: %s\n" "${ip}"
  secondsPassed=$(("${secondsPassed}" + "${secondsPassed}"))
done

echo "${ip}" > ip.txt
