# Release Manager

## Intro

Our whole system is a highly distributed / cloud native / “microservice” environment. Each Service has its own Build and Deploy process and therefore its own Version Lifecycle.
Communication between services are either direct REST calls or via Messagebus (RabbitMQ)
Also we have multiple Environments running for our development stages as well as for individual customers. Each environment can have different versions of the deployed services.

What we need as a business requirement and also for our own internal analysis capabilities when there are problems to have 1 overall Version Number which can be shown to the customer as well as saved to the data created which tells us exactly which versions of which services where deployed at a given time

To handle that we have a Deployment watcher which is running on our cluster and everytime a service is deployed, the watcher sends a message to the release manager with Servicename + version which was deployed.

The releasemanager has to check if the service version has changed, and if yes create a new “SystemVersion” and link that to all service versions deployed at the time and returns the current systemVersion number

**Goal is to write a releasemanager application with 2 Endpoints**

````
POST /deploy
Payload: serviceName (string) + serviceVersionNumber (int)
Response: SystemVersionNumber (int)
````

Checks if serviceVersionNumber changed, if yes increases SystemVersionNumber and takes care that the new SystemVersionNumber is linked to all services deployed at the time

```GET /services?systemVersion=<Int>```

returns a list of Services and their corresponding service version numbers deployed with the given SystemVersionNumber

Example, starting with empty Deployment
````
POST /deploy
{name: “Service A”, version: 1 }
Response: 1

POST /deploy
{name: “Service B”, version: 1 }
Response: 2

POST /deploy
{name: “Service A”, version: 2 }
Response: 3

POST /deploy
{name: “Service B”, version: 1 }
Response: 3

GET /services?systemVersion=2
Response: [ {name: “Service A”, version: 1”}, {name: “Service B”, version: 1}]

GET /services?systemVersion=3
Response: [ {name: “Service A”, version: 2”}, {name: “Service B”, version: 1}]
````