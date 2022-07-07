import React, { Component } from 'react';
import { connect } from 'react-redux';

import {getOrderAction} from '../../actions';


class OrderDetails extends Component {

    componentDidMount(){
        this.props.getOrderAction(this.props.match.params.id);
    }

    orderContent = (orderItems) => {
        return(
            <React.Fragment>
                <div role="listitem" className="item">
                    <div className="content">
                        <h2 className="header">{orderItems.pizzaDescription.item}</h2>
                    </div>
                </div>
                <br />
                <div className="ui grid"> 
                    <div role="list" className="six wide column ui list">  
                        <div role="listitem" className="item">
                            <i aria-hidden="true" className="marker icon"></i>
                            <div className="content">
                                <h4 className="header headerColor">Price</h4>
                                <div className="description">
                                    {orderItems.pizzaDescription.discountedPrice && 
                                        <div>Discounted Price: 
                                            &nbsp; &nbsp;
                                            <span className="lineThrough"> {orderItems.pizzaDescription.price} </span> &nbsp; => &nbsp; {orderItems.pizzaDescription.discountedPrice}$
                                        </div>
                                    }
                                    {!orderItems.pizzaDescription.discountedPrice &&
                                        <div>Pizza Price: 
                                            &nbsp; &nbsp;
                                            {orderItems.pizzaDescription.price}$
                                        </div>
                                    }

                                    <div>Order Price: 
                                        &nbsp; &nbsp;
                                        <span className="totalPrice"> {orderItems.orderPrice}$ </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div role="listitem" className="item">
                            <i aria-hidden="true" className="marker icon"></i>
                            <div className="content">
                                <h4 className="header headerColor">Amount</h4>
                                <div className="description">
                                    {orderItems.numberOfPizzas}
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="six wide column">
                        <div role="list" className="ui list">
                            <div role="listitem" className="item">
                                <i aria-hidden="true" className="marker icon"></i>
                                <div className="content">
                                    <h4 className="header headerColor">Toppings</h4>
                                    <div className="description">
                                        {orderItems.toppings && orderItems.toppings.map((toppings, pizzaIndex) => { 
                                            let numOfToppings = toppings.toppings.length;
                                            return (
                                                <li key={pizzaIndex}>{orderItems.numberOfPizzas > 1 && "Pizza #" + (pizzaIndex + 1) + ": "}
                                                    {toppings.toppings.map((topping, toppingIndex) => {
                                                        if (toppingIndex === numOfToppings-1){
                                                            return (topping.item || topping);
                                                        } 
                                                        return (`${topping.item || topping}, `);                                              
                                                    })}
                                                    {numOfToppings === 0 && `No toppings`}
                                                </li>
                                            );
                                        })}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div className="four wide column">
                        <div role="list" className="ui list">
                            <div role="listitem" className="item">
                                <i aria-hidden="true" className="marker icon"></i>
                                <div className="content">
                                    <h4 className="header headerColor">Drinks</h4>
                                    <div className="description">
                                        {Object.values(orderItems.drinks).length === 0 && 
                                            <li>No drinks</li>
                                        }
                                        {Object.values(orderItems.drinks).map((drink, drinkIndex) => (
                                            <li  key={drinkIndex}>{`${drink.item} x ${drink.amount}`}</li>
                                        ))}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </React.Fragment>
        );
    }

    orderInformation = () => {
        const { orderId: id, name, totalPrice: price, status, location, notes } = this.props.order;

        return (
            <div role="list" className="ui divided middle aligned list">
                <div role="listitem" className="item">
                    <div className="iconWidth inlineBlock"><i className="key icon"></i></div>
                    <div className="content inlineBlock"><b>Order ID: </b>#{id}</div>
                </div>
                <div role="listitem" className="item">
                    <div className="iconWidth inlineBlock"><i className="user circle icon"></i></div>
                    <div className="content inlineBlock"><b>Order Name: </b>{name}</div>
                </div>
                <div role="listitem" className="item">
                    <div className="iconWidth inlineBlock"><i className="money bill alternate icon"></i></div>
                    <div className="content inlineBlock"><b>Total Price: </b>{price}$</div>
                </div>
                <div role="listitem" className="item">
                    <div className="iconWidth inlineBlock"><i className="bell icon"></i></div>
                    <div className="content inlineBlock"><b>Status: </b>{status}</div>
                </div>
                <div role="listitem" className="item">
                    <div className="iconWidth inlineBlock"> <i className="shipping fast icon"></i></div>
                    <div className="content inlineBlock"><b>Delivery Location: &nbsp;</b></div><div className="inlineBlock breakWord">{location}</div>
                </div>
                {notes && 
                <div role="listitem" className="item">
                    <div className="iconWidth inlineBlock"><i className="info circle icon"></i></div>
                    <div className="content inlineBlock">
                        <div className="header">Order Notes:</div>
                        <div className="description">{notes}</div>
                    </div>
                </div>
                }
            </div>         
        );
    }

    showOrderDetails = () => {
        const { items } = this.props.order;

        return (
            <React.Fragment>
                <br />
                <center><h1>THANK YOU!</h1></center>
                <br />

                {this.orderInformation()}
                
                <h4>Order Details</h4>
                <div className="ui grid">
                    {items.map((orderItems, index) => (
                        <div className="row ui very padded segment doubling stackable" key={index}>
                            <div className="four wide column">
                                <img alt="pizza" src={orderItems.pizzaDescription.image} className="ui image orderImage" />
                            </div>
                            <div className="twelve wide column">
                                {this.orderContent(orderItems)}
                            </div>
                        </div>
                    ))}
                </div>
            </React.Fragment>
        );
    }

    render(){
        const {order, orderList} = this.props;

        if (Object.keys(orderList).length === 0 && !order){
            return (
                <div>Loading</div>
            );
        }
         
        else if (Object.keys(orderList)[0] === 'null'){
            return (
                <div>Order not found!</div>
            );
        }

        return (
            <div>
                {this.showOrderDetails()}
            </div>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return { 
        order: state.order.orderList[ownProps.match.params.id],
        orderList: state.order.orderList
     };
};

const mapDispatchToProps = dispatch => {
    return {
        getOrderAction: (id) => getOrderAction(id)(dispatch)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(OrderDetails);