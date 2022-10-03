package com.heechan.barcodepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.zxing.ResultPoint
import com.heechan.barcodepractice.databinding.ActivityMainBinding
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CaptureManager

class MainActivity : AppCompatActivity() {
    private lateinit var capture: CaptureManager
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        capture = CaptureManager(this, binding.barcodeMainBarcodeScanner).apply {
            initializeFromIntent(intent, savedInstanceState)
            decode()
        }

        binding.barcodeMainBarcodeScanner.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                binding.txtMainBarcodeNumber.text = result.toString()
            }

            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {

            }

        })
    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture.onSaveInstanceState(outState)
    }
}