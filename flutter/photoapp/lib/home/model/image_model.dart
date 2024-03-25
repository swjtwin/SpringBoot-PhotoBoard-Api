class ImageModel {}

final class MockData {
  int userId;
  int id;
  String title;
  bool completed;

  MockData({
    required this.userId,
    required this.id,
    required this.title,
    required this.completed,
  });

  factory MockData.fromJson(Map<String, dynamic> jsondata) => MockData(
        userId: jsondata["userId"],
        id: jsondata["id"],
        title: jsondata["title"],
        completed: jsondata["completed"],
      );
}

// void main() {
//   String jsonString = '''
//     {
//       "userId": 1,
//       "id": 1,
//       "title": "delectus aut autem",
//       "completed": false
//     }
//   ''';

//   // Decoding JSON data
//   Map<String, dynamic> decodedJson = jsonDecode(jsonString);

//   // Creating Todo object from JSON data
//   Todo todo = Todo.fromJson(decodedJson);

//   String _ =
//       jsonEncode(MockData(userId: 0, id: 0, title: "title", completed: true));
// }

// class Todo {
//   final int userId;
//   final int id;
//   final String title;
//   final bool completed;

//   Todo({
//     required this.userId,
//     required this.id,
//     required this.title,
//     required this.completed,
//   });

//   factory Todo.fromJson(Map<String, dynamic> json) {
//     return Todo(
//       userId: json['userId'],
//       id: json['id'],
//       title: json['title'],
//       completed: json['completed'],
//     );
//   }
// }
