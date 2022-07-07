import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

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
            <Switch>
                <Route path="/" exact component={Home} />
                <Route path="/menu" exact component={Menu} />
                <Route path="/order/create" exact component={OrderCreate} />
                <Route path="/order" exact component={OrderSearch} />
                <Route path="/order/:id" exact component={OrderDetails} />
            </Switch>
        </BrowserRouter>
        </div>
    );
}

export default App;
