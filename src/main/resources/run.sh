#!/bin/bash
ARGS=(
	0				#metoda pomiaru odleglosci (0 - CENTROIDS, 1- AVERAGE, 2 - MINIMAL)
	2				#stopien odleglosci Minkowskiego (1 - Manchatan, 2- euclides, etc...)
	0				#paramert errorEpsilon algorytmu k-means
	1000			#maksymalna liczba iteracji algorytmu k-means
	1.2				#parametr reduceFactor grupowania herachicznego
	example.tsv		#plik z którego wczytujemy dane
	4				#liczba klastrów na które należy podzielić punkty
)

java -jar kmeans.jar ${ARGS[*]}
