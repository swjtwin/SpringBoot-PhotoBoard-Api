import 'package:flutter/material.dart';
import 'package:photoapp/home/homepage.dart';
import 'package:photoapp/home/repositories/image_repository.dart';
import 'package:photoapp/login/registpage.dart';
import 'package:photoapp/startpage.dart';
import 'package:photoapp/home/edit_imagepage.dart';
import 'package:provider/provider.dart';

void main() => runApp(const PhotoApp());

class PhotoApp extends StatelessWidget {
  const PhotoApp({super.key});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        // 키보드 포커스 제거하기
        FocusManager.instance.primaryFocus?.unfocus();
      },
      child: ChangeNotifierProvider(
        create: (context) => ImageRepository(),
        child: MaterialApp(
          debugShowCheckedModeBanner: true,
          home: const StartPage(),
          routes: {
            '/home': (_) => const HomePage(),
            '/regist': (_) => const RegistPage(),
            '/edit': (_) => const EditImagePage(),
          },
        ),
      ),
    );
  }
}
