# Interactive Text Games PROJECT.

## PROJECT DESCRIPTION.
Look here: [DESCRIPTION.md](DESCRIPTION.md)

## UP and DOWN a service in containers.
### LOCAL IN DOCKER
#### requirements local
- docker & docker-compose
- Java 17
- MAVEN 3.8.3++
- GNU Make 4.2.1++

#### UP & DOWN local
1. Run: `make -f textify.mk up`.
   Service be alive even after host restart!
2. Run: `make -f textify.mk down`.

### GOOGLE CLOUD SERVICES
1. Build project using cloudbuild.yaml (project builds gitHub every commit or PR)
2. Check image build success in GCB -> look in artifacts
3. KUBERNETES STEP IN PRODUCTION

## Develop
Look here: [DEVELOP.md](DEVELOP.md)