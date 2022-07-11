conn = new Mongo();
db = conn.getDB("local");

db.pizza_menu_pizzas.insert( { item: "Neapolitan Pizza", price: 50, discountedPrice: 0, image: "https://i.ibb.co/rH96QMf/download.jpg" } )
db.pizza_menu_pizzas.insert( { item: "Chicago Pizza", price: 50, discountedPrice: 37.5, image: "https://i.ibb.co/g4zrzdc/good-pizza-in-palm-harbor.jpg" } )
db.pizza_menu_pizzas.insert( { item: "New York-Style Pizza", price: 50, discountedPrice: 37.5, image: "https://i.ibb.co/LSMwBWx/Italian-Pizza-Vs-American-Pizza.jpg" } )
db.pizza_menu_pizzas.insert( { item: "Sicilian Pizza", price: 50, discountedPrice: 0, image: "https://i.ibb.co/fXnT8FK/stock-photo-pizza-margherita-or-margarita-with-mozzarella-cheese-tomato-olive-italian-pizza-on-woode.jpg" } )
db.pizza_menu_pizzas.insert( { item: "Greek Pizza", price: 50, discountedPrice: 0, image: "https://i.ibb.co/CbGwBhd/pizza-margherita-margarita-mozzarella-cheese-tomato-olive-italian-pizza-pizza-margherita-margarita-m.jpg" } )
db.pizza_menu_pizzas.insert( { item: "California Pizza", price: 50, discountedPrice: 0, image: "https://i.ibb.co/c2sRHYj/pwb-madeinitaly-lead.jpg" } )
db.pizza_menu_pizzas.insert( { item: "Israel Pizza", price: 50, discountedPrice: 0, image: "https://i.ibb.co/m5PLKjy/images.jpg" } )
db.pizza_menu_pizzas.insert( { item: "Classic Pizza", price: 50, discountedPrice: 25, image: "https://i.ibb.co/PxvdvBB/classic.jpg" } )
