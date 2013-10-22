import static org.apache.commons.lang3.text.WordUtils.*;
import com.hengyi.japp.common.sap.*;

g = new Greet('world')  // create object
g.salute()               // output "Hello World!"

def buck = new Money(1,"USD");
println buck;
println buck==new Money(1,"USD");
println buck+buck==new Money(2,"USD");
println buck+1==new Money(2,"USD");
