## Muzix Application

##### This is an online application that helps users manage music listed on Music Database (https://www.last.fm). Using this app, a user should be able to search and manage music.

Added MongoDB Docker container.

**Docker containers run on localhost IP. Stop any services running in those ports before starting.**

Added support for config-server. See [spring-boot-config-server](https://github.com/Bsd15/spring-boot-config-server)

Added Eureka Naming server config. 


Task - 8: Created 3 tracks using @Value, @PropertySource, Environment and @ConfigurationProperties. Check com.stackroute.trackservice.seeddata.TrackServiceApplicationListener, TrackServiceSeedDataCommandLineRunner

Task - 10: @Primary added to TrackServiceImpl and @Qualifier added in TrackServiceSeedDataCommandLineRunner

Task - 11: Removed @Primary and @Qualifier to demonstrate profiles.

Task - 12: Create database name "taskService" in mysql and add setting in application.properties.

Changed From MySQL to MongoDB.

[Reference for @Query](http://zetcode.com/springboot/datajpaquery/)

[Refrence for Swagger2](https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api)

[Reference for ApplicationListener and CommanLineRunner](https://www.baeldung.com/running-setup-logic-on-startup-in-spring)

[Reference for Task 8 #1](https://www.baeldung.com/properties-with-spring)

[Reference for Task 8 #2](http://appsdeveloperblog.com/reading-application-properties-spring-boot/)

[Reference for @Profiles #1](https://dzone.com/articles/spring-boot-profiles-1)

[Reference for @Profile #2](https://www.baeldung.com/spring-profiles)

[Reference for H2 to MySql. Task - 12](https://springframework.guru/configuring-spring-boot-for-mysql/)
