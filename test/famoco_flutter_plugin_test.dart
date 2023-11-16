import 'package:flutter_test/flutter_test.dart';
import 'package:famoco_flutter_plugin/famoco_flutter_plugin.dart';
import 'package:famoco_flutter_plugin/famoco_flutter_plugin_platform_interface.dart';
import 'package:famoco_flutter_plugin/famoco_flutter_plugin_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFamocoFlutterPluginPlatform
    with MockPlatformInterfaceMixin
    implements FamocoFlutterPluginPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FamocoFlutterPluginPlatform initialPlatform = FamocoFlutterPluginPlatform.instance;

  test('$MethodChannelFamocoFlutterPlugin is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFamocoFlutterPlugin>());
  });

  test('getPlatformVersion', () async {
    FamocoFlutterPlugin famocoFlutterPlugin = FamocoFlutterPlugin();
    MockFamocoFlutterPluginPlatform fakePlatform = MockFamocoFlutterPluginPlatform();
    FamocoFlutterPluginPlatform.instance = fakePlatform;

    expect(await famocoFlutterPlugin.getPlatformVersion(), '42');
  });
}
