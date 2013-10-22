class Invoice{
    List items
    Date date
}
class LineItem{
    Product product
    int count
    int getTotal(){
	return product.dollar * count
    }
}
class Product{
    String name
    def dollar
}
def ulcDate = new Date(107,0,1)
def ulc = new Product(dollar:1499,name:"ULC")
def ve = new Product(dollar:499,name:"Visual Editor")
def invoices=[
    new Invoice(date:ulcDate,items:[
	new LineItem(count:5,product:ulc),
	new LineItem(count:1,product:ve)
    ]),
    new Invoice(date:[107, 1, 2],items:[
	new LineItem(count:4,product:ve)
    ])
]
//println invoices
//println invoices.items
println invoices.items.total
println invoices.grep{it.date == ulcDate}.date