# Google Cloud Build

Ever commit in main branch or ever Pull Request — Google Cloud Build do:
  - validate, compile, test & package java code
  - save jar artifact to GCS
  - build docker container with the jar file & save it to artifact registry
  - deploys the entire project on a Kubernetes cluster & do tests
  - delete the project from Kubernetes cluster (**WARNING** if any cloudbuild step fails, then **you must** manually remove all parts of this project from the Kubernetes cluster!)

Cloudbuild steps [look here](cloudbuild.yaml).

## Google Cloud Build Secrets USAGE

[There is the main article about using Google Secrets Manager in Google Cloud Build!](https://cloud.google.com/build/docs/securing-builds/use-secrets)