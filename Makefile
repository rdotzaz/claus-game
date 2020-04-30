JC = javac
J = java
JFLAGS = -g

all:
	$(JC) $(JFLAGS) *.java

run:
	$(J) Main

fast-run:
	$(JC) $(JFLAGS) *.java
	$(J) Main

clean:
	rm -f *.class
