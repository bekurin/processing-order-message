#!/bin/bash

echo "Enter the names of the submodules you want to build and run, separated by space:"
read -a SUBMODULES

PROJECT_ROOT=$(pwd)

for module in "${SUBMODULES[@]}"; do
  echo "Building $module..."
  cd $module
  $PROJECT_ROOT/gradlew clean build -x test
  cd $PROJECT_ROOT
done

for module in "${SUBMODULES[@]}"; do
  echo "Running $module..."
  cd $module
  nohup $PROJECT_ROOT/gradlew bootRun &
  cd $PROJECT_ROOT
done

echo "All requested submodules have been started."
