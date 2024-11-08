# JENKINS in Docker

> > This folder contains the setup from creating an Jenkins Docker image with docker inside

## Pull

docker pull jenkins/jenkins:2.263.4-lts-centos7

## Build (Not do do anymore)

'docker build . -t myjenkins'

## Run

'docker run \
-d \
-u root \
-p 9000:8080 \
-p 50000:50000 \
--restart=on-failure \
-v jenkins_home:/var/jenkins_home \
-v /var/run/docker.sock:/var/run/docker.sock \
--name jenkins \
jenkins/jenkins:2.263.4-lts-centos7'

## smee.io

### WebHook

https://smee.io/yIlgTEyIHOpXPK1

### Client

smee -p 9000 -u https://smee.io/yIlgTEyIHOpXPK1

## GitHub Private Key

+1WNIbXt1h0MtUmuulBTiBzC2oGkRyFt5puijRcoOXk=

The file is under Private Key

Convert this key

openssl pkcs8 -topk8 -inform PEM -outform PEM -in <github>.pem -out jenkins.pem -nocrypt

## GitHub App

Application ID = 998152
Application Name = JenkinsGithubAppRWDevops999
