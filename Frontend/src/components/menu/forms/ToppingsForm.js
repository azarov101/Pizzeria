import React from 'react';
import { Modal, Header, Button, Card } from 'semantic-ui-react';
import { Field, Form, reduxForm } from 'redux-form';

const ToppingsForm = (props) => {
    const {toppings, order, onToppingsFormSubmit, handleSubmit} = props;

    const onSubmit = (formValues, dispatch) => {
        onToppingsFormSubmit(formValues, currentPizza, dispatch);
    }

    const currentPizza = order.toppings ? order.toppings.length  + 1 : 1;

    const renderFormInput = field => {
        return (
            <div className="ui slider checkbox">
                <input 
                    { ...field.input }
                    type={field.type} 
                />
                <label>{ field.label }</label>
          </div>
          ); 
    }

    const card = (item) => {
        return (
            <Card fluid>
                <img
                    className="toppingImage"
                    alt={item.item} 
                    src={item.image} 
                />
                <Card.Content className="centerText modalCardPadding">
                    <Card.Header>
                        <Field 
                            name={item.item}
                            label={item.item}
                            type="checkbox"
                            component={renderFormInput}
                        />   
                        <div className="modalPrice">Price: <i className="dollar sign icon currencyPadding"></i>{item.price}</div>
                    </Card.Header>
                </Card.Content>
            </Card>
        );
    }

    const toppingsForm = () => {
        return (
            <Form className="ui form" onSubmit={handleSubmit((formValues, dispatch) => onSubmit(formValues, dispatch))}>
                <Modal.Content>
                    <Header as='h3' block textAlign='center'>
                        {order && 
                            ((order.numberOfPizzas === 1 && `Choose toppings for the pizza`) ||
                            (order.numberOfPizzas > 1 && `Choose toppings for Pizza #${currentPizza}`))
                        }
                    </Header>    
                    <div className="ui doubling stackable centered four column grid cardGroup">
                        {toppings && toppings.map((item, index) => {
                            return (
                                <div className="column" key={index}>
                                    {card(item)}
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
            {toppingsForm()}
        </div>
    );
}

export default reduxForm({
    form: 'ToppingsForm',
})(ToppingsForm);
