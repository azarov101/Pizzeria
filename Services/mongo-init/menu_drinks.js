conn = new Mongo();
db = conn.getDB("local");

db.pizza_menu_drinks.insert( { item: "Cola", price: 12, image: "https://sc01.alicdn.com/kf/U14b8dd767c644dbd832cbfeac970f63f1.jpg" } )
db.pizza_menu_drinks.insert( { item: "Sprite", price: 12, image: "https://www.ginspiration.uk/ekmps/shops/b066b7/images/sprite-glass-bottles-330ml-x-24-51882-p.jpg" } )
db.pizza_menu_drinks.insert( { item: "Nestea", price: 12, image: "https://www.nestle.com/sites/default/files/nestea_1.jpg" } )
db.pizza_menu_drinks.insert( { item: "Water", price: 9, image: "https://beercastleny.com/wp-content/uploads/2017/11/evian-bollte-330ml.jpg" } )
