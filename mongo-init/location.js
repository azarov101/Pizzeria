conn = new Mongo();
db = conn.getDB("local");

db.pizza_location.insert( { city: "Tel Aviv" } )
db.pizza_location.insert( { city: "Beersheba" } )
db.pizza_location.insert( { city: "Jerusalem" } )
db.pizza_location.insert( { city: "Haifa" } )
db.pizza_location.insert( { city: "Rishon LeZion" } )
db.pizza_location.insert( { city: "Petah Tikva" } )
db.pizza_location.insert( { city: "Netanya" } )
db.pizza_location.insert( { city: "Ashdod" } )
db.pizza_location.insert( { city: "Ramat Gan" } )
db.pizza_location.insert( { city: "Ashkelon" } )
db.pizza_location.insert( { city: "Herzliya" } )
db.pizza_location.insert( { city: "Bat Yam" } )
db.pizza_location.insert( { city: "Mitzpe Ramon" } )