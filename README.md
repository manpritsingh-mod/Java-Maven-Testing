# Jenkins Pipeline Test Project

This is a test project designed to validate Jenkins shared library functionality.

## Features Tested

- ✅ Java Maven project detection
- ✅ Dependency installation
- ✅ Code compilation
- ✅ Unit test execution (JUnit 5)
- ✅ Lint checking (Checkstyle)
- ✅ Allure report generation
- ✅ Email notifications
- ✅ Build artifacts

## Test Coverage

- **15 test methods** across 2 test classes
- **Parameterized tests** for comprehensive coverage
- **Exception testing** for error scenarios
- **Intentional checkstyle violations** for lint testing

## Expected Results

When pipeline runs successfully:
- **Tests**: 15 total, all should pass
- **Lint**: Several checkstyle violations (intentional)
- **Allure Report**: Comprehensive test results with charts
- **Email**: Build summary with test/lint counts
