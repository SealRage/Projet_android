package com.example.srava.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.wearable.view.WatchViewStub;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.zxing.WriterException;


public class RecupererMenu extends android.support.v4.app.Fragment {

    private static final String TAG = "RecupererMenu";

    DisplayMetrics metrics = new DisplayMetrics();
    private String mEncodeString;
    private TextView mTextDesc;
    private ImageView mImageQR;
    private ProgressBar mProgress;
    private Bitmap mBitmapQR;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //   private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecupererMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static RecupererMenu newInstance(String param1, String param2) {
        RecupererMenu fragment = new RecupererMenu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RecupererMenu() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.qr_main, container, false);

        final WatchViewStub stub = (WatchViewStub) view.findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextDesc = (TextView) stub.findViewById(R.id.textDesc);
                mImageQR = (ImageView) stub.findViewById(R.id.imageQR);
                mProgress = (ProgressBar) stub.findViewById(R.id.progressGenerate);
                mTextDesc.setVisibility(View.GONE);
                mProgress.setVisibility(View.VISIBLE);
            }
        });

        new AsyncGenerateQRCode().execute(GenrateQR.MARGIN_NONE);
        return view;

    }

    private class AsyncGenerateQRCode extends AsyncTask<Integer, Void, Integer> {

        /**
         * Background thread function to generate image
         *
         * @param params margin to use in creating QR Code
         * @return non zero for success
         * <p>
         * Note that is margin is not in pixels.  See the zxing api for details about the margin
         * for QR code generation
         */
        @Override
        protected Integer doInBackground(Integer... params) {
            Log.d(String.valueOf(params), "  params");
            if (params.length != 1) {
                throw new IllegalArgumentException("Must pass QR Code margin value as argument");
            }

            try {
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                final int colorQR = Color.BLACK;
                final int colorBackQR = Color.WHITE;
                final int marginSize = params[0];
                final int width = metrics.widthPixels;;
                final int height = metrics.heightPixels;

                mBitmapQR = GenrateQR.generateBitmap("ntm", width, width,
                        marginSize, colorQR, colorBackQR);
            } catch (IllegalArgumentException iae) {
                Log.e(TAG, "Invalid arguments for encoding QR");
                iae.printStackTrace();
                return 0;
            } catch (WriterException we) {
                Log.e(TAG, "QR Writer unable to generate code");
                we.printStackTrace();
                return 0;
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer result) {
            mProgress.setVisibility(View.GONE);
            if (result != 0) {
                mImageQR.setImageBitmap(mBitmapQR);
                mImageQR.setVisibility(View.VISIBLE);
            } else {
                //mTextDesc.setText(getString(R.string.encode_error));
                mTextDesc.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }
}
