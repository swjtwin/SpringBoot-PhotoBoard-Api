import 'package:flutter/material.dart';
// import 'package:photoapp/home/repositories/image_repository.dart';
// import 'package:provider/provider.dart';

// class RegistPage extends StatelessWidget {
//   const RegistPage({super.key});
//   @override
//   State<RegistPage> createState() => _RegistPageState();
// }

class RegistPage extends StatelessWidget {
  const RegistPage({super.key});
  // bool isConfirmToast = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('회원가입'),
        backgroundColor: Colors.lightBlue[100],
      ),
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
          children: [
            textInput(label: '아이디'),
            textInput(label: '이름'),
            textInput(label: '비밀번호', isPw: true),
            textInput(label: '비밀번호 확인', isPw: true),
            const SizedBox(
              height: 20.0,
            ),
            SizedBox(
              width: 150,
              child: TextButton(
                onPressed: () {
                  Navigator.pushNamed(context, '/home');
                  // Navigator.pushReplacementNamed(context, '/home');
                  // passwordCheck('', '');
                },
                child: const Text('확인'),
              ),
            ),
          ],
        ),
      ),
    );
  }

  //
  Widget textInput({String label = '', bool isPw = false}) {
    return TextField(
      obscureText: isPw,
      // controller: TextEditingController(text: ''),
      decoration: InputDecoration(
        labelText: label,
      ),
    );
  }

  /// 비밀번호 체크
  bool passwordCheck(String bfText, String afText) {
    if (bfText != afText) {
      // setState(() {
      //   isConfirmToast = true;
      // });
      return false;
    }
    return true;
  }
}
