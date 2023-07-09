pipeline {
	agent any


	tools {
		maven 'jenkins-maven'
		jdk 'java-8'
	}

	stages {

		stage('Build producer application'){
			steps {
				sh "cd producer && mvn clean install -DskipTests"
			}
		}

		stage('Test producer application'){
			steps{
				sh "cd producer && mvn test"
			}
		}

		stage('Deploy producer application') {
			steps {
			    sh "echo running deployment"
			}
		}
		
		stage('Build consumer application'){
			steps {
				sh "cd consumer && mvn clean install -DskipTests"
			}
		}

		stage('Test consumer application'){
			steps{
				sh "cd consumer && mvn test"
			}
		}

		stage('Deploy consumer application') {
			steps {
			    sh "echo running deployment"
			}
		}
	}
}