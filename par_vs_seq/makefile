#
# Tim Monfette (tjm354)
# CS 361
# Makefile for Assignment 1
#

# Default is to compile both
default: parallel sequential

# Compile parallel code
parallel:
	@mkdir ./par/bin
	@javac -d ./par/bin/ ./par/*.java

# Compile sequential code
sequential:
	@mkdir ./seq/bin
	@javac -d ./seq/bin/ ./seq/*.java

# Remove compiled code
clean:
	@rm -rf ./par/bin/
	@rm -rf ./seq/bin/
