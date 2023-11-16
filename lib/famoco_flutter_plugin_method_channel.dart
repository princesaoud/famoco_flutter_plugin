import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'famoco_flutter_plugin_platform_interface.dart';

/// An implementation of [FamocoFlutterPluginPlatform] that uses method channels.
class MethodChannelFamocoFlutterPlugin extends FamocoFlutterPluginPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('famoco_flutter_plugin');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
