# Super Simple Stock Market
JP Morgan test

The application was written as a RESTful service providing operations necessary to fulfill requirements.
###Architecture solutions:
The data are stored in a memory of their definition is located in the package wacekh.sssm.model.
The object of a hold data in the application is an object that implements the interface wacekh.sssm.repositories.StocksRepository
Operations on the data are carried out by the service implements the interface wacekh.sssm.service.StocksService
For data sharing as a web service class is responsible wacekh.sssm.web.rest.StocksRestService

###Table with the definition of available operations:

|Name|HTTP Method|Url template|Description|
|---|---|---|---|
|Add stock|POST|/sssm/web/stocks/{symbol}|Url parameter:<br>symbol - Stock symbol, <br>Parameter form:<br>type - typ of stock, (COMMON, PREFERRED) <br>lastDividend - value of last dividend <br>fixedDividend - value of field <br>parValue - par value|
|Add trade|POST|/sssm/web/stocks/{symbol}/trade|Url parameter:<br>symbol - Stock symbol<br>Form parameter:<br>timestamp - timestamp of creation trade <br>quanity- of operation <br>indicator - typ of operation (BUY,SELL)|
|Dividend yield|GET|/sssm/web/{symbol}/dividendyield/price/{price}|Url parameter:<br>symbol - Stock symbol<br>price - price to calculation|
|P/E Ratio|GET|/sssm/web/{symbol}/peratio/price/{price}|Url parameter:<br>symbol - Stock symbol <br>price - price to calculation Volume Weighted |
|Stock Price|GET|/sssm/web/{symbol}/vwst|Url parameter:<br>symbol - Stock symbol|
|GBCE|GET|/sssm/web/gbce||

###Comments:
The specification in the formula P / E Ration for value Dividend is substituted value Last Dividend risk because the document does not specify what value should be used.
To calculate the value GBCE are taken all records of buy and sell.



