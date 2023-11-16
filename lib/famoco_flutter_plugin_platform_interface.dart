import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'famoco_flutter_plugin_method_channel.dart';

abstract class FamocoFlutterPluginPlatform extends PlatformInterface {
  /// Constructs a FamocoFlutterPluginPlatform.
  FamocoFlutterPluginPlatform() : super(token: _token);

  static final Object _token = Object();

  static FamocoFlutterPluginPlatform _instance = MethodChannelFamocoFlutterPlugin();

  /// The default instance of [FamocoFlutterPluginPlatform] to use.
  ///
  /// Defaults to [MethodChannelFamocoFlutterPlugin].
  static FamocoFlutterPluginPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FamocoFlutterPluginPlatform] when
  /// they register themselves.
  static set instance(FamocoFlutterPluginPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
