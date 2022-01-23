package root.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.model.StockData;
import root.model.Ticket;
import root.service.StockService;

import java.util.List;

@RestController
@RequestMapping("/stock-data")
public class StockServiceController {
    private final StockService stockService;

    public StockServiceController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/real-time")
    public ResponseEntity <List<Ticket>> getRealTimeStockData(){
        return ResponseEntity.ok(stockService.getStockData("https://api.stockdata.org/v1/data/quote?symbols=AAPL,TSLA,MSFT&api_token=8EfuMBEyvaG0SJNW8cqqTGUtMLyEbSClhGMhQLFo").getData());
    }

    @GetMapping("/historical")
    public ResponseEntity <StockData> getHistoricalStockData(){
        return ResponseEntity.ok(stockService.getStockData("https://api.stockdata.org/v1/data/eod?symbols=TSLA&api_token=8EfuMBEyvaG0SJNW8cqqTGUtMLyEbSClhGMhQLFo"));
    }
}
