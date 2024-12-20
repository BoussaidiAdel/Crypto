const cryptoService = require('../services/cryptoService');

const resolvers = {
    Query: {
        cryptos: async () => {
            return await cryptoService.getAllCryptos();
        },
        crypto: async (_, { symbol }) => {
            return await cryptoService.getCryptoBySymbol(symbol);
        },
    },
};

module.exports = resolvers;
