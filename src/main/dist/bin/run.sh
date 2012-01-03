#!/bin/sh
cygwin=false
os400=false
darwin=false
case "`uname`" in
CYGWIN*) cygwin=true;;
OS400*) os400=true;;
Darwin*) darwin=true;;
esac

# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

# Get standard environment variables
PRGDIR=`dirname "$PRG"`

# Only set POLYGRAPH_HOME if not already set
[ -z "$POLYGRAPH_HOME" ] && POLYGRAPH_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`

echo Starting Polygraph in $POLYGRAPH_HOME
cd "${POLYGRAPH_HOME}"
java -cp "lib/*:${CLASSPATH}" com.illmeyer.polygraph.dist.simple.Polygraph $@
