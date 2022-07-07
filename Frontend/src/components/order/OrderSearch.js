import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Divider, Grid, Segment, Icon, Table } from 'semantic-ui-react';
import { Field, change, reduxForm } from 'redux-form';

import {getOrderListAction} from '../../actions';

class OrderSearch extends Component{
    state = { showAllOrders: false }

    componentDidMount(){
        this.props.getOrderListAction();
    }
    
    renderFormInput = field => {
        return (
            <div className="ui icon input">
                <input
                    { ...field.input }
                    className={field.className}
                    type = { field.type } 
                    placeholder = { field.placeholder }
                    autoFocus
                    autoComplete = "off"
                    onFocus={() => this.setState({showAllOrders: false})}
                />
                <Icon name='search' inverted circular link />
          </div>
          ); 
    }

    handleClickButton = () => {
        this.props.changeAction(this.props.form, "search", ""); // reset form field value
        this.setState({ showAllOrders: true});
    }

    searchComponent = () => {
        return (
            <Segment textAlign="center" padded="very">
            <Grid columns={2} relaxed='very'>
                <Grid.Column>
                    <Field
                        name="search"
                        type="text"
                        className="searchOrderField"
                        placeholder="Search By Order ID #"
                        component={this.renderFormInput}
                    />
                </Grid.Column>
                <Grid.Column verticalAlign='middle'>
                    <button className="primary ui button" onClick={this.handleClickButton}><i className="clipboard list icon"></i>Show all orders</button>
                </Grid.Column>
            </Grid>
        
            <Divider vertical>Or</Divider>
          </Segment>
        );
    }

    tableComponent = () => {
        const { order, formProps: form } = this.props;
        let orders =  [ ...order ];

        if (form.values) { // search by id
            orders = orders.filter(order => order.orderId.toString().startsWith(form.values.search));
        }
 
        return (
            <Table celled>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Order ID</Table.HeaderCell>
                        <Table.HeaderCell>Order Name</Table.HeaderCell>
                        <Table.HeaderCell>Total Price</Table.HeaderCell>
                        <Table.HeaderCell>Status</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
            
                <Table.Body>
                    {orders && orders.map((order) => (
                        <Table.Row className="orderRow" key={order.orderId} onClick={() => this.props.history.push(`/order/${order.orderId}`)}>
                            <Table.Cell>{order.orderId}</Table.Cell>
                            <Table.Cell>{order.name}</Table.Cell>
                            <Table.Cell>{order.totalPrice}$</Table.Cell>
                            <Table.Cell>{order.status}</Table.Cell>
                        </Table.Row>
                    ))}
                </Table.Body>
          </Table>
        );
    }

    render(){
        if (!this.props.order){
            return (
                <div className="ui active transition visible inverted dimmer">
                    <div className="content"><div className="ui medium text loader">Loading Orders</div></div>
                </div>
            ); 
        }
        return (
            <div>
                <center><h1>Search Your Order</h1></center>
                {this.searchComponent()}
                <br />
                {(this.state.showAllOrders || (this.props.formProps && this.props.formProps.values)) && this.tableComponent()}
            </div>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return { 
        order: Object.values(state.order.orderList),
        formProps: state.form[ownProps.form]
    };
}

const mapDispatchToProps = dispatch => {
    return {
        getOrderListAction: () => getOrderListAction()(dispatch),
        changeAction: (formName, fieldName, value) => dispatch(change(formName, fieldName, value))
    }
}

const formWrapper = connect(mapStateToProps, mapDispatchToProps)(OrderSearch);

export default reduxForm({
    form: 'SearchOrderByIdForm',
})(formWrapper);