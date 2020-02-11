pipeline {
    agent { label "comp" }

    options {
        timestamps ()
    }

    stages {
        stage ('Build') {
            steps {
                script {
                    sh "chmod 775 gradlew"
                    if (isUnix()){
                        sh "./gradlew build"
                    }else{
                        bat label: '', script: 'gradlew build'
                    }
                }
            }
        }
        stage ('Test') {
            steps {
                script {
                    def folder = pwd()
                    def testNGxmlPath
                    if (isUnix()){
                        testNGxmlPath = "$folder/testing/src/main/resources/suite.xml"
                        sh "./gradlew run -DtestNGxmlPath=\"" + testNGxmlPath "\""
                    }else{
                        testNGxmlPath = "$folder\\testing\\src\\main\\resources\\suite.xml"
                        bat label: '', script: 'gradlew run -DtestNGxmlPath=' +'"' + testNGxmlPath +'"'
                    }
                }
            }
        }
    }
}
