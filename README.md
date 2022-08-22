# Updater
Updater API that allows someone to use an SQL server as an update management system for Spigot/Bukkit Plugins. 
### TODO
* [x] Clean up [Updater.java](https://github.com/Signifies/Updater/blob/main/Updater.java).
* [x] Add doc comments to [Updater.java](https://github.com/Signifies/Updater/blob/main/Updater.java).
* [ ] Fix decompiler errors and improper definitions of variables for rest of classes.

## Note
Classes were recently decompiled from orginal source file due to the actual source being lost. Some classes may reflect strange or obfuscated lines of code. This is not intentional, but rather an after effect of the decompiler software. 
 
Updater is designed as a makeshift update avaliable notification system for server administrators. It utilizes a SQL server to handle update schedules and delivery of notifications to administrators. The API is fairly simple to implement and use. Updater.java is all you should need to realistically run and access these features, however, utlizing the SQL.java class would be useful, as Updater is built using this class. 
