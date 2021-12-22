# PostgreSQL

### Install & initialize local DB

[//]: #<TODO write instalation description>
Not today )))

## Install & initialize in Docker
1. Check host port: `sudo netstat -tulpn | grep ':5432'`, it's free?
2. If port 5432 is not free: `sudo kill process_id`.
3. Run container : `docker run --rm --name postgres -p 5432:5432 -e POSTGRES_USER=testUSER -e POSTGRES_PASSWORD='testPASSWORD' -e POSTGRES_DB=testDB -d postgres:14.1-alpine3.15`.
4. Connect to containerized PostgreSQL using CLI: `psql -U testUSER -d testDB -h 0.0.0.0` (**-h** — it means HOST)

You can connect to this container: `docker exec -it postgres sh`


