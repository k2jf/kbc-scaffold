#!/bin/bash
if [ $1 == "add" ]
then
  if [ $# == 3 ]
  then
    cmd1="git remote add -f $2 git@github.com:k2jf/$2.git"
    echo $cmd1
    $cmd1
    cmd2="git subtree add -P src/main/java/com/k2data/${2//-//} $2 $3"
    echo $cmd2
    $cmd2
  else
    echo "wrong parameter count"
  fi
elif [ $1 == "pull" ]
then
  echo "pulling"
elif [ $1 == "push" ]
then
  echo "pushing"
else
  echo "unknown command: $1"
fi
