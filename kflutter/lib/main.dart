import 'dart:ui';

import 'package:flutter/material.dart';

void main() => runApp(_widgetForRoute(window.defaultRouteName));

Widget _widgetForRoute(String route) {
  switch (route) {
    case 'route_home':
      return Container(
        width: double.infinity,
        height: double.infinity,
        color: Colors.white,
        child: Center(
          child: Text("Hello World!",
              style: TextStyle(color: Colors.blue),
              textDirection: TextDirection.ltr),
        ),
      );
    case 'route2':
      return Row(
        children: <Widget>[Text("Row")],
      );
    default:
      return Center(
        child: Text('Unknown route: $route', textDirection: TextDirection.ltr),
      );
  }
}
