up: build
		kubectl apply -f ./k8b/ --recursive

down:
		kubectl delete -f ./k8b/ --recursive
		pvc_name=$(kubectl get pvc -n dev-ns --selector=$sel -o jsonpath={.items..metadata.name})
		kubectl delete pvc "${pvc_name}" -n dev-ns

build: package unpack build-docker push-docker

package:
		cd api; mvn package -Dmaven.test.skip=true

unpack:
		cd api; mkdir -p target/dependency && (cd target/dependency; jar -xfv ../*.jar) ; echo "jar unpack success"

build-docker:
		cd api; docker build -t 'europe-docker.pkg.dev/prod-rest/api-rest/textify-api:HANDLE' -f api.dockerfile .

push-docker:
		cd api; docker push --all-tags europe-docker.pkg.dev/prod-rest/api-rest/textify-api