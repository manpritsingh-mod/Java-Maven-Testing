# Jenkins Pipeline Test Project

This is a test project designed to validate Jenkins shared library functionality with categorized test execution (smoke, sanity, regression).

## Test Categories

This project supports different test categories using Maven profiles and JUnit 5 tags:

### 🔥 Smoke Tests
- **Purpose**: Critical functionality tests that must pass
- **Tag**: `@Tag("smoke")`
- **Command**: `mvn test -Psmoke`
- **Examples**: Basic arithmetic operations, core functionality

### 🧪 Sanity Tests  
- **Purpose**: Basic functionality verification
- **Tag**: `@Tag("sanity")`
- **Command**: `mvn test -Psanity`
- **Examples**: Edge cases, input validation, basic workflows

### 🔄 Regression Tests
- **Purpose**: Comprehensive test suite for full validation
- **Tag**: `@Tag("regression")`
- **Command**: `mvn test -Pregression`
- **Examples**: Complex scenarios, error handling, parameterized tests

## Running Tests

### Local Execution
```bash
# Run specific test categories
mvn test -Psmoke          # Smoke tests only
mvn test -Psanity         # Sanity tests only  
mvn test -Pregression     # Regression tests only
mvn test -Pall-tests      # All categories together
mvn test                  # Regular unit tests

# Using the batch script (Windows)
run-tests.bat smoke       # Run smoke tests
run-tests.bat sanity      # Run sanity tests
run-tests.bat regression  # Run regression tests
run-tests.bat all         # Run all test categories
```

### Jenkins Pipeline Integration
The project uses JavaMavenScript.groovy shared library methods:
- `JavaMavenScript.smokeTestCommand()` → `mvn test -Psmoke`
- `JavaMavenScript.sanityTestCommand()` → `mvn test -Psanity`  
- `JavaMavenScript.regressionTestCommand()` → `mvn test -Pregression`

## Features Tested

- ✅ Java Maven project detection
- ✅ Dependency installation
- ✅ Code compilation
- ✅ Categorized test execution (Smoke/Sanity/Regression)
- ✅ Unit test execution (JUnit 5)
- ✅ Lint checking (Checkstyle)
- ✅ Allure report generation
- ✅ Email notifications
- ✅ Build artifacts

## Test Coverage

- **15 test methods** across 2 test classes
- **Smoke**: 4 tests (critical functionality)
- **Sanity**: 4 tests (basic verification)
- **Regression**: 7 tests (comprehensive coverage)
- **Parameterized tests** for comprehensive coverage
- **Exception testing** for error scenarios
- **Intentional checkstyle violations** for lint testing

## Allure Reporting

This project is enhanced with Allure reporting for detailed test analysis:

### Features
- **Epic/Feature/Story** organization
- **Severity levels** (Blocker, Critical, Normal, Minor, Trivial)
- **Step-by-step** test execution details
- **Rich descriptions** and annotations
- **Test categorization** by tags

### Generate Allure Report Locally
```bash
# Run tests and generate Allure results
mvn test

# Generate and serve Allure report
mvn allure:report
mvn allure:serve
```

### Allure Annotations Used
- `@Epic` - High-level feature grouping
- `@Feature` - Feature-level grouping  
- `@Story` - User story level
- `@Severity` - Test importance level
- `@Description` - Detailed test description
- `Allure.step()` - Step-by-step execution

## Expected Results

When pipeline runs successfully:
- **Tests**: 15 total, categorized by smoke/sanity/regression
- **Lint**: Several checkstyle violations (intentional)
- **Allure Report**: Comprehensive test results with charts, steps, and categorization
- **Email**: Build summary with test/lint counts
