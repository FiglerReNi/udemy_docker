# a line separator beállítása alul LF legyen mindig
# base image amiből kiindulunk, ez a debiant használja base image-nek
FROM openjdk

# itt tárolja a Spring Boot a temp file-okat, ha nem adjuk meg a tomcat nem fog jól futni
VOLUME /tmp
# hozzáadjuk a jdk-hoz a futtatandó jar-t, és megmondjuk hová tegye, illetve itt át is neveztük. A myapp.jar helyére írhatnánk csak egy útvonalat is
ADD maven/ApplicationSimple-0.0.1-SNAPSHOT.jar myapp.jar
# frissíti a jar file dátumát, hogy miért kell:
# https://pubs.opengroup.org/onlinepubs/9699919799/utilities/touch.html
# https://www.quora.com/Bash-shell-Why-is-the-touch-command-named-as-such
RUN sh -c 'touch /myapp.jar'
# ez a parancs futtatja a jar-t
# a második résznek az a lényege, hogy gyorsabban induljon a tomcat
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/myapp.jar"]