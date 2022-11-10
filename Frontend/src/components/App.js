import React from 'react';
import { BrowserRouter, Routes ,Route } from 'react-router-dom';

import Home from './Home';
import Menu from './menu/Menu';
import NavBar from './NavBar';
import OrderCreate from './order/OrderCreate';
import OrderDetails from './order/OrderDetails';
import OrderSearch from './order/OrderSearch';
import '../style/style.css';

function App() {
    return(
        <div className="ui container myContainer">
        <BrowserRouter>
        <NavBar/>
        <br />
            <Routes>
                <Route path='/' exact element={<Home/>} />
                <Route path='/menu' exact element={<Menu/>} />
                <Route path='/order/create' exact element={<OrderCreate/>} />
                <Route path='/order' exact element={<OrderSearch/>} />
                <Route path='/order/:id' exact element={<OrderDetails/>} />
            </Routes>
        </BrowserRouter>
        </div>
    );
}

export default App;
