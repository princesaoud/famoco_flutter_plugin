import 'dart:async';

import 'package:famoco_flutter_plugin/famoco_flutter_plugin.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  final _famocoFlutterPlugin = FamocoFlutterPlugin();
  bool dataOfFinger = false;
  int? dataOfYourFinger;

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    try {
      platformVersion = await _famocoFlutterPlugin.getPlatformVersion() ??
          'Unknown platform version';
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Famoco capture plugin'),
        ),
        body: Column(
          children: [
            TextButton(
              onPressed: () async {
                dataOfYourFinger =
                    await _famocoFlutterPlugin.readFingerprintData();
                if (dataOfYourFinger != null) {
                  setState(() {
                    dataOfFinger = true;
                  });
                }
              },
              onLongPress: () {
                setState(() {
                  dataOfFinger = false;
                });
              },
              child: !dataOfFinger
                  ? Text("Capturer une emprunte")
                  : Text("Scan data is $dataOfYourFinger"),
            ),
            Center(
              child: Text('Running on: $_platformVersion\n'),
            ),
          ],
        ),
      ),
    );
  }
}
