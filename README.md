# spotlight.datasets-test

A small tool that allows you to reduce the DBpedia Spotlight and Wikipedia Dump sizes, in order to create datasets test used to quickly verify your outputs and to minimize the processing time.    

In order to use this code, you will need: 

* Java 1.7+

First of all, clone this repo running the following command:

```
git clone https://github.com/marinadamato/spotlight.datasets-test.git
```

Then, run the script using the following commands:

```
cd spotlight.datasets-test/bin
./script.sh
```

In the end, you can check your output files:

```
cd ../spotlight.datasets-test/data/output
```

In the ```createDataset.java``` class, the value 10 is assigned to the variable N. So, at the end of the process, you obtain a file of 10 URIs as output. However, you can modify the value of this variable and get how many labels you want.

At the end of this process, you obtain a complete collection of files related to the N entities found in ```labels_en.nt``` (which is the original DBpedia file). 

However, during the index builder phase, it may happen that not any URI is valid. In fact, the index builder script selects only concept URIs (i.e. URIs that are not in the disambiguations or redirect files). To make sure of not having an empty index, the ```findGoodURis``` method has been added, which finds the good URIs associated to the labels selected before.
