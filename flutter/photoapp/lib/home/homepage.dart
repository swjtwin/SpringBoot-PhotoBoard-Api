import 'package:flutter/material.dart';
import 'package:photoapp/home/scaleupimagepopup.dart';
import 'package:photoapp/home/image_cellpage.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  List<String> datas = [
    '사랑이 1',
    '사랑이 2',
    '사랑이 3',
    '사랑이 4',
    '사랑이 5',
    '사랑이 6',
    '사랑이 7',
    '사랑이 8',
    '사랑이 9',
    '사랑이 10',
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        backgroundColor: Colors.blueGrey[100],
        title: const Text(
          'Photo Wallet',
          style: TextStyle(
            fontWeight: FontWeight.w600,
          ),
        ),
        actions: [
          IconButton(
              onPressed: () {
                Navigator.pushNamed(context, '/edit');
              },
              icon: const Icon(Icons.edit)),
          PopupMenuButton(
            // 팝업 즉 현재의 context에서 별개의 위젯을 현재 위치의 context에서 띄워야하기 때문에
            // context를 매개변수로 전달해 주어야 한다.
            itemBuilder: (context) {
              return [
                const PopupMenuItem(
                  child: Text('Menu1'),
                ),
                const PopupMenuItem(
                  child: Text('Menu2'),
                ),
              ];
            },
          )
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(
          horizontal: 20.0,
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            // for (var data in datas) Text(data),
            const SizedBox(
              height: 30,
            ),
            Expanded(
              child: GridView.builder(
                gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                    crossAxisCount: 2),
                itemCount: datas.length,
                itemBuilder: (_, index) {
                  return GestureDetector(
                    onLongPress: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => ScaleUpImagePopUp(
                            text: datas[index],
                          ),
                        ),
                      );
                    },
                    child: ImageCell(),
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
