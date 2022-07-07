// import _ from 'lodash';
import ActionType from '../actions/constants';

const initialState = {
    drinks: [],
    pizzas: [],
    toppings: []
};

export default (state = initialState, action) => {
    const { type, payload } = action;
    let newState;

    switch (type) {
        case ActionType.GET_MENU_LIST:
            const {pizzaList, toppingList, drinkList} = payload;
            newState = {...state, pizzas: [...pizzaList], toppings: [...toppingList], drinks: [...drinkList]};
            break;
        default:
            newState = state;
            break;
    }
    return newState;
}