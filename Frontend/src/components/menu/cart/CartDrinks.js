import React from 'react';

/**
 * @desc This functional component handles the addition of the CartDrinks for the current order to the shopping cart. 
 * @param {Object} props - The props parameter contains the cart order. 
 * @return {JSX} Decorated jsx drinks object.
 */
export default function CartDrinks(props){
    const {order} = props;

    const showDrinks = () => {
        if (order.drinks.length === 0) {
            return <p className="cartP">No drinks</p>
        }

        return (
            <React.Fragment>
                {order.drinks.map((drink, index) => {
                    return (
                        <p className="cartP" key={index}>
                            {`${drink.item} x ${drink.amount}`}
                        </p>
                    );
                })}
            </React.Fragment>
        );
    }

    return (
        <React.Fragment>{order.drinks && showDrinks()}</React.Fragment>
    );
}