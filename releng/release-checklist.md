# Karasimulator - Release Checklist

## New Release of Source Code

* replace versions (adapt/run update-versions.kts)
* commit with pattern: "Version X.Y.Z"
* create tag on master

## C++ Simulator Library

* cpp-simulator-tag will be handled by GitHub Actions automatically

## Set new SNAPSHOT Version

* replace versions (adapt/run update-versions.kts)
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
