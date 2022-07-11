conn = new Mongo();
db = conn.getDB("local");

db.pizza_menu_toppings.insert( { item: "Tuna", price: 5, image: "https://images.globes.co.il/images/NewGlobes/big_image_800/2016/1-800.2016317T174006.jpg" } )
db.pizza_menu_toppings.insert( { item: "Corn", price: 5, image: "https://images.globes.co.il/images/NewGlobes/big_image_800/2016/F0_0700_1050_6909724(2)800.jpg" } )
db.pizza_menu_toppings.insert( { item: "Mushrooms", price: 5, image: "https://d3m9l0v76dty0.cloudfront.net/system/photos/1134872/original/c4b8cb29a17a9159ec4ac5a89d1f61b7.jpg" } )
db.pizza_menu_toppings.insert( { item: "Green Olives", price: 5, image: "https://www.poliva.co.il/wp-content/uploads/2021/06/%D7%96%D7%99%D7%AA%D7%99%D7%9D-%D7%99%D7%A8%D7%95%D7%A7%D7%99%D7%9D-%D7%A4%D7%A8%D7%95%D7%A1%D7%95%D7%AA-417x348.jpg" } )
