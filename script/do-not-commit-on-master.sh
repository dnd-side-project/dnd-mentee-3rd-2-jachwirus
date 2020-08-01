#!/bin/sh

protected_branch='master'
current_branch=$(git rev-parse --abbrev-ref HEAD)

if [ "$protected_branch" = "$current_branch" ]
then
  echo FATAL: DO NOT COMMIT ON master BRANCH
  exit 1
fi
