def configurator={format,filter,line ->
    filter(line)?format(line):null;
}
def appender={config,append,line ->
    def out=config(line);
    if(out) append(out);
}
def dateFormatter={line -> "${new Date()}: $line"; }
def debugFilter={line -> line.contains("debug");}
def consoleAppender={line-> println line;}

def myConf=configurator.curry(dateFormatter,debugFilter);
def myLog=appender.curry(myConf,consoleAppender);

myLog("debug");
myLog("test");

import groovy.sql.Sql
sql = Sql.newInstance("jdbc:h2:mem;AUTO_SERVER=TRUE",
"sa","", "org.h2.Driver")

sql.execute('create table TableA (FirstName varchar(40),  LastName varchar(40))')
sql.execute('INSERT INTO TableA (FirstName,LastName) values (?,?)',['Stan','Juka'])
sql.eachRow('select * from TableA') {
    println "TableA row: ${it.firstName},${it.lastName}"
}