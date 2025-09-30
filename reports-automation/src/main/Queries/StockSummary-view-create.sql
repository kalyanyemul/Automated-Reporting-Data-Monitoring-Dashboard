CREATE OR REPLACE VIEW StockSummary AS
SELECT
    ID,
    NSECode,
    StockName,
    BuyingDate,
    BuyingPrice,
    SellingDate,
    SellingPrice,
    Status,
    Quantity,

    -- BuyingValue
    (BuyingPrice * Quantity) AS BuyingValue,

    -- SellingValue
    (SellingPrice * Quantity) AS SellingValue,

    -- RealisedProfit
    ((SellingPrice * Quantity) - (BuyingPrice * Quantity)) AS RealisedProfit,

    -- TaxCategory
    CASE 
        WHEN Status = 'Sold' 
             AND DATEDIFF(SellingDate, BuyingDate) < 365 THEN 'STCG'
        WHEN Status = 'Sold' 
             AND DATEDIFF(SellingDate, BuyingDate) >= 365 THEN 'LTCG'
        ELSE NULL
    END AS TaxCategory

FROM StockTransactions;
