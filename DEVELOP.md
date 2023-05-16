# Introduction to the dev

Models in this project based on JSON schemas. [Details here](Open_API/MODEL_SCHEMAS.md).

API of this project based on OpenAPI.
More details [here](./Open_API/OpenAPI.md).

[Notes about Spring REST-API here.](./api/API.md)

In Database role is PostgreSQL → [details here](./DataB/DB_DESCRIPTION.md).

And all of that takes off on Google services:
    - Cloud Build
    - Cloud Storage
    - Artifact Registry
    - Kubernetes Engine
    - Google Secret Manager


## GOOGLE CLOUD SERVICES
1. Details about Google Cloud Build [look here](./CLOUDBUILD.md).
2. Google Kubernetes Engine [look here](./k8b/K8b.md). (You can connect to the pod in the K8b.) ;)
3. Secrets Manager... // TODO tutorial or link


## Run its locally & look up.

### Requirements:
1. Linux
2. Docker latest
3. Google Cloud CLI installed of [this instruction]() (installation from `*.tar.gz` using `install.sh`).
4. Kubernetes should be installed using command `gcloud components ...`
