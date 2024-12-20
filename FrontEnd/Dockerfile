# Step 1: Build the Angular application using Node.js
FROM node:18 AS build

# Set the working directory
WORKDIR /app

# Install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy the entire project into the container
COPY . .

# Build the Angular application
RUN npm run build --prod

# Step 2: Serve the application using Nginx
FROM nginx:alpine

# Copy the build output from the previous stage to Nginx's HTML folder
COPY --from=build /app/dist/crypto-tracker /usr/share/nginx/html

# Expose the default HTTP port
EXPOSE 80

# Start Nginx when the container runs
CMD ["nginx", "-g", "daemon off;"]
