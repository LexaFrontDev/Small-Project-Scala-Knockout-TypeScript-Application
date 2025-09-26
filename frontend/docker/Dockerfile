FROM node:20-alpine

WORKDIR /app

COPY package.json tsconfig.json ./
RUN npm install

COPY src ./src

RUN mkdir -p dist
COPY src/*.html dist/

RUN npx esbuild src/main.ts --bundle --outfile=dist/main.js --platform=browser


CMD ["npx", "http-server", "dist", "-p", "8080", "--spa"]
