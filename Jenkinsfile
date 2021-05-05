pipeline {
    agent any
     tools {  
        jdk 'jdk1.8' 
        maven 'maven3'   
    }
    stages{
    
    stage ('pulling code from git') {
            steps {
                echo 'pulling code from GIT repo'
                git 'https://gitlab.com/sudo-boddu/automaticdeployment.git'
            }
        }

        stage ('maven clean & build') {
            steps {
        echo 'maven clean and build'
         sh 'mvn clean'
         sh 'mvn install'                
            }
        }
        stage('deploy artifacts'){
            steps{
                script{
                def server = Artifactory.server 'myjfrog'
                def uploadSpec = """{
                    "files":[
                        {
                            "pattern": "/var/lib/jenkins/.m2/repository/com/abc/mvn-hello-world/1.5/mvn-hello-world-1.5.war",
                            "target": "elxr/"
                        }
                    ]
                }"""
                  server.upload(uploadSpec)
                                
                }
            }
        }
        stage('build docker image'){
        steps{
            echo 'build docker image'
            sh 'docker build -t app:1 .'
         }
        }
        stage('login to ECR'){
        steps{
            echo 'login to ECR'
            sh '$(aws ecr get-login --no-include-email)'
        }
        }
       stage('push to ECR'){
        steps{
            echo 'push to ECR'
            sh 'docker tag app:1 796419576504.dkr.ecr.us-east-2.amazonaws.com/myapp:latest'
            sh 'docker push 796419576504.dkr.ecr.us-east-2.amazonaws.com/myapp:latest'
        }
        }
        stage('ping and execute playbook'){
            steps{
                echo 'ping ansible and execute playbook'
                sh 'ansible allslaves -m ping'
                sh 'ansible-playbook myplaybook.yml --syntax -check'
                sh 'ansible-playbook myplaybook.yml' 
            }
        }
    }
}
