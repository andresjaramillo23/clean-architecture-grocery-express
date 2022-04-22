# use maven as a builder image to separate the compile step from our deployment image
FROM maven:3.8.1-openjdk-11-slim AS builder
WORKDIR GroceryExpress
COPY ./ ./
RUN mvn clean install

FROM openjdk:11-slim AS backend
WORKDIR GroceryExpress
# copy the jar from the builder image to the final image
#COPY --from=builder /GroceryExpress/target/GroceryExpress-0.0.1-SNAPSHOT.jar /GroceryExpress/GroceryExpress-0.0.1-SNAPSHOT.jar
EXPOSE 8080
EXPOSE 5432


FROM openjdk:11-slim
RUN apt-get update && apt-get install -y curl
WORKDIR /usr/src/cs6310
COPY test_scenarios ./
COPY test_results ./
COPY --from=builder /GroceryExpress/target/GroceryExpress-0.0.1-SNAPSHOT.jar /usr/src/cs6310/GroceryExpress-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "drone_delivery.jar", "commands_00.txt"]
CMD ["java", "-jar", "GroceryExpress-0.0.1-SNAPSHOT.jar", "commands_00.txt"]