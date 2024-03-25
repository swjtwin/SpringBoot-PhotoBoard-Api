import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:photoapp/home/model/image_model.dart';
import 'package:http/http.dart' as http;

final class ImageRepository extends ChangeNotifier {
  String _content = "기본값!!";
  String get content => _content;

  chgContent(String content) {
    _content = content;
    notifyListeners();
  }

  /// MockData fetchData
  Future<MockData> fetchData() async {
    // Define the API endpoint URL
    String apiUrl = 'https://jsonplaceholder.typicode.com/todos/1';

    // Perform a GET request
    try {
      http.Response response = await http.get(Uri.parse(apiUrl));
      debugPrint('진입');
      // Check if the request was successful (status code 200)
      if (response.statusCode == 200) {
        // Return the response body as a string
        Map<String, dynamic> decodeData = jsonDecode(response.body);
        return MockData.fromJson(decodeData);
      } else {
        // Handle error response and return an error message
        throw 'Failed to fetch data: ${response.statusCode}';
      }
    } catch (e) {
      // Handle exceptions and return an error message
      throw 'Exception occurred: ${e.toString()}';
    }
  }
}
