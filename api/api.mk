start: build run

stop:
	docker stop textify-api || true

test: build
	docker run --rm --name textify-api --net host textify-api

run:
	docker run --rm --name textify-api -d --net host textify-api

build: stop extract
	mvn spring-boot:build-info
	docker build -t textify-api -f ./api.dockerfile .

# depends on dockerfile
extract: package
	#1.
	mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
	#2.
	#mkdir -p target/extracted && java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted

package: clean
	mvn package

clean:
	mvn clean