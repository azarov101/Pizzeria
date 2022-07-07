import React from 'react';
import { Menu } from 'semantic-ui-react'
import { Link } from 'react-router-dom';

function NavBar() {
    return(
        <div className="myHeader">
            <Menu stackable fluid widths={4}>
                <Menu.Item>
                    <h2 className="ui center aligned icon"> <Link to="/"><i className="home icon"></i></Link> </h2>
                </Menu.Item>

                <Menu.Item name="menu">
                    <Link to="/menu">Menu</Link>
                </Menu.Item>

                <Menu.Item name="order" >
                    <Link to="/order">Order</Link>
                </Menu.Item>

                <Menu.Item name="about" >
                    About
                </Menu.Item>
            </Menu>
        </div>
    );
}

export default NavBar;