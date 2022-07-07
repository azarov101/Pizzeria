import React, {Component} from 'react';
import { Modal, Header, Button } from 'semantic-ui-react';
import { connect } from 'react-redux';
import { reset } from 'redux-form';

import {updateOrderPriceAction, updateTotalPriceAction, addNumberOfPizzasToCartAction,
    addToppingsToCartAction, updateCurrentPizzaNumberToCartAction, addDrinksToCartAction, closeModalAction} from '../../actions';
import NumberOfPizzasForm from './forms/NumberOfPizzasForm';
import ToppingsForm from './forms/ToppingsForm';
import DrinksForm from './forms/DrinksForm';

class MainModal extends Component{
    constructor(props){
        super(props);
        this.state = { 
            step: 1
        };
    }

    currentOrderIndex = () => (this.props.cart.order.length - 1);

    addedToCartMessage = () => {
        return (
            <React.Fragment>
                <Modal.Content>
                    <Modal.Description>
                        <Header as='h1' block textAlign='center'>
                            <img 
                                className="ui image"
                                src="https://p7.hiclipart.com/preview/811/919/777/check-mark-computer-icons-royalty-free-clip-art-blue-check-mark.jpg"
                                alt="added to cart successfully" 
                            />
                            <br />
                            Added to Cart Successfully
                        </Header>          
                    </Modal.Description>
                </Modal.Content>
                <Modal.Actions>
                    <Button className="ui primary button" onClick={this.onFormFinish}>Finish</Button>
                </Modal.Actions>
            </React.Fragment>
        );
    }

    updateTotalPrice = () => {
        const { numberOfPizzas, pizzaDescription: { discountedPrice, price }, toppings, drinks } = this.props.cart.order[this.currentOrderIndex()];    

        // update price with old total price
        const totalPrice = this.props.cart.totalPrice;

        // update price by amount of pizzas
        let currentOrderPrice = discountedPrice ? (numberOfPizzas * discountedPrice) : (numberOfPizzas * price); 

        // update price by toppings for each pizza
        for (let i = 0; i < toppings.length; ++i){
            let toppingsArray = toppings[i].toppings;
            for (let j = 0; j < toppingsArray.length; ++j){
                currentOrderPrice += toppingsArray[j].price;
            }
        }

        // update price by drinks
        for (let i = 0; i < drinks.length; ++i){
            currentOrderPrice += (drinks[i].price * drinks[i].amount);
        }

        // save current order price to cart
        this.props.updateOrderPriceAction(currentOrderPrice);

        // save updated total price to cart
        this.props.updateTotalPriceAction(totalPrice + currentOrderPrice);
    }

    onNumberOfPizzasFormSubmit = ({ number }, dispatch) => { 
        this.props.addNumberOfPizzasToCartAction(number);
        dispatch(reset("NumberOfPizzasForm")); // clear form field
        this.setState({step: this.state.step + 1}); // update to next step
    };

    onToppingsFormSubmit = (formValues, currentPizza, dispatch) => { 
        let filteredValues = Object.keys(formValues).filter(val => formValues[val]); // take only the checked fields
        filteredValues = this.props.toppings.filter(val => filteredValues.includes(val.item)); // map values to have all toppings info from store

        this.props.addToppingsToCartAction({toppings: filteredValues});

        dispatch(reset("ToppingsForm")); // clear form fields 
        if (currentPizza === this.props.cart.order[this.currentOrderIndex()].numberOfPizzas){
            this.setState({step: this.state.step + 1}); // update state to next step 
        }
    };

    onDrinksFormSubmit = (formValues, dispatch) => { 
        let filteredValues = Object.keys(formValues).filter(val => formValues[val] > 0); // take only the fields that has at least 1 drink
        filteredValues = this.props.drinks.filter(val => filteredValues.includes(val.item)); // map values to have all drinks info from store
        filteredValues.map(val => val.amount = formValues[val.item]); // add order amount for each drink
 
        this.props.addDrinksToCartAction(filteredValues);

        dispatch(reset("DrinksForm")); // clear form fields 
        this.setState({step: this.state.step + 1});
    };

    onFormFinish = () => {
        this.updateTotalPrice();
        this.props.closeModalAction(true);
        this.setState({step: 1}); // reset the state of step
    }

    closeModal = () => {
        this.props.closeModalAction();
        this.setState({step:  1}); // reset the state of step
    }

    ModalContent = () => {
        switch (this.state.step){
            case 1: 
                return <NumberOfPizzasForm onNumberOfPizzasFormSubmit={this.onNumberOfPizzasFormSubmit} />
            case 2: 
                return <ToppingsForm toppings={this.props.toppings} order={this.props.cart.order[this.currentOrderIndex()]} onToppingsFormSubmit={this.onToppingsFormSubmit} />
            case 3: 
                return <DrinksForm drinks={this.props.drinks} onDrinksFormSubmit={this.onDrinksFormSubmit} />
            case 4:
                return this.addedToCartMessage();
            default:
                return <div>Error Message: step 5 does not exist!</div>
        }
    }

    ShowModal = () => {
        return(
            <Modal 
            size="small"
            open={this.props.cart.isModalOpen}
            onClose={() => this.closeModal()}
            closeIcon>
                <Modal.Header>Order Details</Modal.Header>
                {this.ModalContent()}
            </Modal>
        );
    }
    
    render() {
        return (
            <div>
                {this.ShowModal()}
            </div>
        );
    }
}

const mapStateToProps = state => {
    return { 
        cart: state.cart,
        drinks: state.menu.drinks,
        toppings: state.menu.toppings
     };
}

const mapDispatchToProps = dispatch => {
    return {
        updateOrderPriceAction: (price) => updateOrderPriceAction(price)(dispatch),
        updateTotalPriceAction: (price) => updateTotalPriceAction(price)(dispatch),
        addNumberOfPizzasToCartAction: (item) => addNumberOfPizzasToCartAction(item)(dispatch),
        addToppingsToCartAction: (item) => addToppingsToCartAction(item)(dispatch),
        updateCurrentPizzaNumberToCartAction: (item) => updateCurrentPizzaNumberToCartAction(item)(dispatch),
        addDrinksToCartAction: (item) => addDrinksToCartAction(item)(dispatch),
        closeModalAction: (isOrderFinished) => closeModalAction(isOrderFinished)(dispatch)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(MainModal);