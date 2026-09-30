# Compile-only dependencies

Place these two artifacts in this directory before building. Git ignores their JAR files, and the build does not bundle them.

| Filename | Source |
| --- | --- |
| `toolpouch-fabric-1.1.10+26.3.jar` | [Tool Pouch](https://modrinth.com/mod/tool-pouch), [source](https://github.com/pajicadvance/toolpouch) |
| `mapstitch-polymer-compat-1.0.1+26.3.jar` | [MapStitch Polymer Shim v1.0.1+26.3](https://github.com/THENATHE/mapstitch-polymer-shim/releases/tag/v1.0.1%2B26.3) |

The MapStitch shim is a compile-time API dependency for the optional runtime bridge. It is not required at runtime when MapStitch is absent. Gradle resolves Minecraft, Fabric, and Polymer using the versions in `build.gradle`. Obtain original mod JARs from their authors; this repository does not redistribute them.
