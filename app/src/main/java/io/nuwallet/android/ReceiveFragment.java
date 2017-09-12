package io.nuwallet.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class ReceiveFragment extends Fragment {
    private OnReceiveFragmentInteractionListener mListener;

    public ReceiveFragment() {
        // Required empty public constructor
    }

    public static ReceiveFragment newInstance() {
        return new ReceiveFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receive, container, false);

        Toolbar toolbar = view.findViewById(R.id.view_wallet_toolbar);
        toolbar.setTitle(R.string.title_receive);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        TextView textAddress = view.findViewById(R.id.text_receive_address);

        ImageView qrCode = view.findViewById(R.id.image_qr_code);
        //CSHQBJTUAUWUOCOPQADKYHPECHNFWHC9L9ZUUDKV9PEASLRYRYWRRVOAYRAJOUVGINOGENLBFMJFORCVXRKQASYSKC

        try {
            Bitmap bmp =  generateQRCode("{\"address\":\"CSHQBJTUAUWUOCOPQADKYHPECHNFWHC9L9ZUUDKV9PEASLRYRYWRRVOAYRAJOUVGINOGENLBFMJFORCVXRKQASYSKC\"}");
            qrCode.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private static Bitmap generateQRCode(String address) throws WriterException {
        BitMatrix result;

        try {
            result = new MultiFormatWriter().encode(address, BarcodeFormat.QR_CODE, 256, 256, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }

        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];

        for (int y = 0; y < h; y++) {
            int offset = y * w;

            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 256, 0, 0, w, h);

        return bitmap;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnReceiveFragmentInteractionListener) {
            mListener = (OnReceiveFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnReceiveFragmentInteractionListener {
        void onReceiveFragmentInteraction(Uri uri);
    }
}
