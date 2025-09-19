import 'package:flutter/material.dart';
void main() => runApp(const DriverApp());
class DriverApp extends StatelessWidget {
  const DriverApp({super.key});
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(home: Scaffold(body: Center(child: Text("Driver App Shell"))));
  }
}
