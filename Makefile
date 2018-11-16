
make:
	javac -d bin/ -cp libs/json-simple-1.1.1.jar src/domain/utils/*.java src/domain/classes/*.java src/domain/comparators/*.java src/domain/controllers/*.java src/persistence/*.java src/presentation/*.java
	javac -d bin/ src/domain/drivers/*.java

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
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.ConstraintsDriver

scheduleDriver:
	java -cp bin/:libs/json-simple-1.1.1.jar src.domain.drivers.ScheduleDriver

clean:
	rm -f -r bin/*
