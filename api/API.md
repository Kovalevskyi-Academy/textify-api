# Spring REST API

Trololo

## Requirements

- **PostgreSQL 14 is actually available on port 5432.**
- **And PostgreSQL have:**
    - POSTGRES_DB: "testDB"
    - POSTGRES_USER: "testUSER"
    - POSTGRES_PASSWORD: "testPASSWORD"
- **GNU Make 4.2.1++**

## Howe to test working after runs APP

Just visit:

- curl http://localhost:8080/
- curl http://localhost:8080/messages/
- curl http://localhost:8080/messages/1
- curl -X POST -H "Content-Type: application/json" -d '{"id":-1, "message": "Some message"
  }' http://localhost:8080/messages/

**But is better to use something like `Advanced REST Client`.**

## Run without docker

1. CHECK FOLDER!
2. Move somewhere `module-info.java`.
3. Run `mvn clean`.
4. Run `TextifyApiStarter class`.

Don't forget restore `module-info.java`!

## Run in docker in `host` network in de-touch mode

1. CHECK FOLDER!
2. Run `make -f api.mk start` in this folder.
3. Test it.
4. Stop it! `make -f api.mk stop`

You can connect to this runs container: `docker exec -it textify-api sh`

Run without de-touch mode & watch errors: `make -f api.mk test`

## RESTAPI & Kubernetes

### Requirements:

- RAM min: 250, max: ~300
- CPU min: 0.2, max: ~0.4 (это ограничение тормозит старт приложения)

1. Deploy postgres
   first: `kubectl apply -f ./k8b/db/pg-config.yaml -f ./k8b/db/pg-deploy.yaml -f ./k8b/db/pg-service.yaml`
2. Deploy rest-api: `kubectl apply -f ./k8b/rest/rest-deploy.yaml -f ./k8b/rest/rest-service.yaml`
3. Connect local machine to API:
    - Do port forwarding: `kubectl port-forward API_POD_NAME 8080:8080`
    - GET localhost:8080
4. Check rest-api in internet:
    - find external ip: `kubectl get services`
    - GET IP_ADDRESS:8080
