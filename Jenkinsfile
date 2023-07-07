pipeline {
	agent any


	tools {
		maven 'jenkins-maven'
		jdk 'java-11'
	}

	stages {

		stage('Build producer application'){
			steps {
				bat "cd producer && mvn clean install -DskipTests"
			}
		}

		stage('Test producer application'){
			steps{
				bat "cd producer && mvn test"
			}
		}

		stage('Deploy producer application') {
			steps {
			    bat "cd producer && mvn jar:jar deploy:deploy"
			}
		}
		
		stage('Build consumer application'){
			steps {
				bat "cd consumer && mvn clean install -DskipTests"
			}
		}

		stage('Test consumer application'){
			steps{
				bat "cd consumer && mvn test"
			}
		}

		stage('Deploy consumer application') {
			steps {
			    bat "cd consumer && mvn jar:jar deploy:deploy"
			}
		}
	}
}