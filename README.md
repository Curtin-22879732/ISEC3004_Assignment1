# ISEC3004_Assignment1
This folder contains the programs, source code, and exploitation payloads created for ISEC3004 Assignment1.
The selected topics are Path Traversal and Regular Expression Denial of Service (ReDoS).
Please note that the programs are dedicated to **JRE 11+** in a **Linux** environment.

> For more information
>> See **src** folder for the source code.<br>
>> See **input** folder for the exploitation payloads.<br>
>> See **data** folder for the files exploited by path traversal.<br>
>> See **log** folder for application logs.<br>

GitHub Project: https://github.com/Curtin-22879732/ISEC3004_Assignment1

## Vulnerabilities
### Path Traversal
#### Programs
* path.vulnerable.Program
* path.secure.Program1
* path.secure.Program2
#### Payloads
* path_good.txt
* path_bad.txt
#### Instructions
1. `cd ISEC3004_Assignment1`
2. `javac -d bin src/path/vulnerable/* src/path/secure/*`
3. `java -cp bin <program> $(cat input/path.txt)`

### Regular Expression Denial of Service (ReDoS)
#### Programs
* redos.vulnerable.Program
* redos.secure.Program1
* redos.secure.Program2
* redos.secure.Program3
* redos.secure.Program4
* redos.secure.Program5
  * lib: com.google.re2j
#### Payloads
* redos_good.txt
* redos_bad.txt
#### Instructions
1. `cd ISEC3004_Assignment1`
2. `javac -d bin -cp lib/* src/redos/vulnerable/* src/redos/secure/*`
3. `java -cp "bin:lib/*" <program> $(cat input/redos.txt)`

