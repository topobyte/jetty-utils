#!/bin/bash

DIR=$(dirname $0)
REAL=$(realpath $DIR)
cd "$DIR/../test"
CMD="$REAL/jetty-utils.sh"
CLASS="de.topobyte.jetty.utils.RunEmbeddedServer"

exec "$CMD" "$CLASS" "$@"
