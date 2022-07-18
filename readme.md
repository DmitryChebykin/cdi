This is an application for testing JavaFx and Weld CDI features.

It was created in the process of code review of the application for the operation of the program of the cash trade

network "Komandor".

The project uses the same version of Weld SE as the working project.

Weld SE CDI example use with JavaFX and eager bean init

In Weld SE CDI for bean eager init during bootstrap you can use the following:

Extension

WeldContainer events

Injection point bean

And however you can load application config beans from .properties and .yml files.

With ApacheDeltaSpike you can inject fields in SpingApp style (@Value).

You can run different configuration of JavaFx application, and run one

JavaFx app (for example "Admin user UI") from another JavaFx app ("Regular user UI")

In this project you can run AdminUiFxApp and then switch to UserUiFxApp, or another way -

start with UserUiFxApp and then switch to AdminUiFxApp, with shutting down correspond

ed WeldContainer.

This is example how you could split your heavy JavaFx app to isolated JavaFx app with "dedicated" logic (like

"microservices" pattern).

See Feature/ApacheDeltaSpike branch. 
