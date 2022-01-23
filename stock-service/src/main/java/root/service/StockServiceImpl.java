package root.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import root.model.StockData;

import java.util.List;

@Component
public class StockServiceImpl implements StockService {
    public StockData getStockData(String URL){
        RestTemplate restTemplate = new RestTemplate();

        StockData stockData = restTemplate.getForObject(URL, StockData.class);

        return stockData;
    }
}
