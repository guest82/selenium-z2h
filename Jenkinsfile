pipeline {
    agent {
            docker { image 'maven:3-alpine'}
        }

    options {
        timestamps ()
    }

    stages {
        stage ('Build') {
            steps {
                script {
                    sh "chmod 775 gradlew"
                    sh "./gradlew build"
                }
            }
        }
        stage ('Test') {
            steps {
                script {
                    def folder = pwd()
                    def testNGxmlPath = "$folder/testing/src/main/resources/suite.xml"
                    def jarPath = "$folder/testing/build/libs/testing.jar"
                    sh "java -jar $jarPath $testNGxmlPath"
                }
            }
        }
    }
}
