# Competitive Loan Calculator

This project is a command-line rate calculation system, allowing prospective borrowers to
obtain a quote from our pool of lenders for 36 month loans. 


## Code

This application is developed with TDD approach. 
Clean Code principles are applied.

Technologies used:

* Java 8
* Maven
* Spring Boot
* Spring AOP
* Mockito
* Hamcrest

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* Java 8
* Maven

### Installing

```
mvn clean package
```

## Running the automated tests

```
mvn clean test
```

## Manual tests

```
cd ./target
java -jar loan-calculator-1.0-SNAPSHOT.jar Market.csv 1000
```

## Deployment

```
mvn clean package
cd ./target
ls -al
------------------
loan-calculator-1.0-SNAPSHOT.jar
------------------
java -jar loan-calculator-1.0-SNAPSHOT.jar Market.csv 1000

```
## Authors

* **Bahadir Yilmaz** - *Initial work* - [yilmazbahadir](https://github.com/yilmazbahadir)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

