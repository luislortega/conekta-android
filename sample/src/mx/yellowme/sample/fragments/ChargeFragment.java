/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.sample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.loopj.android.http.JsonHttpResponseHandler;
import java.math.BigInteger;
import mx.yellowme.conekta.constantes.Currency;
import mx.yellowme.conekta.objects.Address;
import mx.yellowme.conekta.objects.Card;
import mx.yellowme.conekta.objects.ChargeCard;
import mx.yellowme.conekta.rest.Conekta;
import mx.yellowme.conekta.rest.HttpResponseLite;
import mx.yellowme.sample.R;
import org.json.JSONObject;

/**
 *
 * @author Javier
 */
public class ChargeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_charge, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnAsync = (Button) view.findViewById(R.id.button_simple_charge_async);
        Button btnSync = (Button) view.findViewById(R.id.button_simple_charge_sync);

        Address address = new Address("250 Alexis St", "Red Deer", "Alberta", "Canada", "T4N 0B8");
        BigInteger tarjeta = new BigInteger("4111111111111111");
        Card card = new Card(tarjeta, 12, 2015, "Thomas Logan", 666);
        card.setAddress(address);

        BigInteger monto = new BigInteger("20000");
        final ChargeCard cargoCard = new ChargeCard(card, "Pago desde android", monto, Currency.MXN);
        cargoCard.setReferenceId("9893-cohib_s1_wolf_pack");

        btnAsync.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Conekta.chargeAsync(ChargeFragment.this.getActivity(), cargoCard, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONObject jsono) {
                        showMessage(jsono.toString());
                    }

                    @Override
                    public void onFailure(Throwable thrwbl, JSONObject jsono) {
                        showMessage("Error:"+jsono.toString());
                    }
                });
            }
        });

        btnSync.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                HttpResponseLite lite = Conekta.chargeSync(ChargeFragment.this.getActivity(), cargoCard);
//                showMessage(lite.getResponse());
                Conekta.doProfileAsync(ChargeFragment.this.getActivity());
            }
        });



    }
    
    public void showMessage(String msg){
        Toast.makeText(ChargeFragment.this.getActivity(), msg,Toast.LENGTH_LONG).show();
    }
}