up: down build
	docker-compose up -d

test: down build
	docker-compose up

down:
	docker-compose down -v

build: compile
	docker-compose build

compile:
	cd ./api && make -f api.mk extract
