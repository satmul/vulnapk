#Base image
FROM node:14

#Working directory
WORKDIR /app

COPY package*.json ./

#Install node modules
RUN npm install

#Copy all files to workdir
COPY . .

#Exposing port
EXPOSE 3000

CMD [ "node", "app.js" ]
