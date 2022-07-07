import { combineReducers } from 'redux';
import { reducer as formReducer } from 'redux-form';

import menuReducer from './menuReducer';
import orderReducer from './orderReducer';
import cartReducer from './cartReducer';
import locationReducer from './locationReducer';

export default combineReducers({
    menu: menuReducer,
    order: orderReducer,
    cart: cartReducer,
    location: locationReducer,
    form: formReducer
});

