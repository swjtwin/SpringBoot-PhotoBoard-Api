import 'dart:js_interop';

import 'package:flutter/material.dart';
import 'package:photoapp/home/model/image_model.dart';
import 'package:photoapp/util/url_api.dart';

final class ImageRepository extends ChangeNotifier {
  String _content = "";
  String get content => _content;
  final UrlApi _urlApi = UrlApi();
  MockData _mockData =
      MockData(userId: 0, id: 0, title: "테스트", completed: false);
  MockData get mockData => _mockData;

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
    try {
      final result = await _urlApi.postRtnJson(
        url: apiUrl,
        body: {
          'username': id,
          'password': password,
        },
      );
      debugPrint('$result');

      if (result['jwtAccessToken'] == '') {
        debugPrint('로그인 실패');
        return false;
      }

      return true;
    } catch (e) {
      debugPrint(e.toString());
      return false;
    }
  }

  /// 회원정보 저장
  Future<bool> registInfoSave({
    required String id,
    required String nickName,
    required String gender,
    required String password,
  }) async {
    String apiUrl = 'https://jsonplaceholder.typicode.com/todos/1';
    try {
      final result = await _urlApi.postRtnJson(
        url: apiUrl,
        body: {
          'username': id,
          'password': password,
          'gender': gender,
          'nickname': nickName,
        },
      );

      debugPrint('$result');

      if (result['result'] == '회원가입 성공') {
        return true;
      }

      return false;
    } catch (e) {
      debugPrint(e.toString());
      return false;
    }
  }

  /// MockData fetchData
  Future<void> fetchData() async {
    // Define the API endpoint URL
    String apiUrl = 'https://jsonplaceholder.typicode.com/todos/1';

    _mockData = await _urlApi.get<MockData>(
      url: apiUrl,
      resultJsonFunc: (decodeData) => MockData.fromJson(decodeData),
    );
    notifyListeners();
  }
}
