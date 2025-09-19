#!/bin/sh
MVNW_DIR="$(cd "$(dirname "$0")"; pwd)"
JAVA_EXE="java"
WRAPPER_JAR="$MVNW_DIR/.mvn/wrapper/maven-wrapper.jar"
WRAPPER_PROPS="$MVNW_DIR/.mvn/wrapper/maven-wrapper.properties"
if [ ! -f "$WRAPPER_JAR" ]; then
  mkdir -p "$(dirname "$WRAPPER_JAR")"
  curl -fsSL "$(grep -i wrapperUrl "$WRAPPER_PROPS" | cut -d= -f2)" -o "$WRAPPER_JAR"
fi
exec "$JAVA_EXE" -classpath "$WRAPPER_JAR" org.apache.maven.wrapper.MavenWrapperMain "$@"
