import axios from 'axios';

export const jsonServer =  axios.create({
    baseURL: "http://localhost:3001"
});

export const location =  axios.create({
    baseURL: "http://localhost:8085/location"
});

export const menu =  axios.create({
    baseURL: "http://localhost:8086/menu"
});

export const order =  axios.create({
    baseURL: "http://localhost:8087/order"
});

export const delivery =  axios.create({
    baseURL: "http://localhost:8088/delivery"
});