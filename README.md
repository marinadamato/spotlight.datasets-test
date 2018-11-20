# spotlight.datasets-test

A small tool that allows you to reduce the DBpedia Spotlight and Wikipedia Dump sizes, in order to create datasets test used to quickly verify your outputs and to minimize the processing time.    

In order to use this code, you will need: 

* Java 1.7+
* Java IDE (for instance, [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows))

First of all, clone this repo running the following command:

```
git clone https://github.com/marinadamato/spotlight.datasets-test.git
```

Then, download the DBpedia files you need using the following links and uncompress each of them (the tool has been tested using the 3.9 version of DBpedia):
* [DBpedia Labels](http://downloads.dbpedia.org/3.9/en/labels_en.nt.bz2)
* [DBpedia Redirects](http://downloads.dbpedia.org/3.9/en/redirects_en.nt.bz2)
* [DBpedia Disambiguation](http://downloads.dbpedia.org/3.9/en/disambiguations_en.nt.bz2)
* [DBpedia Ontology Types](http://downloads.dbpedia.org/3.9/en/instance_types_en.nt.bz2)
* [DBpedia Interlanguage](http://downloads.dbpedia.org/3.9/it/interlanguage_links_it.nt.bz2) (required for the Italian Version)
* [Wikipedia Dump](https://ftp.acc.umu.se/mirror/wikimedia.org/dumps/enwiki/20180820/enwiki-20180820-pages-articles-multistream.xml.bz2)

Now you have to do the following steps:
* Open IntelliJ
* Go to ```File```>```Open``` and select the project path
* Run the script

In the ```Entity.java``` class, the value 10 is assigned to the variable N. So, at the end of the process, you obtain a file of 10 URIs as output. However, you can modify the value of this variable and get how many labels you want.

At the end of this process, you obtain a complete collection of files related to the N entities found in ```labels_en.nt``` (which is the original DBpedia file). 

However, during the index builder phase, it may happen that not any URI is valid. In fact, the index builder script selects only concept URIs (i.e. URIs that are not in the disambiguations or redirect files). To make sure of not having an empty index, the ```findGoodURis``` method has been added, which finds the good URIs associated to the labels selected before.
