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
                def server = Artifactory.server 'myjfrog'
                def uploadSpec = """{
                    "files":[
                        {
                            "pattern": "/var/lib/jenkins/.m2/repository/com/abc/mvn-hello-world/1.5/mvn-hello-world-1.5.war",
                            "target": "sageit/"
                        }
                    ]
                }"""
                  server.upload(uploadSpec)
                                
                }
            }
        }
    
}
