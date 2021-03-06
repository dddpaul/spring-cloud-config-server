# Spring Cloud Config Server
A docker image of [Spring Cloud Config Server](https://cloud.spring.io/spring-cloud-static/spring-cloud-config/2.1.1.RELEASE/single/spring-cloud-config.html).

[![Docker Stars](https://img.shields.io/docker/stars/dddpaul/spring-cloud-config-server.svg?style=flat-square)](https://hub.docker.com/r/dddpaul/spring-cloud-config-server/)
[![Docker Pulls](https://img.shields.io/docker/pulls/dddpaul/spring-cloud-config-server.svg?style=flat-square)](https://hub.docker.com/r/dddpaul/spring-cloud-config-server)
[![Image Size](https://images.microbadger.com/badges/image/dddpaul/spring-cloud-config-server.svg)](https://microbadger.com/images/dddpaul/spring-cloud-config-server)

## Usage
```
docker run -p 8888:8888 dddpaul/spring-cloud-config-server
```


###  Configuring Spring Cloud Config Server
Spring Cloud Config Server is a normal Spring Boot application, it can be configured through all the ways a Spring Boot application can be configured.  You may use environment variables or you can mount configuration in the provided volume.  The configuration file must be named **configserver** and may be a properties or yaml file. See the [Spring Boot documentation](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config) for further information on how to use and configure Spring Boot.


#### Configuration examples
```
# Using a mounted config Directory
docker run -p 8888:8888 -v /path/to/config/dir:/config dddpaul/spring-cloud-config-server

# Using a mounted application.yml
docker run -p 8888:8888 -v /path/to/application.yml:/config/application.yml dddpaul/spring-cloud-config-server

# Configure through environment variables without a configuration file
docker run -p 8888:8888 \
      -e SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/spring-cloud-samples/config-repo \
      dddpaul/spring-cloud-config-server

# Configure through command line arguments without a configuration file
docker run -p 8888:8888 dddpaul/spring-cloud-config-server \
      --spring.cloud.config.server.git.uri=https://github.com/spring-cloud-samples/config-repo
```
#### Verify Samples Above
```
$ curl http://localhost:8888/foo/development
```

### Required Backend Configuration
Spring Cloud Config Server **requires** that you configure a backend to serve your configuration files.  There are currently 3 backends to choose from...

#### Git
```
# Github example
docker run -p 8888:8888 \
      -e SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/spring-cloud-samples/config-repo \
      dddpaul/spring-cloud-config-server

# Local git repo example
docker run -p 8888:8888 \
      -v /path/to/config/files/dir:/config \
      -e SPRING_CLOUD_CONFIG_SERVER_GIT_URI=file:/config/my-local-git-repo \
      dddpaul/spring-cloud-config-server
```

#### Filesystem
```
docker run -p 8888:8888 \
      -v /path/to/config/files/dir:/config \
      -e SPRING_PROFILES_ACTIVE=native \
      dddpaul/spring-cloud-config-server
```

#### Vault
```
docker run -p 8888:8888 \
      -e SPRING_PROFILES_ACTIVE=vault \
      dddpaul/spring-cloud-config-server
```

### Differences from original hyness/spring-cloud-config-server

#### Gradle-based

Even Docker image is built via Gradle with:
```
./gradlew dockerBuildImage

```

#### Secure by default

* Access to `/decrypt` endpoint is restricted by default. Add `-e SECURITY_ENDPOINT_DECRYPT_ENABLED=true` option to `docker run` to permit access to it.
* Docker image has server-side decryption disabled by default. Add `-e SPRING_CLOUD_CONFIG_SERVER_ENCRYPT_ENABLED=true` option to `docker run` to enable server-side decryption.
