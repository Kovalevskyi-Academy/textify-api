# PostgreSQL

### Install & initialize local DB

[//]: #<TODO write instalation description>
Not today )))

## Install & initialize in Docker

1. Check host port: `sudo netstat -tulpn | grep ':5432'`, it's free?
2. If port 5432 is not free: `sudo kill process_id`.
3. Run
   container : `docker run --rm --name postgres -p 5432:5432 -e POSTGRES_USER=testUSER -e POSTGRES_PASSWORD='testPASSWORD' -e POSTGRES_DB=testDB -d postgres:14.1-alpine3.15`
   .
4. Connect to containerized PostgreSQL using CLI: `psql -U testUSER -d testDB -h 0.0.0.0` (**-h** —
   it means HOST)

You can connect to this container: `docker exec -it postgres sh`

## PostgreSQL & Kubernetes

!!!! Изучить **когда-нибудь** подробно управление постгресом с помощью специального контроллера,
который сам разворачивает мастер-слейвы и контролирует нагрузку на экземпляры
постгреса. https://discuss.kubernetes.io/t/news-kubegres-is-a-kubernetes-operator-for-postgresql/15536

### Requirements:

- RAM min: 100, max: 250
- CPU min: 0.1, max: 0.3 (это ограничение тормозит старт приложения)

1. Deploy only
   postgres: `kubectl apply -f ./k8b/pg-config.yaml -f ./k8b/pg-deploy.yaml -f ./k8b/pg-service.yaml`
2. Connect local machine to pg-pod:
    - check the port 5432, is it free? Should be free!
    - do port forwarding: `kubectl port-forward PG_POD_NAME 5432:5432`
    - connect: `psql -U testUSER -d testDB -h 0.0.0.0`
3. Зайти в контейнер в поде:
    - 1 контейнер в поде: `kubectl exec -it POD_NAME -- /bin/sh`
    - много контейнеров в поде: ` kubectl exec -it POD_NAME --container CONTAINER_NAME -- /bin/sh`
4. Как проверить диск для базы:
    - Смотрим создан ли диск: `kubectl get pvc`
    - Подключаемся к контейнеру и смотрим содержимое PGDATA: `kubectl exec -it pg-0 -- /bin/sh -c 'ls $PGDATA'`



