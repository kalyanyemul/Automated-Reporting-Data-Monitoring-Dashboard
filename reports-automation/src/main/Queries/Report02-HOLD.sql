SELECT
    ID,
    NSECode,
    StockName,
    BuyingDate,
    BuyingPrice,
    Quantity,
    
    -- Assign different CurrentPrice per stock
    CASE 
        WHEN NSECode = 'RELIANCE' THEN 2600.50   -- profit
        WHEN NSECode = 'BHARTIARTL' THEN 2550.75 -- profit
        WHEN NSECode = 'TCS' THEN 2900.25        -- loss
        WHEN NSECode = 'ICICIBANK' THEN 1000.50  -- profit
        WHEN NSECode = 'LT' THEN 1500.75         -- loss
        WHEN NSECode = 'WIPRO' THEN 1000.25      -- profit
        ELSE 2500.00                             -- default
    END AS CurrentPrice,
    
    (BuyingPrice * Quantity) AS InvestmentValue,
    
    -- New column: Current Value
    (CASE 
        WHEN NSECode = 'RELIANCE' THEN 2600.50
        WHEN NSECode = 'BHARTIARTL' THEN 2550.75
        WHEN NSECode = 'TCS' THEN 2900.25
        WHEN NSECode = 'ICICIBANK' THEN 1000.50
        WHEN NSECode = 'LT' THEN 1500.75
        WHEN NSECode = 'WIPRO' THEN 1000.25
        ELSE 2500.00
    END * Quantity) AS CurrentValue,
    
    -- Unrealized Profit/Loss (can be positive or negative)
    ((CASE 
        WHEN NSECode = 'RELIANCE' THEN 2600.50
        WHEN NSECode = 'BHARTIARTL' THEN 2550.75
        WHEN NSECode = 'TCS' THEN 2900.25
        WHEN NSECode = 'ICICIBANK' THEN 1000.50
        WHEN NSECode = 'LT' THEN 1500.75
        WHEN NSECode = 'WIPRO' THEN 1000.25
        ELSE 2500.00
    END) * Quantity - (BuyingPrice * Quantity)) AS UnrealizedProfit

FROM stocks.StockTransactions
WHERE Status = 'Hold'
ORDER BY BuyingDate ASC;
