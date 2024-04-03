import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;

abstract class UrlObject {}

final class UrlApi {
  /// get통신
  /// 쿼리스트링 추가하기
  Future<T> get<T extends UrlObject>({
    required String url,
    required T Function(Map<String, dynamic> decodeData) resultJsonFunc,
  }) async {
    String apiUrl = url;

    try {
      http.Response response = await http.get(Uri.parse(apiUrl));

      // Status 200일 경우 진입.
      if (response.statusCode == 200) {
        // Return the response body as a string
        Map<String, dynamic> decodeData = jsonDecode(response.body);
        return resultJsonFunc(decodeData);
      } else {
        throw '통신 에러: ${response.statusCode.toString()}';
      }
    } catch (e) {
      throw 'Exception occurred: ${e.toString()}';
    }
  }

  /// post통신
  Future<T> post<T extends UrlObject>({
    required String url,
    required Object? body,
    required T Function(Map<String, dynamic> decodeData) resultJsonFunc,
  }) async {
    String apiUrl = url;

    try {
      http.Response response = await http.post(
        Uri.parse(apiUrl),
        body: jsonEncode(body),
      );

      if (response.statusCode >= 200 && response.statusCode <= 299) {
        Map<String, dynamic> decodeData = jsonDecode(response.body);
        return resultJsonFunc(decodeData);
      } else {
        throw '통신 에러 : ${response.statusCode.toString()}';
      }
    } catch (e) {
      rethrow;
    }
  }
}
