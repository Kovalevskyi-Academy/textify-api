## Watch for containers

1. You can run it in non de-touch mode: `make -f textify test`, and look the errors.
2. You can connect inside running container:
    - `docker ps`
    - `docker exec -it CONTAINER_NAME or ID`
3. You can watch containers statistic:
    - for all running containers: `docker stats`
    - for specify containers: `docker stats CONTAINER_NAME CONTAINER_NAME`
   Look more options [here](https://docs.docker.com/engine/reference/commandline/stats/).
4. You can watch container logs:
    1. `docker ps`
    2. `docker container logs CONTAINER_ID OR NAME`
   See more options [here](https://docs.docker.com/engine/reference/commandline/logs/).


## API (Open API, swagger)

See API [here](./api/Open_API/storyAPI.yaml).

Use a [Swagger plugin](https://plugins.jetbrains.com/plugin/8347-swagger).