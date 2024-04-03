import 'package:flutter/material.dart';
import 'package:photoapp/home/repositories/image_repository.dart';
// import 'package:photoapp/home/repositories/image_repository.dart';
// import 'package:photoapp/login/registpage.dart';
// import 'package:provider/provider.dart';
// import 'package:flutter/scheduler.dart';
// import 'package:photoapp/home/repositories/image_repository.dart';

final class LoginPage extends StatefulWidget {
  const LoginPage({
    super.key,
  });

  @override
  State<LoginPage> createState() => _LoginPageState();
}

final class _LoginPageState extends State<LoginPage> {
  String _idtext = '';
  String _password = '';
  final bool _isEmptyText = false;
  final bool _isEmptyPassword = false;

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Padding(
        padding: const EdgeInsets.only(top: 15.0),
        child: Column(
          children: [
            const CircleAvatar(
              radius: 70.0,
              backgroundImage: AssetImage('assets/사랑이1.jpeg'),
            ),
            const SizedBox(
              height: 15.0,
            ),
            information(),
            inputText(
              titleText: '아이디',
              icon: const Icon(Icons.person_2),
              ctx: context,
              chgText: _idtext,
              onChangeFunc: (v) {
                setState(() {
                  _idtext = v;
                });
              },
            ),
            const SizedBox(
              height: 15,
            ),
            inputText(
              titleText: '비밀번호',
              icon: const Icon(Icons.lock),
              isOb: true,
              ctx: context,
              chgText: _password,
              onChangeFunc: (v) {
                _password = v;
              },
            ),
            const SizedBox(height: 30),
            SizedBox(
              width: MediaQuery.of(context).size.width,
              child: TextButton(
                style: ButtonStyle(
                  backgroundColor: MaterialStatePropertyAll(
                    Colors.lightBlue[200],
                  ),
                  padding: const MaterialStatePropertyAll(EdgeInsets.symmetric(
                    vertical: 15.0,
                  )),
                ),
                onPressed: () {
                  // if (_idtext.isEmpty) {
                  //   setState(() {
                  //     _isEmptyText = true;
                  //   });
                  //   return;
                  // } else if (_password.isEmpty) {
                  //   setState(() {
                  //     _isEmptyText = false;
                  //     _isEmptyPassword = true;
                  //   });
                  //   return;
                  // }
                  // Navigator.pushNamed(context, '/home');
                  // 이동하는 route를 새로운 root로 만든다. ↓↓
                  Navigator.pushReplacementNamed(context, '/home');
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(
                      content: Text('로그인 되었습니다.'),
                      duration: Duration(
                        seconds: 2,
                      ),
                    ),
                  );
                },
                child: const Text(
                  '로그인',
                  style: TextStyle(
                    color: Colors.white,
                    fontWeight: FontWeight.bold,
                    fontSize: 17.0,
                  ),
                ),
              ),
            ),
            const SizedBox(
              height: 5,
            ),
            TextButton(
              onPressed: () {
                Navigator.pushNamed(context, '/regist');
              },
              style: TextButton.styleFrom(
                foregroundColor: Colors.grey,
              ),
              child: const Text('회원가입'),
            ),
            ElevatedButton(
              onPressed: () async {
                final image = ImageRepository();
                var mocdata = await image.fetchData();
                print(mocdata.title);
              },
              child: const Text('Test'),
            )
          ],
        ),
      ),
    );
  }

  Row inputText({
    required String titleText,
    required Icon icon,
    bool isOb = false,
    required BuildContext ctx,
    required String chgText,
    void Function(String v)? onChangeFunc,
  }) {
    // onChangeFunc가 null일 수 있기때문에 기본 값을 넣어준다.
    Function(String v) onChgFunc = onChangeFunc ?? (v) {};
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        icon,
        const SizedBox(
          width: 13,
        ),
        SizedBox(
          width: MediaQuery.of(ctx).size.width * 0.7,
          child: TextField(
            onChanged: (value) {
              onChgFunc(value);
            },
            obscureText: isOb,
            decoration: InputDecoration(
              labelText: titleText,
              border: const OutlineInputBorder(
                borderRadius: BorderRadius.all(Radius.circular(10)),
              ),
            ),
            // keyboardType: TextInputType.phone,
          ),
        ),
      ],
    );
  }

  Widget information() {
    if (_isEmptyText) {
      return const Text(
        '아이디를 입력해주세요.',
        style: TextStyle(
          color: Colors.red,
        ),
      );
    } else if (_isEmptyPassword) {
      return const Text(
        '비밀번호를 입력해주세요.',
        style: TextStyle(
          color: Colors.red,
        ),
      );
    } else {
      return const SizedBox.shrink();
    }
  }
}
