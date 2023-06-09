# a line separator beállítása alul LF legyen mindig
# base image amiből kiindulunk
FROM debian

# telepítünk jdk-t, a -y azt jelenti, hogy automatikusan yes-t válaszol a telepítő kérdéseire
RUN apt update -y
RUN apt install -y default-jdk

VOLUME /tmp
# hozzáadjuk a jdk-hoz a futtatandó jar-t
ADD /udemy_docker-0.0.1-SNAPSHOT.jar myapp.jar
# https://pubs.opengroup.org/onlinepubs/9699919799/utilities/touch.html
# https://www.quora.com/Bash-shell-Why-is-the-touch-command-named-as-such
RUN sh -c 'touch /myapp.jar'
# a második résznek az a lényege, hogy gyorsabban induljon a tomcat
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/myapp.jar"]