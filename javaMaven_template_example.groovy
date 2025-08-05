/**
 * Example Java Maven Template using JavaMavenScript.groovy shared library
 * This shows how to integrate your JavaMavenScript methods with the Jenkins pipeline
 * Enhanced with Allure reporting support
 */

def call(config) {
    logger.info("=== JAVA MAVEN TEMPLATE EXECUTION ===")
    
    try {
        // Stage 1: Environment Setup and Version Check
        stage('Environment Setup') {
            logger.info("Setting up Java Maven environment")
            
            // Use JavaMavenScript methods for version checking
            sh "${JavaMavenScript.javaVersionCommand()}"
            sh "${JavaMavenScript.mavenVersionCommand()}"
            
            logger.info("Environment setup completed")
        }
        
        // Stage 2: Dependency Installation
        stage('Install Dependencies') {
            logger.info("Installing Maven dependencies")
            
            // Use JavaMavenScript method for dependency installation
            sh "${JavaMavenScript.installDependenciesCommand()}"
            
            logger.info("Dependencies installed successfully")
        }
        
        // Stage 3: Build
        stage('Build') {
            logger.info("Building the project")
            
            // Use JavaMavenScript method for building
            sh "${JavaMavenScript.buildCommand()}"
            
            logger.info("Build completed successfully")
        }
        
        // Stage 4: Code Quality (Lint)
        stage('Code Quality') {
            when {
                expression { config.runLintTests == true }
            }
            steps {
                script {
                    logger.info("Running code quality checks")
                    
                    def lintTool = config.tool_for_lint_testing?.java ?: 'checkstyle'
                    
                    // Use JavaMavenScript method for linting
                    sh "${JavaMavenScript.lintCommand(lintTool)}"
                    
                    logger.info("Code quality checks completed")
                }
            }
        }
        
        // Stage 5: Test Execution (Smoke, Sanity, Regression)
        stage('Test Execution') {
            parallel {
                // Smoke Tests - Critical functionality
                stage('Smoke Tests') {
                    when {
                        expression { config.runSmokeTests == true }
                    }
                    steps {
                        script {
                            logger.info("Running Smoke Tests")
                            
                            // Use JavaMavenScript method for smoke tests
                            sh "${JavaMavenScript.smokeTestCommand()}"
                            
                            // Publish test results
                            publishTestResults testResultsPattern: 'target/surefire-reports/*.xml'
                            
                            logger.info("Smoke tests completed")
                        }
                    }
                }
                
                // Sanity Tests - Basic functionality verification
                stage('Sanity Tests') {
                    when {
                        expression { config.runSanityTests == true }
                    }
                    steps {
                        script {
                            logger.info("Running Sanity Tests")
                            
                            // Use JavaMavenScript method for sanity tests
                            sh "${JavaMavenScript.sanityTestCommand()}"
                            
                            // Publish test results
                            publishTestResults testResultsPattern: 'target/surefire-reports/*.xml'
                            
                            logger.info("Sanity tests completed")
                        }
                    }
                }
                
                // Regression Tests - Comprehensive test suite
                stage('Regression Tests') {
                    when {
                        expression { config.runRegressionTests == true }
                    }
                    steps {
                        script {
                            logger.info("Running Regression Tests")
                            
                            // Use JavaMavenScript method for regression tests
                            sh "${JavaMavenScript.regressionTestCommand()}"
                            
                            // Publish test results
                            publishTestResults testResultsPattern: 'target/surefire-reports/*.xml'
                            
                            logger.info("Regression tests completed")
                        }
                    }
                }
            }
        }
        
        // Stage 6: Unit Tests (if separate from categorized tests)
        stage('Unit Tests') {
            when {
                expression { 
                    config.runUnitTests == true && 
                    !(config.runSmokeTests || config.runSanityTests || config.runRegressionTests)
                }
            }
            steps {
                script {
                    logger.info("Running Unit Tests")
                    
                    def testTool = config.tool_for_unit_testing?.java ?: 'junit'
                    
                    // Use JavaMavenScript method for unit tests
                    sh "${JavaMavenScript.testCommand(testTool)}"
                    
                    // Publish test results
                    publishTestResults testResultsPattern: 'target/surefire-reports/*.xml'
                    
                    logger.info("Unit tests completed")
                }
            }
        }
        
        // Stage 7: Test Reports and Artifacts
        stage('Test Reports') {
            steps {
                script {
                    logger.info("Publishing test reports and artifacts")
                    
                    // Generate Allure report
                    sh "mvn allure:report"
                    
                    // Archive test reports
                    archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                    archiveArtifacts artifacts: 'target/allure-results/**/*', allowEmptyArchive: true
                    archiveArtifacts artifacts: 'target/allure-report/**/*', allowEmptyArchive: true
                    
                    // Publish JUnit test results
                    junit testResultsPattern: 'target/surefire-reports/*.xml'
                    
                    // Publish Allure report
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                    
                    // Publish code coverage if available
                    if (fileExists('target/site/jacoco/jacoco.xml')) {
                        publishCoverage adapters: [jacocoAdapter('target/site/jacoco/jacoco.xml')]
                    }
                    
                    logger.info("Test reports published successfully")
                }
            }
        }
        
    } catch (Exception e) {
        logger.error("Java Maven template execution failed: ${e.getMessage()}")
        throw e
    }
}