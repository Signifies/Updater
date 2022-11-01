# Updater

### Updater bugs have been fixed, documentation added, and incorrect decompiled instances or definitions have been removed. 
Updater API allows a user to use a SQL database to manage the status of their plugin updates. It uses a simple ticket system and checks whenever the version is changed and submits and update notification to anyone who is an administrator. Please note that this code does not come in a compiled format, and must be compiled and integrated into your project manually. It is designed as a suplementary addition to plugins that you may wish to notify users when an update has been released on either your private or public site (such as SpigotMC resources.) 
### TODO
* [x] Clean up [Updater.java](https://github.com/Signifies/Updater/blob/main/Updater.java).
* [x] Add doc comments to [Updater.java](https://github.com/Signifies/Updater/blob/main/Updater.java).
* [x] Fix decompiler errors and improper definitions of variables for rest of classes.

## Note
Classes were recently decompiled from orginal source file due to the actual source being lost. Some classes may reflect strange or obfuscated lines of code. This is not intentional, but rather an after effect of the decompiler software. 
 
Updater is designed as a makeshift update avaliable notification system for server administrators. It utilizes a SQL server to handle update schedules and delivery of notifications to administrators. The API is fairly simple to implement and use. Updater.java is all you should need to realistically run and access these features, however, utlizing the SQL.java class would be useful, as Updater is built using this class. 
