import groovy.json.JsonOutput

pipeline {
    //The agent section specifies where the entire Pipeline, or a specific stage,
    //will execute in the Jenkins environment depending on where the agent section is placed.
    agent any

    triggers {
        parameterizedCron('''
        0 8 * * * %ENV=uat
        ''')
    }

    options {
        timestamps()
        timeout(time: 20, unit: 'HOURS')
        ansiColor('xterm')
    }

    parameters {
         choice(name: 'ENV', choices: ['uat', 'qa', 'dev'], description: 'Pick the env against which you need to run test')
    }

    stages {

        stage('Building'){
           steps {
             echo "Building the application"
//               checkout scmGit(
//                     branches: [[name: "master"]],
//                     userRemoteConfigs: [[credentialsId: 'ssh-keys',
//                         url: 'git@github.com:aditya2001/restassured-api-java-paypal.git']])
             }
          }
        stage('Testing'){
          steps{
            echo "Testing on ${params.ENV}"
//             bat "mvn test -Denv=${params.ENV}"
               bat "mvn clean test"
          }
        }

        stage('Deploying'){
         steps{
          echo "Deploying"
        }
      }
     }

        post {
             always {
                 archiveArtifacts artifacts: "test output/PdfReport/ExtentPdf.pdf, target/surefire-reports/emailable-report.html", onlyIfSuccessful: false
                 publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'target/surefire-reports/', reportFiles: 'emailable-report.html', reportName: 'HTML Report', reportTitles: ''])
             }
         }
 }

