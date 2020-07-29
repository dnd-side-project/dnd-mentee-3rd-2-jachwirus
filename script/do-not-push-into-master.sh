#!/bin/sh

protected_branch='master'
current_branch=$(git symbolic-ref HEAD | sed -e 's,.*/\(.*\),\1,')

if [ "$protected_branch" = "$current_branch" ]
then
  echo FATAL: Do NOT push on master branch
  exit 1
fi

# ref : https://ghost.org/changelog/prevent-master-push/