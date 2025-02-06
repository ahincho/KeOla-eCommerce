FROM amazoncorretto:21.0.6-alpine
WORKDIR /app
COPY target/ecommerce-1.0.0.jar ecommerce.jar
ENV SPRING_PROFILE=dev
ENV SERVER_PORT=8080
EXPOSE 8080
LABEL name="eCommerce"
LABEL authors="Angel Hincho"
LABEL maintainer="ahincho"
CMD ["java", "-jar", "ecommerce.jar", "--spring.profiles.active=${SPRING_PROFILE}"]