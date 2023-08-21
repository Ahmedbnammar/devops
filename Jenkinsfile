pipeline {
    agent any
    
    stages {
        stage("Git Clone") {
            steps {
                git credentialsId: 'GIT_HUB_CREDENTIALS', url: 'https://github.com/NadineMili/Devops.git'
            }
        }
        
        stage('Maven Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        
        stage("Docker build") {
            steps {
                sh 'docker version'
                sh 'docker build -t nadine-docker .'
                sh 'docker image list'
                sh 'docker tag nadine-docker nadinemili/testachat:latest'
            }
        }
        
        stage("Docker Login") {
            steps {
                withCredentials([string(credentialsId: 'DOCKER_HUB_PASSWORD', variable: 'PASSWORD')]) {
                    sh 'docker login -u nadinemili -p $PASSWORD'
                }
            }
        }
        
        stage("Push Image to Docker Hub") {
            steps {
                sh 'docker push nadinemili/testachat:latest'
            }
        }
        
        stage("SSH Into k8s Server") {
            steps {
                script {
                    def remote = [:]
                    remote.name = 'master'
                    remote.host = '192.168.1.101'
                    remote.user = 'master'
                    remote.password = 'master'
                    remote.allowAnyHosts = true
                }
            }
        }
    }
}
