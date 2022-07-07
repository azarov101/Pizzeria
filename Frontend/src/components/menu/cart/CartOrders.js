import React from 'react';
import { connect } from 'react-redux';

import {removeFromCartAction, rearrangeCartOrdersAction} from '../../../actions';
import CartToppings from './CartToppings';
import CartDrinks from './CartDrinks';

function CartOrders(props) {
    const { orders } = props;
    const rowStyle = "layout-inline row";
    const oddRowStyle = " row-bg2";
    let currentRowStyle = "";

    const removeCartItemHandler = (index) => {
        props.removeFromCartAction(index);
        props.rearrangeCartOrdersAction(index);
    }

    const orderRow = (rowIndex, order) => {
        return(
            <React.Fragment>
                <div className="col-remove removeRow">
                    <i className="times circle icon" onClick={() => removeCartItemHandler(rowIndex)}></i>
                </div>
                <div className="col layout-inline">
                    <img className="pizzaCartImg" src={order.pizzaDescription.image} alt={order.pizzaDescription.item} />
                    <p className="cartP">{order.pizzaDescription.item}</p>             
                    <p className="cartMiniP"><span className="underLine">Amount:</span>{order.numberOfPizzas}</p>
                </div>
                <div className="col">
                    <CartToppings order={order}/>
                </div>
                <div className="col">
                    <CartDrinks order={order} />                                            
                </div>
                <div className="col col-total"><p className="cartP"> ${order.orderPrice}</p></div>
            </React.Fragment>
        );
    }

    return (
        <React.Fragment>
            {orders.length > 0 && orders.map((order, index) => {
                currentRowStyle = index % 2 ? rowStyle + oddRowStyle : rowStyle;

                return (
                    <div className={currentRowStyle} key={index}>
                        {orderRow(index, order)}
                    </div>
                );
            })} 
        </React.Fragment>
    );
        
}

const mapStateToProps = state => {
    return { 
        orders: state.cart.order
     };
}

const mapDispatchToProps = dispatch => {
    return {
        removeFromCartAction: (index) => removeFromCartAction(index)(dispatch),
        rearrangeCartOrdersAction: (index) => rearrangeCartOrdersAction(index)(dispatch)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(CartOrders);