import 'package:flutter/material.dart';
import 'package:photoapp/home/model/image_model.dart';
import 'package:photoapp/util/url_api.dart';

final class ImageRepository extends ChangeNotifier {
  String _content = "";
  String get content => _content;
  final UrlApi _urlApi = UrlApi();

  chgContent(String content) {
    _content = content;
    notifyListeners();
  }

  /// 로그인
  Future<bool> login({
    required String id,
    required String password,
  }) async {
    String apiUrl = 'https://jsonplaceholder.typicode.com/todos/1';
    final result = await _urlApi.post<MockData>(
      url: apiUrl,
      body: {
        'username': id,
        'password': password,
      },
      resultJsonFunc: (decodeData) => MockData.fromJson(decodeData),
    );

    return result.completed;
  }

  /// MockData fetchData
  Future<MockData> fetchData() async {
    // Define the API endpoint URL
    String apiUrl = 'https://jsonplaceholder.typicode.com/todos/1';

    return await _urlApi.get<MockData>(
      url: apiUrl,
      resultJsonFunc: (decodeData) => MockData.fromJson(decodeData),
    );
  }
}
