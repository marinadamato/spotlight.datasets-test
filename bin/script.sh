export DOWNLOAD_LIST=../conf/downloadList
export CONFIG_FILE=conf/config.properties

if [ -e ../data  ]; then
    echo '../data already exists.'
else
    mkdir -p ../data
fi

for i in `cat $DOWNLOAD_LIST`
do
filename=$(basename "$i")
echo "$filename"
if [ -e ../data/input/"$filename" ]; then
echo "$i"' already downloaded'
else
 wget --directory-prefix="../data/input" $i
 bzip2 -dk ../data/input/"$filename"
fi
done

#cd ../

#mvn compile
#mvn exec:java -e -Dexec.mainClass="Main" -Dexec.args=$CONFIG_FILE