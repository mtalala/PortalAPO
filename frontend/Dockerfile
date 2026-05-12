# Frontend Dockerfile for Expo React Native Web
FROM node:18-bullseye-slim

WORKDIR /app

# Install global Expo CLI for running the app
RUN npm install -g expo-cli@latest

# Copy dependency manifests first for better Docker layer caching
COPY package.json package-lock.json ./

RUN npm install --production=false

# Copy source files
COPY . ./

EXPOSE 19000 19001 19002 19006

ENV EXPO_DEVTOOLS_LISTEN_ADDRESS=0.0.0.0
ENV REACT_NATIVE_PACKAGER_HOSTNAME=0.0.0.0

# Start Expo for web/mobile. Use tunnel to allow devices and remote simulators to connect.
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0", "--tunnel"]
