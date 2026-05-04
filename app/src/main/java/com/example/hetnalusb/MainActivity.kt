package com.example.hetnalusb

import android.content.Context
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnListDevices = findViewById<Button>(R.id.btnListDevices)
        val tvDeviceList = findViewById<TextView>(R.id.tvDeviceList)

        btnListDevices.setOnClickListener {
            listUsbDevices(tvDeviceList)
        }
    }

    private fun listUsbDevices(textView: TextView) {
        val usbManager = getSystemService(Context.USB_SERVICE) as UsbManager
        val deviceList = usbManager.deviceList

        if (deviceList.isEmpty()) {
            textView.text = "Brak podłączonych urządzeń USB."
            return
        }

        val sb = StringBuilder()
        sb.append("Znaleziono urządzeń: ${deviceList.size}\n\n")

        for (device in deviceList.values) {
            val vid = String.format("%04x", device.vendorId)
            val pid = String.format("%04x", device.productId)

            val maxPowerW = if (device.configurationCount > 0) {
                val config = device.getConfiguration(0)
                // getMaxPower() returns mA. Power (W) = (mA / 1000) * 5V = mA * 0.005
                config.maxPower * 0.005
            } else {
                null
            }
            
            val manufacturer = device.manufacturerName ?: ""
            val product = device.productName ?: ""
            val powerInfo = if (maxPowerW != null) String.format("(%.2f W)", maxPowerW) else "(N/A)"

            // Get max packet size from the first available endpoint
            var firstEpSize: Int? = null
            loop@ for (i in 0 until device.interfaceCount) {
                val usbInterface = device.getInterface(i)
                if (usbInterface.endpointCount > 0) {
                    firstEpSize = usbInterface.getEndpoint(0).maxPacketSize
                    break@loop
                }
            }
            val epInfo = if (firstEpSize != null) " [$firstEpSize B]" else ""

            sb.append("$vid:$pid $manufacturer $product $powerInfo$epInfo\n")
        }

        textView.text = sb.toString()
    }
}