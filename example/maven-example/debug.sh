# mvn dependency:copy-dependencies
# java -jar ../sharpencore-1.0.0-jar-with-dependencies.jar src/main/java -clsp target/dependency/*
java -agentlib:jdwp=transport=dt_socket,address=8888,server=y,suspend=n -jar ../sharpencore-1.0.0-jar-with-dependencies.jar -clsp target/dependency/* src/main/java 
