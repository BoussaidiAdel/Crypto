const { gql } = require('graphql-tag');

const typeDefs = gql`
    type Crypto {
        id: String!
        symbol: String!
        name: String!
        current_price: String!
        market_cap: Float!
        total_volume: Float!
        price_change_24h: Float!
        price_change_percentage_24h: Float!
        circulating_supply: Float!
        image: String!
        last_updated: String!
    }
    type Image {
        thumb: String!
        small: String!
        large: String!
    }

    type Query {
        cryptos: [Crypto!]!
        crypto(symbol: String!): Crypto
    }
`;

module.exports = typeDefs;
