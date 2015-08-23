# Doll Smuggler 0-1 Knapsack Problem

You are a drug trafficker. Every day you meet with a different nice older lady (the mule) and find out how much weight she can carry in her handbag. You then meet with your supplier who has packed various drugs into a myriad of collectible porcelain dolls. Once packed with drugs, each of the precious dolls has a unique combination of weight and street value. Sometimes your supplier has more dolls than the nice lady can carry, though space in her handbag is never an issue. Your job is to choose which dolls the kind soul will carry, maximizing street value, while not going over her weight restriction.

## Objectives

Write a function in the Clojure programming language which given a set of dolls with a name, unique weight, and 
value combination an overall weight restriction produces the optimal set of drug-packed porcelain dolls which are 
within the total weight restriction maximize the total street value of drugs delivered

### Requirements:
- Use leiningen - https://github.com/technomancy/leiningen
- Include multiple high-level test cases to validate your solution.
- Provide instructions in a README for running your test suite from the command line

## Usage

You can execute all tests for the project by executing the following commands:

    $ cd PROJECT_ROOT
    $ lein tests

You can execute the program by executing the following commands:
    
    $ cd PROJECT_ROOT
    $ lein repl
    doll-smuggler.core=> (-main [carry weight] [path to input file] [delimiter])
    
- [carry weight] is a numeric weight the nice older lady (the mule) can carry.
- [path to input file] is a path to an input file containing a list of dolls with a name, unique weight, and value 
separated by whitespace. A different delimiter may be used, but must be supplied in the function call.
- [delimiter] dictates the delimiter to use on the input file. This field is optional. The default is whitespace.

As an example:

    $ cd PROJECT_ROOT
    $ lein repl
    doll-smuggler.core=> (-main 400 "resources/doll-set-1")
    Doll Value:
    1030
    Packed Dolls:
    name	weight	value
    sally	4	50
    eddie	7	20
    grumpy	22	80
    dusty	43	75
    grumpkin	42	70
    marc	11	70
    randal	27	60
    puppy	15	60
    dorothy	50	160
    candice	153	200
    anthony	13	35
    luke	9	150
