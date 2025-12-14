# 1. Image de base pour la construction (Build Stage)
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app

# Copiez le pom.xml et téléchargez les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiez les sources du projet et construisez le JAR
COPY . .
RUN mvn clean install -DskipTests

# Récupérez le nom du JAR (ajustez si votre nom de JAR est différent de 'pp.jar')
# Assurez-vous que le JAR est créé dans le dossier target
# Vous pouvez aussi utiliser find target/*.jar pour un nom dynamique
RUN cp target/pp.jar app.jar

# 2. Image d'exécution (Run Stage) : plus légère
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiez le JAR depuis l'étape de construction
COPY --from=build /app/app.jar .

# Commande pour démarrer l'application (Étape 5/7)
CMD ["java", "-jar", "app.jar"]