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
                sh 'docker build -t nadinemilli/achat .'
                sh 'docker image list'
                sh 'docker tag nadine-docker nadinemilli/achat:latest'
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
                sh 'docker push nadinemilli/achat:latest'
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
                    
                    stage('Put config files into k8smaster') {
                        sshPut remote: remote, from: 'app_deployment.yml', into: '.'
                        sshPut remote: remote, from: 'app_servicce.yml', into: '.'
                        sshPut remote: remote, from: 'db_deployment.yml', into: '.'
                    }
                     stage('Deploy') {
                         sshCommand remote: remote, command: "export KUBECONFIG=/etc/kubernetes/admin.conf && kubectl apply -f app_deployment.yml"
                         sshCommand remote: remote, command: "export KUBECONFIG=/etc/kubernetes/admin.conf && kubectl apply -f app_servicce.yml"
                         sshCommand remote: remote, command: "export KUBECONFIG=/etc/kubernetes/admin.conf && kubectl apply -f db_deployment.yml"
        }
                }
            }
        }
    }
}
