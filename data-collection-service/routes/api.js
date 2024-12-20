const express = require('express');
const cryptoService = require('../services/cryptoService');

const router = express.Router();

router.get('/cryptos', async (req, res) => {
    try {
        const data = await cryptoService.getAllCryptos();
        res.json(data);
    } catch (error) {
        res.status(500).json({ error: 'Failed to fetch crypto data' });
    }
});

router.get('/crypto/:symbol', async (req, res) => {
    try {
        const data = await cryptoService.getCryptoBySymbol(req.params.symbol);
        res.json(data);
    } catch (error) {
        res.status(500).json({ error: 'Failed to fetch crypto data' });
    }
});

module.exports = router;
