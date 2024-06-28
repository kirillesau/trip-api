# Trip API

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![GitHub last commit](https://img.shields.io/github/last-commit/kirillesau/trip-api)
[![Java CI with Maven](https://github.com/kirillesau/trip-api/actions/workflows/build-with-maven.yml/badge.svg?branch=master)](https://github.com/kirillesau/trip-api/actions/workflows/build-with-maven.yml)
[![Docker Image CI](https://github.com/kirillesau/trip-api/actions/workflows/build-docker-image.yml/badge.svg?branch=master)](https://github.com/kirillesau/trip-api/actions/workflows/build-docker-image.yml)

In this repository I provide an API for the management of excursions. The API is implemented using Spring Boot and
PostgreSQL.

## Prerequisite

The example has been created for learning purposes only and is intended for private use only.

## How to run

### Maven

```shell
./mvnw package spring-boot:run
```

### Docker

```shell
docker build -t kirillesau/trip-api .
docker run -p 8888:8080 -d --name trip-api kirillesau/trip-api
```
