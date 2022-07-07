import React from 'react';

import '../../style/style.css';

function NumberInputField(props){  
    const { name, min, max, formValues, minus, plus } = props;

    return (
        <div className="number-input">
            <div onClick={() => minus(name)}></div>
            <input
                { ...formValues.input }
                name={name}
                type="number"
                className="quantity drinkQuantityFix"
                min={min} max={max}
                disabled
            />
            <div onClick={() => plus(name)} className="plus"></div>
        </div> 
    );
}

export default NumberInputField;