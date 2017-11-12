# README #

G-LOGGER is library that implements the centralized logger object. Indeed the behavior dictated by GLogger interface is focus to create a flexible structure that permits to centralize in one single class or more classes, the logging system.

## Usage ##

GLogger<K> interface is the main interface.

GLogFactory is the main factory that read a configuration file () and build by reflection the logger.

Example of configuration file is:
	glog.logger.type = UTIL | LOG4J | SLF4J
	glog.config.file = path of custom configuration file of UTIL or LOG4J or SLF4j
 
If this file is not inside classpath, the default choice is LOG4J

## Contributing

Bug reports and pull requests are welcome on G-EE Sourceforge https://sourceforge.net/projects/g-ee/

## License

The code is is available as open source under the terms of the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
