Java Concurrency
===================

Description
------------
A few projects in Java concurrency.

Original written for a CS class I had to take.

Putting them here for safe keeping and reference if needed.

Coolidge Bus
------------
Implementation of the Coolidge Bus problem.

You can run the code via the Makefile included

In order to compile the code, simply run "make" or you can explicitly run
"make coolidge" (both do the same thing).

You can remove the compiled code with "make clean".
Please run a "make clean" before every compile after the initial.

Once the code has been compiled, it can be run from the "bin"
directory.
  - "cd ./coolidge_bus/bin"
  - "coolidge_bus.Driver"

You pass the number of students you want as an argument -NUMSTUDENTS.
If no argument is provided, the code will run with only 50 students.

An example would look like:
  (from inside coolidge_bus/bin/) `java coolidge_bus.Driver -NUMSTUDENTS 100`

Santa Claus
------------
Implementation of the Santa Claus Problem

You can run the code via the Makefile included

In order to compile the code, simply run "make" or you can explicitly run
"make santa" (both do the same thing).

You can remove the compiled code with "make clean".
Please run a "make clean" before every compile after the initial.

Once the code has been compiled, it can be run from the "bin"
directory.
  - "cd ./santa_claus/bin"
  - "santa_claus.NorthPole"

This code takes no command line arguments or parameters.

An example would look like:
  (from inside santa_claus/bin/) `java santa_claus.NorthPole`

Par vs Seq
------------
Implementation of a Parallel vs. Sequential execution.
Shows timings for execution based on size of elements passed.

You can run the code via the Makefile included.

In order to compile the code, simply run "make".
The default option in the makefile is to compile the code for both
solutions to the assignment.  If you with to only compile one, run
"make parallel" or "make sequential".

You can remove the compiled code with "make clean".
Please run a "make clean" before every compile after the initial.

Once the code has been compiled, it can be run from the "bin"
directory.
  - "cd ./par/bin" or "cd ./seq/bin"
  - "java cs361_1_par.histogram" or "java cs361_1_seq.histogram"

Remember to pass in the proper arguments to the code.
The code will not run if it doesn't have enough arguments.
For sequential, you can pass NUMTHREADS but it will just disregard it.

An example would look like:
  (from inside par/bin/) `java cs361_1_par.histogram -DATASIZE 7000, -NUMTHREADS 8, -NUMBINS 7`

You can pass the arguments in any order you like, just make sure they're
all there!
