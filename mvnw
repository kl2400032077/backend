#!/usr/bin/env sh

BASEDIR=$(cd "$(dirname "$0")" && pwd)
WRAPPER_DIR="$BASEDIR/.mvn/wrapper"
WRAPPER_JAR="$WRAPPER_DIR/maven-wrapper.jar"
WRAPPER_PROPERTIES="$WRAPPER_DIR/maven-wrapper.properties"

if [ ! -f "$WRAPPER_PROPERTIES" ]; then
  echo "Missing Maven wrapper properties at $WRAPPER_PROPERTIES" >&2
  exit 1
fi

WRAPPER_URL=$(grep '^wrapperUrl=' "$WRAPPER_PROPERTIES" | cut -d'=' -f2-)

if [ ! -f "$WRAPPER_JAR" ]; then
  echo "Maven wrapper jar missing. Downloading..."
  mkdir -p "$WRAPPER_DIR"
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL "$WRAPPER_URL" -o "$WRAPPER_JAR"
  else
    wget -q "$WRAPPER_URL" -O "$WRAPPER_JAR"
  fi
fi

exec java -classpath "$WRAPPER_JAR" -Dmaven.multiModuleProjectDirectory="$BASEDIR" org.apache.maven.wrapper.MavenWrapperMain "$@"

