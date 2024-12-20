# Step 1: Start from an official Node.js base image
FROM node:22

# Step 2: Set the working directory inside the container
WORKDIR /usr/src/app

# Step 3: Copy the application code into the container
COPY . .

# Step 4: Install the app's dependencies, bypassing peer dependency conflicts
RUN npm install --legacy-peer-deps

# Step 5: Expose the port the app will run on
EXPOSE 3000

# Step 6: Define the command to start the app
CMD ["npm", "start"]
