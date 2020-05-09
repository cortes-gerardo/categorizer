# Categorizer API

## Purpose
This is a demo spring-boot demo project.

It classifies items into categories and store them in an in-memory DB.
The data is a list of category subcategory pairs.

|Category   |Subcategory|
|--------   |-----------|
|PERSON	    |Bob Jones  |
|PLACE	    |Washington |
|PERSON	    |Mary       |
|COMPUTER	|Mac        |
|PERSON	    |Bob Jones  |
|OTHER	    |Tree       |
|ANIMAL	    |Dog        |
|PLACE	    |Texas      |
|FOOD	    |Steak      |
|ANIMAL	    |Cat        |
|PERSON	    |Mac        |

There is a list of valid categories managed by the API. By default, the valid categories are:

|Category   |
|--------   |
|PERSON     |
|PLACE      |
|ANIMAL     |
|COMPUTER   |
|OTHER      |

## Table of Contents
<!--ts-->
   * [Purpose](#purpose)
   * [Setting Up](#setting-up)
      * [Technology Stack](#technology-stack)
      * [Deploy](#deploy)
   * [Getting Started](#getting-started)
      * [Endpoints](#endpoints)
      * [Data Base](#data-base)
      * [Monitoring](#monitoring)
<!--te-->

---

## Setting Up
What do we need for local Development.

### Technology Stack
- Java 14
- Spring Boot 2.2.7
- Maven 3.6.1

### Deploy

```
mvn clean compile test package
```

---

## Getting Started
How can we use it

### Endpoints
the applications have different endpoints to manage 
```
```

### Data Base
I'm using an in-memory data base, H2 allows see at it's content in the browser once the application is runnig.
```
http://localhost:8080/h2-console
```

You need to use this URL
```
jdbc:h2:mem:testdb
```

### Monitoring
There is and endpoint dedicated to recollect metrics in a prometheus server
```
http://localhost:8080/actuator/prometheus
```

### API documentation

