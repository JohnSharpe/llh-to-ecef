# llh-to-ecef
A Java library to convert longitude/latitude coordinates to ECEF (x,y,z) coordinates.

## Build
Use Maven to build/test this project.
`mvn clean package`

## Usage
To get started you need a `Model`, either a `SphereModel` or an `EllipsoidModel`.

Constructors:
```
new SphereModel(); // radius defaults to 1
new SphereModel(320);

new EllipsoidModel(); // radii both default to 1. You should be using SphereModel instead...
new EllipsoidModel(13, 14); // semi-major, semi-minor

final Model earth = Model.earthKilometers(); // Convenience for 'new EllipsoidModel(6378.1370, 6356.752314245);'
Model.earthMiles(); // Convenience for 'new EllipsoidModel(3963.1906, 3949.9028);'
```

You also need to wrap up your values in a LonLatHeight object.
```
final LonLatHeight llh = new LonLatHeight(20, 30, 0);
```

Then call `translate` to get a Coordinate result.
```
final Coordinate ecef = earth.translate(llh);
ecef.getX();
ecef.getY();
ecef.getZ();
```
