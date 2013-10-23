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
import android.widget.TextView;
import com.loopj.android.http.JsonHttpResponseHandler;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.yellowme.conekta.constantes.BankType;
import mx.yellowme.conekta.constantes.Currency;
import mx.yellowme.conekta.objects.Bank;
import mx.yellowme.conekta.objects.ChargeBank;
import mx.yellowme.conekta.objects.Details;
import mx.yellowme.conekta.rest.Conekta;
import mx.yellowme.sample.R;
import mx.yellowme.sample.util.Messages;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Javier
 */
public class ChargeBankFragment extends Fragment {

    private ProgressDialog pd;
    private TextView txt_service_name;
    private TextView txt_service_number;
    private TextView txt_type;
    private TextView txt_reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bank, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pd = new ProgressDialog(this.getActivity());
        pd.setCancelable(false);
        pd.setMessage("Loading charge..");

        Button btnAsync = (Button) view.findViewById(R.id.button_bank_charge_async);        
        txt_service_name = (TextView) view.findViewById(R.id.txt_service_name);
        txt_service_number = (TextView) view.findViewById(R.id.txt_service_number);
        txt_type = (TextView) view.findViewById(R.id.txt_type);
        txt_reference = (TextView) view.findViewById(R.id.txt_reference);

        Bank bank = new Bank(BankType.BANORTE);
        BigInteger amount = new BigInteger("20000");

        final ChargeBank chargeBank = new ChargeBank(bank, "Charge from android", amount, Currency.MXN);
        chargeBank.setReferenceId("9893-cohib_s1_wolf_pack");

        Details details = new Details("Wolverine", "403-342-0642", "logan@x-men.org");
        chargeBank.setDetails(details);

        btnAsync.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clearValues();
                pd.show();
                Conekta.charge(ChargeBankFragment.this.getActivity(), chargeBank, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONObject jsono) {
                        try {
                            pd.dismiss();
                            JSONObject payment = jsono.getJSONObject("payment_method");
                            setValues(payment.getString("service_name"), payment.getString("service_number"),payment.getString("type"), payment.getString("reference"));
//                            Messages.displayAlert(ChargeBankFragment.this.getActivity(), "Resonse", jsono.toString());
                        } catch (JSONException ex) {
                            Logger.getLogger(ChargeBankFragment.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    @Override
                    public void onFailure(Throwable thrwbl, JSONObject jsono) {
                        pd.dismiss();
                        Messages.displayAlert(ChargeBankFragment.this.getActivity(), "Error:", jsono.toString());
                    }
                });
            }
        });
        

    }
    
    public void setValues(String serviceName, String serviceNumber, String type, String reference){
        txt_service_name.setText("Service Name: "+serviceName);
        txt_service_number.setText("Service Number: "+serviceNumber);
        txt_type.setText("Type: "+type);
        txt_reference.setText("Reference: "+reference);
    }
    
    public void clearValues(){
        txt_service_name.setText("");
        txt_service_number.setText("");
        txt_type.setText("");
        txt_reference.setText("");
    }
}