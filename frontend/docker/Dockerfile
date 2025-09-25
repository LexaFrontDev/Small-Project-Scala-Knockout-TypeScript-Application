FROM node:20-alpine

WORKDIR /app


COPY package.json ./
#COPY package-lock.json ./

RUN npm install


COPY src ./src


CMD ["npx", "http-server", "src", "-p", "8080"]
