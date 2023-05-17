# Release Manager

## Introduction
The goal is to create a release manager application with two endpoints for managing service deployments and retrieving service versions associated with a system version.
## Endpoints

### `POST /deploy`

- Payload:
   - `serviceName` (string) - The name of the service being deployed.
   - `serviceVersionNumber` (integer) - The version number of the service being deployed.
- Response:
   - `SystemVersionNumber` (integer) - The current System Version number.
- Functionality:
   - Checks if the `serviceVersionNumber` has changed. If it has, the System Version Number is increased, and the new System Version Number is linked to all services deployed at that time. The current System Version Number is returned as the response.

### `GET /services`

- Query parameter:
   - `systemVersion` (integer) - The System Version number.
- Response:
   - A list of services and their corresponding service version numbers deployed with the given System Version number.
- Functionality:
   - Retrieves a list of services and their associated service version numbers that were deployed with the specified System Version.

## Example Usage

Starting with an empty deployment:

1. Deploy Service A, Version 1:
   - Request: `POST /deploy`
   - Payload: `{name: "Service A", version: 1}`
   - Response: `1`

2. Deploy Service B, Version 1:
   - Request: `POST /deploy`
   - Payload: `{name: "Service B", version: 1}`
   - Response: `2`

3. Update Service A to Version 2:
   - Request: `POST /deploy`
   - Payload: `{name: "Service A", version: 2}`
   - Response: `3`

4. Redeploy Service B, Version 1:
   - Request: `POST /deploy`
   - Payload: `{name: "Service B", version: 1}`
   - Response: `3`

Retrieve services deployed with System Version 2:
- Request: `GET /services?systemVersion=2`
- Response: `[ {name: "Service A", version: 1}, {name: "Service B", version: 1} ]`

Retrieve services deployed with System Version 3:
- Request: `GET /services?systemVersion=3`
- Response: `[ {name: "Service A", version: 2}, {name: "Service B", version: 1} ]`
### About system

This application was written with using SpringBoot, Gradle, Kotlin, Spring, Junit and H2 as database.
Detekt is a static code analysis tool for the Kotlin programming language.

How to start:
Build and run with command ```./gradlew bootRun``` from project directory or from IDE. No additional arguments are needed
Example of request you can find in postman collection in the project folder.
Swagger is added as well.

### WischList
1. Increase test coverage
    1. Tests for ```ReleaseVersionServiceImpl```
2. Refactoring ```ReleaseVersionServiceImpl```
3. postman collection for easy calls
4. Make reactive
5. Try Qodana