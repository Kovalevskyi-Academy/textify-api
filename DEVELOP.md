# Introduction to the dev

Models in this project based on JSON schemas. [Details here](./Open_API/schemas/MODEL_SCHEMAS.md).

API of this project based on OpenAPI.
More details [here](./Open_API/OpenAPI.md).

[Notes about Spring REST-API here.](./api/API.md)

In Database role is PostgreSQL → [details here](./DataB/DB_DESCRIPTION.md).

And all of that takes off on Google services:
    - Cloud Build
    - Cloud Storage
    - Artifact Registry
    - Kubernetes Engine

**Also, this project can be started on local machine for little experiments. Details is below.**

## GOOGLE CLOUD SERVICES
1. Details about Google Cloud Build [look here](./CLOUDBUILD.md).
2. Google Kubernetes Engine [look here](./k8b/K8b.md). (You can connect to the pod in the K8b.) ;)

## UP and DOWN the project LOCALLY fo tests.
### requirements local
- Docker Engine with Docker Compose Plugin (or Docker Desktop)
- Java 17
- MAVEN 3.x.x
- GNU Make 4.x.x
- Something like **Advanced Rest Client (ARC)**. [install link](https://install.advancedrestclient.com/install)

### Do up & do  down
1. Run:
   - just entire project in detached mod `make -f textify.mk up`.
   - run & watch logs in textify-container (without detached mode) `make -f textify.mk test`
   **ATTENTION** The service will be alive even after the host restarting!
2. Run: `make -f textify.mk down`. **ATTENTION** The server will be dead even after restarting the host.

### Check work
- Or do request from ARC to localhost:8080
- Or use CURL to localhost:8080
### Connect to these working containers & watch them
1. You can connect inside running container:
   - `docker ps`
   - `docker exec -it CONTAINER_NAME or ID`
2. You can watch containers statistic:
   - for all running containers: `docker stats`
   - for specify containers: `docker stats CONTAINER1_NAME CONTAINER2_NAME`
     Look more options [here](https://docs.docker.com/engine/reference/commandline/stats/).
3. You can watch container logs:
   - `docker ps`
   - `docker container logs CONTAINER_ID OR NAME`
      See more options [here](https://docs.docker.com/engine/reference/commandline/logs/).
