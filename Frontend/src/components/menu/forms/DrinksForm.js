import React from 'react';
import { connect } from 'react-redux';
import { Field, change, Form, reduxForm } from 'redux-form';
import { Modal, Header, Button, Card } from 'semantic-ui-react';

import NumberInputField from '../../common/NumberInputField';

const DrinksForm = (props) => {
    const {drinks, onDrinksFormSubmit, handleSubmit, formProps, form, change} = props;
    const min = 0;
    const max = 10;
    

    const onSubmit = (formValues, dispatch) => {
        onDrinksFormSubmit(formValues, dispatch);
    }

    const minus = (name) => {
        const currentVal = formProps.values[name];
        if (currentVal > min) {
            change(form, name, currentVal - 1); // decrease value by 1
        }
    }

    const plus = (name) => {
        const currentVal = formProps.values[name];
        if (currentVal < max) {
            change(form, name, currentVal + 1); // increase value by 1
        }
    }

    const renderInput = (formValues) => {
        if (formValues.input.value === ""){
            change(form, formValues.input.name, parseInt(formValues.min)); // default value for the field is 0
        }
        return (
            <React.Fragment>
                <label className="drinkLabel">{formValues.label}</label>
                <NumberInputField 
                    name={formValues.input.name} 
                    min={formValues.min} 
                    max={formValues.max} 
                    formValues={formValues} 
                    minus={minus} 
                    plus={plus}
                />
            </React.Fragment>
        );
    };
    
    const card = (item, index) => {
        return (
            <Card fluid key={index}>
                <img
                    className="drinkImage"
                    alt="drink" 
                    src={item.image} 
                />
                <Card.Content className="centerText modalCardPadding">
                    <Card.Header>
                    <Field 
                        name={item.item}
                        label={item.item}
                        min={min} max={max}
                        component={renderInput}
                    />   
                    <div className="modalPrice">Price: <i className="dollar sign icon currencyPadding"></i>{item.price}</div>
                    </Card.Header>
                </Card.Content>
            </Card>
        );
    }

    const drinksForm = () => {
        return(
            <Form className="ui form" onSubmit={handleSubmit((formValues, dispatch) => onSubmit(formValues, dispatch))}>
                <Modal.Content>
                    <Header as='h3' block textAlign='center'>Choose your drinks</Header>    
                    <div className="ui doubling stackable centered four column grid cardGroup">
                        {drinks && drinks.map((item, index) => {
                            return (
                                <div className="column" key={index}>
                                    {card(item, index)}
                                </div>
                            );
                        })}
                    </div>  
                </Modal.Content>
                <Modal.Actions className="modalButton">
                    <Button className="ui primary button">Next</Button>
                </Modal.Actions>
            </Form>
        );

    }

    return(
        <div className="ui container">
            {drinksForm()}
        </div>
    );
}

const mapStateToProps = (state, ownProps) => {
    return { formProps: state.form[ownProps.form]}
}

const formWrapper = connect(mapStateToProps, {change})(DrinksForm);

export default reduxForm({
    form: 'DrinksForm',
})(formWrapper);
