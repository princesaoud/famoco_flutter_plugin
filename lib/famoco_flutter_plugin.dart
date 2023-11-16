import 'package:flutter/services.dart';

import 'famoco_flutter_plugin_platform_interface.dart';

class FamocoFlutterPlugin {
  static const MethodChannel _channel = MethodChannel('famoco_flutter_plugin');

  Future<String?> getPlatformVersion() {
    return FamocoFlutterPluginPlatform.instance.getPlatformVersion();
  }

  Future<int?> readFingerprintData() async {
    try {
      final int? result = await _channel.invokeMethod('getFingerprintData');
      return result;
    } on PlatformException catch (e) {
      return 0;
    }
  }
}
