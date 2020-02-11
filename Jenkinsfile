pipeline {
    agent { label "comp" }

    options {
        timestamps ()
    }

    parameters
    {
        choice (name: 'BROWSER',
        choices: "chrome\nfirefox\nedge",
        description: 'Where to run scripts from'
        )
        string (name: 'HUB_URL',
        defaultValue: '',
        description: ''
        )
        string (name: 'SUITE',
        defaultValue: "testing\\src\\main\\resources\\suite.xml",
        description: ''
        )
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
                    def testNGxmlPath = "$folder/$params.SUITE"
                    if (isUnix()){
                        sh "./gradlew run -DtestNGxmlPath=$testNGxmlPath -Dbrowser=$params.BROWSER -DhubUrl=$params.HUB_URL"
                    }else{
                        bat label: '', script: "gradlew run -DtestNGxmlPath=$testNGxmlPath -Dbrowser=$params.BROWSER -DhubUrl=$params.HUB_URL"
                    }
                }
            }
        }
    }
}
