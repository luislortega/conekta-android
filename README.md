conekta-android
===============

Android Library for conekta.mx

## Setup Project
1. Clone [conekta-android](https://github.com/javikin/conekta-android.git) it. Then, import the project to your workspace.
 
2. Add reference from `conekta-android` project to **your app**.

    ![Screenshot](https://raw.github.com/javikin/conekta-android/master/refs/import.png)
    
3. Update the `manifest.xml` of your application and add next lines:

	``` java
	<uses-permission android:name="android.permission.INTERNET" />
	
	<application ...>
	...
        	<meta-data android:name="conektakey" android:value="YOUR_CONEKTAKEY" />
	...
	</application>
	
	
	
## Usage Sync

#### 1. `Simple Charge`

	``` java
	BigInteger tarjeta = new BigInteger("4111111111111111");
        BigInteger monto = new BigInteger("20000");
	Address address = new Address("250 Alexis St", "Red Deer", "Alberta", "Canada", "T4N 0B8");
        Card card = new Card(tarjeta, 12, 2015, "Thomas Logan", 666);
        card.setAddress(address);
        ChargeCard cargoCard = new ChargeCard(card, "Pay from android", monto, Currency.MXN);
        cargoCard.setReferenceId("9893-cohib_s1_wolf_pack");
        Conekta.chargeSync(this, cargoCard);
        
#### 2. `OXXO Charge`

	``` java
	BigInteger tarjeta = new BigInteger("4111111111111111");
        BigInteger monto = new BigInteger("20000");
        Cash cash = new Cash(CashType.OXXO);
        ChargeCash cargoCash = new ChargeCash(cash, "Pago desde android", monto, Currency.MXN);
        Conekta.chargeSync(this, cargoCash);
        
#### 3. `Bank Charge`

	``` java
	BigInteger tarjeta = new BigInteger("4111111111111111");
        BigInteger monto = new BigInteger("20000");
        Bank bank = new Bank(BankType.BANORTE);
        ChargeBank chargeBank = new ChargeBank(bank, "Pago desde android", monto, Currency.MXN);
        Conekta.chargeSync(this, chargeBank);
        
## Usage Async
#### 1. `Simple Charge`

	``` java
	Address address = new Address("250 Alexis St", "Red Deer", "Alberta", "Canada", "T4N 0B8");
        BigInteger tarjeta = new BigInteger("4111111111111111");
        Card card = new Card(tarjeta, 12, 2015, "Thomas Logan", 666);
        card.setAddress(address);
        ChargeCard cargoCard = new ChargeCard(card, "Pay from android", monto, Currency.MXN);
        cargoCard.setReferenceId("9893-cohib_s1_wolf_pack");
        Conekta.chargeAsync(this, cargoCard, new JsonHttpResponseHandler() {
            
            @Override
            public void onSuccess(JSONObject jsono) {
            }

            @Override
            public void onFailure(Throwable thrwbl, JSONObject jsono) {
            }
            
        });
        
#### 2. `OXXO Charge`

	``` java
	BigInteger tarjeta = new BigInteger("4111111111111111");
        BigInteger monto = new BigInteger("20000");
        Cash cash = new Cash(CashType.OXXO);
        ChargeCash cargoCash = new ChargeCash(cash, "Pago desde android", monto, Currency.MXN);
        Conekta.chargeAsync(this, cargoCash, new JsonHttpResponseHandler() {
            
            @Override
            public void onSuccess(JSONObject jsono) {
            }

            @Override
            public void onFailure(Throwable thrwbl, JSONObject jsono) {
            }
            
        });
        
#### 3. `Bank Charge`

	``` java
	BigInteger tarjeta = new BigInteger("4111111111111111");
        BigInteger monto = new BigInteger("20000");
        Bank bank = new Bank(BankType.BANORTE);
        ChargeBank chargeBank = new ChargeBank(bank, "Pago desde android", monto, Currency.MXN);
        Conekta.chargeAsync(this, chargeBank, new JsonHttpResponseHandler() {
            
            @Override
            public void onSuccess(JSONObject jsono) {
            }

            @Override
            public void onFailure(Throwable thrwbl, JSONObject jsono) {
            }
            
        });
        
        
        
        
