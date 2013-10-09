package mx.yellowme.sample;

import android.app.Activity;
import android.os.Bundle;
import com.loopj.android.http.JsonHttpResponseHandler;
import java.math.BigInteger;
import mx.yellowme.conekta.constantes.BankType;
import mx.yellowme.conekta.constantes.CashType;
import mx.yellowme.conekta.constantes.Currency;
import mx.yellowme.conekta.objects.Address;
import mx.yellowme.conekta.objects.Bank;
import mx.yellowme.conekta.objects.Card;
import mx.yellowme.conekta.objects.ChargeCard;
import mx.yellowme.conekta.objects.ChargeCash;
import mx.yellowme.conekta.objects.Cash;
import mx.yellowme.conekta.objects.ChargeBank;
import mx.yellowme.conekta.rest.Conekta;
import mx.yellowme.conekta.rest.HttpResponseLite;
import org.json.JSONObject;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Address address = new Address("250 Alexis St", "Red Deer", "Alberta", "Canada", "T4N 0B8");
        BigInteger tarjeta = new BigInteger("4111111111111111");
        Card card = new Card(tarjeta, 12, 2015, "Thomas Logan", 666);
        card.setAddress(address);
        Cash cash = new Cash(CashType.OXXO);
        Bank bank = new Bank(BankType.BANORTE);

        BigInteger monto = new BigInteger("20000");
        ChargeCard cargoCard = new ChargeCard(card, "Pago desde android", monto, Currency.MXN);
        cargoCard.setReferenceId("9893-cohib_s1_wolf_pack");

        ChargeCash cargoCash = new ChargeCash(cash, "Pago desde android", monto, Currency.MXN);
        cargoCash.setReferenceId("9893-cohib_s1_wolf_pack");


        ChargeBank chargeBank = new ChargeBank(bank, "Pago desde android", monto, Currency.MXN);

        HttpResponseLite lite = Conekta.chargeSync(this, cargoCard);
        lite.getResponse();

        Conekta.chargeAsync(this, cargoCash, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsono) {
            }

            @Override
            public void onFailure(Throwable thrwbl, JSONObject jsono) {
            }
        });

        lite = Conekta.chargeSync(this, cargoCard);
        lite.getResponse();

        lite = Conekta.chargeSync(this, cargoCash);
        lite.getResponse();

    }
}
