import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  @override
  HomeState createState() => HomeState();
}

class HomeState extends State<Home> {
  Option _selectedOption = options[0]; // The app's "state".

  void _select(Option option) {
    // Causes the app to rebuild with the new _selectedOption.
    setState(() {
      _selectedOption = option;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          actions: <Widget>[
            // action button
            IconButton(
              icon: Icon(options[0].icon),
              onPressed: () {
                _select(options[0]);
              },
            ),
            // action button
            IconButton(
              icon: Icon(options[1].icon),
              onPressed: () {
                _select(options[1]);
              },
            ),
            // overflow menu
            PopupMenuButton<Option>(
              onSelected: _select,
              itemBuilder: (BuildContext context) {
                return options.skip(2).map((Option option) {
                  return PopupMenuItem<Option>(
                    value: option,
                    child:FlatButton.icon(
                      onPressed: () {

                      },
                      icon: Icon(option.icon),
                      label: Text(option.title),
                    ),
                  );
                }).toList();
              },
            ),
          ],
        ),
      ),
    );
  }
}

class Option {
  const Option({this.title, this.icon});

  final String title;
  final IconData icon;
}

const List<Option> options = const <Option>[
  const Option(title: 'Home', icon: Icons.home),
  const Option(title: 'Search', icon: Icons.search),
  const Option(title: 'Bookmarks', icon: Icons.bookmark),
  const Option(title: 'History', icon: Icons.history),
  const Option(title: 'About', icon: Icons.info),
  const Option(title: 'Exit', icon: Icons.exit_to_app),
];

void main() {
  runApp(Home());
}
