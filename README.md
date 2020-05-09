# Categorizer API

## Purpose
It classifies items into categories and store them in an in-memory DB.
The data is a list of category sub-category

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

This is a demo spring-boot project.

---

## Setting Up
What do we need for local Development.

### Technology Stack
- Java 14
- Spring Boot 2.2.7
- Maven 3.6.1

### Installation Guide
- VM options
- Environment variables
- Ports
- IntelliJ Plugins
- ...

### Build
### Deploy
```bash
mvn clean compile test
```
---


---

## Getting Started
> How can we use it? How do we know it is working?

### Architecture
### Servers
### Known clients

### Dependencies

### Cron Jobs
### Pipeline
### Monitoring
### Alerting

### API documentation
[Endpoints](#)

### Troubleshooting
|Integration      |Prod02           |
|-----------------|-----------------|
|[Manager](#)     |[Manager](#)     |
|[Ping](http://int-notifications-vip.bcinfra.net/notifications-api/health)        |[Ping](#)        |
|[Health Check](http://int-notifications-vip.bcinfra.net/notifications-api/health)|[Health Check](#)|
|[DB Check](#)    |[DB Check](#)    |

### Known errors
