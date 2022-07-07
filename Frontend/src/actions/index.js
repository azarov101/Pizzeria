import {jsonServer, location, menu, order, delivery} from '../apis/pizza';
import ActionType from './constants';

// **************************** Server & Store Actions **************************** //
// GET List of all item in menu
export const getMenuAction = () => async dispatch => {
    // const response = await jsonServer.get("/menu"); // json-server
    const response = await menu.get("/getMenu"); // menu microservice

    dispatch({ type: ActionType.GET_MENU_LIST, payload: response.data });
};

// GET List of cities
export const getCityAction = () => async dispatch => {
    // const response = await jsonServer.get("/city"); // json-server
    const response = await location.get("/getCityList"); // location microservice

    dispatch({ type: ActionType.GET_CITY_LIST, payload: response.data });
};

// GET Order by id
export const getOrderAction = id => async dispatch => {
    // let response = await jsonServer.get(`/order/${id}`); // json-server
    // response.data.orderId = response.data.id; // uncomment for json server
    const response = await order.get(`/getOrderDetails/${id}`); // order microservice

    dispatch({ type: ActionType.GET_ORDER, payload: response.data });
};

// GET List of all the orders
export const getOrderListAction = () => async dispatch => {
    // const response = await jsonServer.get("/order"); // json-server
    const response = await order.get("/getAllOrders"); // order microservice

    dispatch({ type: ActionType.GET_ORDER_LIST, payload: response.data });
};

// POST new Order
export const createOrderAction = formValues => async dispatch => {
    // let response = await jsonServer.post("/order", formValues); // json-server
    // response.data = {orderId: response.data.id, requestStatus: "Success"}; // uncomment for json server
    const response = await order.post('/createOrder', formValues); // order microservice
    
    dispatch({ type: ActionType.CREATE_ORDER, payload: response.data });
};


// **************************** Store Actions **************************** //
// cart
export const openModalAction = () => dispatch => {
    const action = {type: ActionType.OPEN_MODAL, payload: { isModalOpen: true }};   
    dispatch(action);
}
export const closeModalAction = (isOrderFinished=false) => dispatch => {
    const payload = { isModalOpen: false, isOrderFinished };
    const action = {type: ActionType.CLOSE_MODAL, payload };   
    dispatch(action);
}

export const removeFromCartAction = (index) => dispatch => {
    const payload = index;
    const action = {type: ActionType.REMOVE_FROM_CART, payload };   
    dispatch(action);
}

export const rearrangeCartOrdersAction = (index) => dispatch => {
    const payload = index; // previous removed order index
    const action = {type: ActionType.REARRANGE_CART_ORDERS, payload };   
    dispatch(action);
}

export const addPizzaToCartAction = item => dispatch => {
    const action = {type: ActionType.ADD_PIZZA_TO_CART, payload: item };   
    dispatch(action);
}

export const addNumberOfPizzasToCartAction = number => dispatch => {
    const payload = number;
    const action = { type: ActionType.ADD_NUMBER_OF_PIZZAS_TO_CART, payload };   
    dispatch(action);
}

export const updateCurrentPizzaNumberToCartAction = item => dispatch => {
    const payload = { subOrderIndex: item.subOrderIndex ,currentPizza: item.index};
    const action = { type: ActionType.UPDATE_CURRENT_PIZZA_NUMBER_TO_CART, payload };   
    dispatch(action);
}

export const addToppingsToCartAction = toppings => dispatch => {
    const action = { type: ActionType.ADD_TOPPINGS_TO_CART, payload: toppings };   
    dispatch(action);
}

export const addDrinksToCartAction = drinks => dispatch => {
    const action = { type: ActionType.ADD_DRINKS_TO_CART, payload: drinks };   
    dispatch(action);
}

export const updateOrderPriceAction = price => dispatch => {
    const action = { type: ActionType.UPDATE_ORDER_PRICE, payload: price };
    dispatch(action);
}

export const updateTotalPriceAction = price => dispatch => {
    const action = { type: ActionType.UPDATE_TOTAL_PRICE, payload: price };
    dispatch(action);
}

export const initializeOrderStateAction = () => dispatch => {
    const action = {type: ActionType.INITIALIZE_STATE, payload: null };
    dispatch(action);
}