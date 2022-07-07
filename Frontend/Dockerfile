# base image
FROM node:alpine

# set working directory
WORKDIR /usr/src/app

# install and cache app dependencies
COPY package.json .
RUN npm install

# copy in the rest of your app’s source code from your host to your image filesystem.
COPY . .

# start app
CMD ["npm", "start"]