import React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';

import '../../../style/cart.css';
import CartOrders from './CartOrders';

const ShoppingCart = (props) => {

    const cart = () => {

        return (
            <div className="cartContainer">
                <div className="heading">
                    <h1 className="cartHeader">Shopping Cart</h1>
                    <span className="visibility-cart"><i className="shopping cart icon"></i></span>    
                </div>
                
                <div className="cart">
                    <div className="table">
                        <div className="layout-inline row th">
                            <div className="col col-remove"></div>
                            <div className="col">Product</div>
                            <div className="col">Toppings</div>
                            <div className="col">Drinks</div>
                            <div className="col col-total">Total</div>
                        </div>
                        <CartOrders />      
                        <div className="tf">
                            <div className="row layout-inline">
                                <div className="col col-remove"></div>
                                <div className="col"></div>
                                <div className="col"></div>
                                <div className="col"><p className="cartP">Total</p></div>
                                <div className="col col-total-summary"><p className="cartP">${props.cart.totalPrice}</p></div>
                            </div>
                        </div>         
                    </div>
                    {orderButton()}
                </div>
            </div>
        );
    }

    const orderButton = () => {
        if (props.cart.order.length > 0) {
            return (
                <Link className="ui button orderButton" to="/order/create">Order Now</Link>
            );
        }
        return (
            <p className="cartEmpty">Cart is empty</p>
        );
    }

    return (
        <React.Fragment>
            {cart()}
        </React.Fragment>
    );
}

const mapStateToProps = state => {
    return { 
        cart: state.cart
     };
}

export default connect(mapStateToProps)(ShoppingCart);