FROM jboss/wildfly:23.0.0.Final
ADD target/microprofile-comprovante.war /opt/jboss/wildfly/standalone/deployments/