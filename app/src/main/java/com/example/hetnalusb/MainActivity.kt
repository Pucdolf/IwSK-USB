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
            val vid = String.format("%04X", device.vendorId)
            val pid = String.format("%04X", device.productId)
            
            sb.append("Urządzenie: ${device.deviceName}\n")
            sb.append("  VID: 0x$vid\n")
            sb.append("  PID: 0x$pid\n")
            sb.append("  Manufacturer: ${device.manufacturerName ?: "N/A"}\n")
            sb.append("  Product: ${device.productName ?: "N/A"}\n")
            sb.append("----------------------------\n")
        }

        textView.text = sb.toString()
    }
}