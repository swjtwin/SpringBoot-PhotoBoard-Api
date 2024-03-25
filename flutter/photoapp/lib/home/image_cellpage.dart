import 'package:flutter/material.dart';
import 'package:photoapp/home/repositories/image_repository.dart';
import 'package:provider/provider.dart';
// import 'package:image_picker/image_picker.dart';

class ImageCell extends StatelessWidget {
  const ImageCell({super.key});

  // XFile? image;

  @override
  Widget build(BuildContext context) {
    // String content = widget.content;

    return Padding(
      padding: const EdgeInsets.all(15),
      child: Column(
        children: [
          IconButton.outlined(
            onPressed: () {
              _showBottomSheet(context);
            },
            icon: const Icon(
              Icons.person,
            ),
            iconSize: 50.0,
            padding: const EdgeInsets.all(10.0),
          ),
          TextField(
            controller: TextEditingController(
                text: Provider.of<ImageRepository>(context).content),
            keyboardType: TextInputType.datetime,
            decoration: const InputDecoration(
              labelText: '날짜',
            ),
          ),
        ],
      ),
    );
  }

  _showBottomSheet(BuildContext context) {
    return showModalBottomSheet(
      context: context,
      builder: (_) {
        return Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            TextButton.icon(
              onPressed: () {},
              icon: const Icon(Icons.camera_alt),
              label: const Text('카메라'),
            ),
            const Divider(
              thickness: 1.0,
            ),
            TextButton.icon(
              onPressed: () {},
              icon: const Icon(Icons.image),
              label: const Text('갤러리'),
            ),
            const SizedBox(
              height: 30.0,
            ),
          ],
        );
      },
    );
  }
}
