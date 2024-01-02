UNITTESTS = test_basic test_errors

.SILENT:

all:
	echo "Compiling Java source files"
	ant build
	echo "Generating jar file"
	ant build-jar

build:
	ant build

jar:
	ant build-jar

unit: all
	for tst in $(UNITTESTS); do \
		make $$tst; \
	done

test_%: all
	echo "Running test" $@
	java -cp plp1.jar:../lib/junit4.jar org.junit.runner.JUnitCore test.$@

clean:
	echo "Cleaning up class files and jar file"
	ant clean

