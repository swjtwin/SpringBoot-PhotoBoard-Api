import 'package:flutter/material.dart';
import 'package:photoapp/home/repositories/image_repository.dart';
import 'package:photoapp/login/loginpage.dart';
import 'package:provider/provider.dart';

class StartPage extends StatelessWidget {
  const StartPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // 키보드와 컨텐츠 사이에 공간이 표시되는 것을 없앤다
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        title: const Text('사랑이 기지개'),
        centerTitle: true,
        backgroundColor: Colors.lightBlue[100],
        elevation: 0.0,
        actions: [
          IconButton(
            onPressed: () {
              debugPrint('Presss');
              // Provider.of<ImageRepository>(context, listen: false)
              //     .chgContent('수정완료');
              // debugPrint(Provider.of<ImageRepository>(context, listen: false)
              //     .content);
            },
            icon: const Icon(Icons.settings),
          ),
          // Consumer<ImageRepository>(
          //   builder: (context, value, child) => TextButton(
          //     onPressed: () {
          //       value.chgContent('설정');
          //       print(context.size?.width);
          //     },
          //     child: Text(value.content),
          //   ),
          // ),
        ],
      ),
      body: const SafeArea(
        child: Padding(
          padding: EdgeInsets.all(15.0),
          child: LoginPage(),
        ),
      ),
    );
  }
}
