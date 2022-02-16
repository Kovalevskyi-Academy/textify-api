# Introduction to the dev
This project based on OpenAPI.
More details [here](./api/Open_API/openapi.yaml).

[Notes about Spring REST-API here.](./api/API.md)

In Database role is PostgreSQL → [details here](./DataB/DB_DESCRIPTION.md).

And all this takes off on Google services:
    - Cloud Build
    - Cloud Storage
    - Artifact Registry
    - Kubernetes Engine

Also, this project can be started on local machine for little experiments. Details is below.

## GOOGLE CLOUD SERVICES
1. Details about Google Cloud Build [look here](./CLOUDBUILD.md).
2. Google Kubernetes Engine [look here](./k8b/K8b.md). (You can connect to the pod in the K8b.) ;)

## UP and DOWN the project LOCALLY fo tests.
### requirements local
- docker & docker-compose
- Java 17
- MAVEN 3.8.3++
- GNU Make 4.2.1++
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
### Connect to the working containers & watch them
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


## GOOGLE CLOUD SERVICES
1. Ever commit in main branch or ever Pull Request — Google Cloud Build do:
    - validate, compile, test & package java code
    - save jar artifact to GCS
    - build docker container with the jar file & save it to artifact registry
    - deploys the entire project on a Kubernetes cluster & do tests
    - delete the project from Kubernetes cluster (**WARNING** if any cloudbuild step fails, then **you must** manually remove all parts of this project from the Kubernetes cluster!)

2. Google Kubernetes Engine [look here](./k8b/K8b.md).