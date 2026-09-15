# ISEC3004_Assignment1
## Path Traversal
### Programs
* path.vulnerable.Program
* path.secure.Program1
* path.secure.Program2
* path.secure.Program3
### Instructions
1. `cd ISEC3004_Assignment1`
2. `javac -d bin src/path/vulnerable/*.java src/path/secure/*.java`
3. `java -cp bin <program> $(cat input/path.txt)`
## Regular Expression Denial of Service (ReDoS)
### Programs
* redos.vulnerable.Program
* redos.secure.Program1
* redos.secure.Program2
* redos.secure.Program3
* redos.secure.Program4
### Instructions
1. `cd ISEC3004_Assignment1`
2. `javac -d bin src/redos/vulnerable/*.java src/redos/secure/*.java`
3. `java -cp bin <program> $(cat input/redos.txt)`
