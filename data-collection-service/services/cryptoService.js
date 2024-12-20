const axios = require('axios');

// Helper function to format market data
const formatCryptoData = (ticker, symbolInfo) => {
    const baseAsset = symbolInfo?.baseAsset || ticker.symbol.replace('USDT', '');
    const priceChangePercent = parseFloat(ticker.priceChangePercent);
    const lowercaseSymbol = baseAsset.toLowerCase();

    return {
        id: lowercaseSymbol,
        symbol: baseAsset,
        name: symbolInfo?.baseAsset || baseAsset,
        current_price: formatPrice(ticker.lastPrice),
        market_cap: parseFloat(ticker.quoteVolume),
        total_volume: parseFloat(ticker.volume),
        price_change_24h: parseFloat(ticker.priceChange),
        price_change_percentage_24h: priceChangePercent,
        circulating_supply: parseFloat(ticker.volume),
        image: `https://cdn.jsdelivr.net/gh/vadimmalykhin/binance-icons/crypto/${lowercaseSymbol}.svg`,
        last_updated: new Date(ticker.closeTime).toISOString()
    };
};

const BASE_URL = 'https://api1.binance.com/api/v3';

const formatPrice = (price) => {
    return parseFloat(price).toFixed(2);  // Format price to 2 decimal places
};

// Get all cryptos and format data
const getAllCryptos = async () => {
    try {
        const response = await axios.get(`${BASE_URL}/ticker/24hr`);
        return response.data.map((crypto) => {
            // Assuming symbolInfo is available for additional data (modify as needed)
            const symbolInfo = {}; // You can populate this with additional information if available
            return formatCryptoData(crypto, symbolInfo); // Format each crypto
        });
    } catch (error) {
        console.error('Error fetching crypto data:', error.message);
        throw new Error('Failed to fetch crypto data');
    }
};

// Get a single crypto by symbol and format data
const getCryptoBySymbol = async (symbol) => {
    try {
        const response = await axios.get(`${BASE_URL}/ticker/24hr`, { params: { symbol } });
        const crypto = response.data;
        // Assuming symbolInfo is available for additional data (modify as needed)
        const symbolInfo = {}; // You can populate this with additional information if available
        return formatCryptoData(crypto, symbolInfo); // Format the single crypto
    } catch (error) {
        console.error(`Error fetching data for symbol ${symbol}:`, error.message);
        throw new Error('Failed to fetch crypto data');
    }
};

module.exports = {
    getAllCryptos,
    getCryptoBySymbol,
};
