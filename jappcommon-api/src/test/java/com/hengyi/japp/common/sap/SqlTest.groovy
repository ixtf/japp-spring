import groovy.sql.Sql
import net.sourceforge.pbeans.*
sql = Sql.newInstance("jdbc:h2:mem;AUTO_SERVER=TRUE",
	"sa","", "org.h2.Driver")
sql.execute('create table TableA (FirstName varchar(40),  LastName varchar(40))')
sql.execute('INSERT INTO TableA (FirstName,LastName) values (?,?)',['Stan', 'Juka'])
sql.eachRow('select * from TableA') { println "TableA row: ${it.firstName},${it.lastName}" }

class Person   {
    String name
    String does
    String email
}