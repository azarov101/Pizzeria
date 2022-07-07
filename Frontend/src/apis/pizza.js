import axios from 'axios';

export const jsonServer =  axios.create({
    baseURL: "http://localhost:3001"
});

export const location =  axios.create({
    baseURL: "http://localhost:8080/location"
});

export const menu =  axios.create({
    baseURL: "http://localhost:8081/menu"
});

export const order =  axios.create({
    baseURL: "http://localhost:8082/order"
});

export const delivery =  axios.create({
    baseURL: "http://localhost:8083/delivery"
});