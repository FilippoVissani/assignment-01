# PCD Assignment 01
## Description
The purpose of the assignment is to create a simulator for the movement of N bodies on a two-dimensional plane, which are subjected to two types of forces:
- A repulsive force, whereby every body `bi` exerts on every other body `bj` a force in module equal to `Fij = krep * mi / (dij)^2` where:
  * `mi` is the mass of the body `bi`
  * `krep` is a given constant
  * `dij` is the distance between the two bodies.

The direction of the force is given by the unit vector `(bj - bi)`, therefore it repels the body `bj`.
- A frictional force, whereby on every body `bi` moving at a speed `vi` a force `FRi = - kfri * vi` is exerted which opposes the motion, therefore in the opposite direction to its speed, where `kfri` is a given constant.

## Development tools
1. Intellij IDEA
2. Java Path Finder
3. Java 8 is needed to run JPF
4. If you do not want to install Java 8 you can use this [Docker image](https://hub.docker.com/r/gianlucaaguzzi/pcd-jpf)

## Instructions to run project with Java Path Finder
### Create JPF file
Create a file with .jpf extension with the following structure:
```
target = package.class

classpath = path/to/build/main

listener = gov.nasa.jpf.listener.PreciseRaceDetector

report.console.property_violation = error,trace
```
### Run your program with JPF
1. Open terminal
2. `cd` into project root (directory with project name), Note: THIS POINT IS MANDATORY
3. If using Docker, start the container with following command:
```
docker run -v "$(pwd)":/home -it gianlucaaguzzi/pcd-jpf:latest /bin/bash
```
jump to step 4 otherwise

4. Run your program with JPF using following command:
```
java -jar ./path/to/RunJPF.jar  ./path/to/file.jpf
```
if using Docker:
```
java -jar $JPF ./your/path/to/file.jpf
```
otherwise, if you do not have JPF file, you can specify the classpath directly in the command:
```
java -jar path/to/RunJPF.jar +classpath=./path/to/bin/main package.className
```
