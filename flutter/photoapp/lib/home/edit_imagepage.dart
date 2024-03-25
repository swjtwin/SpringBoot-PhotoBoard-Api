import 'package:flutter/material.dart';
// import 'package:image_picker/image_picker.dart';
import 'package:photoapp/home/image_cellpage.dart';

class EditImagePage extends StatelessWidget {
  const EditImagePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          '등록하기',
          style: TextStyle(
            fontWeight: FontWeight.bold,
          ),
        ),
        actions: [
          TextButton(
            onPressed: () {},
            child: const Text('저장'),
          ),
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.all(15.0),
        child: Column(
          children: [
            // GridView
            ImageCell(),
            // ImageCell(),
          ],
        ),
      ),
    );
  }
}

// final picker = ImagePicker();
// XFile? cameraImage; // 카메라로 촬영한 이미지를 저장할 변수
// List<XFile?> multiImage = []; // 갤러리에서 여러 장의 사진을 선택해서 저장할 변수
// List<XFile?> images = [];
