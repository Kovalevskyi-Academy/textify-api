# PostgreSQL

## Install & initialize local DB

[//]: #<TODO write instalation description>
Not today )))

## Container requirements:

- RAM min: 100, max: 250
- CPU min: 0.1, max: 0.3 (это ограничение тормозит старт приложения)


## PostgreSQL & Kubernetes

1. Use Google Secrets Manager in pod. Variants:
   1. Get secrets in Google Cloud Build & pass them to pod creation in GKE. [Look here](https://stackoverflow.com/questions/66912134/how-to-inject-secrets-from-google-secret-manager-into-kubernetes-pod-as-environm)
      And [here!](https://stackoverflow.com/questions/56003777/how-to-pass-environment-variable-in-kubectl-deployment)
      [There is the main article about using Google Secrets Manager in Google Cloud Build!](https://cloud.google.com/build/docs/securing-builds/use-secrets)
   2. ... No alternatives.
2. DB livenessProbe: https://github.com/kubernetes/kubernetes/issues/40846

