import React from 'react';

import '../style/style.css';
import pizzaImage from '../images/pizza1.png'

function Home() {
    return(
    <React.Fragment>
        <img alt="pizza cover" className="coverImage" src={pizzaImage} />
        <center><h1>Mitzpe Ramon Pizzeria</h1></center>
    </React.Fragment>
    );
}

export default Home;