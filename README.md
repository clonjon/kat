![KAT](kat_logo.png)
# KAT (Keep Administration Trivial)

Currently in development - v1.1.0 Released

### How to use

* Prerequisites :
	
	* JAVA SDK 1.8.0_144
	* Apache Maven 3.3.3
	* Node.js 8.9.4 or higher
	* Sonatype Nexus OSS 3.x
	
* To compile :

	* mvn clean install in kat root directory
	
* To deloy :

	* KAT has two types of components: KATEXEC and KATADMIN. Both run on an Apache Karaf 4.1.5 server in current version.
	* To download Apache Karaf, please go to https://karaf.apache.org/download.html#container-415 and choose your flavor, KAT is meant to perform even well on Windows or Linux systems
	* Extract downloaded Karaf binaries on your disk, to start the server, go to your `${karaf.home}/bin` folder and execute `./karaf` (Linux) or `karaf.bat` (Windows) command to start server
	* You first have to install necessary feature repositories. To do this, issue following commands :
		* `mvn:com.idfor.kat.tools/katfeatures/1.1.0/xml/features`
		* `mvn:org.apache.karaf.decanter/apache-karaf-decanter/2.0.0/xml/features`
		* `mvn:org.apache.karaf.cave/apache-karaf-cave/4.1.0/xml/features`
	* For a KATADMIN server, you have to install following features :
		* `feature:install katbackend`
		* `feature:install katfrontend`
		* `feature:install elasticsearch`
		* `feature:install kibana`
		* `feature:install cave-deployer`
	* For a KATEXEC server, you have to install following features :
		* `feature:install katexec`
		* `feature:install katjobmanager`
		* `feature:install cave-deployer`
		* `feature:install decanter-collector-log`
		* `feature:install decanter-appender-elasticsearch-jest`

* Basic configuration :
	* In directory `${karaf.etc}` of each KATEXEC server, create a new directory called `katexec`
	* For every KAT server, replace the original `${karaf.etc}/users.properties` file by the one provided in `config` directory
	* If running different KAT servers on a local machine, you will probably have to reconfigure used ports for each running KAT instance. To do so:
		* Edit the `${karaf.etc}/org.ops4j.pax.web.cfg` file
		* Change the line `org.osgi.service.http.port=8181` with desired port number
		* Save your changes

* Advanced configuration :
	* Coming SOON with user manual, stay tuned!

At this point, you're all set for a basic KAT experience! Many thanks for using IDFOR KAT.

----
The KAT Team