UTILS = src/domain/utils
COMPARATORS = src/domain/comparators
CONTROLADORES = src/domain/controllers
CLASSES = src/domain/classes
VISTES = src/presentation
DATAMANAGER = src/persistence


default: build

clean:
	rm -f -r bin/*

build:
	javac -cp libs/json-simple-1.1.1.jar -d bin $(UTILS)/*.java $(CLASSES)/*.java $(COMPARATORS)/*.java $(CONTROLADORES)/*.java $(DATAMANAGER)/*.java $(VISTES)/*.java

run:
	java -cp libs/json-simple-1.1.1.jar:bin src.presentation.Main

runjar:
	java -jar FIB-PROP-Schedules-Generator.jar
