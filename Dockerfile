FROM openjdk:11
ADD target/Vilki_Palki_Admin.jar vilki_palki.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "vilki_palki.jar"]