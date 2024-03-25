import 'package:flutter/material.dart';

class ScaleUpImagePopUp extends StatelessWidget {
  const ScaleUpImagePopUp({super.key, required this.text});

  final String text;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: const [
          // DrawerButton(),
        ],
      ),
      // drawer: const Drawer(
      //   backgroundColor: Colors.amber,
      // ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(text),
          ],
        ),
      ),
    );
  }
}
