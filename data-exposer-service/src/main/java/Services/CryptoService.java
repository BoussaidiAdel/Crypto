package Services;

import Models.CryptoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CryptoService {

    private final RestTemplate restTemplate;

    @Value("${crypto.api.base-url}")
    private String baseUrl;

    public CryptoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CryptoDTO> getAllCryptos() {
        String url = baseUrl + "/cryptos";
        CryptoDTO[] cryptos = restTemplate.getForObject(url, CryptoDTO[].class);
        return Arrays.asList(cryptos);
    }



    public CryptoDTO getCryptoBySymbol(String symbol) {
        String url = baseUrl + "/crypto/" + symbol;
        return restTemplate.getForObject(url, CryptoDTO.class);
    }

    public Map<String, Object> compareCryptos(String symbol1, String symbol2) {
        CryptoDTO crypto1 = getCryptoBySymbol(symbol1);
        CryptoDTO crypto2 = getCryptoBySymbol(symbol2);

        Map<String, Object> comparison = new HashMap<>();
        comparison.put("crypto1", crypto1);
        comparison.put("crypto2", crypto2);

        String comparisonResult = crypto1.getCurrentPrice() > crypto2.getCurrentPrice()
                ? crypto1.getName() + " is more expensive than " + crypto2.getName()
                : crypto2.getName() + " is more expensive than " + crypto1.getName();

        comparison.put("result", comparisonResult);
        return comparison;
    }

    // New Service: Get Top N Cryptos by Market Cap
    public List<CryptoDTO> getTopCryptosByMarketCap(int topN) {
        return getAllCryptos().stream()
                .sorted(Comparator.comparing(CryptoDTO::getMarketCap).reversed())
                .limit(topN)
                .collect(Collectors.toList());
    }

    // New Service: Filter Cryptos by Price Range
    public List<CryptoDTO> getCryptosByPriceRange(double minPrice, double maxPrice) {
        return getAllCryptos().stream()
                .filter(crypto -> crypto.getCurrentPrice() >= minPrice && crypto.getCurrentPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    // New Service: Calculate Average Market Cap
    public double calculateAverageMarketCap() {
        return getAllCryptos().stream()
                .mapToDouble(CryptoDTO::getMarketCap)
                .average()
                .orElse(0.0);
    }

    // New Service: Get Cryptos with Price Increase
    public List<CryptoDTO> getCryptosWithPositivePriceChange() {
        return getAllCryptos().stream()
                .filter(crypto -> crypto.getPriceChange24h() != null && crypto.getPriceChange24h() > 0)
                .collect(Collectors.toList());
    }

    // New Service: Sort Cryptos by Volume
    public List<CryptoDTO> getCryptosSortedByVolume(boolean descending) {
        return getAllCryptos().stream()
                .sorted(Comparator.comparing(CryptoDTO::getTotalVolume, descending ? Comparator.reverseOrder() : Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }

    // New Service: Search Cryptos by Name or Symbol
    public List<CryptoDTO> searchCryptos(String query) {
        return getAllCryptos().stream()
                .filter(crypto -> crypto.getName().toLowerCase().contains(query.toLowerCase()) ||
                        crypto.getSymbol().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
