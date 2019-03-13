#!/bin/bash
if [ $# != 3 ]; then
  echo "Usage: kbc <add|pull|push> <component> <branch>"
  exit 2
fi

#Check if command exists
if [ $1 != "add" -a $1 != "pull" -a $1 != "push" ]; then
  echo "Unknown command: $1"
  exit 2
fi

#Check if the component and branch exist
line=`git ls-remote git@github.com:k2jf/$2.git|grep "/$3$"|wc -l`
if [ $line == 0 ]; then
  echo "Component or branch does not exist: $2 $3"
  exit 2
fi

if [ $1 == "add" ]; then
  if [ $# == 3 ]
  then
    cmd1="git remote add -f $2 git@github.com:k2jf/$2.git"
    echo $cmd1
    $cmd1
    cmd2="git subtree add -P src/main/java/com/k2data/${2//-//} $2 $3"
    echo $cmd2
    $cmd2
  else
    echo "Usage: kbc add <component> <branch>"
  fi
elif [ $1 == "pull" ]; then
  if [ $# == 3 ]; then
    cmd1="git subtree pull -P src/main/java/com/k2data/${2//-//} $2 $3"
    echo $cmd1
    $cmd1
  else
    echo "Usage: kbc pull <component> <branch>"
  fi
elif [ $1 == "push" ]; then
  if [ $# == 3 ]; then
    cmd1="git subtree push -P src/main/java/com/k2data/${2//-//} $2 $3"
    echo $cmd1
    $cmd1
  else
    echo "Usage: kbc push <component> <branch>"
  fi
fi
