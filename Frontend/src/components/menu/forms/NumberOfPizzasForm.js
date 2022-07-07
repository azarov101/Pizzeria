import React from 'react';
import { connect } from 'react-redux';
import { Modal, Header, Button } from 'semantic-ui-react';
import { Form, Field, reduxForm, change } from 'redux-form';

import NumberInputField from '../../common/NumberInputField';

const NumberOfPizzasForm = (props) => {
    const {onNumberOfPizzasFormSubmit, formProps, form, change, handleSubmit} = props;
    const min = 1;
    const max = 5;

    const onSubmit = (formValues, dispatch) => {
        onNumberOfPizzasFormSubmit(formValues, dispatch);
    }

    const minus = () => {
        const currentVal = formProps.values.number;
        if (currentVal > min) {
            change(form, "number", currentVal - 1 ); // reset form field value
        }
    }

    const plus = () => {
        const currentVal = formProps.values.number;
        if (currentVal < max) {
            change(form, "number", currentVal + 1 ); // reset form field value
        }
    }

    const renderInput = (formValues) => {
        if (formValues.input.value === ""){
            change(form, formValues.input.name, formValues.min); // default value for the field
        }
        return (
            <NumberInputField 
                name={formValues.name} 
                min={formValues.min} 
                max={formValues.max} 
                formValues={formValues} 
                minus={minus} 
                plus={plus} 
            />
        );
    };   

    const numberOfPizzasForm = () => {
        return(
            <Form className="ui form" onSubmit={handleSubmit((formValues, dispatch) => onSubmit(formValues, dispatch))}>
                <Modal.Content>
                    <Modal.Description>
                        <Header as='h4' textAlign='center' block className="numberOfPizzas">
                            Number of Pizzas: 
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <Field 
                                name="number"
                                min={min} max={max}
                                component={renderInput}
                            />                          
                        </Header>          
                    </Modal.Description>
                </Modal.Content>
                <Modal.Actions className="modalButton">
                    <Button className="ui primary button">Next</Button>
                </Modal.Actions>
            </Form>
        );
    }

    return (
        <div className="ui container">
            {numberOfPizzasForm()}
        </div>
    );
}

const mapStateToProps = (state, ownProps) => {
    return {
        formProps: state.form[ownProps.form]
    }
}

const formWrapper = connect(mapStateToProps, { change })(NumberOfPizzasForm);

export default reduxForm({
    form: 'NumberOfPizzasForm',
})(formWrapper);
