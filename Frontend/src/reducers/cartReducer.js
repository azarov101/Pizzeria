import ActionType from '../actions/constants';

const initialState = {
    isModalOpen: false,
    totalPrice: 0,
    order: []
};

export default (state = initialState, action) => {
    const { type, payload } = action;
    let newState;
    let index, itemIndex, updatedOrder; // helper variables

    switch (type) {
        case ActionType.INITIALIZE_STATE:
            newState = initialState;
            break;

        case ActionType.OPEN_MODAL:
            newState = {...state, ...payload};
            break;

        case ActionType.CLOSE_MODAL:
            if (payload.isOrderFinished) {
                newState = {...state, isModalOpen: payload.isModalOpen};
            } else {
                if (state.totalPrice !== 0){ // clear current order only
                    updatedOrder = [ ...state.order ];
                    updatedOrder.pop(); // remove order from state

                    updatedOrder = { ...state, order: [ ...updatedOrder ], isModalOpen: payload.isModalOpen };
                    newState = { ...state, ...updatedOrder };
                } else {
                    newState = initialState;
                }
            }
            break;

        case ActionType.REMOVE_FROM_CART:
            index = payload;
            updatedOrder = { totalPrice: state.totalPrice - state.order[index].orderPrice }; // update total price

            newState = [ ...state.order ];
            newState.splice(index, 1); // remove order from state
            newState = { ...state, ...updatedOrder ,order: newState};        
            break;

        case ActionType.ADD_PIZZA_TO_CART:
            updatedOrder = [ ...state.order ]; // add payload to existing order
            updatedOrder.push(payload);
            newState = {...state, order: [ ...updatedOrder]};
            break;

        case ActionType.ADD_NUMBER_OF_PIZZAS_TO_CART:
            index = state.order.length - 1;
            updatedOrder = {...state.order[index], numberOfPizzas: payload};
            newState = [ ...state.order ];
            newState.splice(index, 1, updatedOrder); // replace old order object with the new object
            newState = { ...state, order: newState};
            break;

        case ActionType.ADD_TOPPINGS_TO_CART:
            index = state.order.length - 1; // index of this order    
            itemIndex = !state.order[index].toppings ? 0 : state.order[index].toppings.length; // index of the pizza number inside this order

            updatedOrder = { pizzaNumber: itemIndex + 1, ...payload }; // create the toppings object 
            updatedOrder = itemIndex === 0 ? [updatedOrder] : [...state.order[index].toppings, updatedOrder]; // add the new toppings object to toppings array

            updatedOrder = { ...state.order[index], toppings: updatedOrder };
            newState = [ ...state.order ];
            newState.splice(index, 1, updatedOrder);

            newState = { ...state, order: newState};
            break;

        case ActionType.ADD_DRINKS_TO_CART:
            index = state.order.length - 1; // index of this order  
            updatedOrder = { ...state.order[index], drinks: payload };
            newState = [ ...state.order ];
            newState.splice(index, 1, updatedOrder);

            newState = { ...state, order: newState};
            break;

        case ActionType.UPDATE_ORDER_PRICE:
            index = state.order.length - 1; // index of this order
            updatedOrder = { ...state.order[index], orderPrice: payload };

            newState = [ ...state.order ];
            newState.splice(index, 1, updatedOrder);

            newState = { ...state, order: newState};
            break;

        case ActionType.UPDATE_TOTAL_PRICE:
            newState = {...state, totalPrice: payload};
            break;
            
        default:
            newState = state;
            break;
    }
    return newState;
}