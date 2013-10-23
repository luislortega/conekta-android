/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.sample.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.loopj.android.http.JsonHttpResponseHandler;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import mx.yellowme.conekta.constantes.Currency;
import mx.yellowme.conekta.objects.Address;
import mx.yellowme.conekta.objects.BillingAddress;
import mx.yellowme.conekta.objects.Card;
import mx.yellowme.conekta.objects.ChargeCard;
import mx.yellowme.conekta.objects.Details;
import mx.yellowme.conekta.objects.LineItem;
import mx.yellowme.conekta.objects.Shipment;
import mx.yellowme.conekta.rest.Conekta;
import mx.yellowme.sample.R;
import mx.yellowme.sample.util.Messages;
import org.json.JSONObject;

/**
 *
 * @author Javier
 */
public class ChargeCompleteFragment extends Fragment {

    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_charge_complete, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pd = new ProgressDialog(this.getActivity());
        pd.setMessage("Loading Charge..");

        Button btnAsync = (Button) view.findViewById(R.id.button_charge_async);        

        Address address = new Address("250 Alexis St", "Red Deer", "Alberta", "Canada", "T4N 0B8");
        BigInteger numberCard = new BigInteger("4111111111111111");
        Card card = new Card(numberCard, 12, 2015, "Thomas Logan", 666);
        card.setAddress(address);

        BigInteger amount = new BigInteger("20000");
        final ChargeCard chargeCard = new ChargeCard(card, "Charge from android", amount, Currency.MXN);
        chargeCard.setDescription("Stogies");
        chargeCard.setReferenceId("9893-cohib_s1_wolf_pack");

        BigInteger quantity = new BigInteger("1");
        BillingAddress billingAddress = new BillingAddress("xmn671212drx", "X-Men Inc.", "77 Mystery Lane", "Darlington", "NJ", "10192", "77-777-7777", "purshasing@x-men.org");
        LineItem lineItem = new LineItem("Box of Cohiba S1s", "Imported from Mex.", "cohb_s1", amount, amount, quantity, "other_human_consumption");
        List<LineItem> line_items = new ArrayList<LineItem>();
        line_items.add(lineItem);
        Shipment shipment = new Shipment("estafeta", "international", "XXYYZZ-9990000", amount, address);
        Details details = new Details("Wolverine", "403-342-0642", "logan@x-men.org", "1980-09-24", billingAddress, line_items, shipment);        
        chargeCard.setDetails(details);

        btnAsync.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pd.show();
                Conekta.charge(ChargeCompleteFragment.this.getActivity(), chargeCard, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONObject jsono) {
                        pd.dismiss();
                        Messages.displayAlert(ChargeCompleteFragment.this.getActivity(), "Resonse", jsono.toString());
                    }

                    @Override
                    public void onFailure(Throwable thrwbl, JSONObject jsono) {
                        pd.dismiss();
                        Messages.displayAlert(ChargeCompleteFragment.this.getActivity(), "Error:", jsono.toString());
                    }
                });
            }
        });


    }
}