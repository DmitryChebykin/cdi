Weld SE CDI example use with JavaFX and eager bean init

In Weld SE CDI for bean eager init during bootstrap you can use the following:

- Extension
-
- WeldContainer events
-
- Injection point bean

And however you can load application config beans from .properties and .yml files.

With ApacheDeltaSpike you can inject fields in SpingApp style (@Value).

You can run different configuration of JavaFx application, and run one

JavaFx app (for example "Admin user UI") from another JavaFx app ("Regular user UI")

In this project you can run AdminUiFxApp and then switch to UserUiFxApp, or another way -

start with UserUiFxApp and then switch to AdminUiFxApp, with shutting down corresponded WeldContainer

See Feature/ApacheDeltaSpike branch.
