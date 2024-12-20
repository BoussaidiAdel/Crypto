package Controller;

import Models.CryptoDTO;
import Services.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CryptoController {

    private final CryptoService cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    // Get all cryptocurrencies
    @GetMapping("/all")
    public List<CryptoDTO> getAllCryptos() {
        return cryptoService.getAllCryptos();
    }

    // Get a cryptocurrency by its symbol
    @GetMapping("/crypto/{symbol}")
    public CryptoDTO getCryptoBySymbol(@PathVariable String symbol) {
        return cryptoService.getCryptoBySymbol(symbol);
    }

    // Compare two cryptocurrencies by their symbols
    @GetMapping("/compare")
    public Map<String, Object> compareCryptos(@RequestParam String symbol1, @RequestParam String symbol2) {
        return cryptoService.compareCryptos(symbol1, symbol2);
    }

    // Get top N cryptocurrencies by market cap
    @GetMapping("/top/{n}")
    public List<CryptoDTO> getTopCryptosByMarketCap(@PathVariable int n) {
        return cryptoService.getTopCryptosByMarketCap(n);
    }

    // Get cryptocurrencies within a specific price range
    @GetMapping("/price-range")
    public List<CryptoDTO> getCryptosByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return cryptoService.getCryptosByPriceRange(minPrice, maxPrice);
    }

    // Get average market cap of all cryptocurrencies
    @GetMapping("/average-market-cap")
    public double calculateAverageMarketCap() {
        return cryptoService.calculateAverageMarketCap();
    }

    // Get cryptocurrencies with positive price change in the last 24 hours
    @GetMapping("/positive-price-change")
    public List<CryptoDTO> getCryptosWithPositivePriceChange() {
        return cryptoService.getCryptosWithPositivePriceChange();
    }

    // Get cryptocurrencies sorted by volume
    @GetMapping("/sorted-by-volume")
    public List<CryptoDTO> getCryptosSortedByVolume(@RequestParam boolean descending) {
        return cryptoService.getCryptosSortedByVolume(descending);
    }

    // Search for cryptocurrencies by name or symbol
    @GetMapping("/search")
    public List<CryptoDTO> searchCryptos(@RequestParam String query) {
        return cryptoService.searchCryptos(query);
    }


}
