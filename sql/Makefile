all: compile
	@echo -e '[INFO] Done!\n' 
clean:
	@echo -e '\n[INFO] Cleaning Up..'
	@-rm -rf cs430 src/cs430/a6/*.class src/cs430/a6/**/*.class

compile: clean
	@echo -e '[INFO] Compiling the Source..'
	@javac -cp ./lib -d . src/cs430/a6/*.java src/cs430/a6/**/*.java


