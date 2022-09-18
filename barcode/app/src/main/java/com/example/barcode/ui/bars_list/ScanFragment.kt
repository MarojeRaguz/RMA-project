package com.example.barcode.ui.bars_list

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.budiyev.android.codescanner.*
import com.example.barcode.R
import com.example.barcode.databinding.FragmentScanBinding
import com.example.barcode.ui.menu_list.MenuListActivity

private const val CAMERA_REQUEST_CODE = 101

class ScanFragment: Fragment() {
    private lateinit var codeScanner: CodeScanner
    private lateinit var binding: FragmentScanBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScanBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPermissions()
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                activity.runOnUiThread{
                    val idAndTable: List<String> = it.text.split("/")
                    Log.i("main","scanning successful ${idAndTable[0]} is id and table is ${idAndTable[1]}")
                    val intent = Intent(context,MenuListActivity::class.java).apply {
                        putExtra("barId",idAndTable[0])
                        putExtra("tableNumber",idAndTable[1])
                    }
                    startActivity(intent)
                }
            }
            errorCallback = ErrorCallback {
                activity.runOnUiThread{
                    Log.i("main", "camera initialization error: ${it.message}")
                }
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }
    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions(){
        val permission: Int = ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.CAMERA)

        if(permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA),CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(requireActivity(),"You need the camera permission to be able to scan barcode!",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}