#!/bin/bash

for BODIES in 100 1000 5000
do
	for STEPS in 1000 10000 50000
	do
		for THREADS in 1 9 17 33
		do
			java -jar assignment-01.main.jar ${BODIES} ${STEPS} ${THREADS}
		done
	
	done
done
