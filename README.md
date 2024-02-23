END OF LIFECYCLE NOTICE
===

Development of P4LX has been retired in favor of [GLX](https://github.com/heronarts/GLX), a replacement library that uses [LWJGL3](https://github.com/lwjgl) and [bgfx](https://github.com/bkaradzic/bgfx/) in place of the Processing runtime.

The flagship [LX](https://github.com/heronarts/LX) GUI application is now [Chromatik](https://chromatik.co/).

---

**BY DOWNLOADING OR USING THE LX SOFTWARE OR ANY PART THEREOF, YOU AGREE TO THE TERMS AND CONDITIONS OF THE [LX STUDIO SOFTWARE LICENSE AND DISTRIBUTION AGREEMENT](http://lx.studio/license).**

Please note that LX is not open-source software. The license grants permission to use this software freely in non-commercial applications. Commercial use is subject to a total annual revenue limit of $25K on any and all projects associated with the software. If this licensing is obstructive to your needs or you are unclear as to whether your desired use case is compliant, contact me to discuss proprietary licensing: mark@heronarts.com

---

### P4LX Overview ###

P4LX is a Processing 4 wrapper library for the [LX](https://github.com/heronarts/LX) lighting engine and the basis of the legacy [LX Studio](http://lx.studio/) application. It allows you to simply embed LX inside a Processing sketch with a rich UI library that makes it easy and painless to render 3D simulations alongside versatile 2D controls.

This development branch has now been abandoned in favor of [GLX](https://github.com/heronarts/GLX), which powers the [Chromatik](https://chromatik.co/) Digital Lighting Workstation application.

### Development Environment ###

At the time of writing, Processing 4.0 beta 8 uses the [Eclipse Temurin Java 17 JDK (17.0.2+8)](https://adoptium.net/). Configure your development environment to use this JVM for greatest compatibility with the Processing 4 runtime.

The recommended IDE for LX is Eclipse, using `mvn` for command-line build. Create a folder to work in and clone both the LX and P4LX repositories side-by-side.
```
$ mkdir workspace
$ cd workspace
$ git clone https://github.com/heronarts/LX.git
$ git clone https://github.com/heronarts/P4LX.git
```

To open the project in Eclipse:
```
File | Import...
General > Existing Projects Into Workspace
Select root directory...
```

Go through this process for both the LX and P4LX projects, selecting `workspace/LX` and `workspace/P4LX` as the root directories.

#### Maven Build Process ####

First: Install Maven for your platform. Google is your friend.

P4LX depends upon the Processing core library JAR file, as well as custom JOGL builds. These custom JOGL builds are not presently available in Maven central. First, you will need to install these to your local Maven repository using the `mvn validate` command. 
```
$ cd P4LX
# mvn validate
```

This will locally install the JOGL dependencies with the artificial version identifier 4.0b8. The installed libraries are lifted directly from the distributable Processing application.

Once you have done this, you can build and install both the LX and P4LX packages
```
$ cd ../LX
$ mvn install
$ cd ../P4LX
$ mvn install
```
The above commands result in the following artifacts:
in `P4LX/target`:
1. fat jar with dependencies
1. thin jar for distribution via maven repository publishing
1. source jar for distribution via maven repository publishing
1. javadoc jar for distribution via maven repository publishing
1. javadoc html files for publishing to web: `apidocs`

The Maven build is IDE-agnostic, so any IDE that can import Maven projects (Eclipse, IntelliJ) should have no problem importing and building this repo.

#### Deployment ####

Deployment requires access to the central Sonatype repository and appropriate GPG keys.

```
$ cd P4LX
$ mvn deploy -Pdeploy
```

### Contact and Collaboration ###

Building a big cool project? I'm probably interested in hearing about it! Want to solicit some help, request new framework features, or just ask a random question? Drop me a line: mark@heronarts.com

---

HERON ARTS MAKES NO WARRANTY, EXPRESS, IMPLIED, STATUTORY, OR OTHERWISE, AND SPECIFICALLY DISCLAIMS ANY WARRANTY OF MERCHANTABILITY, NON-INFRINGEMENT, OR FITNESS FOR A PARTICULAR PURPOSE, WITH RESPECT TO THE SOFTWARE.

