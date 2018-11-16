
make:
	javac -d bin/ -cp libs/json-simple-1.1.1.jar src/domain/utils/*.java src/domain/classes/*.java src/domain/comparators/*.java src/domain/controllers/*.java src/persistence/*.java src/presentation/*.java
	javac -d bin/ src/domain/drivers/*.java
	javac -d bin/ -cp libs/*.jar:bin/  src/domain/junits/*.java

run:
	java -cp bin/:libs/json-simple-1.1.1.jar src.presentation.CtrlPresenter

maintest:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.MainTests

subjectDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.SubjectDriver

sessionDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.SessionDriver

theoryClassroomDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.TheoryClassroomDriver

laboratoryClassroomDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.LabClassroomDriver

theoryClassDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.TheoryClassDriver

laboratoryClassDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.LaboratoryClassDriver

problemsClassDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.ProblemasClassDriver

subjectsSetDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.SubjectsSetDriver

classSetDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.ClassSetDriver

classroomSetDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.ClassroomSetDriver

musDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.MUSDriver

classroomSessionDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.ClassroomSessionDriver

constraintsDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.src.domain.drivers.ConstraintsDriver

junit:
	java -cp bin/:libs/ src.domain.junits.TestSchedule

clean:
	rm -f -r bin/*
