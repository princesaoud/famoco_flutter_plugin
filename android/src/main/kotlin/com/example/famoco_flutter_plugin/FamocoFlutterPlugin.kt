package com.example.famoco_flutter_plugin

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** FamocoFlutterPlugin */
class FamocoFlutterPlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private lateinit var fingerPrintManagerWrapper: FingerprintManagerWrapper

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "famoco_flutter_plugin")
        channel.setMethodCallHandler(this)
        fingerPrintManagerWrapper = FingerprintManagerWrapper()

    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "getPlatformVersion" -> {
                result.success("Android ${android.os.Build.VERSION.RELEASE}")
            }
            "getFingerprintData" -> {
                result.success(fingerPrintManagerWrapper.readFingerprintData())
            }
            else -> {
                result.notImplemented()
            }
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }


    private fun readFingerprintData(): String {
        // Implement your Famoco SDK fingerprint reading logic here
        // Use Famoco SDK's FingerprintReader class or any other relevant classes

        return "Fingerprint data from Famoco SDK"
    }
}

