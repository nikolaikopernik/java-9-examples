#!/bin/bash
MODULE=java9.jigsaw

echo Clean...
rm -r build/mods/*
mkdir -p build/mods/$MODULE

echo Compiling...
javac -d build/mods/$MODULE src/main/java/java9/jigsaw/*

echo Running...
java --module-path build/mods -m $MODULE/java9.jigsaw.GreetingEx


echo JAR...
rm -r build/mlib
mkdir -p build/mlib
jar --create --file=build/mlib/$MODULE.jar --main-class=java9.jigsaw.GreetingEx -c build/mods/$MODULE
ls build/mlib
java -p build/mlib -m $MODULE

