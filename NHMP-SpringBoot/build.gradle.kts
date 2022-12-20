plugins {
	java
	id("org.springframework.boot") version "3.0.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_HIGHER

repositories {
	mavenCentral()
}

dependencies {
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-jdbc
	implementation("org.springframework.boot:spring-boot-starter-jdbc:3.0.0")
	// https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc8
	implementation("com.oracle.database.jdbc:ojdbc8:21.8.0.0")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.0")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.0.0")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
	implementation("org.springframework.boot:spring-boot-starter-web:3.0.0")
	// https://mvnrepository.com/artifact/org.webjars/bootstrap
	implementation("org.webjars:bootstrap:5.2.3")
	// https://mvnrepository.com/artifact/org.webjars.bower/jquery
	implementation("org.webjars.bower:jquery:3.6.2")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
	compileOnly("org.projectlombok:lombok:1.18.24")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools
	implementation("org.springframework.boot:spring-boot-devtools:3.0.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
