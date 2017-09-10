#!/usr/bin/env groovy
/**
 * Create Docker Image
 * Refer example Jenkinsfile-dockerTask under 'example' for use case
 */


def call(body) {
    // evaluate the body block, and collect configuration into the object
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config


    body()

        sh("docker build -t ${config.imageTag} .")



}

return this;
