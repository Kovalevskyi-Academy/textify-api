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
- curl -X POST -H "Content-Type: application/json" -d '{"id":-1, "message": "Some message"}' http://localhost:8080/messages/ 

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
