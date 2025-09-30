SELECT
    ID,
    NSECode,
    StockName,
    BuyingDate,
    BuyingPrice,
    SellingDate,
    SellingPrice,
    Quantity,
    (BuyingPrice * Quantity) AS BuyingValue,
    (SellingPrice * Quantity) AS SellingValue,
    ((SellingPrice * Quantity) - (BuyingPrice * Quantity)) AS RealizedProfit,
    CASE 
        WHEN DATEDIFF(SellingDate, BuyingDate) < 365 THEN 'STCG'
        ELSE 'LTCG'
    END AS TaxCategory
FROM stocks.StockTransactions
WHERE Status = 'Sold'
ORDER BY SellingDate DESC;
