#!/bin/sh
set -e

branch=$(git rev-parse --abbrev-ref HEAD)

if [ "$branch" = "master" ]
then
  echo ERROR: Do NOT commit on master branch
  exit 1
fi
