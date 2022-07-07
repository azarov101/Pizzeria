import _ from 'lodash';
import ActionType from '../actions/constants';

const initialState = {
    lastOrder: {},
    orderList: []
};


export default (state = initialState, action) => {
    const {type, payload} = action;
    let newState;

    switch (type){
        case ActionType.INITIALIZE_STATE:
            newState = initialState;
            break;
        case ActionType.GET_ORDER_LIST:
            newState = {...state.orderList, ..._.mapKeys(payload.orderList, "orderId")};
            newState = { ...state, orderList: newState };
            break;
        case ActionType.GET_ORDER:
            newState = { ...state.orderList, [payload.orderId]: payload };
            newState = { ...state, orderList: newState };
            break;
        case ActionType.CREATE_ORDER:
            newState = { ...state, lastOrder: payload };
            break;
        default:
            newState = state;
            break;
    }
    return newState;
}