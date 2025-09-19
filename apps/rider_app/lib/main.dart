import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() => runApp(const RiderApp());

class RiderApp extends StatefulWidget {
  const RiderApp({super.key});
  @override State<RiderApp> createState() => _RiderAppState();
}

class _RiderAppState extends State<RiderApp> {
  String _quote = "Tap to get estimate";
  Future<void> _estimate() async {
    final uri = Uri.parse("http://localhost:8088/rider/estimate");
    final resp = await http.post(uri,
      headers: {"Content-Type":"application/json"},
      body: jsonEncode({
        "pickup":{"lat":12.9716,"lng":77.5946},
        "drop":{"lat":13.0359,"lng":77.5970},
        "category":"MINI"
      }));
    setState(() => _quote = resp.body);
  }
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: const Text("Rider App (Dev)")),
        body: Center(child: Column(mainAxisSize: MainAxisSize.min, children: [
          Text(_quote, textAlign: TextAlign.center),
          const SizedBox(height: 16),
          FilledButton(onPressed: _estimate, child: const Text("Get Estimate"))
        ])),
      ),
    );
  }
}
