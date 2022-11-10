import React, {Component} from 'react';
import { connect } from 'react-redux';
import { Field, Form, reduxForm } from 'redux-form';
import { Button } from 'semantic-ui-react';
import DropdownList from 'react-widgets/DropdownList'
import 'react-widgets/styles.css'

import {getCityAction, createOrderAction, getOrderAction} from '../../actions';
import deliveryImage from '../../images/delivery.png';

class OrderCreate extends Component {
    constructor(props){
        super(props);
        this.state = { isOrderCreated: false };
    }

    componentDidMount(){
        this.props.getCityAction();
    }

    componentDidUpdate(){
        if (this.props.order.lastOrder.requestStatus) {
            if (this.props.order.lastOrder.requestStatus === 'Success'){
                this.props.history.push(`/order/${this.props.order.lastOrder.orderId}`);
            } else {
                // else status == Failure -> show message // new component
            }
        }
    }

    onSubmit = formValues => {
        const { order: items, totalPrice} = this.props.cart;

        const order = {
            name: formValues.fullName,
            totalPrice: totalPrice,
            location: `${formValues.city}, ${formValues.street} ${formValues.number}`,
            notes: formValues.notes,
            status: "Delivered",
            items: items
        };
        this.props.createOrderAction(order);   
        this.setState({isOrderCreated: true});     
    }

    renderFormInput = field => {
        return (
            <React.Fragment>
                <label>{ field.label }</label>
                <div className={field.className}>
                    <input 
                        { ...field.input }
                        autoComplete="off"
                        type={field.type} 
                        placeholder={field.placeholder}
                        />
                </div>
                {this.renderError(field.meta)}
          </React.Fragment>
          ); 
    }

    renderError = ({error, touched}) => {
        if (error && touched){
            return <div className="ui error message">{error}</div>;
        }
    }

    renderDropdownList  = field => {
        return (
            <React.Fragment>
                <label>{ field.label }</label>
                <DropdownList  
                    { ...field.input }
                    placeholder={field.placeholder}
                    data={field.data}
                />
                {this.renderError(field.meta)}
            </React.Fragment>
        );
    }

    renderFormTextArea = field => {
        return (
            <React.Fragment>
                <label>{ field.label }</label>
                <div className={field.className}>
                    <textarea 
                        { ...field.input }
                        autoComplete="off"
                        placeholder={field.placeholder}
                        rows={field.rows}
                    />
                </div>
          </React.Fragment>
        ); 
    }

    showForm = () => {
        return (
            <React.Fragment>
                <div className="centerText">                
                    <img alt="delivery" className="delivery image" src={deliveryImage} />
                </div>
                <h1 className="centerText">Enter Your Details</h1>
                <br />
                <Form className="ui form error" onSubmit={this.props.handleSubmit(this.onSubmit)}>
                    <div className="fields">
                        <div className="four wide field">
                            <Field 
                                name="fullName"
                                label="Full Name"
                                placeholder="Full Name"
                                className="ui input"
                                type="input"
                                component={this.renderFormInput}
                            />   
                        </div>
                        <div className="four wide field">
                            <Field 
                                name="city"
                                label="City"
                                placeholder="City"
                                data={this.props.city}
                                component={this.renderDropdownList}
                            />   
                        </div>
                        <div className="four wide field">
                            <Field 
                                name="street"
                                label="Street"
                                placeholder="Street"
                                className="ui input"
                                type="input"
                                component={this.renderFormInput}
                            />   
                        </div>
                        <div className="four wide field">
                            <Field 
                                name="number"
                                label="Number"
                                placeholder="Number"
                                className="ui input"
                                type="input"
                                component={this.renderFormInput}
                            />   
                        </div>
                    </div>

                    <div className="field">
                        <Field 
                            name="notes"
                            label="Notes"
                            placeholder="Enter notes for the order..."
                            rows="3"
                            component={this.renderFormTextArea}
                        />
                    </div>
                    <Button fluid className="ui primary button">Submit</Button>
                </Form>
            </React.Fragment>
        );
    }

    render(){
        const { order } = this.props.cart;

        // no orders in cart
        if (Object.keys(order).length === 0) {
            return <h1>There are no orders it cart!</h1>
        }
        
        // loading cities
        if (this.props.city.length === 0) {
            return (
                <div className="ui active transition visible inverted dimmer">
                    <div className="content"><div className="ui medium text loader">Loading Cities</div></div>
                </div>
            );
        }

        // show loading
        if (this.state.isOrderCreated){
            return (
                <div className="ui active transition visible inverted dimmer">
                    <div className="content"><div className="ui medium text loader">Saving Order</div></div>
                </div>
            );
        }

        // there is an order - redirect to order page
        if (Object.values(this.props.order).length === 1){
            return (
                <div className="ui active transition visible inverted dimmer">
                    <div className="content"><div className="ui medium text loader">Order was completed successfully - Redirect to order page</div></div>
                </div>
            );
        }

        return(
            <div> { this.showForm() } </div>
        );
    }
}

const validate = formValues => {
    const errors = {};
    const regex = /^[a-zA-Z-][a-zA-Z- ]*$/; // regular expression for full name field

    if (!formValues.fullName){
        errors.fullName = "You must enter Full Name";
    }
    else if (!regex.test(formValues.fullName)){ // input should contain only letters
        errors.fullName = "Field can contain only letters";
    }

    if (!formValues.city){
        errors.city = "You must enter City";
    }

    if (!formValues.street){
        errors.street = "You must enter Street";
    }

    if (!formValues.number){
        errors.number = "You must enter Home Number";
    }

    return errors;
}

const mapStateToProps = state => {
    return ({
        cart: state.cart,
        city: state.location.city,
        order: state.order
    });
}

const mapDispatchToProps = dispatch => {
    return {
        getCityAction: () => getCityAction()(dispatch),
        createOrderAction: (order) => createOrderAction(order)(dispatch),
        getOrderAction: (id) => getOrderAction(id)(dispatch)
    }
}

const formWrapper =  connect(mapStateToProps, mapDispatchToProps)(OrderCreate); 

export default reduxForm({
    form: 'OrderForm',
    validate // attach validate function to the form
})(formWrapper);