// import _ from 'lodash';
import ActionType from '../actions/constants';

const initialState = {
    city: []
};

export default (state = initialState, action) => {
    const { type, payload } = action;
    let newState, updatedState;

    switch (type) {
        case ActionType.GET_CITY_LIST:
            updatedState = Object.values(payload.cityList).map(val => (val.city));
            newState = { ...state, city: updatedState };
            break;
        default:
            newState = state;
            break;
    }
    return newState;
}