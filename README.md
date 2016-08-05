# LogToSplunk
Event logging plugin for Spigot with support for Splunk HTTP Event Collector.

[![GitHub issues](https://img.shields.io/github/issues/PowerSchill/LogToSplunk.svg)](https://github.com/PowerSchill/LogToSplunk/issues) [![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/PowerSchill/LogToSplunk/master/LICENSE)

## Getting Started

### Requirements
* [Spigot](https://www.spigotmc.org/) Minecraft server software version 1.10 or later. 
* [Splunk](http://www.splunk.com) Enterprise 6.3 or later for HTTP Event Collector support. 

### Installing the plugin

1. Download the [latest pre-compiled version of the plugin](releases).
2. Configure the Splunk HTTP Event Collector on your Splunk instance according to the [documentation](http://dev.splunk.com/view/event-collector/SP-CAAAE6M).
3. Extract the LogToSplunk.zip file within the plugins directory of your Spigot Minecraft server. 
4. Copy the *LogToSplunk.properties.spec* file in the plugins/LogToSplunk directory to *LogToSplunk.properties*.
5. Edit the *LogToSplunk.properties* file.

## Known Issues


## Support

Please use the GitHub [issue tracker](issues) to submit issues or requests for new features. Screenshots and/or log outputs would be very helpful.

## Credit

> The code in this project was originally developed from the[PowerSchill/minecraft-app](https://github.com/PowerSchill/minecraft-app) project forked from the [Splunk/minecraft-app](https://github.com/splunk/minecraft-app).