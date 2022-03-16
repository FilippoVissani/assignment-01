# PCD Assignment 01
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
otherwise, if you do not have JPF file, you can specify classpath directly in the command:
```
java -jar path/to/RunJPF.jar +classpath=./path/to/bin/main package.className
```
