/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.sample.fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.loopj.android.http.JsonHttpResponseHandler;
import java.math.BigInteger;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.yellowme.conekta.constantes.CashType;
import mx.yellowme.conekta.constantes.Currency;
import mx.yellowme.conekta.objects.Cash;
import mx.yellowme.conekta.objects.ChargeCash;
import mx.yellowme.conekta.objects.Details;
import mx.yellowme.conekta.rest.Conekta;
import mx.yellowme.conekta.rest.HttpResponseLite;
import mx.yellowme.sample.R;
import mx.yellowme.sample.util.Messages;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Javier
 */
public class ChargeOxxoFragment extends Fragment {

    private ImageView iv;    
    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_oxxo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pd = new ProgressDialog(this.getActivity());
        pd.setMessage("Loading Oxxo codebar..");

        Button btnAsync = (Button) view.findViewById(R.id.button_oxxo_charge_async);
        Button btnSync = (Button) view.findViewById(R.id.button_oxxo_charge_sync);
        iv = (ImageView) view.findViewById(R.id.codeBarOxxo);

        BigInteger amount = new BigInteger("20000");
        Cash cash = new Cash(CashType.OXXO);

        final ChargeCash cargoCash = new ChargeCash(cash, "Charge from android", amount, Currency.MXN);
        cargoCash.setReferenceId("9893-cohib_s1_wolf_pack");
        Details details = new Details("Wolverine", "403-342-0642", "logan@x-men.org");
        cargoCash.setDetails(details);

        btnAsync.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                iv.setImageBitmap(null);
                pd.show();
                Conekta.chargeAsync(ChargeOxxoFragment.this.getActivity(), cargoCash, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONObject jsono) {
                        try {
                            JSONObject payment = jsono.getJSONObject("payment_method");
                            String barcode = payment.getString("barcode");
                            Bitmap bitmap = null;
                            try {
                                bitmap = encodeAsBitmap(barcode, BarcodeFormat.CODE_128, 600, 300);
                                iv.setImageBitmap(bitmap);
                                pd.dismiss();
                            } catch (WriterException ex) {
                                pd.dismiss();
                                Messages.displayAlert(ChargeOxxoFragment.this.getActivity(), "Error:", ex.getMessage());
                            }
                        } catch (JSONException ex) {
                            pd.dismiss();
                            Messages.displayAlert(ChargeOxxoFragment.this.getActivity(), "Error:", ex.getMessage());
                            Logger.getLogger(ChargeOxxoFragment.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    @Override
                    public void onFailure(Throwable thrwbl, JSONObject jsono) {
                        pd.dismiss();
                        Messages.displayAlert(ChargeOxxoFragment.this.getActivity(), "Error:", jsono.toString());
                    }
                });
            }
        });

        btnSync.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {                    
                    iv.setImageBitmap(null);
                    HttpResponseLite lite = Conekta.chargeSync(ChargeOxxoFragment.this.getActivity(), cargoCash);                    
                    JSONObject jSonResult = new JSONObject(lite.getResponse());
                    JSONObject payment = jSonResult.getJSONObject("payment_method");
                    String barcode = payment.getString("barcode");
                    Bitmap bitmap = null;
                    try {
                        bitmap = encodeAsBitmap(barcode, BarcodeFormat.CODE_128, 600, 300);
                        iv.setImageBitmap(bitmap);                        
                    } catch (WriterException ex) {                        
                        Messages.displayAlert(ChargeOxxoFragment.this.getActivity(), "Error:", ex.getMessage());
                        Logger.getLogger(ChargeOxxoFragment.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                } catch (JSONException ex) {                    
                    Messages.displayAlert(ChargeOxxoFragment.this.getActivity(), "Error:", ex.getMessage());
                    Logger.getLogger(ChargeOxxoFragment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    private Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
}