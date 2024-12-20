const express = require('express');
const cors = require('cors');
const { graphqlHTTP } = require('express-graphql');
const { makeExecutableSchema } = require('@graphql-tools/schema');
const typeDefs = require('./schema/typeDefs');
const resolvers = require('./resolvers/cryptoResolver');
const apiRouter = require('./routes/api');

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(cors());
app.use(express.json());

// GraphQL Schema
const schema = makeExecutableSchema({ typeDefs, resolvers });

// GraphQL Endpoint
app.use(
    '/graphql',
    graphqlHTTP({
        schema,
        graphiql: true, // Enable GraphQL Playground
    })
);

// REST Routes
app.use('/api', apiRouter);

// Start the server
app.listen(PORT, () => {
    console.log(`Server running at http://localhost:${PORT}`);
    console.log(`GraphQL Playground at http://localhost:${PORT}/graphql`);
});
