import React, {Component} from 'react';
import { connect } from 'react-redux';
import { Card, Icon } from 'semantic-ui-react';

import {getMenuAction, initializeOrderStateAction,
    addPizzaToCartAction,  openModalAction} from '../../actions';
import MainModal from './MainModal';
import ShoppingCart from './cart/ShoppingCart';

class Menu extends Component {

    componentDidMount(){
        this.props.getMenuAction();
        this.props.initializeOrderStateAction();
    }

    pizzaClickHandler = item => {
        item = { pizzaDescription: item };
        this.props.addPizzaToCartAction(item);
        this.props.openModalAction();
    }

    PizzaMenu = () => (
        <div className="ui doubling stackable verticalAlign centered four column grid">
            {this.props.menu && this.props.menu.map((item, index) => {
                return(
                    <div className="column" key={index}>
                        <Card className="pizzaCard" fluid onClick={() => this.pizzaClickHandler(item)}>
                            <React.Fragment>
                                <img alt="pizza" src={item.image} />
                            </React.Fragment>
                            <Card.Content>
                                <Card.Header>{item.item}</Card.Header>
                                {item.discountedPrice && 
                                    <Card.Meta>
                                        <span className='description'>DISCOUNT: {(item.price-item.discountedPrice) * 100 / item.price}%</span>
                                    </Card.Meta>
                                }
                                <Card.Description>
                                    Some Description about the Pizza, the origin of the ingredients, what topping does it include
                                </Card.Description>
                            </Card.Content>
                            <Card.Content extra textAlign="center">                    
                                <span className="price">
                                    Price:
                                </span>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <span>  
                                    {item.discountedPrice &&      
                                        <React.Fragment>                                     
                                            <span className="lineThrough"> {item.price} </span>
                                            <Icon name='angle double right' />
                                            {item.discountedPrice}
                                        </React.Fragment>
                                    }
                                    { !item.discountedPrice && item.price }
                                    <Icon name='dollar sign' />
                                </span>
                            </Card.Content>
                        </Card>
                    </div>
                );
            })}
        </div>
    )

    render() {
        if (!this.props.menu){
            return (
                <div className="ui active transition visible inverted dimmer">
                    <div className="content"><div className="ui medium text loader">Loading Menu Items</div></div>
                </div>
            ); 
        }
        return (
            <div>
                <h2 className="ui center aligned icon header">
                    <i className="circular utensils icon"></i>
                    Choose Your Pizza
                </h2>
                <div className="ui grid">
                    <div className="row ui doubling stackable">
                        <div className="twelve wide column">
                            {this.PizzaMenu()}
                            {this.PizzaMenu()}
                            <MainModal />
                        </div>
                        <div className="four wide column">
                            <ShoppingCart />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = state => {
    return { 
        menu: state.menu.pizzas,
     };
}

const mapDispatchToProps = dispatch => {
    return {
        getMenuAction: () => getMenuAction()(dispatch),
        initializeOrderStateAction: () => initializeOrderStateAction()(dispatch),
        openModalAction: () => openModalAction()(dispatch),
        addPizzaToCartAction: (item) => addPizzaToCartAction(item)(dispatch)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Menu);