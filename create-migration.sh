#!/bin/bash

function usage() {
  echo "Usage:"
  echo "create-migration.sh -h    Displays this message"
  echo "create-migration.sh -d <description> Creates a new migration with description"
}

function create_migration() {
  description=$1
  now=$(date -u +%Y%m%d%H%M%S)
  snake_case_description=$(echo "$description" | sed 's/  */_/g' | tr '[:upper:]' '[:lower:]')
  file="src/main/resources/db/migration/V${now}__${snake_case_description}.sql"
  touch "$file"
}


while getopts ":h:d:" opt; do
  case ${opt} in
    h )
      usage
      exit 0
      ;;
    d )
      description=$OPTARG
      create_migration "$description"
      ;;
    * )
      usage
      exit 1
      ;;
  esac
done
