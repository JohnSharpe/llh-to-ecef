# llh-to-ecef [![Build Status](https://travis-ci.com/JohnSharpe/llh-to-ecef.svg?branch=master)](https://travis-ci.com/JohnSharpe/llh-to-ecef)
A Java library to convert longitude/latitude coordinates to ECEF (x,y,z) coordinates.

## Build
Use Maven to build/test this project.
`mvn clean package`

## Usage
To get started you need a `Model`, either a `SphereModel` or an `EllipsoidModel`.

Constructors:
```
final Model unitSphere = new SphereModel(); // radius defaults to 1
final Model sphere = new SphereModel(320);

final Model slowerUnitSphere = new EllipsoidModel(); // radii both default to 1. You should be using SphereModel instead...
final Model ellipsoid = new EllipsoidModel(13, 14); // semi-major, semi-minor

final Model earth = Model.earthKilometers(); // Convenience for 'new EllipsoidModel(6378.1370, 6356.752314245);'
final Model earthMiles = Model.earthMiles(); // Convenience for 'new EllipsoidModel(3963.1906, 3949.9028);'
```

You also need to wrap up your values in a LonLatHeight object. Note that `height` is an offset from the circumference at the point defined by `lon` and `lat`.
```
final LonLatHeight london = new LonLatHeight(-0.1277583, 51.5073509, 0);

final LonLatHeight westminsterUndergroundStation = new LonLatHeight(-0.1270247, 51.5013233, -0.032);
final LonLatHeight topOfTheShard = new LonLatHeight(-0.0886887, 51.5045033, 0.306);
```

Then call `translate` to get a Coordinate result.
```
final Coordinate ecef = earth.translate(london);
ecef.getX(); // 3977.999
ecef.getY(); // -8.87
ecef.getZ(); // 4968.872
```
