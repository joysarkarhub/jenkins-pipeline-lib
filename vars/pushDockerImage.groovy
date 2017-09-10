#!/usr/bin/env groovy
/**
 * Push Docker Image
 * Refer example Jenkinsfile-dockerTask under 'example' for use case
 */

def call(body) {
    // evaluate the body block, and collect configuration into the object
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    def credentials = "${config.dockerCredentialsId}"

    withDockerRegistry([credentialsId: credentials, url: 'https://hub.docker.com']) {
        wrap([$class: 'hudson.plugins.ansicolor.AnsiColorBuildWrapper']) {

            // let's retry a few times before giving up
            retry(3) {

                sh("docker push ${config.imageTag}")
            }
        }
    }
}
