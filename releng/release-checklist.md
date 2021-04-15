# Karasimulator - Release Checklist

## New Release of Source Code

* replace pom.xml versions
* replace MANIFEST.MF versions
* commit with pattern: "Version X.Y.Z"
* create tag on master

## C++ Simulator Library

* wait until C++ simulator is built on GitHub
* switch to branch "cpp-simulator"
* replace files from remote branch "cpp-simulator-snapshot"
* commit with pattern "C++ Simulator: Version X.Y.Z"
* tag with pattern "X.Y.Z-cpp-simulator"

## Set new SNAPSHOT Version

* replace pom.xml versions to next snapshot
* replace MANIFEST.MF versions to next snapshot
* commit with pattern: "Version X.Y.Z-SNAPSHOT"

# Update MPW Framework Version (release version)

* update targetplatform.target version:

```
    <location includeAllPlatforms="false" includeConfigurePhase="true" includeMode="planner" includeSource="true" type="InstallableUnit">
        	<repository location="https://raw.githubusercontent.com/SQAHamster/mpw-modeling-framework/X.Y.Z-updatesite/repository"/>
        	<unit id="de.unistuttgart.iste.sqa.mpw.feature.feature.group" version="X.Y.Z"/>
    	</location>
```
* Java Simulator: update `de.unistuttgart.iste.sqa.mpw.framework` versions
* C++ Simulator: in `core`, update git tag version for FetchContent:


```
    FetchContent_Declare(mpw_lib
        GIT_REPOSITORY https://github.com/SQAHamster/mpw-modeling-framework.git
        GIT_TAG X.Y.Z-cpp-framework
```
* Commit with pattern "Build: update MPW Framework dependency to X.Y.Z"
