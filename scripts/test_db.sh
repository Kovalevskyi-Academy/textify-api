#!/bin/bash
set -Eeuo pipefail
pg_pod_name=$(kubectl get pod --selector=app=pg -o jsonpath={.items..metadata.name})
is_pod_started=false
waitTime=5
while ! ${is_pod_started}
do
  echo "TEST CYCLE"
  is_pod_started=$(kubectl get pod "${pg_pod_name}" -o jsonpath={.status..started})
  echo "sleeping on $waitTime seconds" && sleep "${waitTime}"
  waitTime=$(( "${waitTime}" + 1 ))
  if [ "${waitTime}" -gt 30 ]; then
    echo "DB is unavailable!"
    exit 1
  fi
done
echo "PG IS STARTED!"
# TODO fix secrets!
kubectl exec "${pg_pod_name}" -- /bin/sh -c 'pgbench -i -U testUSER -d testDB'
echo "DB TEST SUCCESS"
